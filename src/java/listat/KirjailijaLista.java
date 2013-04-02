
package listat;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import kirja.Kirjailija;


public class KirjailijaLista {
    private EntityManagerFactory kirjailijat = null;

    public KirjailijaLista() {
         kirjailijat = Persistence.createEntityManagerFactory("KirjastotietokantaPU");
    }

    public EntityManager getEntityManager() {
        return kirjailijat.createEntityManager();
    }

    public List<Kirjailija> getKirjailijat() {
        EntityManager eeam = this.getEntityManager();
        return eeam.createQuery("SELECT DISTINCT k FROM Kirjailija k").getResultList();
    }

    public void lisaaKirjailija(Kirjailija k) {
        EntityManager eeam = getEntityManager();
        eeam.getTransaction().begin();
        eeam.persist(k);
        eeam.getTransaction().commit();
    }
    
    
}
