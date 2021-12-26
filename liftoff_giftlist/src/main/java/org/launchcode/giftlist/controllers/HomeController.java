package org.launchcode.giftlist.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

  //*** DON'T FORGET TO AUTOWIRE REPO'S INTO CONTROLLERS

  @GetMapping
  public String index(Model model) {
    model.addAttribute("title", "Welcome to the Ultimate Gift & Wish List Tracker");
    model.addAttribute("name", "fellow Argonaut");
    return "index";
  }

  @GetMapping("User")
  public String User(Model model, @RequestParam String Username, @RequestParam String Password){
    model.addAttribute("Username", Username);
    model.addAttribute("Password", Password);
    return "User";
  }

  @GetMapping("User/Registration")
  public String Registration(Model model, @RequestParam String Username, @RequestParam String Password, @RequestParam String Email){
    model.addAttribute("Username", Username);
    model.addAttribute("Password", Password);
    model.addAttribute("Email", Email);
    return "User";
  }



}
