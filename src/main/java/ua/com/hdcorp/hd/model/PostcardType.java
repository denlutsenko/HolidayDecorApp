package ua.com.hdcorp.hd.model;

import javax.persistence.*;

@Entity
@Table(name = "postcard_types")
public class PostcardType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 70, unique = true)
    private String name;

    public PostcardType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}