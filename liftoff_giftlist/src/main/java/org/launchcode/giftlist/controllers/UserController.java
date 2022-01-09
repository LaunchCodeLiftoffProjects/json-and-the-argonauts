package org.launchcode.giftlist.controllers;

import org.launchcode.giftlist.models.User;
import org.launchcode.giftlist.models.dto.LoginFormDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class UserController {

  @GetMapping("/user")
  public String testHandler(@ModelAttribute User newUser, Model model) {
    model.addAttribute(new LoginFormDTO());
    String username = newUser.getUsername();
    model.addAttribute("username", username);
    return "user";
  }
}
