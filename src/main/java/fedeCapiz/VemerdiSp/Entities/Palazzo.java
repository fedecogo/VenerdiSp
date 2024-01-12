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

public class Palazzo {

    @Id
    @GeneratedValue
    private UUID id;
    String nome  ;
    String indirizzo ;
    String citta;

    @OneToMany(mappedBy = "palazzo")
    protected List<Base> listaDelleBasi  ;


    public Palazzo(String nome, String indirizzo, String citta) {
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.citta = citta;
    }
}
