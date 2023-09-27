package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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
	
	public static Long buscarSomaPapel() throws Exception {
	    EntityManager em = Jpa.criarEntityManager();
	    try {
	        Query query = em.createQuery("SELECT SUM(j.papel) FROM Jogada j");
	        Long somaPapel = (Long) query.getSingleResult();
	        System.out.println(somaPapel);
	        return somaPapel != null ? somaPapel : 0; // Retorna 0.0 se a soma for nula
	    } catch (Exception e) {
	        throw e;
	    } finally {
	        em.close();
	    }
	}

	public static Long buscarSomaPedra() throws Exception {
	    EntityManager em = Jpa.criarEntityManager();
	    try {
	        Query query = em.createQuery("SELECT SUM(j.pedra) FROM Jogada j");
	        Long somaPedra = (Long) query.getSingleResult();
	        System.out.println(somaPedra);
	        return somaPedra != null ? somaPedra : 0; // Retorna 0.0 se a soma for nula
	    } catch (Exception e) {
	        throw e;
	    } finally {
	        em.close();
	    }
	}
	
	public static Long buscarSomaTesoura() throws Exception {
	    EntityManager em = Jpa.criarEntityManager();
	    try {
	        Query query = em.createQuery("SELECT SUM(j.tesoura) FROM Jogada j");
	        Long somaTesoura = (Long) query.getSingleResult();
	        System.out.println(somaTesoura);
	        return somaTesoura != null ? somaTesoura : 0; // Retorna 0.0 se a soma for nula
	    } catch (Exception e) {
	        throw e;
	    } finally {
	        em.close();
	    }
	}

}
