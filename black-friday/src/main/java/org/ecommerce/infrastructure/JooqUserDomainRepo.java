package org.ecommerce.infrastructure;

import org.ecommerce.domain.UserDomain;
import org.ecommerce.domain.UserDomainRepository;
import org.ecommerce.infrastructure.jooq.tables.records.UserRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JooqUserDomainRepo implements UserDomainRepository {
  @Autowired
  DSLContext dslContext;

  //public static final User USER_T = new User();

  @Override
  public void createUser(UserDomain userDomain) {
    UserRecord userRecord = new UserRecord();
    userRecord.setUserId(userDomain.getUserId());
    userRecord.setUserName(userDomain.getUserName());
    userRecord.setPassword(userDomain.getPassword());

    dslContext.executeInsert(userRecord);
  }

  @Override
  public UserDomain getUserByUserName(String userName) {
    return null;
  }

  @Override
  public UserDomain getUserByUserId(String userId) {
    return null;
  }

  @Override
  public void updateUser(UserDomain userDomain) {

  }
}
