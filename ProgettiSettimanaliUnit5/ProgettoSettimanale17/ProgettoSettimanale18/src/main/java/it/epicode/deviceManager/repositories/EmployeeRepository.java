package it.epicode.deviceManager.repositories;

import it.epicode.deviceManager.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
