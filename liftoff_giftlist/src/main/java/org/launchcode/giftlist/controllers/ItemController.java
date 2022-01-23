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
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/wishlists/{id}/additem")
    public String displayAddItemForm(Model model, HttpSession session, @PathVariable String id) {
        if (!model.containsAttribute("item")) {
            model.addAttribute(new Item());
        }
        WishList wishList = wishListRepository.findById(Integer.parseInt(id)).get();
        model.addAttribute("wishList", wishList);
        return "additem";
    }

    @PostMapping("/wishlists/{id}/additem")
    public String processAddItemForm(@ModelAttribute @Valid Item item, Errors errors,
                                           Model model, HttpSession session, @PathVariable String id) {
        if (errors.hasErrors()) {
            WishList wishList = wishListRepository.findById(Integer.parseInt(id)).get();
            model.addAttribute("wishList", wishList);
            return "additem";
        }
        item.setWishList(wishListRepository.findById(Integer.parseInt(id)).get());
        itemRepository.save(item);
        Integer currentUserId = (Integer) session.getAttribute("user");
        User user = userRepository.findById(currentUserId).get();
        List<WishList> wishlists =  wishListRepository.findAllBylistOwner(user);
        model.addAttribute("wishlists", wishlists);
        return "redirect:/wishlists/" + id + "/items";
    }

    @GetMapping("wishlists/{listid}/items/edit/{itemid}")
    public String displayEditItemForm(Model model, @PathVariable String listid, @PathVariable String itemid) {
        if (!model.containsAttribute("item")) {
            Item item = itemRepository.findById(Integer.parseInt(itemid)).get();
            model.addAttribute("item", item);
        }
        WishList wishList = wishListRepository.findById(Integer.parseInt(listid)).get();
        model.addAttribute("wishList", wishList);
        return "item_details";
    }

    @PostMapping("wishlists/{listid}/items/edit/{itemid}")
    public String processEditItemForm(@PathVariable String listid, @PathVariable String itemid,
                                            @Valid @ModelAttribute Item editItem, Errors errors) {
        if (errors.hasErrors()) {
            return "item_details";
        }
        Item item = itemRepository.findById(Integer.parseInt(itemid)).get();
        item.setName(editItem.getName());
        item.setDescription(editItem.getDescription());
        itemRepository.save(item);

        return "redirect:/wishlists/" + listid + "/items";
    }

}
