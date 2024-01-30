package me.ricky.guides.securityguides.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import me.ricky.guides.securityguides.model.UserRole;

@Data
public class UserDto {

  private String email;
  
  private String password;

  private String firstName;

  private String lastName;

  @NotNull
  private UserRole userRole;
  
  private int status;
  private String statusInfo;

}