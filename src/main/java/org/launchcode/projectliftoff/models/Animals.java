package org.launchcode.projectliftoff.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public  class Animals extends AnimalData {
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    public String name;

    @NotBlank(message = "color is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    public String color;

    @NotBlank(message = "breed is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    public String breed;
    public int age;


    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    public String location;

    public Animals(String name, int age, String color, String breed,String location) {
        this.name = name;
        this.age = age;
        this.color = color;
        this.breed = breed;
        this.location=location;
    }
    //-----------------------------------------------
    //Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return
                "name= " + name + "\n" +
                        "color= " + color + "\n" +
                        "breed= " + breed + "\n" +
                        "age= " + age;
    }
}
