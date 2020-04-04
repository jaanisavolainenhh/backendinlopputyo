package jaanin.projekti.BackendinLopputyo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Laina {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Long id;
private String lainanOttaja;
//private String author;
//private int year;
//private String isbn;
private double lainanMaara;
@ManyToOne	
//@JsonIgnore //Siirretty tämä Categoryyn jotta saataisiin "oikea" data kirjasta jossa myös categoria.
@JoinColumn(name = "lainatyyppi")
private Lainatyyppi lainatyyppi;







public String getLainanOttaja() {
	return lainanOttaja;
}



public Laina(String lainanOttaja, double lainanMaara, Lainatyyppi lainatyyppi) {
	super();
	this.lainanOttaja = lainanOttaja;
	this.lainanMaara = lainanMaara;
	this.lainatyyppi = lainatyyppi;
}



public void setLainanOttaja(String lainanOttaja) {
	this.lainanOttaja = lainanOttaja;
}



public double getLainanMaara() {
	return lainanMaara;
}



public void setLainanMaara(double lainanMaara) {
	this.lainanMaara = lainanMaara;
}




public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}

public Lainatyyppi getLainatyyppi() {
	return lainatyyppi;
}



public void setLainatyyppi(Lainatyyppi lainatyyppi) {
	this.lainatyyppi = lainatyyppi;
}





public Laina() {
}
@Override
public String toString() {
	return "Laina [id=" + id + ", lainanOttaja=" + lainanOttaja + ", lainanMaara=" + lainanMaara + ", lainatyyppi="
			+ lainatyyppi + "]";
}
}
