package ec.edu.ups.on;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.dao.TituloDAO;
import ec.edu.ups.modelo.Titulo;

@Stateless
public class TituloON {
	@Inject
	private TituloDAO tdao;

	public void guardar(Titulo titulo) throws Exception {
		tdao.insertar(titulo);
	}

	public List<Titulo> buscarC(String nombre) throws Exception {
		if (tdao.buscar(nombre) == null) {
			throw new Exception("No existe Titulos\r\n" + 
					"coincidentes");
		}
		return tdao.buscar(nombre);
	}

	public List<Titulo> listar() throws Exception {
		try {
			if (tdao.listar() == null) {
				throw new Exception("No existe");
			} else {
				return tdao.listar();
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return null;
	}

}
