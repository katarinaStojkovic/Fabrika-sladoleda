package Entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "radnik", catalog = "fabrika_sladoleda")
public class Radnik implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "Radnik_Id", unique = true, nullable = false)
	private int radnikId;
	@Column(name = "Ime", nullable = false, length = 20)
	private String ime;
	@Column(name = "Prezime", nullable = false, length = 20)
	private String prezime;
	@Column(name = "Username", nullable = false, length = 20)
	private String username;
	@Column(name = "Sef")
	private Boolean sef;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "radnik")
	private Set<SkladisteSladoleda> skladisteSladoledas = new HashSet<SkladisteSladoleda>(0);
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "radnik")
	private Set<Skladistelog> skladistelogs = new HashSet<Skladistelog>(0);

	public Radnik() {
	}

	public Radnik(int radnikId, String ime, String prezime, String username) {
		this.radnikId = radnikId;
		this.ime = ime;
		this.prezime = prezime;
		this.username = username;
	}
	public Radnik(String ime, String prezime, String username, Boolean sef) {
		this.ime = ime;
		this.prezime = prezime;
		this.username = username;
		this.sef = sef;
	}


	public Radnik(int radnikId, String ime, String prezime, String username, Boolean sef,
			Set<SkladisteSladoleda> skladisteSladoledas, Set<Skladistelog> skladistelogs) {
		this.radnikId = radnikId;
		this.ime = ime;
		this.prezime = prezime;
		this.username = username;
		this.sef = sef;
		this.skladisteSladoledas = skladisteSladoledas;
		this.skladistelogs = skladistelogs;
	}

	public int getRadnikId() {
		return this.radnikId;
	}

	public void setRadnikId(int radnikId) {
		this.radnikId = radnikId;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Boolean getSef() {
		return this.sef;
	}

	public void setSef(Boolean sef) {
		this.sef = sef;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "radnik")
	public Set<SkladisteSladoleda> getSkladisteSladoledas() {
		return this.skladisteSladoledas;
	}

	public void setSkladisteSladoledas(Set<SkladisteSladoleda> skladisteSladoledas) {
		this.skladisteSladoledas = skladisteSladoledas;
	}

	public Set<Skladistelog> getSkladistelogs() {
		return this.skladistelogs;
	}

	public void setSkladistelogs(Set<Skladistelog> skladistelogs) {
		this.skladistelogs = skladistelogs;
	}

	@Override
	public String toString() {
		return "Radnik [ime=" + ime + ", prezime=" + prezime + ", username=" + username + ", sef=" + sef + "]";
	}

}
