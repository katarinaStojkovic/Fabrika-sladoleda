package Entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "recept", catalog = "fabrika_sladoleda")
public class Recept implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "RECEPTID", unique = true, nullable = false)
	private Integer receptid;
	@Column(name = "RECEPT_IME", nullable = false, length = 30)
	private String receptIme;
	@Column(name = "OPIS", nullable = false, length = 250)
	private String opis;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "recept")
	private Set<ReceptSirovina> receptSirovinas = new HashSet<ReceptSirovina>(0);
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "recept")
	private Set<Sladoled> sladoleds = new HashSet<Sladoled>(0);

	public Recept() {
	}

	public Recept(String receptIme, String opis) {
		this.receptIme = receptIme;
		this.opis = opis;
	}

	public Recept(String receptIme, String opis, Set<ReceptSirovina> receptSirovinas, Set<Sladoled> sladoleds) {
		this.receptIme = receptIme;
		this.opis = opis;
		this.receptSirovinas = receptSirovinas;
		this.sladoleds = sladoleds;
	}

	public Integer getReceptid() {
		return this.receptid;
	}

	public void setReceptid(Integer receptid) {
		this.receptid = receptid;
	}

	public String getReceptIme() {
		return this.receptIme;
	}

	public void setReceptIme(String receptIme) {
		this.receptIme = receptIme;
	}

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Set<ReceptSirovina> getReceptSirovinas() {
		return this.receptSirovinas;
	}

	public void setReceptSirovinas(Set<ReceptSirovina> receptSirovinas) {
		this.receptSirovinas = receptSirovinas;
	}

	public Set<Sladoled> getSladoleds() {
		return this.sladoleds;
	}

	public void setSladoleds(Set<Sladoled> sladoleds) {
		this.sladoleds = sladoleds;
	}

	@Override
	public String toString() {
		return "Recept [receptIme=" + receptIme + ", opis=" + opis + "]";
	}

}
