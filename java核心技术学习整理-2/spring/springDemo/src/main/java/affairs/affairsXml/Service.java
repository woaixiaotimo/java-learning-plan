package affairs.affairsXml;

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
