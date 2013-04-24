/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package listat;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import kirja.Kirja;
import kirja.Lainaus;

/**
 * Käsittelee tietokannan lainaus-olioita.
 *
 * @author hkskogbe
 */
public class LainausLista {

    private EntityManagerFactory kirjatehdas;

    public LainausLista() {
        kirjatehdas = Persistence.createEntityManagerFactory("KirjastotietokantaPU");
    }

    public EntityManager getEntityManager() {
        return kirjatehdas.createEntityManager();
    }

    /**
     * Lisää tietokantaan lainaus.
     *
     * @param laina
     */
    public void lisaaLainaus(Lainaus laina) {
        EntityManager eeam = getEntityManager();
        eeam.getTransaction().begin();
        eeam.persist(laina);
        eeam.getTransaction().commit();
    }

    /**
     * Kaikki tietokannan lainaukset.
     *
     * @return
     */
    public List<Lainaus> getLainaukset() {
        EntityManager eeam = getEntityManager();
        return eeam.createQuery("SELECT k FROM Lainaus k").getResultList();
    }

    /**
     * Kaikki parametrina annetuna lainaajan lainaukset.
     *
     * @param lainaaja
     * @return
     */
    public List<Lainaus> getLainaukset(String lainaaja) {
        EntityManager eeam = getEntityManager();
        return eeam.createQuery("SELECT k FROM Lainaus k WHERE k.lainaaja = '" + lainaaja + "'").getResultList();
    }

    /**
     * Hae teokseen liittyvät lainaukset.
     *
     * @param kirja
     * @return
     */
    public List<Lainaus> getLainaukset(Kirja kirja) {
        EntityManager eeam = getEntityManager();
        return eeam.createQuery("SELECT k FROM Lainaus k WHERE k.kirja = '" + kirja + "'").getResultList();
    }
}
