package ua.com.hdcorp.hd.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.hdcorp.hd.model.Production;
import ua.com.hdcorp.hd.model.dto.ProductionDto;
import ua.com.hdcorp.hd.service.interf.ProductionService;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/")
public class ProductionController {

    private final ProductionService productionService;

    @Autowired
    public ProductionController(final ProductionService productionService) {
        this.productionService = productionService;
    }

    @PostMapping(value = "production")
    public ResponseEntity<Production> createEmployee(@Valid @RequestBody ProductionDto productionDtoList) {
        productionService.addProduction(productionDtoList);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
