package it.epicode.be.gestioneventi.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.epicode.be.gestioneventi.model.Concerto;
import it.epicode.be.gestioneventi.model.Evento;
import it.epicode.be.gestioneventi.model.GaraDiAtletica;
import it.epicode.be.gestioneventi.model.GenereConcerto;
import it.epicode.be.gestioneventi.model.Partecipazione;
import it.epicode.be.gestioneventi.model.PartitaDiCalcio;
import it.epicode.be.gestioneventi.model.Persona;
import it.epicode.be.gestioneventi.util.JpaUtil;

public class EventoDAO {
	private static final Logger logger = LoggerFactory.getLogger(EventoDAO.class);

	public void save(Evento object) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {

			EntityTransaction transaction = em.getTransaction();
			transaction.begin();

			em.persist(object);

			transaction.commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();

			logger.error("Error saving object: " + object.getClass().getSimpleName(), ex);
			throw ex;

		} finally {
			em.close();
		}

	}

	public void refresh(Evento object) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {

			em.refresh(object);

		} finally {
			em.close();
		}

	}

	public Evento getById(Long id) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {

			return em.find(Evento.class, id);

		} finally {
			em.close();
		}

	}

	public void delete(Evento object) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {

			EntityTransaction transaction = em.getTransaction();
			transaction.begin();

			em.remove(object);

			transaction.commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
			logger.error("Error deleting object: " + object.getClass().getSimpleName(), ex);
			throw ex;

		} finally {
			em.close();
		}

	}

	public List<Concerto> getConcertiInStreaming(Boolean inStreaming) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {

			Query query = em.createNamedQuery("concertiInStreaming");

			query.setParameter("streaming", inStreaming);
			return query.getResultList();

		} finally {
			em.close();
		}
	}

	public List<Concerto> getConcertiPerGenere(List<GenereConcerto> listaGeneri) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {

			Query query = em.createNamedQuery("concertiPerGenere");

			query.setParameter("listagenere", listaGeneri);
			return query.getResultList();

		} finally {
			em.close();
		}
	}

	public void getVincitoriInCasa(){
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {

			Query query = em.createNamedQuery("vintoInCasa");

			for (int i = 0; i < query.getResultList().size(); i++) {
				System.out.println(query.getResultList().get(i));
			}

		} finally {
			em.close();
		}
	}
	public void getPerdentiInCasa(){
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {

			Query query = em.createNamedQuery("vintoInTrasferta");

			for (int i = 0; i < query.getResultList().size(); i++) {
				System.out.println(query.getResultList().get(i));
			}

		} finally {
			em.close();
		}
	}
	public void getPariInCasa(){
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {

			Query query = em.createNamedQuery("pareggio");

			for (int i = 0; i < query.getResultList().size(); i++) {
				System.out.println(query.getResultList().get(i));
			}

		} finally {
			em.close();
		}
	}

	public void getVincitoreGara(String vincitore){
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {

			Query query = em.createNamedQuery("garaDaVincitore");

			query.setParameter("vincitore", vincitore);

			for (int i = 0; i < query.getResultList().size(); i++) {
				System.out.println(query.getResultList().get(i));
			}

		} finally {
			em.close();
		}
	}
	public static void getEventiSoldOut() {

		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();

		try {
			Query q = em.createQuery(
					"SELECT e FROM Evento e WHERE SIZE(e.setPartecipazioni) = e.numeroMassimoPartecipanti");

			List<Evento> e = q.getResultList();

			if (e.size() != 0) {
				for (Evento ev : e) {
					System.out.println(ev);
				}
			} else {
				System.out.println(" B==========D");
			}

		} finally {
			em.close();
		}
	}
	public void getPartecipanteGara(String partecipante){
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {

			Query query = em.createNamedQuery("garaDaPartecipante");

			query.setParameter("partecipante", partecipante);

			for (int i = 0; i < query.getResultList().size(); i++) {
				System.out.println(query.getResultList().get(i));
			}

		} finally {
			em.close();
		}
	}

	public void getEventiPerInvitato(String invitato){
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();

		try{
			Query q = em.createQuery(
					"SELECT e.evento FROM Partecipazione e WHERE e.persona IN(SELECT p FROM Persona p WHERE p.nome LIKE :invitato)"
			);

			q.setParameter("invitato", invitato);

			List<Evento> e = q.getResultList();

			for (Evento ev : e
			) {
				System.out.println(ev);
			}
		} finally {
			em.close();
		}
	}


}
