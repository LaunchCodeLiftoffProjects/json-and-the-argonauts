package org.launchcode.giftlist.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @GetMapping
  public String index(Model model) {
    model.addAttribute("title", "Welcome to the Ultimate Gift & Wish List Tracker");
    model.addAttribute("name", "fellow Argonaut");
    return "index";
  }
}
