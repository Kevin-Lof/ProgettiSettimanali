package it.epicode.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class LoginResponseDto {
    private long id;
    private String nomeUtente;
    private String soprannome;
    private final List<String> ruolo;
    private String token;

    @Builder(setterPrefix = "with")
    public LoginResponseDto(long id, String nomeUtente, String soprannome, String token, List<String> ruolo) {
        this.id = id;
        this.nomeUtente = nomeUtente;
        this.soprannome= soprannome;
        this.ruolo = ruolo;
        this.token = token;
    }
}
