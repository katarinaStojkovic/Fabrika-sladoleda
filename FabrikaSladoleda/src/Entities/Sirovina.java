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
@Table(name = "sirovina", catalog = "fabrika_sladoleda")
public class Sirovina implements java.io.Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "SIROVINAID", unique = true, nullable = false)
	private Integer sirovinaid;
	@Column(name = "NAZIV_SIROVINE", nullable = false, length = 20)
	private String nazivSirovine;
	@Column(name = "KOLICINA", nullable = false, precision = 22, scale = 0)
	private double kolicina;
	@Column(name = "CENA_SIROVINE", nullable = false, precision = 22, scale = 0)
	private double cenaSirovine;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sirovina")
	private Set<ReceptSirovina> receptSirovinas = new HashSet<ReceptSirovina>(0);

	public Sirovina() {
	}

	public Sirovina(String nazivSirovine, double kolicina, double cenaSirovine) {
		this.nazivSirovine = nazivSirovine;
		this.kolicina = kolicina;
		this.cenaSirovine = cenaSirovine;
	}

	public Sirovina(String nazivSirovine, double kolicina, double cenaSirovine, Set<ReceptSirovina> receptSirovinas) {
		this.nazivSirovine = nazivSirovine;
		this.kolicina = kolicina;
		this.cenaSirovine = cenaSirovine;
		this.receptSirovinas = receptSirovinas;
	}

	public Integer getSirovinaid() {
		return this.sirovinaid;
	}

	public void setSirovinaid(Integer sirovinaid) {
		this.sirovinaid = sirovinaid;
	}


	public String getNazivSirovine() {
		return this.nazivSirovine;
	}

	public void setNazivSirovine(String nazivSirovine) {
		this.nazivSirovine = nazivSirovine;
	}

	
	public double getKolicina() {
		return this.kolicina;
	}

	public void setKolicina(double kolicina) {
		this.kolicina = kolicina;
	}

	
	public double getCenaSirovine() {
		return this.cenaSirovine;
	}

	public void setCenaSirovine(double cenaSirovine) {
		this.cenaSirovine = cenaSirovine;
	}

	
	public Set<ReceptSirovina> getReceptSirovinas() {
		return this.receptSirovinas;
	}

	public void setReceptSirovinas(Set<ReceptSirovina> receptSirovinas) {
		this.receptSirovinas = receptSirovinas;
	}

	@Override
	public String toString() {
		return "Sirovina [nazivSirovine=" + nazivSirovine + ", kolicina=" + kolicina + ", cenaSirovine=" + cenaSirovine
				+ "]";
	}

	
}
