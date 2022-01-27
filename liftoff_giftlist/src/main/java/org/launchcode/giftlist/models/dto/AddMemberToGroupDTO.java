package org.launchcode.giftlist.models.dto;

import org.launchcode.giftlist.models.User;
import org.launchcode.giftlist.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddMemberToGroupDTO {

    @Autowired
    UserRepository userRepository;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 20, message = "Invalid username. Must be between 3 and 20 characters.")
    private String username;


    public String getUsername(){
        return this.username;
    }



}
