package com.devopsbuddy9.backend.persistence.repositories;

import com.devopsbuddy9.backend.persistence.domain.backend.Plan;
import com.devopsbuddy9.backend.persistence.domain.backend.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by kb on 7/19/2017.
 */
@Repository
public interface PlanRepository extends CrudRepository<Plan, Integer> {


}
