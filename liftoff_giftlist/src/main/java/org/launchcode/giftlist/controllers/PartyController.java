package org.launchcode.giftlist.controllers;

import org.launchcode.giftlist.models.Party;
import org.launchcode.giftlist.models.User;
import org.launchcode.giftlist.models.WishList;
import org.launchcode.giftlist.repositories.PartyRepository;
import org.launchcode.giftlist.repositories.UserRepository;
import org.launchcode.giftlist.repositories.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class PartyController {

    @Autowired
    PartyRepository partyRepository;
    @Autowired
    WishListRepository wishListRepository;
    @Autowired
    UserRepository userRepository;


    @GetMapping("/createparty")
    public String displayCreatePartyForm(Model model){
        model.addAttribute(new Party());
        return "createparty";
    }

    @PostMapping("/createparty")
    public String processCreatePartyForm(@ModelAttribute @Valid Party party, Errors errors,
                                        Model model, HttpSession session){
        if (errors.hasErrors()) {
            return "createparty";
        }
        Integer currentUserId = (Integer) session.getAttribute("user");
        User partyCreator = userRepository.findById(currentUserId).get();
        party.setPartyOwner(partyCreator);
        party.addMember(partyCreator);
        partyRepository.save(party);

    //  Add group info to appropriate User instance
        partyCreator.addOwnedParty(party);
        partyCreator.addJoinedParty(party);

        return "redirect:/party_list";
    }

    /*@GetMapping("/party_list")
    public String showGroups(Model model, HttpSession session){
        Integer currentUserId = (Integer) session.getAttribute("user");
        User user = userRepository.findById(currentUserId).get();
        Party party = (Party) partyRepository.findAllById(Collections.singleton(currentUserId));
        model.addAttribute("party", party);
        model.addAttribute("user", user);
        return "party_list";
    }*/




}
