package org.launchcode.giftlist.controllers;

import org.launchcode.giftlist.models.Item;
import org.launchcode.giftlist.models.User;
import org.launchcode.giftlist.models.WishList;
import org.launchcode.giftlist.repositories.ItemRepository;
import org.launchcode.giftlist.repositories.UserRepository;
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

    @Autowired
    UserRepository userRepository;

    @GetMapping("/additem")
    public String displayAddItemForm(Model model, HttpSession session) {
        model.addAttribute(new Item());
        Integer currentUserId = (Integer) session.getAttribute("user");
        User user = userRepository.findById(currentUserId).get();
        List<WishList> wishlists =  wishListRepository.findAllBylistOwner(user);
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
