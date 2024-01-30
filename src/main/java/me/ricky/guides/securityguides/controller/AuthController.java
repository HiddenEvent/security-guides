package me.ricky.guides.securityguides.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.ricky.guides.securityguides.model.dto.UserDto;
import me.ricky.guides.securityguides.model.dto.UsersSearchRequest;
import me.ricky.guides.securityguides.service.AuthService;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

  private final AuthService authService;

  /*
  * 회원가입
  * */
  @PostMapping("/signup")
  public ResponseEntity registerUser(@RequestBody UserDto userDto) {
    if(authService.existsByUsername(userDto.getEmail())) {
      return ResponseEntity.ok("유저가 존재합니다.");
    }

    UserDto result = authService.createUser(userDto);
    return ResponseEntity.ok(result);
  }

  /*
  *  로그인
  * */
  @PostMapping(path = "/signin")
  public ResponseEntity<?> authenticateUser(@RequestBody  UserDto userDto) {

    AccessTokenResponse response = authService.setAuth(userDto);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/users")
  public ResponseEntity<?> findAllUsers(UsersSearchRequest request) {
    authService.findAllUsers(request);
    return ResponseEntity.ok(null);
  }
}