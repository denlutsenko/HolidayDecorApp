package ua.com.hdcorp.hd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.hdcorp.hd.exception.BadRequestException;
import ua.com.hdcorp.hd.model.Employee;
import ua.com.hdcorp.hd.model.dto.ProductionDto;
import ua.com.hdcorp.hd.repository.ProductionRepository;
import ua.com.hdcorp.hd.service.interf.EmployeeService;
import ua.com.hdcorp.hd.service.interf.ProductionService;

import java.util.Date;

import static ua.com.hdcorp.hd.utils.Constants.CAN_NOT_SAVE_PRODUCTION;

@Service
public class ProductionServiceImpl implements ProductionService {

    private final ProductionRepository productionRepository;
    private final EmployeeService employeeService;


    @Autowired
    public ProductionServiceImpl(ProductionRepository productionRepository, EmployeeService employeeService) {
        this.productionRepository = productionRepository;
        this.employeeService = employeeService;
    }


    @Override
    @Transactional
    public void addProduction(ProductionDto productionDtoList) {
        Employee employee = employeeService.getEmployeeById(productionDtoList.getEmployeeId());
        Date currentDate = new Date();
        try {
            productionDtoList.getProductionList().forEach(item -> {
                item.setEmployee(employee);
                item.setDate(currentDate);
                productionRepository.save(item);
            });
        } catch (BadRequestException e) {
            throw new BadRequestException(CAN_NOT_SAVE_PRODUCTION);
        }
    }
}
