package jsf.project.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Persistencia {
		//Essa classe gerencia as atividades de abrir conexao e outros
	
		//Nome da unidade de persistencia
		EntityManagerFactory f;
		
		//Gerencia as transacoes
		EntityManager em;
		
		public Persistencia(EntityManagerFactory f) {
			// TODO Auto-generated constructor stub
			this.f = f;
			this.em = f.createEntityManager();
		}
		public Persistencia() {
			// TODO Auto-generated constructor stub
			this.f = Persistence.createEntityManagerFactory("bazinga");
			this.em = this.f.createEntityManager();
		}
		public void beginTransaction(){
			this.em.getTransaction().begin();
		}
		public void commit(){
			this.em.getTransaction().commit();
		}	
		public void close(){
			this.em.close();
			this.f.close();
		}
		
		public void rollBack(){
			this.em.getTransaction().rollback();
		}		
		public EntityManagerFactory getF() {
			return f;
		}
		public void setF(EntityManagerFactory f) {
			this.f = f;
		}
		public EntityManager getEm() {
			return em;
		}
		public void setEm(EntityManager em) {
			this.em = em;
		}
	}	