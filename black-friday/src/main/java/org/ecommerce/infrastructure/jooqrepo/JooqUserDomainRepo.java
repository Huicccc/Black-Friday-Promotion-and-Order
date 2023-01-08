package org.ecommerce.infrastructure.jooqrepo;

import java.util.Optional;
import org.ecommerce.domain.user.UserDomain;
import org.ecommerce.domain.user.UserDomainRepository;
import org.ecommerce.infrastructure.jooq.tables.User;
import org.ecommerce.infrastructure.jooq.tables.records.UserRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JooqUserDomainRepo implements UserDomainRepository,
    RecordDomainMapping<UserRecord, UserDomain> {

  @Autowired
  DSLContext dslContext;

  public static final User USER_T = new User();

  @Override
  public UserDomain createUser(UserDomain userDomain) {
    dslContext.executeInsert(toRecord(userDomain));
    return userDomain;
  }

  @Override
  public UserDomain toDomain(UserRecord userRecord) {
    return UserDomain.builder()
        .userId(userRecord.getUserId())
        .userName(userRecord.getUserName())
        .password(userRecord.getPassword())
        .build();
  }

  @Override
  public UserDomain getUserByUserName(String userName) {
    Optional<UserDomain> userDomainOptional =
        dslContext.selectFrom(USER_T)
            .where(USER_T.USER_NAME.eq(userName))
            .fetchOptional(this::toDomain);
    return userDomainOptional.orElse(null);
  }

  @Override
  public UserDomain updateUser(UserDomain userDomain) {
    dslContext.executeUpdate(toRecord(userDomain));
    return userDomain;
  }

  @Override
  public UserRecord toRecord(UserDomain userDomain) {
    UserRecord userRecord = new UserRecord();
    userRecord.setUserId(userDomain.getUserId());
    userRecord.setUserName(userDomain.getUserName());
    userRecord.setPassword(userDomain.getPassword());
    return userRecord;
  }

  @Override
  public UserDomain getUserByUserId(String userId) {
    Optional<UserDomain> userDomainOptional =
        dslContext.selectFrom(USER_T)
            .where(USER_T.USER_ID.eq(userId))
            .fetchOptional(this::toDomain);
    return userDomainOptional.orElse(null);
  }
}
