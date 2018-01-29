package Entities;



import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "skladistelog", catalog = "fabrika_sladoleda")
public class Skladistelog implements java.io.Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "SkladisteSladoledaLogId", unique = true, nullable = false)
	private int skladisteSladoledaLogId;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Radnik_Id")
	private Radnik radnik;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SLADOLED_ID")
	private Sladoled sladoled;
	@Column(name = "PROIZVEDENA_KOLICINA", nullable = false, precision = 22, scale = 0)
	private double proizvedenaKolicina;
	//@Temporal(TemporalType.DATE)
	@Column(name = "DATUM_PROIZVODNJE", nullable = false, length = 0)
	private Date datumProizvodnje;

	public Skladistelog() {
	}

	public Skladistelog(Sladoled sladoled, double proizvedenaKolicina, Date datumProizvodnje) {
		this.sladoled = sladoled;
		this.proizvedenaKolicina = proizvedenaKolicina;
		this.datumProizvodnje = datumProizvodnje;
	}
	
	public Skladistelog(int skladisteSladoledaLogId, double proizvedenaKolicina, Date datumProizvodnje) {
		this.skladisteSladoledaLogId = skladisteSladoledaLogId;
		this.proizvedenaKolicina = proizvedenaKolicina;
		this.datumProizvodnje = datumProizvodnje;
	}

	public Skladistelog(int skladisteSladoledaLogId, Radnik radnik, Sladoled sladoled, double proizvedenaKolicina,
			Date datumProizvodnje) {
		this.skladisteSladoledaLogId = skladisteSladoledaLogId;
		this.radnik = radnik;
		this.sladoled = sladoled;
		this.proizvedenaKolicina = proizvedenaKolicina;
		this.datumProizvodnje = datumProizvodnje;
	}

	
	
	public Skladistelog(Radnik radnik, Sladoled sladoled, double proizvedenaKolicina,
			Date datumProizvodnje) {
		this.radnik = radnik;
		this.sladoled = sladoled;
		this.proizvedenaKolicina = proizvedenaKolicina;
		this.datumProizvodnje = datumProizvodnje;
	}

	
	public int getSkladisteSladoledaLogId() {
		return this.skladisteSladoledaLogId;
	}

	public void setSkladisteSladoledaLogId(int skladisteSladoledaLogId) {
		this.skladisteSladoledaLogId = skladisteSladoledaLogId;
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

	@Override
	public String toString() {
		return "Skladistelog [radnik=" + radnik + ", sladoled=" + sladoled + ", proizvedenaKolicina="
				+ proizvedenaKolicina + ", datumProizvodnje=" + datumProizvodnje + "]";
	}

	
}
