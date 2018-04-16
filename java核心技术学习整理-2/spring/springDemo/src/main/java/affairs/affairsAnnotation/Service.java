package affairs.affairsAnnotation;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class Service {

    public void setDao(Dao dao) {
        this.dao = dao;
    }

    private Dao dao;


    public void testAffairs() {
        dao.update();
        int a = 1 / 0;
        dao.update2();
    }
}
