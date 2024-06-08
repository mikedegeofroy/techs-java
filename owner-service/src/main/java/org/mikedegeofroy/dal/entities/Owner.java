package org.mikedegeofroy.dal.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String name;
    private String surname;
    private Date bithdate;

//    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinTable(
//            name = "cat_owners",
//            joinColumns = @JoinColumn(name = "owner_id"),
//            inverseJoinColumns = @JoinColumn(name = "cat_id")
//    )
//    private List<Cat> cats;
//
//    public void addCat(Cat cat) {
//        if (!cats.contains(cat)) {
//            cats.add(cat);
//            cat.setOwner(this);
//        }
//    }
}