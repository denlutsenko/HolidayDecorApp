package ua.com.hdcorp.hd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.hdcorp.hd.model.PostcardAccounting;
import ua.com.hdcorp.hd.photo.PhotoHandler;
import ua.com.hdcorp.hd.repository.PostcardAccountingRepository;
import ua.com.hdcorp.hd.service.interf.IPostcardAccountingRepository;
import ua.com.hdcorp.hd.utils.FileUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class PostcardAccountingServiceImpl implements IPostcardAccountingRepository {
    private final PostcardAccountingRepository postcardAccountingRepository;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public PostcardAccountingServiceImpl(final PostcardAccountingRepository postcardAccountingRepository) {
        this.postcardAccountingRepository = postcardAccountingRepository;
    }


    @Override
    public List<PostcardAccounting> getMonthlyAccounting(String tmpDate) {
        Date date;
        List<PostcardAccounting> accounting = new ArrayList<>();
        if (null != tmpDate) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
                date = sdf.parse(tmpDate);
                accounting = postcardAccountingRepository.getAllByDate(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            accounting = getCurrentMonthAccounting();
        }
        for (PostcardAccounting acc : accounting) {
            String path = acc.getPhotoLocationPath() + File.separator + acc.getPhoto_name();
            acc.setPhoto(PhotoHandler.getPhotoBytes(path));
        }
        return accounting;
    }

    @Override
    public List<String> getDistinctMonths() {
        List<Date> monthsList = postcardAccountingRepository.findDistinctMonths();
        Set<String> tmp = new HashSet<>();
        for (Date pa : monthsList) {
            tmp.add(FileUtils.convertDateToString(pa));
        }
        return new ArrayList<>(tmp);
    }

    @Override
    public List<PostcardAccounting> getCurrentMonthAccounting() {
        return postcardAccountingRepository.findCurrMonthAccounting();
    }


}
