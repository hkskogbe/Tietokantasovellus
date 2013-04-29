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
 * Lainoja käsittelevä olio.
 *
 * @author hkskogbe
 */
@Entity
public class Lainattava implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @JoinColumn
    @ManyToOne
    private Kirja kirja;
    @Column
    private String lainaaja;
    @Column
    private String lainauspvm;
    @Column
    private String lainassaMihinAsti;
    @Column
    private boolean lainassa;

    public Lainattava() {
    }

    public Lainattava(Kirja kirja) {
        this.kirja = kirja;
        this.lainaaja = " ";
        this.lainauspvm = " ";
        this.lainassaMihinAsti = " ";
        this.lainassa = false;
    }

    public void lainaa(String lainaaja, String lainauspvm, String lainassaMihinAsti) {
        this.lainaaja = lainaaja;
        this.lainauspvm = lainauspvm;
        this.lainassaMihinAsti = lainassaMihinAsti;
        this.lainassa = true;
    }

    public void palauta() {
        this.lainaaja = " ";
        this.lainauspvm = " ";
        this.lainassaMihinAsti = " ";
        this.lainassa=false;
    }
    
    public void setLainassa(boolean lainassa) {
        this.lainassa = lainassa;
    }

    public boolean isLainassa() {
        return lainassa;
    }

    public void setLainauspvm(String lainauspvm) {
        this.lainauspvm = lainauspvm;
    }

    public void setLainassaMihinAsti(String lainassaMihinAsti) {
        this.lainassaMihinAsti = lainassaMihinAsti;
    }

    public void setLainaaja(String lainaaja) {
        this.lainaaja = lainaaja;
    }

    public void setKirja(Kirja kirja) {
        this.kirja = kirja;
    }

    public String getLainauspvm() {
        return lainauspvm;
    }

    public String getLainassaMihinAsti() {
        return lainassaMihinAsti;
    }

    public String getLainaaja() {
        return lainaaja;
    }

    public Kirja getKirja() {
        return kirja;
    }

    public long getId() {
        return id;
    }
}
