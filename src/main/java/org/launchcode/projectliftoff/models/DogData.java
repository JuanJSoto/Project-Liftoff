package org.launchcode.projectliftoff.models;

import java.util.ArrayList;

public class DogData {

    public static ArrayList<Dog> findByColumnAndValue(String column, String value, Iterable<Dog> allDogs) {

        ArrayList<Dog> results = new ArrayList<>();

        if (value.toLowerCase().equals("all")) {
            return (ArrayList<Dog>) allDogs;
        }

        if (column.equals("all")) {
            results = findByValue(value, allDogs);
            return results;
        }
        for (Dog dog : allDogs) {

            String aValue = getFieldValue(dog, column);

            if (aValue != null && aValue.toLowerCase().contains(value.toLowerCase())) {
                results.add(dog);
            }
        }

        return results;
    }

    public static String getFieldValue(Dog dog, String fieldName) {
        String theValue;
        if (fieldName.equals("name")) {
            theValue = dog.getName();
        } else if (fieldName.equals("breed")) {
            theValue = dog.getBreed().toString();
        } else if (fieldName.equals("location")) {
            theValue = dog.getLocation();
        } else {
            theValue = dog.getBreed().toString();
        }
        return theValue;
    }


    public static ArrayList<Dog> findByValue(String value, Iterable<Dog> allDogs) {
        String lower_val = value.toLowerCase();

        ArrayList<Dog> results = new ArrayList<>();

        for (Dog dog : allDogs) {

            if (dog.getName().toLowerCase().contains(lower_val)) {
                results.add(dog);
            } else if (dog.getDogs().toString().toLowerCase().contains(lower_val)) {
                results.add(dog);
            } else if (dog.getDogs().toString().toLowerCase().contains(lower_val)) {
                results.add(dog);
            } else if (dog.toString().toLowerCase().contains(lower_val)) {
                results.add(dog);
            }

        }

        return results;
    }
}
