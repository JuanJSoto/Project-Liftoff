package org.launchcode.projectliftoff.controllers;

import org.launchcode.projectliftoff.models.Favorite;
import org.launchcode.projectliftoff.models.FavoriteData;
import org.launchcode.projectliftoff.models.User;
import org.launchcode.projectliftoff.models.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class FavoritesController {

    @Autowired //should be given an instance of this class by framework
    private FavoriteData favoriteData;

    @Autowired
    private UserData userData;

    //Request path: /favorite
    @RequestMapping(value = "favorite", method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("favorites", favoriteData.findAll());
        model.addAttribute("title", "My favorites");
        return "favorite";
    }


    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String displayDeleteForm(Model model) {
      model.addAttribute("favorites", favoriteData.findAll());
      model.addAttribute("title", "Delete Favorite");
      return "delete";
    }

    //Request path: favorites/delete
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String processDeletedFavorite(@RequestParam int[] favoriteIds) {

        for(int favoriteId: favoriteIds) {
            favoriteData.deleteById(favoriteId);
        }
        return "redirect:";
    }

    //Request path: favorite/add
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {

        model.addAttribute("title", "Add Favorite");
        model.addAttribute(new Favorite());
        model.addAttribute("users", userData.findAll());
        return "add";
    }

    //Request path: favorite/add
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddFavoriteForm(@ModelAttribute @Valid Favorite newFavorite,
                                         Errors errors, Model model,
                                         @RequestParam int userId) {
        if (errors.hasErrors()) {
            model.addAttribute("users", userData.findAll());
            model.addAttribute("title", "Add Favorite");
            return "add";
        }

        User u = userData.findById(userId).orElse(new User());
        newFavorite.setUser(u);
        favoriteData.save(newFavorite);
        //redirect to favorite/
        return "redirect:";

    }
    @RequestMapping(value = "user", method = RequestMethod.GET)
    public String displayUsers(Model model, @RequestParam int id) {

        //finds the user and stores it in u
        User u = userData.findById(id).orElse(new User());
        List<Favorite> favorites = u.getFavorites();
        model.addAttribute("favorites", favorites);
        model.addAttribute("title", "Favorites in User: " + u.getUsername());
        return "favorite";
    }
}
