package org.launchcode.giftlist.models.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AddMemberToGroupDTO {

    @NotNull
    @NotBlank
    private String username;


    public String getUsername(){
        return username;
    }


}
