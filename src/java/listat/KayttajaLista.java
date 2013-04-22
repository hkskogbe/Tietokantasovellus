package listat;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import kayttaja.Kayttaja;

public class KayttajaLista {

    private EntityManagerFactory tehdas = null;

    public KayttajaLista() {
        tehdas = Persistence.createEntityManagerFactory("KirjastotietokantaPU");
    }

    public EntityManager getEntityManager() {
        return tehdas.createEntityManager();
    }

    public void lisaaKayttaja(Kayttaja k) {
        EntityManager eeam = getEntityManager();
        eeam.getTransaction().begin();
        eeam.persist(k);
        eeam.getTransaction().commit();
    }

    public Kayttaja getKayttaja(String tunnus) {
        EntityManager eeam = getEntityManager();
        return (Kayttaja) eeam.createQuery(
                "SELECT k FROM Kayttaja k WHERE k.tunnus = '" + tunnus + "'").getSingleResult();
    }

    public List<Kayttaja> getKayttajat() {
        EntityManager eeam = getEntityManager();
        return eeam.createQuery("SELECT k FROM Kayttaja k").getResultList();

    }
}
