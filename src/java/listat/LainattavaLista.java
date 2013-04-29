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
import kirja.Lainattava;

/**
 * Käsittelee tietokannan lainaus-olioita.
 *
 * @author hkskogbe
 */
public class LainattavaLista {

    private EntityManagerFactory kirjatehdas;

    public LainattavaLista() {
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
    public void lisaaLainaus(Lainattava laina) {
        EntityManager eeam = getEntityManager();
        eeam.getTransaction().begin();
        eeam.persist(laina);
        eeam.getTransaction().commit();
    }

    /**
     * Palauta lainaus.
     *
     * @param isbn
     * @param lainaaja
     */
    public void palautaLainaus(Lainattava laina) {
        EntityManager eeam = getEntityManager();
        eeam.getTransaction().begin();
        eeam.find(Lainattava.class, laina.getId()).palauta();
        eeam.getTransaction().commit();
    }

    /**
     * Poista Lainattava tietkannasta.
     *
     * @param laina
     */
    public void poistaLainattava(Lainattava laina) {
        EntityManager eeam = getEntityManager();
        eeam.getTransaction().begin();
        eeam.remove(eeam.find(Lainattava.class, laina.getId()));
        eeam.getTransaction().commit();
    }

    /**
     * Lainaa yksittäinen teos.
     *
     * @param laina
     * @param lainaaja
     * @param lainauspvm
     * @param mihinasti
     */
    public void lainaaLainaus(Lainattava laina, String lainaaja, String lainauspvm, String mihinasti) {
        EntityManager eeam = getEntityManager();
        eeam.getTransaction().begin();
        eeam.find(Lainattava.class, laina.getId()).lainaa(lainaaja, lainauspvm, mihinasti);
        eeam.getTransaction().commit();
    }

    /**
     * Kaikki tietokannan lainaukset.
     *
     * @return
     */
    public List<Lainattava> getLainaukset() {
        EntityManager eeam = getEntityManager();
        return eeam.createQuery("SELECT k FROM Lainattava k").getResultList();
    }

    /**
     * Kaikki parametrina annetuna lainaajan lainaukset.
     *
     * @param lainaaja
     * @return
     */
    public List<Lainattava> getLainaukset(String lainaaja) {
        EntityManager eeam = getEntityManager();
        return eeam.createQuery("SELECT k FROM Lainattava k WHERE k.lainaaja = '" + lainaaja + "'").getResultList();
    }

    /**
     * Hae teokseen liittyvät lainaukset.
     *
     * @param kirja
     * @return
     */
    public List<Lainattava> getLainaukset(Kirja kirja) {
        EntityManager eeam = getEntityManager();
        return eeam.createQuery("SELECT k FROM Lainattava k WHERE k.kirja = '" + kirja + "'").getResultList();
    }

    /**
     * Hae teokseen liittyvät lainaukset.
     *
     * @param kirja
     * @return
     */
    public List<Lainattava> getLainauksetNimella(String nimi) {
        EntityManager eeam = getEntityManager();
        return eeam.createQuery("SELECT k FROM Lainattava k WHERE k.kirja.nimi LIKE '%" + nimi + "%'").getResultList();
    }

    /**
     * Hae yksittäinen lainaamaton teos ISBNllä.
     *
     * @param isbn
     * @return
     */
    public List<Lainattava> getLainaamatonLainausISBNllä(String isbn) {
        EntityManager eeam = getEntityManager();
        return eeam.createQuery("SELECT l FROM Lainattava l, Kirja k WHERE l.lainassa = '" + false
                + "' AND l.kirja = k AND k.ISBN = '" + isbn + "'").getResultList();
    }

    /**
     * Hae teokseen liittyvät lainaukset.
     *
     * @param isbn
     * @return
     */
    public List<Lainattava> getLainauksetISBNlla(String isbn) {
        EntityManager eeam = getEntityManager();
        return eeam.createQuery("SELECT k FROM Lainattava k WHERE k.kirja.ISBN = '" + isbn + "'").getResultList();
    }
}
