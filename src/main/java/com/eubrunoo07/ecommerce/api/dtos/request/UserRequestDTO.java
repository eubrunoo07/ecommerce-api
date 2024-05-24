package com.eubrunoo07.ecommerce.api.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
    @NotBlank(message = "Name field cannot be empty")
    private String name;
    @NotBlank(message = "Email field cannot be empty")
    @Email(message = "Email is invalid")
    private String email;
    @NotBlank(message = "Password field cannot be empty")
    private String password;
    @NotBlank(message = "Address field cannot be empty")
    private String address;
}
