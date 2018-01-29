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
@Table(name = "dobavljac_kupac", catalog = "fabrika_sladoleda")
public class DobavljacKupac implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "DOBAVLJACID", unique = true, nullable = false)
	private Integer dobavljacid;
	@Column(name = "NAZIV", nullable = false, length = 50)
	private String naziv;
	@Column(name = "ADRESA", nullable = false, length = 30)
	private String adresa;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dobavljacKupac")
	private Set<Prodaja> sladoledSkladisteDobavljacKupacs = new HashSet<Prodaja>(
			0);

	public DobavljacKupac() {
	}

	public DobavljacKupac(String naziv, String adresa) {
		this.naziv = naziv;
		this.adresa = adresa;
	}

	public DobavljacKupac(String naziv, String adresa,
			Set<Prodaja> sladoledSkladisteDobavljacKupacs) {
		this.naziv = naziv;
		this.adresa = adresa;
		this.sladoledSkladisteDobavljacKupacs = sladoledSkladisteDobavljacKupacs;
	}

	public Integer getDobavljacid() {
		return this.dobavljacid;
	}

	public void setDobavljacid(Integer dobavljacid) {
		this.dobavljacid = dobavljacid;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getAdresa() {
		return this.adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
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
		return "DobavljacKupac [naziv=" + naziv + ", adresa=" + adresa + "]";
	}

}
