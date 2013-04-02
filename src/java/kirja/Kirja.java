
package kirja;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Kirja implements Serializable {
    @Id
    private String ISBN;

    @Column
    private String nimi;
    
    @Column
    private int julkaisuvuosi;
    
    public Kirja() {
    }

    public Kirja(String ISBN, String nimi, int julkaisuvuosi) {
        this.ISBN = ISBN;
        this.nimi = nimi;
        this.julkaisuvuosi = julkaisuvuosi;
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
    
}
