package Entities;


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "sladoled", catalog = "fabrika_sladoleda")
public class Sladoled implements java.io.Serializable {

	
	private static final long serialVersionUID = 1L;
	private Integer sladoledid;
	private Recept recept;
	private TipSladoleda tipSladoleda;
	private String sladoledIme;
	private double cenaSladoleda;
	private Set<Skladistelog> skladistelogs = new HashSet<Skladistelog>(0);
	private Set<SkladisteSladoleda> skladisteSladoledas = new HashSet<SkladisteSladoleda>(0);

	public Sladoled() {
	}

	public Sladoled(Recept recept, TipSladoleda tipSladoleda, String sladoledIme, double cenaSladoleda) {
		this.recept = recept;
		this.tipSladoleda = tipSladoleda;
		this.sladoledIme = sladoledIme;
		this.cenaSladoleda = cenaSladoleda;
	}

	public Sladoled(Recept recept, TipSladoleda tipSladoleda, String sladoledIme, double cenaSladoleda,
			Set<Skladistelog> skladistelogs, Set<SkladisteSladoleda> skladisteSladoledas) {
		this.recept = recept;
		this.tipSladoleda = tipSladoleda;
		this.sladoledIme = sladoledIme;
		this.cenaSladoleda = cenaSladoleda;
		this.skladistelogs = skladistelogs;
		this.skladisteSladoledas = skladisteSladoledas;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "SLADOLEDID", unique = true, nullable = false)
	public Integer getSladoledid() {
		return this.sladoledid;
	}

	public void setSladoledid(Integer sladoledid) {
		this.sladoledid = sladoledid;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "RECEPTID", nullable = false)
	public Recept getRecept() {
		return this.recept;
	}

	public void setRecept(Recept recept) {
		this.recept = recept;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TIP_SLADOLEDA", nullable = false)
	public TipSladoleda getTipSladoleda() {
		return this.tipSladoleda;
	}

	public void setTipSladoleda(TipSladoleda tipSladoleda) {
		this.tipSladoleda = tipSladoleda;
	}

	@Column(name = "SLADOLED_IME", nullable = false, length = 30)
	public String getSladoledIme() {
		return this.sladoledIme;
	}

	public void setSladoledIme(String sladoledIme) {
		this.sladoledIme = sladoledIme;
	}

	@Column(name = "CENA_SLADOLEDA", nullable = false, precision = 22, scale = 0)
	public double getCenaSladoleda() {
		return this.cenaSladoleda;
	}

	public void setCenaSladoleda(double cenaSladoleda) {
		this.cenaSladoleda = cenaSladoleda;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sladoled")
	public Set<Skladistelog> getSkladistelogs() {
		return this.skladistelogs;
	}

	public void setSkladistelogs(Set<Skladistelog> skladistelogs) {
		this.skladistelogs = skladistelogs;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sladoled")
	public Set<SkladisteSladoleda> getSkladisteSladoledas() {
		return this.skladisteSladoledas;
	}

	public void setSkladisteSladoledas(Set<SkladisteSladoleda> skladisteSladoledas) {
		this.skladisteSladoledas = skladisteSladoledas;
	}

	@Override
	public String toString() {
		return "Sladoled [recept=" + recept + ", tipSladoleda=" + tipSladoleda + ", sladoledIme=" + sladoledIme
				+ ", cenaSladoleda=" + cenaSladoleda + "]";
	}

	
}
