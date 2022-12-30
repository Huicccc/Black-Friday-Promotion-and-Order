package org.ecommerce.domain.user;

public interface UserDomainRepository {

  UserDomain createUser(UserDomain userDomain);

  UserDomain getUserByUserId(String userId);

  UserDomain getUserByUserName(String userName);

  UserDomain updateUser(UserDomain userDomain);

}
