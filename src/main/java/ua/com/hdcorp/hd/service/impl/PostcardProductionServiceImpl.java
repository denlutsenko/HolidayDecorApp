package ua.com.hdcorp.hd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.hdcorp.hd.dto.PostcardDto;
import ua.com.hdcorp.hd.dto.PostcardProductionDto;
import ua.com.hdcorp.hd.model.PostcardProduction;
import ua.com.hdcorp.hd.model.Remain;
import ua.com.hdcorp.hd.repository.PostcardProductionRepository;
import ua.com.hdcorp.hd.service.interf.IPostcardProductionService;
import ua.com.hdcorp.hd.service.interf.IPostcardService;
import ua.com.hdcorp.hd.service.interf.IRemainsService;
import ua.com.hdcorp.hd.service.interf.IUserService;

import java.util.Date;

@Service
public class PostcardProductionServiceImpl implements IPostcardProductionService {
    private final PostcardProductionRepository postcardProductionRepository;
    private final IRemainsService remainsService;
    private IUserService userService;
    private IPostcardService postcardService;

    @Autowired
    public PostcardProductionServiceImpl(final PostcardProductionRepository postcardProductionRepository,
                                         final IRemainsService remainsService,
                                          final IUserService userService,
                                         final IPostcardService postcardService) {
        this.postcardProductionRepository = postcardProductionRepository;
        this.remainsService = remainsService;
        this.userService = userService;
        this.postcardService = postcardService;
    }

    @Transactional
    public void updatePostcardProduction(PostcardProductionDto productionDto) {
        for (PostcardDto p : productionDto.getPostcards()) {
            addPostcardProduction(p.getQuantity(), new Date(), p.getPostcardId(), productionDto.getUserId());
            Remain remain = remainsService.updateRemain(p);
            remainsService.saveRemain(remain);
        }
    }

    @Override
    public void addPostcardProduction(int quantity, Date date, Long postcardId, Long userId) {
        PostcardProduction pp = new PostcardProduction(userService.findUserById(userId), postcardService.findPostcard(postcardId), quantity, date);
        postcardProductionRepository.save(pp);
    }
}
