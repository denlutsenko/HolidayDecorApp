package ua.com.hdcorp.hd.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "currencies")
public class Currency implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 3, unique = true)
    private String name;
}