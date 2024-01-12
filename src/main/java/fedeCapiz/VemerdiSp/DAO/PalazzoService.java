package fedeCapiz.VemerdiSp.DAO;

import fedeCapiz.VemerdiSp.Entities.Palazzo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PalazzoService {
    @Autowired
    private PalazzoDAO palazzoDAO;

    public void save(Palazzo palazzo){
        palazzoDAO.save(palazzo);
        System.out.println("Ciao da ajeje;)");
    }
    public Optional<Palazzo> findById(Long id) {
        return palazzoDAO.findById(id);
    }

    public List<Palazzo> findAll() {
        return palazzoDAO.findAll();
    }
}
