package it.epicode.be.gestioneventi.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(
		name="garaDaVincitore",
		query="SELECT g FROM GaraDiAtletica g WHERE g.vincitore = :vincitore"
)
@NamedQuery(
		name="garaDaPartecipante",
		query="SELECT g FROM GaraDiAtletica g WHERE :vincitore in IN g.setAtleti"
)
public class GaraDiAtletica extends Evento {
	
	@ManyToMany
	private Set<Persona> setAtleti;
	
	@ManyToOne
	private Persona vincitore;

	public Set<Persona> getSetAtleti() {
		return setAtleti;
	}

	public void setSetAtleti(Set<Persona> setAtleti) {
		this.setAtleti = setAtleti;
	}

	public Persona getVincitore() {
		return vincitore;
	}

	public void setVincitore(Persona vincitore) {
		this.vincitore = vincitore;
	}
	
	

}
