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
    public void poistaKirja(Kirja poistettava) {
        EntityManager eeam = getEntityManager();

        eeam.getTransaction().begin();
//        eeam.merge(poistettava);
        eeam.remove(poistettava);
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
     * Hae usealla hakuparametrilla.
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
        String query;

        query = kirjaVuosiKysely(teos, avuosi, lvuosi);
        
        query = join(query, kirjailijaKysely(kirjailija));
        query = join(query, aiheKysely(aihe));



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
        String query;

        query = kirjaVuosiKysely(nimi, avuosi, lvuosi);

        if (!kirjailijat.isEmpty()) {

            String kirjailijakysely = "";

            for (String kir : kirjailijat) {
                query = join(query, kirjailijaKysely(kir));
            }
        }


        if (!aiheet.isEmpty()) {
            String aihekysely = "";
            for (String aihe : aiheet) {
                query = join(query, aiheKysely(aihe));
            }

        }


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
        if (aihe==null) {
            return "";
        }
        if (aihe.isEmpty()) {
            return "";
        }
        return "SELECT k FROM Kirja k, Aihe q WHERE UPPER(q.aihe) = UPPER('%" + aihe + "%') AND q.kirja = k";
    }

    /**
     * Luo kyselyn kirjojen hakemiseksi aiheen perusteella.
     *
     * @param nimi
     * @return
     */
    private String kirjailijaKysely(String nimi) {
        if (nimi==null) {
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
