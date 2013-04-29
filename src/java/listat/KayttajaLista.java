package listat;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import kayttaja.Kayttaja;

/**
 * Käsittelee tietokannan Käyttäjä-olioita.
 *
 * @author hese
 */
public class KayttajaLista {

    private EntityManagerFactory tehdas = null;

    public KayttajaLista() {
        tehdas = Persistence.createEntityManagerFactory("KirjastotietokantaPU");
    }

    public EntityManager getEntityManager() {
        return tehdas.createEntityManager();
    }
/**
 * Lisää käyttäjän tietokantaan.
 * @param k 
 */
    public void lisaaKayttaja(Kayttaja k) {
        EntityManager eeam = getEntityManager();
        eeam.getTransaction().begin();
        eeam.persist(k);
        eeam.getTransaction().commit();
    }
/**
 * Hae käyttäjä tunnuksella.
 * @param tunnus
 * @return 
 */
    public Kayttaja getKayttaja(String tunnus) {
        EntityManager eeam = getEntityManager();
        return (Kayttaja) eeam.createQuery(
                "SELECT k FROM Kayttaja k WHERE k.nimi = '" + tunnus + "'").getSingleResult();
    }
/**
 * Hae kaikki käyttäjät.
 * @return 
 */
    public List<Kayttaja> getKayttajat() {
        EntityManager eeam = getEntityManager();
        return eeam.createQuery("SELECT k FROM Kayttaja k").getResultList();

    }
    /**
     * Poista käyttäjä. Käyttäjä identifioidaan tunnuksella.
     * @param tunnus 
     */
    public void poistaKayttaja(String tunnus) {
        EntityManager eeam = getEntityManager();

        eeam.getTransaction().begin();
        eeam.createQuery("DELETE FROM Kayttaja k WHERE k.nimi = '" + tunnus + "'").executeUpdate();
        eeam.getTransaction().commit();
    }
}
