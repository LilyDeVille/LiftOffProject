package org.launchcode.LiftOffProject.Repository;

import org.launchcode.LiftOffProject.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);

}