package org.launchcode.projectliftoff.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity
public class User extends AbstractEntity{

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String username;

    @NotNull
    private String pwHash;

    @OneToMany
    private List<Favorite> favorites;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User() {}


    public User(String username, String password) {
        this.username = username;
        this.pwHash = encoder.encode(password);
    }

    public String getUsername() {
        return username;
    }

    public List<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Favorite> favorites) {
        this.favorites = favorites;
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password,pwHash);
    }
}
