package it.epicode.deviceManager.services;

import it.epicode.deviceManager.entities.Employee;
import it.epicode.deviceManager.exceptions.NotFoundException;
import it.epicode.deviceManager.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    private final Path root = Paths.get("uploads");

    public Employee save(Employee body){
        return employeeRepository.save(body);
    }

    public Page<Employee> getEmployees(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return employeeRepository.findAll(pageable);
    }

    public Employee findById(Long id){
        return employeeRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(Long id){
        Employee found = this.findById(id);
        employeeRepository.delete(found);
    }

    public Employee findByIdAndUpdate(Long id, Employee body){
        Employee found = this.findById(id);
        found.setEmail(body.getEmail());
        found.setUsername(body.getUsername());
        found.setFirstName(body.getFirstName());
        found.setLastName(body.getLastName());
        return employeeRepository.save(found);
    }

    public Employee saveProfileImage(Long id, MultipartFile file) throws IOException {
        Employee employee = findById(id);
        if (!Files.exists(root)) {
            Files.createDirectories(root);
        }
        String filename = id + "-" + file.getOriginalFilename();
        Path destinationFile = this.root.resolve(filename);
        // Sovrascrive il file se esiste già
        Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);
        employee.setProfileImagePath(destinationFile.toString());
        return employeeRepository.save(employee);
    }
}
