package fedeCapiz.VemerdiSp.DAO;

import fedeCapiz.VemerdiSp.Entities.Persona;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class PersonaService {
    @Autowired
    private PersonaDAO personaDAO;
    public void save(Persona persona) {
        personaDAO.save(persona);
        System.out.println("sono fortissimooooooo");
    }
    public Optional<Persona> findById(UUID id) {
        return personaDAO.findById(id);
    }

    public List<Persona> findAll() {
        return personaDAO.findAll();
    }



}
