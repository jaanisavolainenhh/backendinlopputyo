package jaanin.projekti.BackendinLopputyo.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Asiakas {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	

	//@NotBlank
	private String nimi;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "asiakas")
	@JsonIgnore // Siirretty tämä Categoryyn jotta saataisiin "oikea" data kirjasta jossa myös
	private List<Laina> lainat;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNimi() {
		return nimi;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	public List<Laina> getLainat() {
		return lainat;
	}

	@Override
	public String toString() {
		return "Asiakas [nimi=" + nimi + "]";
	}

	public void setLainat(List<Laina> lainat) {
		this.lainat = lainat;
	}

	public Asiakas(String nimi) {
		super();
		this.nimi = nimi;

	}

	public Asiakas() {

	}

}
