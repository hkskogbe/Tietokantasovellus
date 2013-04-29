package listat;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import kirja.Kirja;
import kirja.Kirjailija;

/**
 * Käsittelee tietokannan Kirja-olioita.
 *
 * @author hkskogbe
 */
public class KirjaLista {

    private EntityManagerFactory kirjatehdas = null;

    public KirjaLista() {
        kirjatehdas = Persistence.createEntityManagerFactory("KirjastotietokantaPU");
    }

    public EntityManager getEntityManager() {
        return kirjatehdas.createEntityManager();
    }

    /**
     * Hae kaikki teokset.
     *
     * @return
     */
    public List<Kirja> getKirjat() {
        EntityManager eeam = getEntityManager();
        return eeam.createQuery("SELECT k FROM Kirja k").getResultList();
    }

    /**
     * Lisää teos tietokantaan.
     *
     * @param k
     */
    public void lisaaKirja(Kirja k) {
        EntityManager eeam = getEntityManager();
        eeam.getTransaction().begin();
        eeam.persist(k);
        eeam.getTransaction().commit();
    }

    /**
     * Poistetaan teos tietokannasta.
     *
     * @param ISBN
     */
    public void poistaKirja(String isbn) {
        EntityManager eeam = getEntityManager();
        eeam.getTransaction().begin();
        eeam.createQuery("DELETE FROM Kirja k WHERE k.ISBN = '" + isbn + "'").executeUpdate();
        eeam.getTransaction().commit();
    }

    /**
     * Hae ISBN:llä. Palauttaa yksittäisen teoksen.
     *
     * @param hakuISBN
     * @return
     */
    public Kirja getKirjaISBNlla(String hakuISBN) {
        EntityManager eeam = getEntityManager();
        String query = "SELECT k FROM Kirja k WHERE k.ISBN = '" + hakuISBN + "'";
        return (Kirja) eeam.createQuery(query).getSingleResult();
    }

    /**
     * Hae kysely yksittäisen teokselle ISBN:llä.
     *
     * @param hakuISBN
     * @return
     */
    public String getKirjaISBN(String hakuISBN) {
        return "SELECT k FROM Kirja k WHERE k.ISBN = '" + hakuISBN + "'";
    }

    /**
     * Hae usealla hakuparametrilla. Vain yksittäiset hakuehdot (ei
     * listamuotoista hakua kirjailija/aihe).
     *
     * @param teos
     * @param kirjailija
     * @param aihe
     * @param avuosi
     * @param lvuosi
     * @return
     */
    public List<Kirja> getKirja(String teos, String kirjailija, String aihe, String avuosi, String lvuosi) {

        EntityManager eeam = getEntityManager();


        String query = "SELECT DISTINCT k FROM Kirja k, Kirjailija q, Aihe a WHERE ";

        if (!teos.isEmpty()) {
            query += "UPPER(k.nimi) LIKE UPPER('%" + teos + "%') AND ";
        }

        if (!avuosi.isEmpty()) {
            int av = Integer.parseInt(avuosi);
            query += "k.julkaisuvuosi >= " + av + " AND ";
        }

        if (!lvuosi.isEmpty()) {
            int lv = Integer.parseInt(lvuosi);
            query += "k.julkaisuvuosi <= " + lv + " AND ";
        }

        if (!kirjailija.isEmpty()) {
            query += "UPPER(q.kirjailijanNimi) LIKE UPPER('%" + kirjailija + "%') AND q.kirja = k AND ";
        }

        if (!aihe.isEmpty()) {
            query += "UPPER(a.aihe) LIKE UPPER('%" + aihe + "%') AND a.kirja = k AND ";
        }


        query = query.substring(0, query.length() - 4).trim();

        return eeam.createQuery(query).getResultList();
    }

    /**
     * Luo kyselyn kirjojen hakemiseksi aiheen perusteella.
     *
     * @param nimi
     * @param avuosi
     * @param lvuosi
     * @return
     */
    private String kirjaVuosiKysely(String nimi, String avuosi, String lvuosi) {
        if (nimi.isEmpty() && avuosi.isEmpty() && lvuosi.isEmpty()) {
            return "";
        }
        String query = "SELECT DISTINCT k FROM Kirja k WHERE ";

        if (!nimi.isEmpty()) {
            query += "UPPER(k.nimi) LIKE UPPER('%" + nimi + "%') AND ";
        }

        if (!avuosi.isEmpty()) {
            int a = Integer.parseInt(avuosi);
            query += "k.julkaisuvuosi >= " + a + " AND ";
        }

        if (!lvuosi.isEmpty()) {
            int l = Integer.parseInt(lvuosi);
            query += "k.julkaisuvuosi <= " + l + " AND ";
        }

        query = query.substring(0, query.length() - 4).trim();

        return query;
    }

