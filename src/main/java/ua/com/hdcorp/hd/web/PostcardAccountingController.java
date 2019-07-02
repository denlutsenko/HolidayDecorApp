package ua.com.hdcorp.hd.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.hdcorp.hd.data.Error;
import ua.com.hdcorp.hd.service.interf.IPostcardAccountingRepository;
import ua.com.hdcorp.hd.service.interf.IPostcardTypeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class PostcardAccountingController {
    private IPostcardAccountingRepository accountingRepository;
    private IPostcardTypeService postcardTypeService;

    @Autowired
    public PostcardAccountingController(IPostcardAccountingRepository accountingRepository, IPostcardTypeService postcardTypeService) {
        this.accountingRepository = accountingRepository;
        this.postcardTypeService = postcardTypeService;
    }

    //get report for current month or by date.
    @GetMapping(value = "/account/remains/report")
    public ResponseEntity<?> getMonthlyReport(@RequestParam(value = "date", required = false) String date) {
        Map<String, List<?>> objMap = new HashMap<>();
        objMap.put("accounting", accountingRepository.getMonthlyAccounting(date));
        try {
            objMap.put("months", accountingRepository.getDistinctMonths());

            objMap.put("postcardTypes", postcardTypeService.getPostcardTypes());
            return ResponseEntity.ok().body(objMap);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new Error(400, "Ошибка запроса"));
        }
    }
}






