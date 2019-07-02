package ua.com.hdcorp.hd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.hdcorp.hd.model.PostcardAccounting;

import java.util.Date;
import java.util.List;

@Repository
public interface PostcardAccountingRepository extends JpaRepository<PostcardAccounting, Long> {

    @Query(value = "select * from postcard_accounting where Year(date) = YEAR(?1) and Month(date) = MONTH(?1)", nativeQuery = true)
    List<PostcardAccounting> getAllByDate(Date date);

    @Query(value = "SELECT DISTINCT date FROM postcard_accounting", nativeQuery = true)
    List<Date> findDistinctMonths();

    @Query(value =
            "SELECT p.id, p.photo_location_path, p.vendor_code, pt.type as postcard_type," +
                    "  IFNULL(pp.count, 0) AS curr_produced," +
                    "  IFNULL(od.ord_cout, 0) AS curr_ordered," +
                    "  IFNULL(mr.quantity, 0) AS first_day_remain," +
                    "  IFNULL(r.quantity, 0) AS current_remain, " +
                    "CURDATE() as date, pt.id as p_type_id, p.photo_name as photo_name "+
                    "FROM hdcorp.postcards p LEFT JOIN (" +
                    "              SELECT SUM(pp.quantity) AS count, pp.postcard_id" +
                    "              FROM hdcorp.postcards_production pp" +
                    "              WHERE" +
                    "                MONTH(pp.production_date) = MONTH(CURDATE())" +
                    "                AND YEAR(pp.production_date) = YEAR(CURDATE())" +
                    "              GROUP BY pp.postcard_id" +
                    "            ) pp ON p.id = pp.postcard_id" +
                    "" +
                    "  LEFT JOIN (" +
                    "              SELECT od.postcard_id, SUM(od.quantity) AS ord_cout" +
                    "              FROM order_details od" +
                    "                JOIN orders o ON o.id = od.order_id" +
                    "              WHERE" +
                    "                MONTH(o.order_date) = MONTH(CURDATE())" +
                    "                AND YEAR(o.order_date) = YEAR(CURDATE())" +
                    "              GROUP BY od.postcard_id) od ON p.id = od.postcard_id" +
                    "" +
                    "  LEFT JOIN (" +
                    "              SELECT mr.postcard_id, mr.quantity" +
                    "              FROM monthly_remains mr" +
                    "              WHERE" +
                    "                MONTH(mr.date) = MONTH(CURDATE())" +
                    "                AND YEAR(mr.date) = YEAR(CURDATE()) ) mr ON p.id = mr.postcard_id" +
                    "" +
                    "  LEFT JOIN (" +
                    "              SELECT r.postcard_id, r.quantity" +
                    "              FROM remains r" +
                    "              WHERE" +
                    "                MONTH(r.date) = MONTH(CURDATE())" +
                    "                AND YEAR(r.date) = YEAR(CURDATE()) ) r ON p.id = r.postcard_id" +
                    "  INNER JOIN postcard_type pt ON p.type_id = pt.id"
            , nativeQuery = true)
    List<PostcardAccounting> findCurrMonthAccounting();

}
