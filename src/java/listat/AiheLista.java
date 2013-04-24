package listat;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import kirja.Aihe;
import kirja.Kirja;

/**
 * Käsittelee tietokannan Aihe-olioita.
 *
 * @author hkskogbe
 */
public class AiheLista {

    private EntityManagerFactory kirjatehdas = null;

    public AiheLista() {
        kirjatehdas = Persistence.createEntityManagerFactory("KirjastotietokantaPU");
    }

    public EntityManager getEntityManager() {
        return kirjatehdas.createEntityManager();
    }

    /**
     * Hae kaikki aiheet. Sisältää myös mahdollisia duplikaatteja.
     *
     * @return
     */
    public List<Aihe> getAiheet() {
        EntityManager eeam = getEntityManager();
        return eeam.createQuery("SELECT k FROM Aihe k").getResultList();
    }

    /**
     * Lisaa aihe tietokantaan.
     *
     * @param k
     */
    public void lisaaAihe(Aihe k) {
        EntityManager eeam = getEntityManager();
        eeam.getTransaction().begin();
        eeam.persist(k);
        eeam.getTransaction().commit();
    }

    /**
     * Hae aiheet, jotka liittyvät parametrina annettavaan kirjaan.
     *
     * @param kirja
     * @return
     */
    public List<Aihe> getKirjaanLiittyvatAiheet(Kirja kirja) {
        EntityManager eeam = getEntityManager();
        return eeam.createQuery("SELECT k FROM Aihe k WHERE k.kirja = '" + kirja + "'").getResultList();
    }
}
