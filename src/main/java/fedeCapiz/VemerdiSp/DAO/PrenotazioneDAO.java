package fedeCapiz.VemerdiSp.DAO;

import fedeCapiz.VemerdiSp.Entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PrenotazioneDAO extends JpaRepository<Prenotazione, UUID> {
}
