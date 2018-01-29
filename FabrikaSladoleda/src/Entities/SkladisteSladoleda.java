package Entities;


import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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
@Table(name = "skladiste_sladoleda", catalog = "fabrika_sladoleda")
public class SkladisteSladoleda implements java.io.Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "SKLADISTE_SLADOLEDID", unique = true, nullable = false)
	private Integer skladisteSladoledid;
	@ManyToOne(fetch= FetchType.EAGER)
	@JoinColumn(name = "Radnik_Id")
	private Radnik radnik;
	@ManyToOne(fetch= FetchType.EAGER)
	@JoinColumn(name = "SLADOLED_ID", nullable = false)
	private Sladoled sladoled;
	@Column(name = "PROIZVEDENA_KOLICINA", nullable = false, precision = 22, scale = 0)
	private double proizvedenaKolicina;
	//@Temporal(TemporalType.DATE)
	@Column(name = "DATUM_PROIZVODNJE", nullable = false, length = 0)
	private Date datumProizvodnje;
	//@OneToMany(cascade = CascadeType.ALL, mappedBy = "fmbrojid", fetch= FetchType.EAGER)
	@OneToMany(cascade = CascadeType.ALL,fetch= FetchType.EAGER, mappedBy = "skladisteSladoleda")
	private Set<Prodaja> sladoledSkladisteDobavljacKupacs = new HashSet<Prodaja>(
			0);

	public SkladisteSladoleda() {
	}

	public SkladisteSladoleda(Sladoled sladoled, double proizvedenaKolicina, Date datumProizvodnje) {
		this.sladoled = sladoled;
		this.proizvedenaKolicina = proizvedenaKolicina;
		this.datumProizvodnje = datumProizvodnje;
	}

	public SkladisteSladoleda(Radnik radnik, Sladoled sladoled, double proizvedenaKolicina, Date datumProizvodnje,
			Set<Prodaja> sladoledSkladisteDobavljacKupacs) {
		this.radnik = radnik;
		this.sladoled = sladoled;
		this.proizvedenaKolicina = proizvedenaKolicina;
		this.datumProizvodnje = datumProizvodnje;
		this.sladoledSkladisteDobavljacKupacs = sladoledSkladisteDobavljacKupacs;
	}
	
	public SkladisteSladoleda(Radnik radnik, Sladoled sladoled, double proizvedenaKolicina, Date datumProizvodnje) {
		this.radnik = radnik;
		this.sladoled = sladoled;
		this.proizvedenaKolicina = proizvedenaKolicina;
		this.datumProizvodnje = datumProizvodnje;
	}

	
	public Integer getSkladisteSladoledid() {
		return this.skladisteSladoledid;
	}

	public void setSkladisteSladoledid(Integer skladisteSladoledid) {
		this.skladisteSladoledid = skladisteSladoledid;
	}

	
	public Radnik getRadnik() {
		return this.radnik;
	}

	public void setRadnik(Radnik radnik) {
		this.radnik = radnik;
	}

	
	public Sladoled getSladoled() {
		return this.sladoled;
	}

	public void setSladoled(Sladoled sladoled) {
		this.sladoled = sladoled;
	}

	
	public double getProizvedenaKolicina() {
		return this.proizvedenaKolicina;
	}

	public void setProizvedenaKolicina(double proizvedenaKolicina) {
		this.proizvedenaKolicina = proizvedenaKolicina;
	}

	
	public Date getDatumProizvodnje() {
		return this.datumProizvodnje;
	}

	public void setDatumProizvodnje(Date datumProizvodnje) {
		this.datumProizvodnje = datumProizvodnje;
	}

	
	public Set<Prodaja> getSladoledSkladisteDobavljacKupacs() {
		return this.sladoledSkladisteDobavljacKupacs;
	}

	public void setSladoledSkladisteDobavljacKupacs(
			Set<Prodaja> sladoledSkladisteDobavljacKupacs) {
		this.sladoledSkladisteDobavljacKupacs = sladoledSkladisteDobavljacKupacs;
	}

	@Override
	public String toString() {
		return "SkladisteSladoleda [radnik=" + radnik + ", sladoled=" + sladoled + ", proizvedenaKolicina="
				+ proizvedenaKolicina + ", datumProizvodnje=" + datumProizvodnje + "]";
	}

	
}
