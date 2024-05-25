package org.mikedegeofroy.dal.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Date birthdate;

    @Enumerated(EnumType.STRING)
    private Breed breed;

    @Enumerated(EnumType.STRING)
    private Color color;

//    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinTable(
//            name = "cat_owners",
//            joinColumns = @JoinColumn(name = "cat_id"),
//            inverseJoinColumns = @JoinColumn(name = "owner_id")
//    )
//    private Owner owner;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "cat_friends",
            joinColumns = @JoinColumn(name = "cat_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private List<Cat> friends;

    public void addFriend(Cat friend) {
        if (!friends.contains(friend)) {
            friends.add(friend);
            friend.getFriends().add(this);
        }
    }

    public void removeFriend(Cat friend) {
        if (friends.contains(friend)) {
            friends.remove(friend);
            friend.getFriends().remove(this);
        }
    }
}
