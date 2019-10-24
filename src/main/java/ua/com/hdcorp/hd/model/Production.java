package ua.com.hdcorp.hd.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "production")
public class Production implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "postcard_id", nullable = false)
    private Postcard postcard;

    @Column(name = "quantity", nullable = false)
    @Min(value = 0, message = "The value must be positive")
    private Integer quantity;

    @Column(name = "date", nullable = false)
    private Date date;

    public Production() {
    }

    public Production(Employee employee, Postcard postcard, @Min(value = 0, message = "The value must be positive") Integer quantity, Date date) {
        this.employee = employee;
        this.postcard = postcard;
        this.quantity = quantity;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Postcard getPostcard() {
        return postcard;
    }

    public void setPostcard(Postcard postcard) {
        this.postcard = postcard;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}