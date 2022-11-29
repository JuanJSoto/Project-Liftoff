package org.launchcode.projectliftoff.models;

import java.util.ArrayList;

public class AnimalData extends AbstractEntity {

    public static ArrayList<Dog> findDogByColumnAndValue(String column, String value, Iterable<Dog> allDogs) {

        ArrayList<Dog> results = new ArrayList<>();

        if (value.toLowerCase().equals("all")) {
            return (ArrayList<Dog>) allDogs;
        }

        if (column.equals("all")) {
            results = findDogByValue(value, allDogs);
            return results;
        }
        for (Dog dog : allDogs) {

            String aValue = getDogFieldValue(dog, column);

            if (aValue != null && aValue.toLowerCase().contains(value.toLowerCase())) {
                results.add(dog);
            }
        }

        return results;
    }

    public static String getDogFieldValue(Dog dog, String fieldName) {
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


    public static ArrayList<Dog> findDogByValue(String value, Iterable<Dog> allDogs) {
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

    public static ArrayList<Cat> findCatByColumnAndValue(String column, String value, Iterable<Cat> allCats) {

        ArrayList<Cat> results = new ArrayList<>();

        if (value.toLowerCase().equals("all")) {
            return (ArrayList<Cat>) allCats;
        }

        if (column.equals("all")) {
            results = findCatByValue(value, allCats);
            return results;
        }
        for (Cat cat : allCats) {

            String aValue = getCatFieldValue(cat, column);

            if (aValue != null && aValue.toLowerCase().contains(value.toLowerCase())) {
                results.add(cat);
            }
        }

        return results;
    }

    public static String getCatFieldValue(Cat cat, String fieldName) {
        String theValue;
        if (fieldName.equals("name")) {
            theValue = cat.getName();
        } else if (fieldName.equals("breed")) {
            theValue = cat.getBreed().toString();
        } else if (fieldName.equals("location")) {
            theValue = cat.getLocation();
        } else {
            theValue = cat.getBreed().toString();
        }
        return theValue;
    }


    public static ArrayList<Cat> findCatByValue(String value, Iterable<Cat> allCats) {
        String lower_val = value.toLowerCase();

        ArrayList<Cat> results = new ArrayList<>();

        for (Cat cat : allCats) {

            if (cat.getName().toLowerCase().contains(lower_val)) {
                results.add(cat);
            } else if (cat.getCats().toString().toLowerCase().contains(lower_val)) {
                results.add(cat);
            } else if (cat.getCats().toString().toLowerCase().contains(lower_val)) {
                results.add(cat);
            } else if (cat.toString().toLowerCase().contains(lower_val)) {
                results.add(cat);
            }
        }
        return results;
    }
}
