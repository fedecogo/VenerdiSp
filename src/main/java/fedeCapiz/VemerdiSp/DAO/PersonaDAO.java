package fedeCapiz.VemerdiSp.DAO;

import fedeCapiz.VemerdiSp.Entities.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PersonaDAO extends JpaRepository<Persona, UUID> {
    @Query("SELECT p FROM Persona p WHERE p.mail = :email")
    Persona findByEmail(String email);

    @Query("SELECT p FROM Persona p WHERE p.username LIKE %:partialUsername%")
    List<Persona> findByPartialUsername(String partialUsername);
}