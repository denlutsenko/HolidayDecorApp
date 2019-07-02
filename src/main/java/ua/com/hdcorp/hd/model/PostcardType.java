package ua.com.hdcorp.hd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "postcard_type")
public class PostcardType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type", length = 70)
    private String type;

    @JsonIgnore
    @OneToMany(mappedBy = "postcardType")
    private List<Postcard> postcardList = new ArrayList<>();

    public PostcardType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Postcard> getPostcardList() {
        return postcardList;
    }

    public void setPostcardList(List<Postcard> postcardList) {
        this.postcardList = postcardList;
    }


}
