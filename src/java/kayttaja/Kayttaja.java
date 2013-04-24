package kayttaja;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Kayttaja-oliot pitävät sisällään tietokantaan kirjautumiseen oikeutettujen
 * tunnusten nimet sekä salasanat. Käyttäjillä on myös tyyppi.
 *
 * @author hkskogbe
 */
@Entity
public class Kayttaja implements Serializable {

    @Id
    private String nimi;
    @Column
    private String salasana;
    @Column
    private KayttajaTyyppi tyyppi;

    public Kayttaja() {
    }

    public Kayttaja(String nimi, String salasana, KayttajaTyyppi tyyppi) {
        this.nimi = nimi;
        this.salasana = salasana;
        this.tyyppi = tyyppi;
    }

    public String getSalasana() {
        return salasana;
    }

    public String getNimi() {
        return nimi;
    }

    public KayttajaTyyppi getTyyppi() {
        return tyyppi;
    }

    public void setTyyppi(KayttajaTyyppi tyyppi) {
        this.tyyppi = tyyppi;
    }

    public void setSalasana(String salasana) {
        this.salasana = salasana;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
}
