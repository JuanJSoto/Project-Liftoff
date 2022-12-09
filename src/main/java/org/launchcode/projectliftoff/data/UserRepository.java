package org.launchcode.projectliftoff.data;

import org.launchcode.projectliftoff.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}
