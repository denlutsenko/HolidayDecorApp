package ua.com.hdcorp.hd.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @CreatedDate
    @Column(name = "created")
    private Date created;

    @LastModifiedDate
    @Column(name = "updated")
    private Date updated;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status = Status.ACTIVE;

    @NotNull(message = "First name can not be null")
    @Size(max = 50)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotNull(message = "Last name can not be null")
    @Size(max = 50)
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Size(max = 50)
    @Column(name = "address")
    private String address;

    @Size(max = 20)
    @Column(name = "phone")
    private String phone;

    @NotNull(message = "Email can not be null")
    @Valid()
    @Size(max = 50)
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @NotNull(message = "Email can not be null")
    @Size(min = 8, max = 250)
    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    public Employee() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
