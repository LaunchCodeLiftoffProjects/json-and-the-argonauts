package org.launchcode.giftlist.models.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RegisterFormDTO extends LoginFormDTO {

    @NotBlank
    @NotNull
    private String firstName;
    @NotNull
    @NotBlank
    private String lastName;
    @NotNull
    @NotBlank
    private String email;
    private String verifyPassword;

//    Getter Setter Salad
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getVerifyPassword() { return verifyPassword; }

    public void setVerifyPassword(String verifyPassword) { this.verifyPassword = verifyPassword; }
}
