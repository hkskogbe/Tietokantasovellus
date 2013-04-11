package kirja;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Aihe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @JoinColumn
    @ManyToOne
    private Kirja kirja;
    
    @Column
    private String aihe;

    public Aihe() {
    }

    public Aihe(Kirja kirja, String aihe) {
        this.kirja = kirja;
        this.aihe = aihe;
    }

    public String getAihe() {
        return aihe;
    }

    public Kirja getKirja() {
        return kirja;
    }

    public void setAihe(String aihe) {
        this.aihe = aihe;
    }

    public void setKirja(Kirja kirja) {
        this.kirja = kirja;
    }
    
    
}
