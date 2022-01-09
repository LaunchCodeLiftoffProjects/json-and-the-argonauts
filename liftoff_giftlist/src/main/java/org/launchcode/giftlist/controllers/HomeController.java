package org.launchcode.giftlist.controllers;

import org.launchcode.giftlist.models.dto.LoginFormDTO;
import org.launchcode.giftlist.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @Autowired
  UserRepository userRepository;

  @GetMapping("/index")
  public String index(Model model) {
    model.addAttribute(new LoginFormDTO());
    return "index";
  }




}
