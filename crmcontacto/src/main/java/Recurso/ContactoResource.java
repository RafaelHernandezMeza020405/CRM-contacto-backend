package Recurso;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import modelos.Contacto;
import Servicio.ContactoService;

@Path("/contacto")
public class ContactoResource {

    @Inject
    ContactoService service;

    @GET
    public List<Contacto> mostrarLista() {
        return service.lista();

    }

    @POST
    public List<Contacto> agregarContacto(Contacto objContacto) {
        service.agregarContactoABasededatos(objContacto);
        return mostrarLista();
    }

    @GET
    @Path("/{email}")
    public Contacto obtenerContactoPorNombre(@PathParam("email") String email) {
        Contacto contacto = service.obtenerContactoPorNombre(email);
        return service.verificarSiExisteContacto(contacto);
    }

    @DELETE
    @Path("/{email}")
    public void borrar(@PathParam("email") String email) {
        service.borrarContacto(email);
    }

    @PUT
    @Path("/{email}")
    public Contacto actualizar(@PathParam("email") String email, Contacto objContact) {
        Contacto contactoActualizar = service.actualizarContacto(email, objContact);
        return service.verificarSiExisteContacto(contactoActualizar);
    }

}
