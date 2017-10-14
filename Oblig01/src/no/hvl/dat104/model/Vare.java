package no.hvl.dat104.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/*@NamedQueries({
        @NamedQuery(name = "Vare.visAlle", query = "SELECT v FROM Vare v"),
        @NamedQuery(name = "Vare.visKurv", query = "SELECT v.navn FROM Vare v where v.kurv.kurv_id=:id")
})//SELECT v.navn FROM vare v where v.kurv_id =2*/

@Entity
@Table(name = "vare", schema = "handleliste")
public class Vare {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer vare_id;

    @Column(name = "navn")
    private String navn;

    @ManyToOne
    @JoinColumn(name = "kurv_id")
    private Kurv kurv;

    public Vare() {
        this("", null);
    }

    public Vare(String navn, Kurv k) {
        this.navn = navn;
        this.kurv = k;
    }

    public Integer getVare_id() {
        return vare_id;
    }

    public void setVare_id(Integer vare_id) {
        this.vare_id = vare_id;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public Kurv getKurv() {
        return kurv;
    }

    public void setKurv(Kurv kurv) {
        this.kurv = kurv;
    }
}
