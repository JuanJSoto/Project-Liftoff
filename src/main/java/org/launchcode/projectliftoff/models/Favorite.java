package org.launchcode.projectliftoff.models;

import org.apache.catalina.User;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Favorite extends Animals {

    @ManyToOne //many favorites to each user
    private User user;

    //represents the list of User objects that a favorite animal is contained in

    @ManyToMany( mappedBy = "favorites")//this is the favorites field in the user class
    private List<User> users;

    public Favorite() { }
    public Favorite(String name, int age, String color, String breed, String location) {
        super(name, age, color, breed, location);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
