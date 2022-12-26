package org.ecommerce.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  UserDomainRepository userDomainRepository;

  public UserDomain registerUser(UserDomain userDomain) {
    return userDomainRepository.createUser(userDomain);
  }

  public UserDomain getUserById(String id) {
    return userDomainRepository.getUserByUserId(id);
  }

  public UserDomain getUserByName(String userName) {
    return userDomainRepository.getUserByUserName(userName);
  }

  public UserDomain updateUserDomain(UserDomain userDomain) {
    return userDomainRepository.updateUser(userDomain);
  }
}
