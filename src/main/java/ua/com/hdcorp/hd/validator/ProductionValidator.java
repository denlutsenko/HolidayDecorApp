package ua.com.hdcorp.hd.validator;

import org.springframework.stereotype.Component;
import ua.com.hdcorp.hd.exception.BadRequestException;
import ua.com.hdcorp.hd.model.Production;

import static ua.com.hdcorp.hd.utils.Constants.NEGATIVE_QUANTITY;

@Component
public class ProductionValidator {

    public ProductionValidator() {
    }

    public Integer validateQuantity(Integer quantity) {
        if (quantity < 0) {
            throw new BadRequestException(NEGATIVE_QUANTITY);
        }
        return quantity;
    }
}
