package blaybus.global.jwt.domain.repository;

import blaybus.global.jwt.domain.entity.GoogleJsonWebToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoogleJsonWebTokenRepository extends CrudRepository<GoogleJsonWebToken, String> {
}
