package com.voting.app.dao;

import com.voting.app.entity.Designation;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DesignationRepository extends CrudRepository<Designation, Integer> {

    Optional<Designation> findByDesignationName(String designationName);
}
