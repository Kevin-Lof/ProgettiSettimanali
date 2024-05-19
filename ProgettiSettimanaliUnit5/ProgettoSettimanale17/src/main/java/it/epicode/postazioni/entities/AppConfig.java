package it.epicode.postazioni.entities;


import it.epicode.postazioni.enums.TipoPostazione;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean(name = "utente1")
    public Utente utente1Bean(){
        return new Utente("mariorossi69","Mario","Rossi","mariorossi@email.com");
    }
    @Bean(name = "utente2")
    public Utente utente2Bean(){
        return new Utente("luigibiachi","Luigi","Bianchi","luigibianchi@email.com");
    }
    @Bean(name = "utente3")
    public Utente utente3Bean(){
        return new Utente("pippofranco","Pippo","Franco","pippofranco@email.com");
    }

    @Bean(name = "edificio1")
    public Edificio edificio1Bean(){
        return new Edificio("e1","Via roma,1","Roma");
    }

    @Bean(name = "edificio2")
    public Edificio edificio2Bean(){
        return new Edificio("e2","Via milano ,2","Roma");
    }

    @Bean(name = "postazione1")
    public Postazione postazione1Bean(){
        return new Postazione("Bellissima postazione privata", TipoPostazione.PRIVATO,6,edificio1Bean());
    }
    @Bean(name = "postazione2")
    public Postazione postazione2Bean(){
        return new Postazione("Postazione mediocre ma silenziosa", TipoPostazione.SALA_RIUNIONI,10,edificio1Bean());
    }
    @Bean(name = "postazione3")
    public Postazione postazione3Bean(){
        return new Postazione("Spazio aperto ben areato", TipoPostazione.OPENSPACE, 30,edificio1Bean());
    }

    @Bean(name = "postazione4")
    public Postazione postazione4Bean(){
        return new Postazione("Comodo e riservato", TipoPostazione.PRIVATO,4,edificio2Bean());
    }

}
