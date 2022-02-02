package org.launchcode.giftlist.controllers;

import jdk.jfr.Frequency;
import org.launchcode.giftlist.models.Item;
import org.launchcode.giftlist.models.Party;
import org.launchcode.giftlist.models.User;
import org.launchcode.giftlist.models.WishList;
import org.launchcode.giftlist.models.dto.*;
import org.launchcode.giftlist.repositories.ItemRepository;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class PartyController {

    @Autowired
    PartyRepository partyRepository;
    @Autowired
    WishListRepository wishListRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ItemRepository itemRepository;

    @GetMapping("/party_list")
    public String showAllGroups(Model model, HttpSession session, @RequestParam(value = "memberId", required = false) List<String> memberIds) {


        Integer currentUserId = (Integer) session.getAttribute("user");
        User currentUser = userRepository.findById(currentUserId).get();
        List<Party> ownedParties = partyRepository.findAllByPartyOwner(currentUser);
        List<Party> allParties = partyRepository.findAllByMembers(currentUser);
//        System.out.println("Main: " + currentUser.getUsername() + " has " + currentUser.getJoinedParties().size());

        model.addAttribute("ownedParties", ownedParties);
        model.addAttribute("allParties", allParties);
        model.addAttribute("user", currentUser);
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
        User currentUser = userRepository.findById(currentUserId).get();
        List<Party> ownedParties = partyRepository.findAllByPartyOwner(currentUser);
        List<Party> allParties = partyRepository.findAllByMembers(currentUser);
        model.addAttribute("ownedParties", ownedParties);
        model.addAttribute("allParties", allParties);
        return "redirect:/party_list";
    }


    @GetMapping("/createparty")
    public String displayCreateGroupForm(Model model) {
        model.addAttribute(new Party());
        return "createparty";
    }

    @PostMapping("/createparty")
    public String processCreatePartyForm(@ModelAttribute @Valid Party party, Errors errors,
                                         Model model, HttpSession session) {
        if (errors.hasErrors()) {
            return "createparty";
        }
        Integer currentUserId = (Integer) session.getAttribute("user");
        User partyCreator = userRepository.findById(currentUserId).get();
        party.setPartyOwner(partyCreator);
        party.addMember(partyCreator);
        partyRepository.save(party);
        partyCreator.addOwnedParty(party);
        partyCreator.addJoinedParty(party);
        return "redirect:/party_list";
    }

    @GetMapping("/party_list/{groupId}")
    public String showSpecificParty(Model model, @PathVariable String groupId, HttpSession session) {
        Party party = partyRepository.findById(Integer.parseInt(groupId)).get();
        Integer currentUserObj = (Integer) session.getAttribute("user");
        User currentUser = userRepository.findById(currentUserObj).get();
        User partyOwner = party.getPartyOwner();
        Boolean isOwner = currentUser.isPartyOwner(currentUser, partyOwner);
        model.addAttribute("isOwner", isOwner);
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
        return "redirect:/party_list";
    }

    @GetMapping("/party_list/{groupId}/leave")
    public String leaveParty(@PathVariable String groupId, HttpSession session, Model model) {
        Integer currentUserId = (Integer) session.getAttribute("user");
        User currentUser = userRepository.findById(currentUserId).get();
        Party partyToLeave = partyRepository.findById(Integer.parseInt(groupId)).get();
        partyToLeave.removeMember(currentUser);
        partyRepository.save(partyToLeave);
        currentUser.removeJoinedParty(partyToLeave);
        userRepository.save(currentUser);
//        System.out.println("Leaving party: " + groupId + " " + partyToLeave.getName());
//        System.out.println("Leave: " + currentUser.getUsername() + " has " + currentUser.getJoinedParties().size());
        return "redirect:/party_list";
    }

    @GetMapping("/party_list/{groupId}/members")
    public String showMembers(Model model, HttpSession session, @PathVariable String groupId, String username, @ModelAttribute ViewUserWishListDTO viewUserWishListDTO) {
        Party party = partyRepository.findById(Integer.parseInt(groupId)).get();
        List<User> members = party.getMembers();
        Integer currentUserObj = (Integer) session.getAttribute("user");
        User currentUser = userRepository.findById(currentUserObj).get();
        User partyOwner = party.getPartyOwner();
        Boolean isOwner = currentUser.isPartyOwner(currentUser, partyOwner);
        model.addAttribute("isOwner", isOwner);
        model.addAttribute("members", members);
        model.addAttribute("party", party);
        return "members";
    }

    @PostMapping("/party_list/{groupId}/members")
    public String deleteMembers(@RequestParam(value = "memberId", required = false) List<String> memberIds, @PathVariable String groupId, Model model) {

        Party party = partyRepository.findById(Integer.parseInt(groupId)).get();
        if (memberIds != null) {
            for (String memberid : memberIds) {
                User userToRemove = userRepository.findById(Integer.parseInt(memberid)).get();
                party.removeMember(userToRemove);
                userToRemove.removeFromGroupCreatedByAnotherUser(party);
            }
            partyRepository.save(party);
        }
        List<User> members = party.getMembers();
        model.addAttribute("party", party);
        model.addAttribute("members", members);

        return "redirect:members";
    }

    @GetMapping("/party_list/{groupId}/add_member")
    public String renderAddMemberForm(@PathVariable String groupId, Model model) {
            boolean userNameNotFound = true;
            boolean isPartOfGroup = true;
            model.addAttribute("usernameNotFound", userNameNotFound);
            model.addAttribute("isPartOfGroup", isPartOfGroup);
            Party party = partyRepository.findById(Integer.parseInt(groupId)).get();
            model.addAttribute("party", party);
            return ("add_member");
    }

    @PostMapping("/party_list/{groupId}/add_member")
    public String processAddMemberForm(@PathVariable String groupId, Model model, @RequestParam(value = "username", required = false) String username){
        User userToAdd = userRepository.findByUsername(username);
        Party party = partyRepository.findById(Integer.parseInt(groupId)).get();
        if (userToAdd == null){
            boolean userNameNotFound = false;
            model.addAttribute("usernameNotFound", userNameNotFound);
            boolean isPartOfGroup = true;
            model.addAttribute("isPartOfGroup", isPartOfGroup);
            model.addAttribute("party", party);
            return "add_member";
        }
        if (party.getMembers().contains(userToAdd)){
            boolean isPartOfGroup = false;
            boolean userNameNotFound = true;
            model.addAttribute("usernameNotFound", userNameNotFound);
            model.addAttribute("isPartOfGroup", isPartOfGroup);
            model.addAttribute("party", party);
            return "add_member";
        }
        model.addAttribute("username", userToAdd.getUsername());
        party.addMember(userToAdd);
        userToAdd.addToGroupCreatedByAnotherUser(party);
        userRepository.save(userToAdd);  // IS THIS RIGHT?
        partyRepository.save(party);
        List<User> members = party.getMembers();
        model.addAttribute("members", members);
        model.addAttribute("party", party);
        return "redirect:/party_list/" + groupId + "/members";
    }

    @GetMapping("/party_list/{groupId}/members/{memberId}")
    public String showUserWishlist(Model model, @PathVariable String groupId, @PathVariable String memberId, @ModelAttribute ViewUserWishListDTO viewUserWishListDTO) {

        Party party = partyRepository.findById(Integer.parseInt(groupId)).get();
        User user = userRepository.findById(Integer.parseInt(memberId)).get();
        List<WishList> wishLists = user.getWishLists();
        model.addAttribute("member", user);
        model.addAttribute("party", party);
        model.addAttribute("wishLists", wishLists);
        return "view_userwishlist";
    }


    @GetMapping("/party_list/{groupId}/members/{memberId}/{wishListId}")
    public String showItemsInUserlist(@PathVariable String groupId, @PathVariable String memberId,
                                      @PathVariable String wishListId, Model model, HttpSession session){
        Party party = partyRepository.findById(Integer.parseInt(groupId)).get();
//        User user = userRepository.findById(Integer.parseInt(memberId)).get();
        Integer currentUserId = (Integer) session.getAttribute("user");
        User currentUser = userRepository.findById(currentUserId).get();
        WishList wishList = wishListRepository.findById(Integer.parseInt(wishListId)).get();
        List<Item> items = itemRepository.findAllBywishList(wishList);
        model.addAttribute("party", party);
        model.addAttribute("user", currentUser);
        model.addAttribute("wishList", wishList);
        model.addAttribute("items", items);
        model.addAttribute("listId", wishListId);
        return "view_userwishlist_items";
    }


    @PostMapping("/party_list/{groupId}/members/{memberId}/{wishListId}")
    public String processItemsInUserList(@PathVariable String groupId, @PathVariable String memberId,
                                        @PathVariable String wishListId, Model model, HttpSession session,
                                        @RequestParam(value="itemId", required = false) List<String> itemIds){
        if (itemIds != null){
            for (String itemid: itemIds){
                Item item = itemRepository.findById(Integer.parseInt(itemid)).get();
                if (item.getPurchased() == true) {
                    item.setPurchased(false);
                }
                else {
                    item.setPurchased(true);
                }
                itemRepository.save(item);
            }
        }
        Party party = partyRepository.findById(Integer.parseInt(groupId)).get();
        Integer currentUserId = (Integer) session.getAttribute("user");
        User currentUser = userRepository.findById(currentUserId).get();
        WishList wishList = wishListRepository.findById(Integer.parseInt(wishListId)).get();
        List<Item> items = itemRepository.findAllBywishList(wishList);
        model.addAttribute("party", party);
        model.addAttribute("user", currentUser);
        model.addAttribute("wishList", wishList);
        model.addAttribute("items", items);
        model.addAttribute("wishListId", wishListId);
        return "redirect:/party_list/" + groupId + "/members/" + memberId + "/" + wishListId;
    }

}
