package fedeCapiz.VemerdiSp.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Prenotazione {
    @Id
    @GeneratedValue
    private UUID id;
    private LocalDate inizioPrenotazione = LocalDate.now();
    private LocalDate finePrenotazione = inizioPrenotazione.plusDays(1);
    @ManyToOne
    @JoinColumn(name = "base_id")
    private Base basePrenotazione ;
    @ManyToOne
    @JoinColumn(name = "persona_id")
    private Persona persona;

    public Prenotazione( Base basePrenotazione, Persona personaP ) {
        this.basePrenotazione = basePrenotazione;
        this.persona=personaP;
    }


}
