package fedeCapiz.VemerdiSp.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Persona {
    @Id
    @GeneratedValue
    private UUID id;
    String nomeVero;
    String username;
    String mail;
    // nel service ci vorra il controllo per l'email
    @OneToMany(mappedBy = "persona")
    protected List<Prenotazione> listaDiPrenotazioni;

    public Persona(String nomeVero, String username, String mail) {
        this.nomeVero = nomeVero;
        this.username = username;
        this.mail = mail;
    }
    @Override
    public String toString() {
        return "Persona{id=" + id + ", nome=" + nomeVero + ", username=" + username + ", mail=" + mail + '}';
    }


}
