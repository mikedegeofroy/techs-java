package org.mikedegeofroy.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String name;
    private String email;
    private String surname;
    private Date bithdate;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "cat_owners",
            joinColumns = @JoinColumn(name = "onwer_id"),
            inverseJoinColumns = @JoinColumn(name = "cat_id")
    )
    private List<Cat> cats;

    public void addCat(Cat cat) {
        if (!cats.contains(cat)) {
            cats.add(cat);
            cat.setOwner(this);
        }
    }
}