package ua.com.hdcorp.hd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.hdcorp.hd.dto.PostcardDto;
import ua.com.hdcorp.hd.model.Postcard;
import ua.com.hdcorp.hd.model.Remain;
import ua.com.hdcorp.hd.repository.RemainRepository;
import ua.com.hdcorp.hd.service.interf.IPostcardService;
import ua.com.hdcorp.hd.service.interf.IRemainsService;

import java.util.Date;

@Service
public class RemainServiceImpl implements IRemainsService {
    private final RemainRepository remainRepository;
    private final IPostcardService postcardService;

    @Autowired
    public RemainServiceImpl(final RemainRepository remainRepository, final IPostcardService postcardService) {
        this.remainRepository = remainRepository;
        this.postcardService = postcardService;
    }

    @Override
    public void saveRemain(Remain remain) {
        remainRepository.save(remain);
    }

    @Override
    public Remain getRemain(Long id) {
        return remainRepository.getRemainByPostcardId(id);
    }

    @Override
    public Remain updateRemain(PostcardDto pDto) {
        Remain remain = getRemain(pDto.getPostcardId());
        if (remain != null) {
            int newQuantity = remain.getQuantity() + pDto.getQuantity();
            remain.setQuantity(newQuantity);
            remain.setDate(new Date());
            return remain;
        } else {
            Postcard p = postcardService.findOne(pDto.getPostcardId());
            return new Remain(new Date(), pDto.getQuantity(), p);
        }
    }
}
