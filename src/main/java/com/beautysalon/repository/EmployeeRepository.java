package com.beautysalon.repository;

import com.beautysalon.repository.model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends ListCrudRepository<Employee, Integer> {

    Employee findByName(String employeeName);

    boolean existsByName(String name);
}
