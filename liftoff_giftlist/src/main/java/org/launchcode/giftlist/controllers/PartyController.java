package org.launchcode.giftlist.controllers;

import org.launchcode.giftlist.models.Item;
import org.launchcode.giftlist.models.Party;
import org.launchcode.giftlist.models.User;
import org.launchcode.giftlist.models.WishList;
import org.launchcode.giftlist.models.dto.UpdatePartyDetailsDTO;
import org.launchcode.giftlist.models.dto.UpdateWishListDetailsDTO;
import org.launchcode.giftlist.repositories.PartyRepository;
import org.launchcode.giftlist.repositories.UserRepository;
import org.launchcode.giftlist.repositories.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

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

    @PostMapping("/party_list/{groupId}")
    public String processEditPartyForm(@PathVariable String groupId, @Valid @ModelAttribute UpdatePartyDetailsDTO updatePartyDetailsDTO) {
        Party party = partyRepository.findById(Integer.parseInt(groupId)).get();
        party.setName(updatePartyDetailsDTO.getName());
        party.setDescription(updatePartyDetailsDTO.getDescription());
        partyRepository.save(party);
        return "redirect:";
    }

    @GetMapping("/party_list/{groupId}/members")
    public String showMembers(Model model, HttpSession session, @PathVariable String groupId, String username){
        Party party = partyRepository.findById(Integer.parseInt(groupId)).get();
        List<User> members = party.getMembers();
        model.addAttribute("members", members);
        model.addAttribute("party", party);
        return "party_list";


        /*Integer currentUserId = (Integer) session.getAttribute("user");
        User partyOwner = userRepository.findById(currentUserId).get();
        List<Party> parties = partyRepository.findAllByPartyOwner(partyOwner);
        model.addAttribute("parties", parties);
        Party party = partyRepository.findById(Integer.parseInt(groupId)).get();
        model.addAttribute("party", party);
        model.addAttribute("username", username);
        User userToAdd = userRepository.findByUsername(username);
        model.addAttribute("user", userToAdd);*/

        /*Integer currentUserId = (Integer) session.getAttribute("user");
        userRepository.findById(currentUserId);
        partyRepository.findAllById(Collections.singleton(currentUserId));
        User user = userRepository.findById(currentUserId).get();
        List<User> members = party.getMembers();
        List<Party> parties =  partyRepository.findAllByPartyOwner(user);
        model.addAttribute("members", members);
        model.addAttribute("party", partyRepository.findById(currentUserId));*/
    }

    @PostMapping("/party_list/{groupId}/members")
    public RedirectView deleteMembers(@RequestParam(value = "memberId", required = false) List<String> memberIds, @PathVariable String memberId, @PathVariable String groupId, Model model) {
        if (memberIds != null) {
            for (String memberid : memberIds) {
                partyRepository.deleteById(Integer.parseInt(memberid));
            }
        }
        Party party = partyRepository.findById(Integer.parseInt(groupId)).get();
        List<User> members = party.getMembers();
        model.addAttribute("party", party);
        model.addAttribute("members", members);
        return new RedirectView("/party_list/" + memberId + "/members");
    }

    /*@GetMapping("/party_list/{groupId}/members/add_member")
    public RedirectView renderAddMemberForm(@RequestParam(value = "memberId")){
        return new RedirectView("/party_list/")
    }*/



}
