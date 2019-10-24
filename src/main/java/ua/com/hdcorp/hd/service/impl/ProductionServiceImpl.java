package ua.com.hdcorp.hd.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.hdcorp.hd.model.Employee;
import ua.com.hdcorp.hd.model.Postcard;
import ua.com.hdcorp.hd.model.Production;
import ua.com.hdcorp.hd.model.dto.ProductionDto;
import ua.com.hdcorp.hd.repository.ProductionRepository;
import ua.com.hdcorp.hd.service.interf.EmployeeService;
import ua.com.hdcorp.hd.service.interf.ProductionService;

import java.util.Date;
import java.util.List;

@Service
public class ProductionServiceImpl implements ProductionService {

    private final ProductionRepository productionRepository;
    private final EmployeeService employeeService;
    private final PostcardServiceImpl postcardService;


    public ProductionServiceImpl(ProductionRepository productionRepository, EmployeeService employeeService, PostcardServiceImpl postcardService) {
        this.productionRepository = productionRepository;
        this.employeeService = employeeService;
        this.postcardService = postcardService;
    }

    @Override
    @Transactional
    public void addProduction(List<ProductionDto> productionDtoList, Long employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        Date currentDate = new Date();
        productionDtoList.forEach(item -> {
            Postcard postcard = postcardService.getPostcardById(item.getPostcardId());
            Production production = new Production(employee, postcard, item.getQuantity(), currentDate);
            saveProduction(production);
        });
    }

    @Override
    public void saveProduction(Production production) {
        productionRepository.save(production);
    }
}
