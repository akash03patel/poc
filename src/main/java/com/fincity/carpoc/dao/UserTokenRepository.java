package com.fincity.carpoc.dao;

import java.util.List;
import java.util.Optional;
import com.fincity.carpoc.model.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * The interface User token repository.
 */
public interface UserTokenRepository extends JpaRepository<UserToken, Long> {

  /**
   * Find by token optional.
   *
   * @param jwtToken the jwt token
   * @return the optional
   */
  Optional<UserToken> findByToken(String jwtToken);

  /**
   * Delete by token.
   *
   * @param token the token
   */
  void deleteByToken(String token);

  /**
   * Find all by user id and token type list.
   *
   * @param userId      the user id
   * @param tokenTypeId the token type id
   * @return the list
   */
  @Query("from UserToken as ut where ut.tokenType = :tokenTypeId"
      + " and ut.user.id=:userId")
  List<UserToken> findAllByUserIdAndTokenType(long userId, int tokenTypeId);
}
