package kirja;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Kirjailija-oliot liittyvät kirjoihin. Kirjalla voi olla useampi kirjailija.
 * Yksi kirjailija liittyy vain yhteen kirjaan. Kirjailijalla on nimi, joka
 * usein talletetaan muodossa "sukunimi, etunimi".
 *
 * @author hkskogbe
 */
@Entity
public class Kirjailija implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @JoinColumn
    @ManyToOne(cascade = CascadeType.ALL)
    private Kirja kirja;
    @Column
    private String kirjailijanNimi;

    public Kirjailija() {
    }

    public Kirjailija(Kirja kirja, String kirjailijanNimi) {
        this.kirja = kirja;
        this.kirjailijanNimi = kirjailijanNimi;
    }

    public Kirja getKirja() {
        return kirja;
    }

    public String getKirjailijanNimi() {
        return kirjailijanNimi;
    }

    public void setKirja(Kirja kirja) {
        this.kirja = kirja;
    }

    public void setKirjailijanNimi(String kirjailijanNimi) {
        this.kirjailijanNimi = kirjailijanNimi;
    }

    public long getId() {
        return id;
    }
}
