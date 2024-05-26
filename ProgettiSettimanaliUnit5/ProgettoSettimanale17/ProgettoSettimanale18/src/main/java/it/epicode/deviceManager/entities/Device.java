package it.epicode.deviceManager.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import it.epicode.deviceManager.entities.enums.DeviceStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "devices")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String type;
    @Enumerated(EnumType.STRING)
    private DeviceStatus status;

    @ManyToOne
    @JsonBackReference
    private Employee employee;

}
