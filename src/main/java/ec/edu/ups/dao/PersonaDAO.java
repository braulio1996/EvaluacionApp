package ec.edu.ups.dao;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.ups.modelo.Persona;
import ec.edu.ups.modelo.Titulo;





@Stateless
public class PersonaDAO {
	@PersistenceContext(name="EvaluacionWSCastroBraulioPersistenceUnit")
	private EntityManager em;

	public void insertar(Persona persona) throws Exception {
		
        try {  
        	em.persist(persona);
        	
        } catch (Exception e) {
        	throw new Exception(e.toString());
        	
        } 	
	}
public void editar(Persona persona) throws Exception {
		
        try {  
        	em.merge(persona);
        	
        } catch (Exception e) {
        	throw new Exception(e.toString());
        	
        } 	
	}
	
	
	public Persona buscar(String cedula) throws Exception {
		
		try {
				String jpql = "SELECT a FROM Persona a WHERE a.cedula = :cedula";
				Query query = em.createQuery(jpql, Persona.class);
				query.setParameter("cedula", cedula);
				//Persona persona = (Persona) query.getSingleResult();
				List<Persona>personas=query.getResultList();
				int c=0;
				for(Persona a:personas) {
					
					a.getTitulos().get(c).getId();
					c++;
				}
				return personas.get(0);
		    
		}catch(Exception e) {
			throw new Exception(e.toString());
		}
		
	}
public List<Persona>listar() throws Exception {
		
		try {
			String jpql = "SELECT l FROM Persona l";
			Query query = em.createQuery(jpql, Persona.class);
			
			List<Persona> personaz = query.getResultList();
			
			for(Persona persona:personaz) {
				List<Titulo> titulos=persona.getTitulos();
				persona.setTitulos(titulos);
				titulos=null;
			}
			return personaz;
		
		    
		}catch(Exception e) {
			throw new Exception(e.toString());
		}
		
	}
	
	
}
