package org.launchcode.giftlist.controllers;

import org.launchcode.giftlist.models.Party;
import org.launchcode.giftlist.models.User;
import org.launchcode.giftlist.models.WishList;
import org.launchcode.giftlist.models.dto.UpdateUserDetailsDTO;
import org.launchcode.giftlist.repositories.ItemRepository;
import org.launchcode.giftlist.repositories.PartyRepository;
import org.launchcode.giftlist.repositories.UserRepository;
import org.launchcode.giftlist.repositories.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

  @Autowired
  UserRepository userRepository;
  @Autowired
  PartyRepository partyRepository;
  @Autowired
  ItemRepository itemRepository;
  @Autowired
  WishListRepository wishListRepository;


  @GetMapping("/{username}")
  public String displayUserPage(Model model, @PathVariable String username, HttpSession session) {

//    Find current user information
    Integer currentUserId = (Integer) session.getAttribute("user");
    User user = userRepository.findById(currentUserId).get();
    String currentUsername = user.getUsername();

//    If path variable doesn't match user's username, redirect the path
    if (!username.equals(currentUsername)) {
      return "redirect:/" + currentUsername;
    }

    model.addAttribute("username", currentUsername);
    model.addAttribute("firstName", user.getFirstName());
    model.addAttribute("lastName", user.getLastName());
    model.addAttribute("email", user.getEmail());

//    Find all wishlists owned by this user
    List<WishList> wishLists = user.getWishLists();
    model.addAttribute("wishlists", wishLists);

//    Find all groups this user belongs to
    List<Party> groups = user.getJoinedParties();
    model.addAttribute("groups", groups);
    return "user";
  }

  @GetMapping("user_details1")
  public String displayUpdateUserDetailsForm (Model model, HttpSession session) {
    Integer currentUserId = (Integer) session.getAttribute("user");
    User user = userRepository.findById(currentUserId).get();
    model.addAttribute("user", user);
    return "user_details";
  }

  @PostMapping("user_details1")
  public String processUpdateUserDetailsForm (@ModelAttribute @Valid UpdateUserDetailsDTO updateUserDetailsDTO,
                                           HttpSession session, Model model) {
    Integer currentUserId = (Integer) session.getAttribute("user");
    User user = userRepository.findById(currentUserId).get();
    user.setFirstName(updateUserDetailsDTO.getFirstName());
    user.setLastName(updateUserDetailsDTO.getLastName());
    user.setUsername(updateUserDetailsDTO.getUsername());
    user.setEmail(updateUserDetailsDTO.getEmail());
    userRepository.save(user);
    model.addAttribute("firstName", user.getFirstName());
    model.addAttribute("lastName", user.getLastName());
    model.addAttribute("username", user.getUsername());
    model.addAttribute("email", user.getEmail());
    return "user";

  }

}

