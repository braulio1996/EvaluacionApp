package ec.edu.ups.on;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;


import ec.edu.ups.dao.PersonaDAO;
import ec.edu.ups.dao.TituloDAO;
import ec.edu.ups.modelo.Persona;
import ec.edu.ups.modelo.Titulo;
import ec.edu.ups.services.Respuesta;
import ec.edu.ups.services.TituloTemporal;



@Stateless
public class PersonaON {
	@Inject
	private PersonaDAO pdao;
	@Inject
	private TituloDAO tdao;
	
	Date myDate = new Date();
	
	public void guardar(Persona persona) throws Exception {
		pdao.insertar(persona);	
	}
	
	public Persona buscarC(String cedula) throws Exception{
		if(pdao.buscar(cedula)==null) {
			throw new Exception("No existe");
		}
		return pdao.buscar(cedula);
	}
	public List<Persona> listar() throws Exception {
		try {
		if(pdao.listar()==null) {
			throw new Exception("No existe");	
		}else {
		return pdao.listar();
		}
	} catch (SQLException e) {
		System.out.println(e.toString());
	}
	return null;
}
	public Respuesta addTitulos(TituloTemporal t) {
		List<Titulo>titulos= new ArrayList<>();
		Respuesta r = new Respuesta();
		Titulo ti= new Titulo();
		try{
			Persona p =pdao.buscar(t.getCedula());
			if(p==null) {
			r.setCodigo(0);	
			r.setMensaje("No existe la persona ");
			}else{
				ti.setEstado(t.getEstado());
				ti.setFechaRegistro(new SimpleDateFormat("dd/MM/yyyy").format(myDate));
				ti.setNombre(t.getNombre());
				ti.setPersona(p);
				titulos.add(ti);
				p.setTitulos(titulos);
				//pdao.editar(p);	
				tdao.insertar(ti);
				r.setCodigo(1);	
				r.setMensaje("Persona Ingresada");
				System.out.println(p.toString());
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			r.setCodigo(99);
			r.setMensaje(e.toString());
			return null;
		}
		return r;
		
	}
}
