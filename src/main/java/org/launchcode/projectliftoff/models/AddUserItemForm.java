package org.launchcode.projectliftoff.models;

import javax.validation.constraints.NotNull;

public class AddUserItemForm {

    //these are the two variables that are related to each other that we will use to make sure
    //that the favorites go to the right user

    @NotNull
    private int userId;

    @NotNull
    private int favoriteId;

    //this will be used for the dropdown so we can select the favorite animal
    private Iterable<Favorite> favorites;

    private User user;

    public AddUserItemForm() {}

    public AddUserItemForm(Iterable<Favorite> favorites, User user) {
        this.favorites = favorites;
        this.user = user;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(int favoriteId) {
        this.favoriteId = favoriteId;
    }

    public Iterable<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(Iterable<Favorite> favorites) {
        this.favorites = favorites;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
