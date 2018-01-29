package Entities;
// Generated Jan 13, 2018 5:54:31 PM by Hibernate Tools 5.1.0.Alpha1

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
@Table(name = "tip_sladoleda", catalog = "fabrika_sladoleda")
public class TipSladoleda implements java.io.Serializable {

	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String imeTipa;
	private Set<Sladoled> sladoleds = new HashSet<Sladoled>(0);

	public TipSladoleda() {
	}

	public TipSladoleda(String imeTipa) {
		this.imeTipa = imeTipa;
	}
	
	public TipSladoleda(String imeTipa, Set<Sladoled> sladoleds) {
		this.imeTipa = imeTipa;
		this.sladoleds = sladoleds;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "IME_TIPA", length = 20)
	public String getImeTipa() {
		return this.imeTipa;
	}

	public void setImeTipa(String imeTipa) {
		this.imeTipa = imeTipa;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipSladoleda")
	public Set<Sladoled> getSladoleds() {
		return this.sladoleds;
	}

	public void setSladoleds(Set<Sladoled> sladoleds) {
		this.sladoleds = sladoleds;
	}

	@Override
	public String toString() {
		return "TipSladoleda [imeTipa=" + imeTipa + "]";
	}
	
	

}
