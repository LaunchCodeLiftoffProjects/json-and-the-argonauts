package org.launchcode.giftlist.controllers;

import org.launchcode.giftlist.models.User;
import org.launchcode.giftlist.models.WishList;
import org.launchcode.giftlist.models.dto.UpdateWishListDetailsDTO;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class WishListController {

    @Autowired
    WishListRepository wishListRepository;

    @Autowired
    UserRepository userRepository;

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
        wishList.setListOwner(userRepository.findById(currentUserId).get());
        wishListRepository.save(wishList);
        return "user";
    }

    @GetMapping("wishlists")
    public String displayWishLists(Model model, HttpSession session){
        Integer currentUserId = (Integer) session.getAttribute("user");
        User user = userRepository.findById(currentUserId).get();
        List<WishList> wishlists =  wishListRepository.findAllBylistOwner(user);
        model.addAttribute("wishlists", wishlists);
        return "wishlists";
    }

    @GetMapping("wishlists/{id}")
    public String displayEditWishListForm(Model model, @PathVariable String id) {
        WishList wishList = wishListRepository.findById(Integer.parseInt(id)).get();
        model.addAttribute("wishList", wishList);
        return "list_details";
    }

    @PostMapping("wishlists/{id}")
    public String processEditWishListForm(@PathVariable String id, @Valid @ModelAttribute UpdateWishListDetailsDTO updateWishListDetailsDTO) {
        WishList wishList = wishListRepository.findById(Integer.parseInt(id)).get();
        wishList.setName(updateWishListDetailsDTO.getName());
        wishList.setDescription(updateWishListDetailsDTO.getDescription());
        wishListRepository.save(wishList);
        return "redirect:";
    }


}
