package jaanin.projekti.BackendinLopputyo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Laina {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Long id;

@NotNull
@ManyToOne	
//@JsonIgnore //Siirretty tämä Categoryyn jotta saataisiin "oikea" data kirjasta jossa myös categoria.
@JoinColumn(name = "asiakas")
private Asiakas asiakas;


@ManyToOne	
@JoinColumn(name = "lainatyyppi")
private Lainatyyppi lainatyyppi;

@Positive
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


public Laina(@NotNull Asiakas asiakas, Lainatyyppi lainatyyppi, @Positive double lainanMaara) {
	super();
	this.asiakas = asiakas;
	this.lainatyyppi = lainatyyppi;
	this.lainanMaara = lainanMaara;
}

@Override
public String toString() {
	return "Laina [id=" + id + ", asiakas=" + asiakas + ", lainatyyppi=" + lainatyyppi + ", lainanMaara=" + lainanMaara
			+ "]";
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
