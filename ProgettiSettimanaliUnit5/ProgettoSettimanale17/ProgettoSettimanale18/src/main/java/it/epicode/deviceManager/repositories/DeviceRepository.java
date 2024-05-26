package it.epicode.deviceManager.repositories;

import it.epicode.deviceManager.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device,Long> {
}
