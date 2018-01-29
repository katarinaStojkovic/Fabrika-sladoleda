package Entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "recept_sirovina", catalog = "fabrika_sladoleda")
public class ReceptSirovina implements java.io.Serializable {

	
	
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "RECEPT_SIROVINAID", unique = true, nullable = false)
	private Integer receptSirovinaid;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "RECEPT_ID", nullable = false)
	private Recept recept;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SIROVINA_ID", nullable = false)
	private Sirovina sirovina;
	//@OneToMany(cascade = CascadeType.ALL, mappedBy = "fmbrojid", fetch= FetchType.EAGER)
	@Column(name = "KOLICINA_SIROVINE_RECEPT", nullable = false, precision = 22, scale = 0)
	private double kolicinaSirovineRecept;

	public ReceptSirovina() {
	}

	public ReceptSirovina(Recept recept, Sirovina sirovina, double kolicinaSirovineRecept) {
		this.recept = recept;
		this.sirovina = sirovina;
		this.kolicinaSirovineRecept = kolicinaSirovineRecept;
	}

	
	public Integer getReceptSirovinaid() {
		return this.receptSirovinaid;
	}

	public void setReceptSirovinaid(Integer receptSirovinaid) {
		this.receptSirovinaid = receptSirovinaid;
	}

	
	public Recept getRecept() {
		return this.recept;
	}

	public void setRecept(Recept recept) {
		this.recept = recept;
	}

	
	public Sirovina getSirovina() {
		return this.sirovina;
	}

	public void setSirovina(Sirovina sirovina) {
		this.sirovina = sirovina;
	}

	
	public double getKolicinaSirovineRecept() {
		return this.kolicinaSirovineRecept;
	}

	public void setKolicinaSirovineRecept(double kolicinaSirovineRecept) {
		this.kolicinaSirovineRecept = kolicinaSirovineRecept;
	}

	@Override
	public String toString() {
		return "ReceptSirovina [recept=" + recept + ", sirovina=" + sirovina + ", kolicinaSirovineRecept="
				+ kolicinaSirovineRecept + "]";
	}

	
}
