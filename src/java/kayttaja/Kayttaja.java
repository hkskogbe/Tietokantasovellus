package kayttaja;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Kayttaja implements Serializable {

    @Id
    private String nimi;
    @Column
    private byte[] salasana;
    @Column
    private KayttajaTyyppi tyyppi;

    public Kayttaja() {
    }

    public Kayttaja(String nimi, byte[] salasana, KayttajaTyyppi tyyppi) {
        this.nimi = nimi;
        this.salasana = salasana;
        this.tyyppi = tyyppi;
    }

    public byte[] getSalasana() {
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

    public void setSalasana(byte[] salasana) {
        this.salasana = salasana;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
}
