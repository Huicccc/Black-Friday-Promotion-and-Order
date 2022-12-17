package org.ecommerce.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  UserDomainRepository userDomainRepository;
  public UserDomain registerUser(UserDomain userDomain) {
    userDomainRepository.createUser(userDomain);
    return userDomain;
  }

}
