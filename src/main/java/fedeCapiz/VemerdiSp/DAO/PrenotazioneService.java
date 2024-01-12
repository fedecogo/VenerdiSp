package fedeCapiz.VemerdiSp.DAO;

import fedeCapiz.VemerdiSp.Entities.Base;
import fedeCapiz.VemerdiSp.Entities.Prenotazione;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PrenotazioneService {
    @Autowired
    private PrenotazioneDAO prenotazioneDAO;
    @Autowired
    private BaseService baseService;

    public void save(Prenotazione prenotazione) {
        // Verifica se la base è disponibile prima di effettuare la prenotazione
        if (isBaseAvailable(prenotazione.getBasePrenotazione())) {
            prenotazioneDAO.save(prenotazione);
            System.out.println("TECCCCHHHH");
            System.out.println("Prenotazione effettuata con successo!");
        } else {
            System.out.println("Impossibile effettuare la prenotazione. La base non è disponibile.");
        }
    }
    private boolean isBaseAvailable(Base base) {
        return base != null && base.isLibero();
    }
    public Optional<Prenotazione> findById(Long id) {
        return prenotazioneDAO.findById(id);
    }
    public List<Prenotazione> findAll() {
        return prenotazioneDAO.findAll();
    }
}
