package sk.ygor.stackoverflow.q53220918.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sk.ygor.stackoverflow.q53220918.domain.UserRefreshToken;

import java.util.Optional;

@Repository
public interface UserRefreshTokenRepository extends CrudRepository<UserRefreshToken, Long> {

    Optional<UserRefreshToken> findByToken(String token);

}
