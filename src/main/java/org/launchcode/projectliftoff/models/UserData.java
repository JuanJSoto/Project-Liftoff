package org.launchcode.projectliftoff.models;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserData extends CrudRepository<User, Integer> {
}
