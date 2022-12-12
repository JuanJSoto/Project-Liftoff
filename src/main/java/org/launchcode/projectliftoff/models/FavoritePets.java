package org.launchcode.projectliftoff.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
public class FavoritePets extends Animals {

    private List<FavoritePets> favoritePets = new ArrayList<>();

    @ManyToOne
    private User user;


    public FavoritePets(String name, int age, String color, String breed, String location) {
        super(name, age, color, breed, location);
    }
}
