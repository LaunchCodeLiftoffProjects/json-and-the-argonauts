package org.launchcode.giftlist.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

  @GetMapping("user")
  public String testHandler(Model model) {
    return "user";
  }
}
