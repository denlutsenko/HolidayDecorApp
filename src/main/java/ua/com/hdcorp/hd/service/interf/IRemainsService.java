package ua.com.hdcorp.hd.service.interf;

import org.springframework.transaction.annotation.Transactional;
import ua.com.hdcorp.hd.dto.PostcardDto;
import ua.com.hdcorp.hd.model.Remain;

public interface IRemainsService {

    @Transactional
    Remain getRemain(Long id);

    @Transactional
    void saveRemain(Remain remain);

    Remain updateRemain(PostcardDto production);
}
