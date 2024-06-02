package it.epicode.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Data
@Builder(setterPrefix = "with")
@AllArgsConstructor
public class RegisterUserDto extends DtoBase{
    private String nomeUtente;
    private String password;
    private String soprannome;
    private String ruolo;
}
