package org.launchcode.giftlist.controllers;

import org.launchcode.giftlist.models.User;
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
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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

  @GetMapping("wishlists")
  public String displayWishLists(Model model) {

    return "wishlists";
  }
  @GetMapping("party_list")
  public String displayGroupList(Model model){

   return "party_list";
  }

  @GetMapping("user_details")
  public String displayUpdateUserDetailsForm (Model model, HttpSession session) {
    Integer currentUserId = (Integer) session.getAttribute("user");
    User user = userRepository.findById(currentUserId).get();
    model.addAttribute("user", user);
    return "user_details";
  }

  @PostMapping("user_details")
  public String processUpdateUserDetailsForm (@ModelAttribute @Valid UpdateUserDetailsDTO updateUserDetailsDTO,
                                           HttpSession session) {
    Integer currentUserId = (Integer) session.getAttribute("user");
    User user = userRepository.findById(currentUserId).get();
    user.setFirstName(updateUserDetailsDTO.getFirstName());
    user.setLastName(updateUserDetailsDTO.getLastName());
    user.setUsername(updateUserDetailsDTO.getUsername());
    user.setEmail(updateUserDetailsDTO.getEmail());
    userRepository.save(user);
    return "user";

  }



  /*@GetMapping("/login")
  public String loginUser(Model model){
    model.addAttribute(new LoginFormDTO());
    return "login";
  }*/


  /*@GetMapping("user")
  public String loginUser(Model model, @ModelAttribute User user) {

    /*model.addAttribute(new LoginFormDTO());
    Integer userId = user.getId();
    Optional<User> optionalUser = userRepository.findById(userId);
    if (optionalUser.isPresent()) {
      User user1 = (User) optionalUser.get();
      model.addAttribute("username", user1.getUsername());
      model.addAttribute("firstName", user1.getFirstName());
      model.addAttribute("lastName", user1.getLastName());
      model.addAttribute("email", user1.getEmail());
      return "user";
    } else {
      return "redirect:";
    }

    /*Optional optionalUser = userRepository.findById(userId);
    if (!optionalUser.isEmpty()) {
      User user1 = (User) optionalUser.get();
      model.addAttribute("username", user1.getUsername());
      model.addAttribute("firstName", user1.getFirstName());
      model.addAttribute("lastName", user1.getLastName());
      model.addAttribute("email", user1.getEmail());
      return "user";
    } else {
      return "index";
    }*/
    // }

  }

