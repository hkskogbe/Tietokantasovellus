package listat;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import kirja.Kirja;
import kirja.Kirjailija;

/**
 * Käsittelee tietokannan Kirjailija-olioita.
 *
 * @author hkskogbe
 */
public class KirjailijaLista {

    private EntityManagerFactory kirjailijat = null;

    public KirjailijaLista() {
        kirjailijat = Persistence.createEntityManagerFactory("KirjastotietokantaPU");
    }

    public EntityManager getEntityManager() {
        return kirjailijat.createEntityManager();
    }

    /**
     * Hakee kaikki kirjailijat.
     *
     * @return
     */
    public List<Kirjailija> getKirjailijat() {
        EntityManager eeam = this.getEntityManager();
        return eeam.createQuery("SELECT k FROM Kirjailija k").getResultList();
    }

    /**
     * Lisää tietokantaan kirjailijan.
     *
     * @param k
     */
    public void lisaaKirjailija(Kirjailija k) {
        EntityManager eeam = getEntityManager();
        eeam.getTransaction().begin();
        eeam.persist(k);
        eeam.getTransaction().commit();
    }

    /**
     * Hakee kirjailijat kirjan perusteella.
     *
     * @param kirja
     * @return
     */
    public List<Kirjailija> getKirjanKirjailijat(Kirja kirja) {
        EntityManager eeam = getEntityManager();
        return eeam.createQuery("SELECT k FROM Kirjailija k WHERE k.kirja.ISBN = '" + kirja.getISBN() + "'").getResultList();
    }
    /**
     * Hakee kirjailijat kirjan perusteella.
     *
     * @param isbn
     * @return
     */
    public List<Kirjailija> getKirjanKirjailijat(String isbn) {
        EntityManager eeam = getEntityManager();
        return eeam.createQuery("SELECT k FROM Kirjailija k WHERE k.kirja.ISBN = '" + isbn + "'").getResultList();
    }

    /**
     * Poistetaan kaikki ISBN:ään liittyvät kirjailijat.
     *
     * @param kirja
     */
    public void poistaKirjanISBNlla(String isbn) {
        EntityManager eeam = getEntityManager();

        eeam.getTransaction().begin();
        eeam.createQuery("DELETE FROM Kirjailija k WHERE k.kirja.ISBN = '" + isbn + "'").executeUpdate();
        eeam.getTransaction().commit();
    }
}
