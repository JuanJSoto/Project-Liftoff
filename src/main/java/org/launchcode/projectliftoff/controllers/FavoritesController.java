package org.launchcode.projectliftoff.controllers;

import org.launchcode.projectliftoff.models.Favorite;
import org.launchcode.projectliftoff.models.FavoriteData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "favorite")
public class FavoritesController {

    @Autowired //should be given an instance of this class by framework
    private FavoriteData favoriteData;

    @Autowired
    private UserData userdata;

    //Request path: /favorite
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("favorites", favoriteData.findAll());
        model.addAttribute("title", "My favorites");
        return "favorite/index";
    }


    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String displayDeleteForm(Model model) {
      model.addAttribute("favorites", favoriteData.findAll());
      model.addAttribute("title", "Delete Favorite");
      return "favorite/delete";
    }

    //Request path: favorites/delete
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processDeletedFavorite(@RequestParam int[] favoriteIds) {

        for(int favoriteId: favoritesIds) {
            favoriteData.delete(favoriteId);
        }
        return "redirect:";
    }

    //Request path: favorite/add
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {

        model.addAttribute("title", "Add Favorite");
        model.addAttribute(new Favorite());
        model.addAttribute("users", userdata.findAll());
        return "favorite/add";
    }

    //Request path: favorite/add
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddFavoriteForm(@ModelAttribute @Valid Favorite newFavorite,
                                         Errors errors, Model model,
                                         @RequestParam int userId) {
        if (errors.hasErrors()) {
            model.addAttribute("users", userData.findAll());
            model.addAttribute("title", "Add Favorite");
            return "favorite/add";
        }

        User u = userData.findOne(userId);
        newFavorite.setUser(u);
        favoriteData.save(newFavorite);
        //redirect to favorite/
        return "redirect:";

    }
    @RequestMapping(value = "user", method = RequestMethod.GET)
    public String displayUsers(Model model, @RequestParam int id) {

        //finds the user and stores it in u
        User u = userData.findOne(id);
        List<Favorite> favorites = u.getFavorites();
        model.addAttribute("favorites", favorites);
        model.addAttribute("title", "Favorites in User: " + u.getName());
        return "favorite/index";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    //need to get to favorite/edit/0
    public String displayEditForm( Model model, @PathVariable int id) {
        Favorite favorite = favoriteData.findOne(id);
        model.addAttribute(favorite);
        model.addAttribute("users", userData.findAll());
        model.addAttribute("title", "Edit Favorite" + favorite.getName());
        return "favorite/edit";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String processEditForm(@RequestParam int id, @ModelAttribute @Valid Favorite newFavorite,
                                  Errors errors, Model model) {
        //find the post version of @PathVariable
        Favorite aFavorite = favoriteData.findOne(id);

        if (errors.hasErrors()) {
            model.addAttribute(aFavorite);
            model.addAttribute("users", userData.findAll());
            model.addAttribute("title", "Edit Favorite" + aFavorite.getName());
            return "redirect:/favorite/edit/" + id;
        }
        aFavorite.setName(newFavorite.getName());
        aFavorite.setAge(newFavorite.getAge());
        aFavorite.setColor(newFavorite.getColor());
        aFavorite.setBreed(newFavorite.getBreed());
        aFavorite.setLocation(newFavorite.getLocation());

        return "redirect:";

    }

}
