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
private String title;
private String author;
private int year;
private String isbn;
private double price;

@ManyToOne	
//@JsonIgnore //Siirretty tämä Categoryyn jotta saataisiin "oikea" data kirjasta jossa myös categoria.
@JoinColumn(name = "lainatyyppi")
private Lainatyyppi lainatyyppi;


public Laina(String title, String author, int year, String isbn, double price, Lainatyyppi lainatyyppi) {
	super();
	this.title = title;
	this.author = author;
	this.year = year;
	this.isbn = isbn;
	this.price = price;
	this.lainatyyppi = lainatyyppi;
}



public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public Lainatyyppi getLainatyyppi() {
	return lainatyyppi;
}



public void setLainatyyppi(Lainatyyppi lainatyyppi) {
	this.lainatyyppi = lainatyyppi;
}



public String getAuthor() {
	return author;
}
public void setAuthor(String author) {
	this.author = author;
}
public int getYear() {
	return year;
}
public void setYear(int year) {
	this.year = year;
}
public String getIsbn() {
	return isbn;
}
public void setIsbn(String isbn) {
	this.isbn = isbn;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}

public Laina() {
}
@Override
public String toString() {
	return "Laina [id=" + id + ", title=" + title + ", author=" + author + ", year=" + year + ", isbn=" + isbn
			+ ", price=" + price + "]";
}
}
