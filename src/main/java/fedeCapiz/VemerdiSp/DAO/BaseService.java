package fedeCapiz.VemerdiSp.DAO;

import fedeCapiz.VemerdiSp.Entities.Base;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class BaseService {
    @Autowired
    private BaseDAO baseDAO;
    public void save(Base base){
        baseDAO.save(base);
        System.out.println("Daje roma daje dal baseServic, ajeje è in zona");
    }
    public Optional<Base> findById(UUID id){
        return baseDAO.findById(id);
    }
    public List<Base> findAll(){
        return baseDAO.findAll();
    }


}
