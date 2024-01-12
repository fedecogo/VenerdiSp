package fedeCapiz.VemerdiSp.DAO;

import fedeCapiz.VemerdiSp.Entities.Base;
import fedeCapiz.VemerdiSp.Entities.Prenotazione;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class PrenotazioneService {
    @Autowired
    private PrenotazioneDAO prenotazioneDAO;
    @Autowired
    private BaseService baseService;

    public void save(Prenotazione prenotazione) {
        if (10>4) {
            prenotazioneDAO.save(prenotazione);
         System.out.println("salvato!!");
        } else {
       System.out.println("non salvata");  }
    }
    public Optional<Prenotazione> findById(UUID id) {
        return prenotazioneDAO.findById(id);
    }

    public List<Prenotazione> findAll() {
        return prenotazioneDAO.findAll();
    }
}
