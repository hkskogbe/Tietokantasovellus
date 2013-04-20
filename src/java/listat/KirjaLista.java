package listat;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import kirja.Aihe;
import kirja.Kirja;
import kirja.Kirjailija;

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
        return eeam.createQuery("SELECT k FROM Kirja k WHERE k.ISBN = '" + hakuISBN + "'").getResultList();
    }

    public List<Kirja> getKirja(String nimi, List<String> kirjailijat, List<String> aiheet, String avuosi, String lvuosi) {
        EntityManager eeam = getEntityManager();
        String query = "";
        if (!nimi.isEmpty() || !avuosi.isEmpty() || !lvuosi.isEmpty()) {
            query = kirjaVuosiKysely(nimi, avuosi, lvuosi);
        }

        if (!kirjailijat.isEmpty()) {
            String kirjailijakysely = "";
//            if (kirjailijat.size() == 1) {
//                kirjailijakysely += kirjailijaKysely(kirjailijat.get(0));
//            } else {
            for (String kir : kirjailijat) {
                query = join(query, kirjailijaKysely(kir));
            }
//            }
        }


        if (!aiheet.isEmpty()) {
            String aihekysely = "";
            for (String aihe : aiheet) {
                query = join(query, aiheKysely(aihe));
            }

        }


        return eeam.createQuery(query).getResultList();
    }

    private String join(String eka, String toka) {
        if (eka.isEmpty()) {
            return toka;
        }
        return "(" + eka + ") INNER JOIN (" + toka + ")";
    }

    public List<Kirja> getKirja(Kirjailija kirjailija) {
        EntityManager eeam = getEntityManager();
        return eeam.createQuery("SELECT k FROM Kirjailija k WHERE k.id = " + kirjailija.getId()).getResultList();
    }

    public List<Kirja> getKirjaNimella(String nimi) {
        EntityManager eeam = getEntityManager();
        
        //Jos ISBN hakutulokssia
        String query = "(SELECT k FROM Kirja k WHERE k.ISBN = '" + nimi + "') ";
        
        // Hakutulokset nimen pohjalta
        query += "FULL OUTER JOIN (SELECT k FROM Kirja k WHERE UPPER(k.nimi) like UPPER('%" + nimi + "%') )";
        
        return eeam.createQuery(query).getResultList();
    }

    private String aiheKysely(String aihe) {
        return "SELECT k FROM Kirja k, Aihe q WHERE UPPER(q.aihe) = UPPER('%" + aihe + "%') AND q.kirja = k";
    }

    private String kirjailijaKysely(String nimi) {
        return "SELECT k FROM Kirja k, Kirjailija q WHERE UPPER(q.kirjailijanNimi) LIKE UPPER('%" + nimi + "%') AND q.kirja = k";
    }

    private String kirjaVuosiKysely(String nimi, String avuosi, String lvuosi) {
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

    public List<Kirja> getKirjat(int vuosi) {
        EntityManager eeam = getEntityManager();
        return eeam.createQuery("SELECT k FROM Kirja k WHERE k.julkaisuvuosi = " + vuosi).getResultList();
    }

    public List<Kirja> getKirjatValilta(int ensimmainenVuosi, int jalkimmainenVuosi) {
        EntityManager eeam = getEntityManager();
        return eeam.createQuery("SELECT k FROM Kirja k WHERE k.julkaisuvuosi >= " + ensimmainenVuosi + " AND k.julkaisuvuosi <= " + jalkimmainenVuosi).getResultList();
    }
}
