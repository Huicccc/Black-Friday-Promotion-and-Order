package org.ecommerce.api;

import java.util.UUID;
import org.ecommerce.api.dto.in.UserInDto;
import org.ecommerce.api.dto.out.UserOutDto;
import org.ecommerce.domain.UserDomain;
import org.ecommerce.domain.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Autowired
  UserService userService;

  @PostMapping("/account")
  public UserOutDto createNewUser(@RequestBody UserInDto userInDto) {
    UserDomain userDomain = createUserDomain(userInDto);
    UserDomain savedUserDomain = userService.registerUser(userDomain);
    return createUserOutDto(savedUserDomain);
  }

  private UserDomain createUserDomain(UserInDto userInDto) {
    return UserDomain.builder()
        .userId(UUID.randomUUID().toString())
        .userName(userInDto.getUserName())
        .password(userInDto.getPassword())
        .build();
  }

  private UserOutDto createUserOutDto(UserDomain savedUserDomain) {
    return UserOutDto.builder()
        .userId(savedUserDomain.getUserId())
        .userName(savedUserDomain.getUserName())
        .build();
  }

}
