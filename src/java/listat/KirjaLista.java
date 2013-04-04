
package listat;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import kirja.Kirja;

public class KirjaLista {

    private EntityManagerFactory kirjatehdas = null;

    public KirjaLista() {
        kirjatehdas = Persistence.createEntityManagerFactory("KirjastotietokantaPU");
    }

    public EntityManager getEntityManager() {
        return kirjatehdas.createEntityManager();
    }

    public List<Kirja> getKirjat() {
        EntityManager eeam = getEntityManager();
        return eeam.createQuery("SELECT k FROM Kirja k").getResultList();
    }

    public void lisaaKirja(Kirja k) {
        EntityManager eeam = getEntityManager();
        eeam.getTransaction().begin();
        eeam.persist(k);
        eeam.getTransaction().commit();
    }

    public List<Kirja> getKirja(String hakuISBN) {
        EntityManager eeam = getEntityManager();
        return eeam.createQuery("SELECT k FROM Kirja k WHERE k.ISBN = '" + hakuISBN +"'").getResultList();
    }

    public List<Kirja> getKirjat(int vuosi) {
        EntityManager eeam = getEntityManager();
        return eeam.createQuery("SELECT k FROM Kirja k WHERE k.julkaisuvuosi = " + vuosi).getResultList();
    }

    public List<Kirja> getKirjatEnnenVuotta(int vuosi) {
        EntityManager eeam = getEntityManager();
        return eeam.createQuery("SELECT k FROM Kirja k WHERE k.julkaisuvuosi < " + vuosi).getResultList();
    }

    public List<Kirja> getKirjatVuodenJalkeen(int vuosi) {
        EntityManager eeam = getEntityManager();
        return eeam.createQuery("SELECT k FROM Kirja k WHERE k.julkaisuvuosi > " + vuosi).getResultList();
    }

    public List<Kirja> getKirjatValilta(int ensimmainenVuosi, int jalkimmainenVuosi) {
        EntityManager eeam = getEntityManager();
        return eeam.createQuery("SELECT k FROM Kirja k WHERE k.julkaisuvuosi >= " + ensimmainenVuosi + " AND k.julkaisuvuosi <= " + jalkimmainenVuosi).getResultList();
    }
}
