package jaanin.projekti.BackendinLopputyo.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Asiakas {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	//@Size(min = 10, max = 10)
	private String henkilotunnus;

	//@NotBlank
	private String nimi;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "asiakas")
	@JsonIgnore // Siirretty tämä Categoryyn jotta saataisiin "oikea" data kirjasta jossa myös
	private List<Laina> lainat;

	
	public String getHenkilotunnus() {
		return henkilotunnus;
	}
	
	public void setHenkilotunnus(String henkilotunnus) {
		this.henkilotunnus = henkilotunnus;
	}
	
	
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

	public Asiakas(String henkilotunnus, String nimi) {
		super();
		this.henkilotunnus = henkilotunnus;
		this.nimi = nimi;
	}

	public List<Laina> getLainat() {
		return lainat;
	}

	@Override
	public String toString() {
		return "Asiakas [id=" + id + ", henkilotunnus=" + henkilotunnus + ", nimi=" + nimi + "]";
	}

	public void setLainat(List<Laina> lainat) {
		this.lainat = lainat;
	}



	public Asiakas() {

	}

}
