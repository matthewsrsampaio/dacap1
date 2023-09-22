package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Jpa {
	//Criar a fábrica
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("persisteDacap1");
	
	public static EntityManager criarEntityManager() {
		return emf.createEntityManager();
	}

}
