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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class WishListController {

    @Autowired
    WishListRepository wishListRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ItemRepository itemRepository;

    @GetMapping("/createlist")
    public String displayCreateListForm(Model model) {
        model.addAttribute(new WishList());
        return "createlist";
    }

    @PostMapping("/createlist")
    public String processCreateListForm(@ModelAttribute @Valid WishList wishList, Errors errors,
                                        Model model, HttpSession session) {
        if (errors.hasErrors()) {
            return "createlist";
        }
        Integer currentUserId = (Integer) session.getAttribute("user");
        User user = userRepository.findById(currentUserId).get();
        wishList.setListOwner(user);
        wishListRepository.save(wishList);
        List<WishList> wishlists = wishListRepository.findAllBylistOwner(user);
        model.addAttribute("wishlists", wishlists);
        return "redirect:wishlists";
    }

    @GetMapping("wishlists")
    public String displayWishLists(Model model, HttpSession session){
        Integer currentUserId = (Integer) session.getAttribute("user");
        User user = userRepository.findById(currentUserId).get();
        List<WishList> wishlists =  wishListRepository.findAllBylistOwner(user);
        model.addAttribute("wishlists", wishlists);
        return "wishlists";
    }

    @PostMapping("wishlists")
    public String deleteWishLists(@RequestParam(value = "wishlistid", required = false) List<String> ids, Model model, HttpSession session) {
        if (ids != null) {
            for (String id : ids) {
                wishListRepository.deleteById(Integer.parseInt(id));
            }
        }
        Integer currentUserId = (Integer) session.getAttribute("user");
        User user = userRepository.findById(currentUserId).get();
        List<WishList> wishlists =  wishListRepository.findAllBylistOwner(user);
        model.addAttribute("wishlists", wishlists);
        return "redirect:wishlists";
    }

    @GetMapping("wishlists/{id}")
    public String displayEditWishListForm(Model model, @PathVariable String id) {
        WishList wishList = wishListRepository.findById(Integer.parseInt(id)).get();
        model.addAttribute("wishList", wishList);
        return "list_details";
    }

    @PostMapping("wishlists/{id}")
    public String processEditWishListForm(@PathVariable String id, @Valid @ModelAttribute WishList editWishList,
                                          Errors errors) {
        if (errors.hasErrors()) {
            return "list_details";
        }
        WishList wishList = wishListRepository.findById(Integer.parseInt(id)).get();
        wishList.setName(editWishList.getName());
        wishList.setDescription(editWishList.getDescription());
        wishListRepository.save(wishList);
        return "redirect:";
    }

    @GetMapping("wishlists/{id}/items")
    public String displayListItems(Model model, @PathVariable String id) {
        WishList wishList = wishListRepository.findById(Integer.parseInt(id)).get();
        List<Item> items = itemRepository.findAllBywishList(wishList);
        model.addAttribute("wishList", wishList);
        model.addAttribute("items", items);
        return "list_items";
    }

    @PostMapping("wishlists/{id}/items")
    public String deleteListItems(@RequestParam(value = "itemid", required = false) List<String> itemIds, @PathVariable String id, Model model) {
        if (itemIds != null) {
            for (String itemId : itemIds) {
                itemRepository.deleteById(Integer.parseInt(itemId));
            }
        }
        WishList wishList = wishListRepository.findById(Integer.parseInt(id)).get();
        List<Item> items = itemRepository.findAllBywishList(wishList);
        model.addAttribute("wishList", wishList);
        model.addAttribute("items", items);
        return "redirect:/wishlists/" + id + "/items";
    }


}
