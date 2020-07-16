package ec.edu.ups.services;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import ec.edu.ups.modelo.Titulo;
import ec.edu.ups.on.PersonaON;
import ec.edu.ups.on.TituloON;

@Path("/users")
public class ClienteServiceRest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private PersonaON pon;
	@Inject
	private TituloON ton;

	Date myDate = new Date();

	@POST
	@Path("/addTitulo")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Respuesta transferencia2(TituloTemporal t) {
		Respuesta r = new Respuesta();
		try {
			return pon.addTitulos(t);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			r.setCodigo(999);
			r.setMensaje(e.getMessage());
		}
		return r;

	}

	@GET
	@Path("/BuscarTitulo")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Titulo> buscar(@QueryParam("nombre") String nombre) throws Exception {
		TituloTemporal2 t= new TituloTemporal2();
		try {
             
//             t.setTitulo(ton.buscarC(nombre).getNombre());
//             t.setFechaIngreso(ton.buscarC(nombre).getFechaRegistro());
//             t.setId(ton.buscarC(nombre).getId());
//             t.setPersona(ton.buscarC(nombre).getPersona().getNombre()+" "+ton.buscarC(nombre).getPersona().getApellido());
			return ton.buscarC(nombre);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

}