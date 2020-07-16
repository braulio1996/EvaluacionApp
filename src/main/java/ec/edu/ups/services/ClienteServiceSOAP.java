package ec.edu.ups.services;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.QueryParam;

import ec.edu.ups.modelo.Titulo;
import ec.edu.ups.on.PersonaON;
import ec.edu.ups.on.TituloON;

@WebService
public class ClienteServiceSOAP {

	@Inject
	private PersonaON pon;
	@Inject
	private TituloON ton;

	Date myDate = new Date();

	@WebMethod
	public Respuesta addTitulo(TituloTemporal t) {
		Respuesta r = new Respuesta();
		try {
			return pon.addTitulos(t);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			r.setCodigo(99);
			r.setMensaje(e.getMessage());
		}
		return r;

	}

	@WebMethod
	public List<Titulo> buscar(@QueryParam("nombre") String nombre) throws Exception {

		try {

			return ton.buscarC(nombre);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

}
