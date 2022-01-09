package org.launchcode.giftlist.controllers;

import org.launchcode.giftlist.models.User;
import org.launchcode.giftlist.models.dto.LoginFormDTO;
import org.launchcode.giftlist.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class HomeController {

  @Autowired
  UserRepository userRepository;

  @GetMapping("/index")
  public String index(Model model) {
    model.addAttribute(new LoginFormDTO());
    return "index";
  }

  @PostMapping("/user")
  public String loadUser(@ModelAttribute @Valid User user, Model model) {

    Integer userId = user.getUserID();

    Optional optionalUser = userRepository.findById(userId);
    if (!optionalUser.isEmpty()) {
      User user1 = (User) optionalUser.get();
      model.addAttribute("username", user1.getUsername());
      model.addAttribute("firstName", user1.getFirstName());
      model.addAttribute("lastName", user1.getLastName());
      model.addAttribute("email", user1.getEmail());
      return "user";
    } else {
      return "redirect:/";
    }
  }

}
