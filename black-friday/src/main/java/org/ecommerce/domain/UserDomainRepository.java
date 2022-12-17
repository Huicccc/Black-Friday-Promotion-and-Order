package org.ecommerce.domain;

public interface UserDomainRepository {
  void createUser(UserDomain userDomain);

  UserDomain getUserByUserName(String userName);

  UserDomain getUserByUserId(String userId);

  void updateUser(UserDomain userDomain);

}
