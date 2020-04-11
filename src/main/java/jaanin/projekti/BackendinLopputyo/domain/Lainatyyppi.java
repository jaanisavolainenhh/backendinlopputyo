package jaanin.projekti.BackendinLopputyo.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Lainatyyppi {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotBlank
	private String name;
	
	@Positive
	private double korkoprosentti;
	
	@Positive
	private int maksuaika; //kk

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "lainatyyppi")
	@JsonIgnore
	private List<Laina> lainat;

	public Lainatyyppi(@NotBlank String name, @Positive double korkoprosentti, @Positive int maksuaika) {
		super();
		this.name = name;
		this.korkoprosentti = korkoprosentti;
		this.maksuaika = maksuaika;
	}

	public double getKorkoprosentti() {
		return korkoprosentti;
	}

	public void setKorkoprosentti(double korkoprosentti) {
		this.korkoprosentti = korkoprosentti;
	}

	public int getMaksuaika() {
		return maksuaika;
	}



	public void setMaksuaika(int maksuaika) {
		this.maksuaika = maksuaika;
	}


	public List<Laina> getLainat() {
		return lainat;
	}

	public void setLainat(List<Laina> lainat) {
		this.lainat = lainat;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	@Override
	public String toString() {
		return "Lainatyyppi [id=" + id + ", name=" + name + ", korkoprosentti=" + korkoprosentti + ", maksuaika="
				+ maksuaika + "]";
	}

	public Lainatyyppi() {

	}
}
