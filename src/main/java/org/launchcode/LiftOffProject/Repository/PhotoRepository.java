package org.launchcode.LiftOffProject.Repository;

import org.launchcode.LiftOffProject.models.Photo;
import org.springframework.data.repository.CrudRepository;

public interface PhotoRepository extends CrudRepository <Photo, Integer> {
}
