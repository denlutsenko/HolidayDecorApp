package ua.com.hdcorp.hd.service.interf;

import ua.com.hdcorp.hd.model.Production;
import ua.com.hdcorp.hd.model.dto.ProductionDto;

import java.util.List;

public interface ProductionService {
    void saveProduction(Production production);
    void addProduction(List<ProductionDto> productionDtoList, Long employeeId);
}
