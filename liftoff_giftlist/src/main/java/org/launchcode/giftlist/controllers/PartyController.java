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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class PartyController {

    @Autowired
    PartyRepository partyRepository;
    @Autowired
    WishListRepository wishListRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/party_list")
    public String showAllGroups(Model model, HttpSession session){
        Integer currentUserId = (Integer) session.getAttribute("user");
        User user = userRepository.findById(currentUserId).get();
        List<Party> parties = partyRepository.findAllByPartyOwner(user);
        model.addAttribute("parties", parties);
        model.addAttribute("user", user);
        return "party_list";
    }

    @PostMapping("/party_list")
    public String deleteGroup(@RequestParam(value = "groupId", required = false) List<String> groupIds, Model model, HttpSession session) {
        if (groupIds != null) {
            for (String groupId : groupIds) {
                partyRepository.deleteById(Integer.parseInt(groupId));
            }
        }
        Integer currentUserId = (Integer) session.getAttribute("user");
        User user = userRepository.findById(currentUserId).get();
        List<Party> parties =  partyRepository.findAllByPartyOwner(user);
        model.addAttribute("parties", parties);
        return "redirect:party_list";
    }

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
        party.setPartyOwner(userRepository.findById(currentUserId).get());
        party.addMember(userRepository.findById(currentUserId).get());
        partyRepository.save(party);
        return "party_list";
    }

    @GetMapping("/party_list/{groupId}")
    public String showSpecificPartyWithMembersAndAllWishlists(Model model, @PathVariable String groupId, HttpSession session){
        Party party = partyRepository.findById(Integer.parseInt(groupId)).get();
        model.addAttribute("party", party);
        List<User> members = party.getMembers();
        model.addAttribute("members", members);
        return "party_details";
    }

    @GetMapping("/party_list/{groupId}/add_member")
    public String renderAddToGroupForm(Model model, HttpSession session, @PathVariable String groupId, String username){
        Integer currentUserId = (Integer) session.getAttribute("user");
        User partyOwner = userRepository.findById(currentUserId).get();
        List<Party> parties = partyRepository.findAllByPartyOwner(partyOwner);
        model.addAttribute("parties", parties);
        Party party = partyRepository.findById(Integer.parseInt(groupId)).get();
        model.addAttribute("party", party);
        model.addAttribute("username", username);
        User userToAdd = userRepository.findByUsername(username);
        model.addAttribute("user", userToAdd);

        /*Integer currentUserId = (Integer) session.getAttribute("user");
        userRepository.findById(currentUserId);
        partyRepository.findAllById(Collections.singleton(currentUserId));
        User user = userRepository.findById(currentUserId).get();
        List<User> members = party.getMembers();
        List<Party> parties =  partyRepository.findAllByPartyOwner(user);
        model.addAttribute("members", members);
        model.addAttribute("party", partyRepository.findById(currentUserId));*/
        return "add_member";
    }

    @PostMapping("/party_list/{groupId}/add_member")
    public String processAddToGroupForm(Model model, @ModelAttribute @Valid User user, HttpSession session, @PathVariable String groupId, Errors errors, String username){
        if (errors.hasErrors()) {
            Party party = partyRepository.findById(Integer.parseInt(groupId)).get();
            model.addAttribute("party", party);
            model.addAttribute("user", user);
            model.addAttribute("members", party.getMembers());
            return "add_member";
        }
        Party party = partyRepository.findById(Integer.parseInt(groupId)).get();
        User userToAdd = userRepository.findByUsername(username);
        party.addMember(userToAdd);
        partyRepository.save(party);
        model.addAttribute("members", party.getMembers());
        model.addAttribute("party", party);
        return "add_member";
    }

}
