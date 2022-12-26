package org.ecommerce.api;

import java.util.Objects;
import java.util.UUID;
import org.ecommerce.api.dto.in.PasswordInDto;
import org.ecommerce.api.dto.in.UserInDto;
import org.ecommerce.api.dto.out.UserOutDto;
import org.ecommerce.api.util.Response;
import org.ecommerce.api.util.ResponseUtil;
import org.ecommerce.domain.UserDomain;
import org.ecommerce.domain.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class UserController {

  @Autowired
  UserService userService;

  @PostMapping
  public ResponseEntity<Response> createNewUser(@RequestBody UserInDto userInDto) {
    UserDomain userDomain = createUserDomain(userInDto);
    UserDomain savedUserDomain;
    try {
      savedUserDomain = userService.registerUser(userDomain);
    } catch (Exception e) {
      Response response = Response.builder()
          .msg(ResponseUtil.USER_EXISTS)
          .build();
      return ResponseEntity.status(ResponseUtil.BAD_REQUEST).body(response);
    }

    UserOutDto userOutDto = createUserOutDto(savedUserDomain);
    Response response = Response.builder()
        .result(userOutDto)
        .build();
    return ResponseEntity.status(ResponseUtil.SUCCESS).body(response);
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

  @GetMapping("/id/{id}")
  public ResponseEntity<Response> getUserById(@PathVariable("id") String id) {
    UserDomain userDomain = userService.getUserById(id);
    if (Objects.nonNull(userDomain)) {
      return ResponseEntity.status(ResponseUtil.SUCCESS)
          .body(Response.builder().result(userDomain).build());
    }
    return ResponseEntity.status(ResponseUtil.BAD_REQUEST)
        .body(Response.builder().msg(String.format(ResponseUtil.USER_ID_WRONG, id)).build());
  }

  @GetMapping("/name/{name}")
  public ResponseEntity<Response> getUserByName(@PathVariable("name") String name) {
    UserDomain userDomain = userService.getUserByName(name);
    if (Objects.nonNull(userDomain)) {
      return ResponseEntity.status(ResponseUtil.SUCCESS)
          .body(Response.builder().result(userDomain).build());
    }
    return ResponseEntity.status(ResponseUtil.BAD_REQUEST)
        .body(Response.builder().msg(String.format(ResponseUtil.USER_NAME_WRONG, name)).build());
  }

  @PostMapping("/login")
  public ResponseEntity<Response> login(@RequestBody UserInDto userInDto) {
    // get UserDomain by userName
    UserDomain userDomain = userService.getUserByName(userInDto.getUserName());
    if (Objects.isNull(userDomain)) {
      return ResponseEntity.status(ResponseUtil.BAD_REQUEST)
          .body(Response.builder()
              .msg(String.format(ResponseUtil.USER_NAME_WRONG, userInDto.getUserName())).build());
    }
    // check password match
    if (!userDomain.getPassword().equals(userInDto.getPassword())) {
      return ResponseEntity.status(ResponseUtil.BAD_REQUEST)
          .body(Response.builder().msg(ResponseUtil.PASSWORD_NOT_MATCH).build());
    }
    // login successfully
    return ResponseEntity.status(ResponseUtil.SUCCESS)
        .body(Response.builder().result(createUserOutDto(userDomain)).build());
  }


  @PutMapping("/password")
  public ResponseEntity<Response> updatePassword(
      @Validated @RequestBody PasswordInDto passwordInDto) {
    // find user by id
    UserDomain userDomain = userService.getUserById(passwordInDto.getUserId());
    if (Objects.isNull(userDomain)) {
      return ResponseEntity.status(ResponseUtil.BAD_REQUEST).body(Response.builder()
          .msg(String.format(ResponseUtil.USER_ID_WRONG, passwordInDto.getUserId())).build());
    }
    // check old password
    if (!userDomain.getPassword().equals(passwordInDto.getOldPassword())) {
      return ResponseEntity.status(ResponseUtil.BAD_REQUEST)
          .body(Response.builder().msg(ResponseUtil.PASSWORD_NOT_MATCH).build());
    }
    // update new password
    userDomain.setPassword(passwordInDto.getNewPassword());
    UserDomain savedUserDomain = userService.updateUserDomain(userDomain);
    return ResponseEntity.status(HttpStatus.OK)
        .body(Response.builder().result(createUserOutDto(savedUserDomain)).build());
  }

}
