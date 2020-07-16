package ec.edu.ups.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.ups.modelo.Titulo;

@Stateless
public class TituloDAO {
	@PersistenceContext(name = "EvaluacionWSCastroBraulioPersistenceUnit")
	private EntityManager em;

	public void insertar(Titulo titulo) throws Exception {

		try {
			em.persist(titulo);

		} catch (Exception e) {
			throw new Exception(e.toString());

		}
	}

	public void editar(Titulo titulo) throws Exception {

		try {
			em.merge(titulo);

		} catch (Exception e) {
			throw new Exception(e.toString());

		}
	}

	public List<Titulo> buscar(String nombre) throws Exception {

		try {
			String jpql = "SELECT a FROM Titulo a WHERE a.nombre LIKE '"+nombre+"%'";
			Query query = em.createQuery(jpql, Titulo.class);
			//query.setParameter("nombre", nombre);
			//Titulo titulo = (Titulo) query.getSingleResult();
			List<Titulo>t=query.getResultList();

			return t;

		} catch (Exception e) {
			throw new Exception(e.toString());
		}

	}

	public List<Titulo> listar() throws Exception {

		try {
			String jpql = "SELECT l FROM Titulo l";
			Query query = em.createQuery(jpql, Titulo.class);

			List<Titulo> t = query.getResultList();

			return t;

		} catch (Exception e) {
			throw new Exception(e.toString());
		}

	}

}
