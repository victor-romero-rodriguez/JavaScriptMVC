package org.curso.vinos;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/vinos")
public class VinoServicio {

	VinoDAO dao = new VinoDAO();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Vino> findAll() {		
		return dao.findAll();
	}

	@GET @Path("search/{query}")
	@Produces(MediaType.APPLICATION_JSON)
	public Vino findByName(@PathParam("query") String query) {		
		return dao.findByName(query).get(0);
	}

	@GET @Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Vino findById(@PathParam("id") int id) {
		System.out.println("findById " + id);
		return dao.findById(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Vino create(Vino vino) {
		System.out.println("creando vino");
		return dao.save(vino);
	}

	@PUT @Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Vino update(Vino vino, @PathParam("id") int id) {		
		System.out.println("Actualizando vino: " + vino.getNombre());
		vino.setId(id);
		dao.save(vino);
		return vino;
	}
	
	@DELETE @Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void remove(@PathParam("id") int id) {
		dao.remove(id);
	}
}