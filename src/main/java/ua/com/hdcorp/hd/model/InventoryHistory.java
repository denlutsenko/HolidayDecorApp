package ua.com.hdcorp.hd.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "inventory_history")

public class InventoryHistory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "postcard_id")
    private Postcard postcard;

    @Column(name = "month_year", nullable = false)
    private Date monthYear;

    @Column(name = "curr_produced")
    private Integer currProduced;

    @Column(name = "curr_ordered")
    private Integer currOrdered;

    @Column(name = "first_day_remain")
    private Integer firstDayRemain;

    @Column(name = "last_day_remain")
    private Integer lastDayRemain;

    public InventoryHistory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Postcard getPostcard() {
        return postcard;
    }

    public void setPostcard(Postcard postcard) {
        this.postcard = postcard;
    }

    public Date getMonthYear() {
        return monthYear;
    }

    public void setMonthYear(Date monthYear) {
        this.monthYear = monthYear;
    }

    public Integer getCurrProduced() {
        return currProduced;
    }

    public void setCurrProduced(Integer currProduced) {
        this.currProduced = currProduced;
    }

    public Integer getCurrOrdered() {
        return currOrdered;
    }

    public void setCurrOrdered(Integer currOrdered) {
        this.currOrdered = currOrdered;
    }

    public Integer getFirstDayRemain() {
        return firstDayRemain;
    }

    public void setFirstDayRemain(Integer firstDayRemain) {
        this.firstDayRemain = firstDayRemain;
    }

    public Integer getLastDayRemain() {
        return lastDayRemain;
    }

    public void setLastDayRemain(Integer lastDayRemain) {
        this.lastDayRemain = lastDayRemain;
    }
}