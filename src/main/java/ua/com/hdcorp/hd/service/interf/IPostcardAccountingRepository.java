package ua.com.hdcorp.hd.service.interf;

import ua.com.hdcorp.hd.model.PostcardAccounting;

import java.util.List;

public interface IPostcardAccountingRepository {

    List<PostcardAccounting> getMonthlyAccounting(String date);

    List<String> getDistinctMonths();

    List<PostcardAccounting> getCurrentMonthAccounting();
}
