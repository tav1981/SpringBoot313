package web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import web.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //User findByUsername(String username);

    @Query("select c from User c join fetch c.roles where c.username = :username")
    User findByNameFetchUser(@Param("username") String username);
}
