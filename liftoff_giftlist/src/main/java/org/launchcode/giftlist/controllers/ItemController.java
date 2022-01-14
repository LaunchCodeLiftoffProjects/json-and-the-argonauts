package org.launchcode.giftlist.controllers;

import org.launchcode.giftlist.models.Item;
import org.launchcode.giftlist.models.WishList;
import org.launchcode.giftlist.repositories.ItemRepository;
import org.launchcode.giftlist.repositories.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class ItemController {
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    WishListRepository wishListRepository;

    @GetMapping("/additem")
    public String displayAddItemForm(Model model) {
        model.addAttribute(new Item());
        List<WishList> wishlists = (List<WishList>) wishListRepository.findAll();
        model.addAttribute("wishlists", wishlists);
        return "additem";
    }

    @PostMapping("/additem")
    public String processAddItemForm(@ModelAttribute @Valid Item item, Errors errors,
                                        Model model) {
        if (errors.hasErrors()) {
            return "additem";
        }
        itemRepository.save(item);
        return "user";
    }
}
