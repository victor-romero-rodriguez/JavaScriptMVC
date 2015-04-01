package api;

import java.util.ArrayList;
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

import entities.Contact;

@Path("/contacts")
public class ContactsService {

	private static List<Contact> contactos;
	private static int contContactos = 2;

	static {
		contactos = new ArrayList<Contact>();

		contactos.add(new Contact(1, "Paco", "paco@gmail.com", "C\\Mayor 1",
				"234234234"));
		contactos.add(new Contact(2, "Silvia", "silvia@yahoo.es",
				"C\\Menor s/n", "675757676"));
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Contact create(Contact contacto) {
		++contContactos;
		System.out.println("Salvando contacto...");
		contacto.setId(contContactos);
		contactos.add(contacto);
		System.out.println("Ok..." + contacto);
		return contacto;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Contact> findAll() {
		System.out.println("Devolviendo lista de contactos...");
		printContacts();
		System.out.println("Ok!");
		return contactos;
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Contact findById(@PathParam("id") int id) {
		System.out.println("findById: " + id);

		for (Contact contacto : contactos) {
			if (contacto.getId() == id) {
				System.out.println("Contacto encontrado: " + contacto);
				return contacto;
			}
		}

		System.out.println("ATENCION: Contacto inexistente!");
		return null;
	}

	private void printContacts() {
		for (Contact contacto : contactos) {
			System.out.println(contacto);
		}
	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Contact update(Contact contacto) {
		System.out.println("Actualizando contacto...");

		int index = 0;
		for (Contact con : contactos) {
			if (con.getId() == contacto.getId()) {
				contactos.set(index, contacto);
				System.out.println("Ok..." + contacto);
				return contacto;
			}
			index++;
		}
		System.out.println("ATENCION: Contacto inexistente!");
		return null;
	}

	@DELETE
	@Path("{id}")
	public void remove(@PathParam("id") int id) {
		System.out.println("Eliminando contacto...");
		contactos.remove(findById(id));
		System.out.println("Ok.");
	}

}
