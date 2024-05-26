package it.epicode.deviceManager.entities.controllers;

import it.epicode.deviceManager.entities.Device;
import it.epicode.deviceManager.services.DeviceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @PostMapping
    public ResponseEntity<Device> createDevice(@Valid @RequestBody Device device) {
        return ResponseEntity.ok(deviceService.save(device));
    }

    @GetMapping
    public ResponseEntity<Page<Device>> getAllDevices(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort) {
        Page<Device> devices = deviceService.getDevices(page, size, sort);
        return ResponseEntity.ok(devices);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Device> getDeviceById(@PathVariable Long id) {
        return ResponseEntity.ok(deviceService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Device> updateDevice(@PathVariable Long id, @Valid @RequestBody Device device) {
        return ResponseEntity.ok(deviceService.findByIdAndUpdate(id, device));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable Long id) {
        deviceService.findByIdAndDelete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/assign/{employeeId}")
    public ResponseEntity<Device> assignDeviceToEmployee(@PathVariable Long id, @PathVariable Long employeeId) {
        Device device = deviceService.assignToEmployee(id, employeeId);
        return ResponseEntity.ok(device);
    }
}
