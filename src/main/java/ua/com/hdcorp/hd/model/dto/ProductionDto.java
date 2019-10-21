package ua.com.hdcorp.hd.model.dto;

import ua.com.hdcorp.hd.model.Production;

import java.util.List;

public class ProductionDto {
    private Long employeeId;
    private List<Production> productionList;

    public ProductionDto(){}

    public ProductionDto(Long employeeId, List<Production> productionList) {
        this.employeeId = employeeId;
        this.productionList = productionList;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public List<Production> getProductionList() {
        return productionList;
    }

    public void setProductionList(List<Production> productionList) {
        this.productionList = productionList;
    }
}
