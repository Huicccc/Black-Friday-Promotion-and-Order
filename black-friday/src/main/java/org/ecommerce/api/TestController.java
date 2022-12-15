package org.ecom.api;

import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

  @GetMapping("/name")
  public String getName() {
    return "Hello, here is Spring";
  }

  @GetMapping("/plus/{a}")
  public Integer plus(@PathVariable("a") Integer number) {
    return number + 1;
  }

  @PostMapping("/user")
  public User createUser(@RequestBody User user) {
    user.userId = UUID.randomUUID().toString();
    System.out.println(user.userName);
    System.out.println(user.password);
    // TODO: SAVE TO DATABASE
    return user;
  }

}
