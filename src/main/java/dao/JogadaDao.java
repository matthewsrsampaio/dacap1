package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import bean.JogadaBean;
import entity.Jogada;
import util.Jpa;

public class JogadaDao {
	
	public static void salvar(Jogada jogada) throws Exception {
	    EntityManager em = Jpa.criarEntityManager();
	    try {
		    em.getTransaction().begin();
		    em.persist(jogada);
		    em.getTransaction().commit();
	    } catch (Exception e) {
		    throw e;
	    } finally {
		    em.close();
	    }
	}
	
	public static void editar(Jogada jogada) {
		EntityManager em = Jpa.criarEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(jogada);
			em.getTransaction().commit();
			System.out.println("Depois da transação feita em DAO:");
			System.out.println(jogada.getJogador1());
			System.out.println(jogada.getJogador2());
		} catch(Exception e) {
			throw e;
		} finally {
			em.close();
		}
	}
	
	public static void deletar(Jogada jogada) throws Exception {
		EntityManager em = Jpa.criarEntityManager();
		try {
			em.getTransaction().begin();
			jogada = em.find(Jogada.class, jogada.getId());
			em.remove(jogada);
			em.getTransaction().commit();
		}catch(Exception e) {
			throw e;
		}finally {
			em.close();
		}
	}
	
	public static List<Jogada> buscarTodos() throws Exception {
		EntityManager em = Jpa.criarEntityManager();
		try {
			Query query = em.createQuery("select j from Jogada j");
			List<Jogada> lista = query.getResultList();
			return lista;
		}catch(Exception e) {
			throw e;
		}finally {
			em.close();
		}
	}
	
	public static Jogada buscarPorId(Integer id) throws Exception{
		EntityManager em = Jpa.criarEntityManager();
		try {
			Jogada jogada = em.find(Jogada.class, id);
			return jogada;
		}catch(Exception e) {
			throw e;
		}finally {
			em.close();
		}
	}

}
