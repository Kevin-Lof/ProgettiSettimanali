package it.epicode.config;

import it.epicode.Mapper;
import it.epicode.dto.LoginResponseDto;
import it.epicode.dto.RegisterUserDto;
import it.epicode.dto.RegisteredUserDto;
import it.epicode.entities.Utente;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

public class BeansConfiguration {
    @Bean
    @Scope("singleton")
    Mapper<RegisterUserDto, Utente> mapRegisterUser2UserEntity() {
        return (input) -> Utente.builder()
                .withNome(input.getNomeUtente())
                .withPassword(input.getPassword())
                .withEmail(input.getNomeUtente())
                .build();
    }

    @Bean
    @Scope("singleton")
    Mapper<Utente, RegisteredUserDto> mapUserEntity2RegisteredUser() {
        return (input) -> RegisteredUserDto.builder()
                .withSoprannome(input.getNome())
                .withId(input.getId())
                .withNomeUtente(input.getNome())
                .withRuolo(input.getRoles().stream().map(r -> r.getNome()).toList())
                .build();
    }

    @Bean
    @Scope("singleton")
    Mapper<Utente, LoginResponseDto> mapUserEntity2LoginResponse() {
        return (input) -> LoginResponseDto.builder() //
                .withSoprannome(input.getNome()) //
                .withId(input.getId()) //
                .withNomeUtente(input.getNome()) //
                .withRuolo(input.getRoles().stream().map(r -> r.getNome()).toList()) //
                .build();
    }
}
