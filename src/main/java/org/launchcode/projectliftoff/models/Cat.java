package org.launchcode.projectliftoff.models;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Cat extends Animals {

    private List<Cat> cats = new ArrayList<>();

    public Cat(String name, int age, String color, String breed, String location) {
        super(name, age, color, breed, location);
    }
    public List<Cat> getCats() {
        return cats;
    }


}