    /**
     * Hae usealla hakuparametrilla.
     *
     * @param nimi
     * @param kirjailijat
     * @param aiheet
     * @param avuosi
     * @param lvuosi
     * @return
     */
    public List<Kirja> getKirja(String nimi, List<String> kirjailijat, List<String> aiheet, String avuosi, String lvuosi) {
        EntityManager eeam = getEntityManager();
        String query = "SELECT k FROM Kirja k, Aihe a, Kirjailija q WHERE ";

        query = kirjaVuosiKysely(nimi, avuosi, lvuosi);



        return eeam.createQuery(query).getResultList();
    }

    /**
     * Liittää kaksi stringiä INNER JOINilla, stringien ympärille sulut.
     *
     * @param eka
     * @param toka
     * @return
     */
    private String join(String eka, String toka) {
        if (eka.isEmpty() && toka.isEmpty()) {
            return "";
        }
        if (eka.isEmpty()) {
            return toka;
        }
        if (toka.isEmpty()) {
            return eka;
        }

        return eka + " INNER JOIN " + toka;
//        return "(" + eka + ") INNER JOIN (" + toka + ")";
    }

    /**
     * Kirjailijan teokset.
     *
     * @param kirjailija
     * @return
     */
    public List<Kirja> getKirja(Kirjailija kirjailija) {
        EntityManager eeam = getEntityManager();
        return eeam.createQuery("SELECT k FROM Kirjailija k WHERE k.id = " + kirjailija.getId()).getResultList();
    }

    /**
     * Kirjailijan teokset string-muotoisella parametrilla.
     *
     * @param kirjailija
     * @return
     */
    public List<Kirja> getKirjaKirjailijanNimella(String kirjailija) {
        EntityManager eeam = getEntityManager();
        String query = kirjailijaKysely(kirjailija);
        return eeam.createQuery(query).getResultList();
    }

    /**
     * Aiheeseen liittyvät teokset string-muotoisella parametrilla.
     *
     * @param aihe
     * @return
     */
    public List<Kirja> getKirjaAiheella(String aihe) {
        EntityManager eeam = getEntityManager();
        String query = aiheKysely(aihe);
        return eeam.createQuery(query).getResultList();
    }

    /**
     * Teos nimen perusteella.
     *
     * @param nimi
     * @return
     */
    public List<Kirja> getKirjaNimella(String nimi) {
        EntityManager eeam = getEntityManager();

        String query;

////        Jos ISBN hakutuloksia
//        String query = "SELECT k FROM Kirja k WHERE k.ISBN = '" + nimi + "' ";

        // Hakutulokset nimen pohjalta
        query = "SELECT k FROM Kirja k WHERE UPPER(k.nimi) like UPPER('%" + nimi + "%')";

        return eeam.createQuery(query).getResultList();
    }

    /**
     * Luo kyselyn kirjojen hakemiseksi aiheen perusteella.
     *
     * @param aihe
     * @return
     */
    private String aiheKysely(String aihe) {
        if (aihe == null) {
            return "";
        }
        if (aihe.isEmpty()) {
            return "";
        }
        return "SELECT k FROM Kirja k, Aihe q WHERE UPPER(q.aihe) = UPPER('%" + aihe + "%') AND q.kirja = k";
    }
/**
 * Lisää tai poistaa kirjalle poistomerkinnän.
 * @param poistettava kirja
 * @param arvo 
 */
    public void poistoMerkinta(String poistettavaISBN, boolean arvo) {
        EntityManager eeam = getEntityManager();
        eeam.getTransaction().begin();
        eeam.find(Kirja.class, poistettavaISBN).setPoistettu(arvo);
        eeam.getTransaction().commit();
    }
    
    /**
     * Luo kyselyn kirjojen hakemiseksi aiheen perusteella.
     *
     * @param nimi
     * @return
     */
    private String kirjailijaKysely(String nimi) {
        if (nimi == null) {
            return "";
        }
        if (nimi.isEmpty()) {
            return "";
        }
        return "SELECT k FROM Kirja k, Kirjailija q WHERE UPPER(q.kirjailijanNimi) LIKE UPPER('%" + nimi + "%') AND q.kirja = k";
    }

    /**
     * Parametrina saatavana vuotena julkaistut teokset.
     *
     * @param vuosi
     * @return
     */
    public List<Kirja> getKirjat(int vuosi) {
        EntityManager eeam = getEntityManager();
        return eeam.createQuery("SELECT k FROM Kirja k WHERE k.julkaisuvuosi = " + vuosi).getResultList();
    }

    /**
     * Jollakin valilla (vuosissa) julkaistut teokset. Parametreina annetut
     * vuodet sisältyvät hakutuloksiin.
     *
     * @param ensimmainenVuosi
     * @param jalkimmainenVuosi
     * @return
     */
    public List<Kirja> getKirjatValilta(int ensimmainenVuosi, int jalkimmainenVuosi) {
        EntityManager eeam = getEntityManager();
        return eeam.createQuery("SELECT k FROM Kirja k WHERE k.julkaisuvuosi >= " + ensimmainenVuosi + " AND k.julkaisuvuosi <= " + jalkimmainenVuosi).getResultList();
    }
}
