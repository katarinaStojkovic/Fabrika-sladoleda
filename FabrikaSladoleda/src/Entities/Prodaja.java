package Entities;
// Generated Jan 13, 2018 5:54:31 PM by Hibernate Tools 5.1.0.Alpha1


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "prodaja", catalog = "fabrika_sladoleda")
public class Prodaja implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int sladoledSkladisteDobavljacKupacid;
	private DobavljacKupac dobavljacKupac;
	private SkladisteSladoleda skladisteSladoleda;
	private double prodataKolicina;
	private Date datumIsporucenja;
	private String statusIsporuke;

	public Prodaja() {
	}

	public Prodaja(int sladoledSkladisteDobavljacKupacid, DobavljacKupac dobavljacKupac,
			SkladisteSladoleda skladisteSladoleda, double prodataKolicina, Date datumIsporucenja,
			String statusIsporuke) {
		this.sladoledSkladisteDobavljacKupacid = sladoledSkladisteDobavljacKupacid;
		this.dobavljacKupac = dobavljacKupac;
		this.skladisteSladoleda = skladisteSladoleda;
		this.prodataKolicina = prodataKolicina;
		this.datumIsporucenja = datumIsporucenja;
		this.statusIsporuke = statusIsporuke;
	}
	
	public Prodaja(DobavljacKupac dobavljacKupac,
			SkladisteSladoleda skladisteSladoleda, double prodataKolicina, Date datumIsporucenja,
			String statusIsporuke) {
		this.dobavljacKupac = dobavljacKupac;
		this.skladisteSladoleda = skladisteSladoleda;
		this.prodataKolicina = prodataKolicina;
		this.datumIsporucenja = datumIsporucenja;
		this.statusIsporuke = statusIsporuke;
	}


	@Id

	@Column(name = "SLADOLED_SKLADISTE_DOBAVLJAC_KUPACID", unique = true, nullable = false)
	public int getSladoledSkladisteDobavljacKupacid() {
		return this.sladoledSkladisteDobavljacKupacid;
	}

	public void setSladoledSkladisteDobavljacKupacid(int sladoledSkladisteDobavljacKupacid) {
		this.sladoledSkladisteDobavljacKupacid = sladoledSkladisteDobavljacKupacid;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DOBAVLJAC_ID", nullable = false)
	public DobavljacKupac getDobavljacKupac() {
		return this.dobavljacKupac;
	}

	public void setDobavljacKupac(DobavljacKupac dobavljacKupac) {
		this.dobavljacKupac = dobavljacKupac;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SKLADISTE_SLADOLED_ID", nullable = false)
	public SkladisteSladoleda getSkladisteSladoleda() {
		return this.skladisteSladoleda;
	}

	public void setSkladisteSladoleda(SkladisteSladoleda skladisteSladoleda) {
		this.skladisteSladoleda = skladisteSladoleda;
	}

	@Column(name = "PRODATA_KOLICINA", nullable = false, precision = 22, scale = 0)
	public double getProdataKolicina() {
		return this.prodataKolicina;
	}

	public void setProdataKolicina(double prodataKolicina) {
		this.prodataKolicina = prodataKolicina;
	}

	//@Temporal(TemporalType.DATE)
	@Column(name = "DATUM_ISPORUCENJA", nullable = false, length = 0)
	public Date getDatumIsporucenja() {
		return this.datumIsporucenja;
	}

	public void setDatumIsporucenja(Date datumIsporucenja) {
		this.datumIsporucenja = datumIsporucenja;
	}

	@Column(name = "STATUS_ISPORUKE", nullable = false, length = 10)
	public String getStatusIsporuke() {
		return this.statusIsporuke;
	}

	public void setStatusIsporuke(String statusIsporuke) {
		this.statusIsporuke = statusIsporuke;
	}

	@Override
	public String toString() {
		return "SladoledSkladisteDobavljacKupac [sladoledSkladisteDobavljacKupacid=" + sladoledSkladisteDobavljacKupacid
				+ ", dobavljacKupac=" + dobavljacKupac + ", skladisteSladoleda=" + skladisteSladoleda
				+ ", prodataKolicina=" + prodataKolicina + ", datumIsporucenja=" + datumIsporucenja
				+ ", statusIsporuke=" + statusIsporuke + "]";
	}

	
}
