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
@ManyToOne	
//@JsonIgnore //Siirretty tämä Categoryyn jotta saataisiin "oikea" data kirjasta jossa myös categoria.
@JoinColumn(name = "asiakas")
private Asiakas asiakas;


@ManyToOne	
@JoinColumn(name = "lainatyyppi")
private Lainatyyppi lainatyyppi;

private double lainanMaara;


public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public Asiakas getAsiakas() {
	return asiakas;
}
public void setAsiakas(Asiakas asiakas) {
	this.asiakas = asiakas;
}
public double getLainanMaara() {
	return lainanMaara;
}
public void setLainanMaara(double lainanMaara) {
	this.lainanMaara = lainanMaara;
}
public Laina(Asiakas asiakas, double lainanMaara, Lainatyyppi lainatyyppi) {
	super();
	this.asiakas = asiakas;
	this.lainanMaara = lainanMaara;
	this.lainatyyppi = lainatyyppi;
}
public Lainatyyppi getLainatyyppi() {
	return lainatyyppi;
}
public void setLainatyyppi(Lainatyyppi lainatyyppi) {
	this.lainatyyppi = lainatyyppi;
}

public Laina()
{
	
}
}
