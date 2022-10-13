package it.epicode.be.gestioneventi.model;

import it.epicode.be.gestioneventi.util.JpaUtil;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.Query;

@Entity
@NamedQuery(
		name="vintoInCasa",
		query="SELECT p FROM PartitaDiCalcio p WHERE p.squadraVincente LIKE p.squadraDiCasa"
)
@NamedQuery(
		name="vintoInTrasferta",
		query="SELECT p FROM PartitaDiCalcio p WHERE p.squadraVincente LIKE p.squadraOspite"
)
@NamedQuery(
		name="pareggio",
		query="SELECT p FROM PartitaDiCalcio p WHERE p.squadraVincente LIKE null"
)
public class PartitaDiCalcio extends Evento {
	
	private String squadraDiCasa;
	private String squadraOspite;
	private String squadraVincente;
	private Integer numGolCasa = 0;
	private Integer numGolOspite = 0;
	public String getSquadraDiCasa() {
		return squadraDiCasa;
	}
	public void setSquadraDiCasa(String squadraDiCasa) {
		this.squadraDiCasa = squadraDiCasa;
	}
	public String getSquadraOspite() {
		return squadraOspite;
	}
	public void setSquadraOspite(String squadraOspite) {
		this.squadraOspite = squadraOspite;
	}
	public String getSquadraVincente() {
		return squadraVincente;
	}
	public void setSquadraVincente(String squadraVincente) {
		this.squadraVincente = squadraVincente;
	}
	public Integer getNumGolCasa() {
		return numGolCasa;
	}
	public void setNumGolCasa(Integer numGolCasa) {
		this.numGolCasa = numGolCasa;
	}
	public Integer getNumGolOspite() {
		return numGolOspite;
	}
	public void setNumGolOspite(Integer numGolOspite) {
		this.numGolOspite = numGolOspite;
	}
	
	public void vinteInCasa(){


	}
	public void perseInCasa(){

	}
	public void pariInCasa(){

	}

}
