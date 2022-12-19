package org.launchcode.projectliftoff.models;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Dog extends Animals {
    private Boolean trained;

      private List<Dog> dogs= new ArrayList<>();

    public List<Dog> getDogs() {
        return dogs;
    }

    public void setDogs(List<Dog> dogs) {
        this.dogs = dogs;
    }

    public Dog(String name, int age, String color, String breed, String location, Boolean trained) {
        super(name, age, color, breed, location);
        this.trained = trained;
    }


    public Boolean getTrained() {
        return trained;
    }

    public void setTrained(Boolean trained) {
        this.trained = trained;
    }

    @Override
    public String toString() {
        return
                "name= " + name + "\n" +
                        "color= " + color + "\n" +
                        "breed= " + breed + "\n" +
                        "age= " + age +
                        "trained" + trained;
    }

}
