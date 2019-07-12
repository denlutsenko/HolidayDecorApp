package ua.com.hdcorp.hd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "users")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User implements Serializable, UserDetails {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role authorities;

    @Column(name = "password", length = 20, nullable = false)
    private String password;

    @Column(name = "email", length = 50)
    private String username;

    @Column(name="accountNonExpired", columnDefinition="tinyint(1) default true")
    private boolean accountNonExpired;

    @Column(name="accountNonLocked", columnDefinition="tinyint(1) default true")
    private boolean accountNonLocked;

    @Column(name="credentialsNonExpired", columnDefinition="tinyint(1) default true")
    private boolean credentialsNonExpired;

    @Column(name="enabled", columnDefinition="tinyint(1) default true")
    private boolean enabled;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "address", length = 50)
    private String address;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(columnDefinition="tinyint(1) default true", name = "active_status", nullable = false)
    private boolean activeStatus;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<PostcardProduction> postcardProductionList = new ArrayList<>();

    public User() {
    }

    public User(Long id){
        this.id = id;
    }

    //save new user
    public User(String firstName, String lastName, String username, String password, String phone, String address, Role authorities, boolean activeStatus) {
       this.firstName = firstName;
       this.lastName = lastName;
       this.username = username;
       this.password = password;
       this.phone = phone;
       this.address = address;
       this.authorities = authorities;
       this.activeStatus = activeStatus;
    }

    //updateUser
    public User(Long id,String firstName, String lastName, String username, String password, String phone, String address, Role authorities, boolean activeStatus) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.authorities = authorities;
        this.activeStatus = activeStatus;
    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(authorities);
    }

    public void setAuthorities(Role authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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

    public boolean isActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    public List<PostcardProduction> getPostcardProductionList() {
        return postcardProductionList;
    }

    public void setPostcardProductionList(List<PostcardProduction> postcardProductionList) {
        this.postcardProductionList = postcardProductionList;
    }
}
