package jsf.project.DAO;

import javax.persistence.EntityManager;

import jsf.project.model.Usuario;
import jsf.project.util.UsuarioLogado;

import java.lang.reflect.ParameterizedType;
import java.util.List;


//PK - Primary key - chave primaria
//T - Type - Tipo
@SuppressWarnings("unchecked")//Avisa que o codigo deve ignorar avisos de nao verificacao
public class GenericDAO<PK, T> {

	private EntityManager em;
	
	public GenericDAO(EntityManager em){
		this.em  = em;
	}
	

	public T getById(PK pk){
		return (T) em.find(getTypeClass(), pk);
	}
	
	
	public void save (T entity){
		em.persist(entity);
	}
	
	
	public void update (T entity){
		em.merge(entity);
	}
	
	public void delete(T entity){
		em.remove(entity);
		
	}
	
	//Pega todos os registros inseridos
	public List<T> findAll(){
		return em.createQuery(("FROM "+ getTypeClass().getName())).getResultList();
	}
	
	//Pega todos os registros inseridos pelo vendedor
	public List<T> findAllOfVendedor(){
		return em.createQuery(("FROM "+ getTypeClass().getName()+" WHERE usuarioVendedor_CPF='"+ UsuarioLogado.getUsuario().getCPF()+"'")).getResultList();
	}
	
	//Pega todas as sacolas do usuario comprador
	public List<T> findAllOfComprador(){
		//Status 1 - Aberto
		//Status 2 - Fechado
		return em.createQuery(("FROM "+ getTypeClass().getName()+" WHERE usuarioComprador_CPF='"+ UsuarioLogado.getUsuario().getCPF()+"' AND status=1")).getResultList();
	}
	
	//Pega todos os pedidos da sacola aberta
	public List<T> findAllOpenPedidos(){
		return em.createQuery(("FROM "+ getTypeClass().getName()+" WHERE sacola_idSacola="+ UsuarioLogado.getSacola().getIdSacola())).getResultList();
	}
	
	//Conta quantos registros estao inseridos em uma tabela
	public Number count() {
		String consulta = "SELECT COUNT (*) FROM "+getTypeClass().getName();
		
		Number resultado = (Number) em.createQuery(consulta).getSingleResult();
		return resultado;
	}
	

	public int Login(String email, String senha){
		
		//String consulta = "SELECT *FROM USUARIO WHERE EMAIL='"+email+"'";
		List<Usuario> resultado = (List<Usuario>) em.createQuery(("FROM "+ getTypeClass().getName()+" WHERE EMAIL='"+email+"'")).getResultList();
		
		if(resultado.isEmpty()) {
			//Usuario nao existe no banco
			return 0;
		}
		else{
			String senhaBanco = resultado.get(0).getSenha().toString();
			
			if(senhaBanco.equalsIgnoreCase(senha)) {
				//Senha correta
				//Guarda as informações do usuario logado
				UsuarioLogado.setUsuario(resultado.get(0));
				return 1;
			}
			else {
				//Senha incorreta
				return 2;
			}
			
			
		}
		
	}

	//Descobre qual e a classe que fez a requisicao
	private Class<?> getTypeClass(){
		Class<?> clazz = (Class<?>) ((ParameterizedType) this.getClass()
                .getGenericSuperclass()).getActualTypeArguments()[1];
			return clazz;
	}
}
