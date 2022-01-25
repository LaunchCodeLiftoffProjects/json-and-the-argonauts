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
import org.springframework.web.bind.annotation.PathVariable;
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

  @GetMapping("/{username}")
  public String displayUserPage(Model model, @PathVariable String username, HttpSession session) {
    Integer currentUserId = (Integer) session.getAttribute("user");
    User user = userRepository.findById(currentUserId).get();
    model.addAttribute("username", user.getUsername());
    model.addAttribute("firstName", user.getFirstName());
    model.addAttribute("lastName", user.getLastName());
    model.addAttribute("email", user.getEmail());
    return "user";
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

}

