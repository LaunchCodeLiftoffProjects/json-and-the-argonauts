package org.launchcode.giftlist.repositories;

import org.launchcode.giftlist.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);

    Optional<User> findById(Integer userId);
}
