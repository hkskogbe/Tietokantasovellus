package listat;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import kirja.Aihe;
import kirja.Kirja;

public class AiheLista {

    private EntityManagerFactory kirjatehdas = null;

    public AiheLista() {
        kirjatehdas = Persistence.createEntityManagerFactory("KirjastotietokantaPU");
    }

    public EntityManager getEntityManager() {
        return kirjatehdas.createEntityManager();
    }

    public List<Aihe> getAiheet() {
        EntityManager eeam = getEntityManager();
        return eeam.createQuery("SELECT k FROM Aihe k").getResultList();
    }

    public void lisaaAihe(Aihe k) {
        EntityManager eeam = getEntityManager();
        eeam.getTransaction().begin();
        eeam.persist(k);
        eeam.getTransaction().commit();
    }

    public List<Aihe> getKaikkiAiheet() {
        EntityManager eeam = getEntityManager();
        return eeam.createQuery("SELECT DISTINCT k FROM Aihe k").getResultList();
    }

    public List<Aihe> getKirjaanLiittyvatAiheet(Kirja kirja) {
        EntityManager eeam = getEntityManager();
        return eeam.createQuery("SELECT k FROM Aihe k WHERE k.kirja = '" + kirja + "'").getResultList();
    }
}
