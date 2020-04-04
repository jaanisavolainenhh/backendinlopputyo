package jaanin.projekti.BackendinLopputyo.domain;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Lainatyyppi {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
	@JsonIgnore
	private List<Laina> lainat;



	public List<Laina> getBooks() {
		return lainat;
	}
	public void setBooks(List<Laina> books) {
		this.lainat = books;
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
	public Lainatyyppi(String name) {
		super();
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Lainatyyppi [name=" + name + "]";
	}
	public Lainatyyppi()
	{
		
	}
}
