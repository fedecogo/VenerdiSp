package fedeCapiz.VemerdiSp.Entities;

import fedeCapiz.VemerdiSp.EnumLOCAZIONE;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Base {
    @Id
    @GeneratedValue
    private UUID id;
    String descrizione ;
    @Enumerated(EnumType.STRING)
    @Column(name="tipo locazione")
    private EnumLOCAZIONE enumLOCAZIONE;
    int maxPosti ;
    @ManyToOne
    @JoinColumn(name="palazzo_id")
    Palazzo palazzo ;
    boolean isLibero;

    public Base(String descrizione, EnumLOCAZIONE enumLOCAZIONE, int maxPosti, Palazzo palazzo, boolean isLibero) {
        this.descrizione = descrizione;
        this.enumLOCAZIONE = enumLOCAZIONE;
        this.maxPosti = maxPosti;
        this.palazzo = palazzo;
        this.isLibero = true;
    }
    @Override
    public String toString() {
        return "Base{id=" + id + ", desc=" + descrizione + ", locazione=" + enumLOCAZIONE + ", palazzo=" + palazzo.getNome() + ", è disponibile=" + isLibero() + '}';
    }

}

