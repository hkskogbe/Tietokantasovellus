package kirja;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Tietokannan olennaisin tietosisältö sisältyy Kirja-olioihin. Kirjat pitävät
 * sisällään teoksen ISBN:n, nimen, julkaisuvuoden sekä tiedon siitä, onko teos
 * poistettu tietokannasta.
 *
 * @author hkskogbe
 */
@Entity
public class Kirja implements Serializable {

    @Id
    private String ISBN;
    @Column
    private String nimi;
    @Column
    private int julkaisuvuosi;
    @Column
    private boolean poistettu;

    public Kirja() {
    }

    public Kirja(String ISBN, String nimi, int julkaisuvuosi) {
        this.ISBN = ISBN;
        this.nimi = nimi;
        this.julkaisuvuosi = julkaisuvuosi;
        this.poistettu = false;
    }

    public int getJulkaisuvuosi() {
        return julkaisuvuosi;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getNimi() {
        return nimi;
    }

    public boolean isPoistettu() {
        return poistettu;
    }

    public void poista() {
        this.poistettu = true;
    }

    public void setPoistettu(boolean poistettu) {
        this.poistettu = poistettu;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setJulkaisuvuosi(int julkaisuvuosi) {
        this.julkaisuvuosi = julkaisuvuosi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
}
