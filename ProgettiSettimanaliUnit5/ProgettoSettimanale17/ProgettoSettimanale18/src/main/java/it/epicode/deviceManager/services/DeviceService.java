package it.epicode.deviceManager.services;

import it.epicode.deviceManager.entities.Device;
import it.epicode.deviceManager.entities.Employee;
import it.epicode.deviceManager.entities.enums.DeviceStatus;
import it.epicode.deviceManager.exceptions.NotFoundException;
import it.epicode.deviceManager.repositories.DeviceRepository;
import it.epicode.deviceManager.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public Device save(Device body){
        body.setStatus(DeviceStatus.AVAILABLE);
        return deviceRepository.save(body);
    }

    public Page<Device> getDevices(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return deviceRepository.findAll(pageable);
    }

    public Device findById(Long id){
        return deviceRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(Long id){
        Device found = this.findById(id);
        deviceRepository.delete(found);
    }

    public Device findByIdAndUpdate(Long id, Device body){
        Device found = this.findById(id);
        found.setStatus(body.getStatus());
        found.setType(body.getType());
        found.setEmployee(body.getEmployee());
        return deviceRepository.save(found);
    }

    public Device assignToEmployee(Long deviceId, Long employeeId) {
        Device device = deviceRepository.findById(deviceId).orElseThrow(() -> new NotFoundException(deviceId));
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new NotFoundException(employeeId));

        device.setEmployee(employee);
        device.setStatus(DeviceStatus.ASSIGNED);
        return deviceRepository.save(device);
    }
}
