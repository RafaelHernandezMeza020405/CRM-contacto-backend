package mpContacto;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import modelos.Contacto;
import mnContacto.ContactoService;

@Path("/contacto")
public class GreetingResource {

    @Inject
    ContactoService service;

    @GET

    public List<Contacto> mostrarLista() {
        return service.lista();

    }

    @POST
    public List<Contacto> agregarContacto(Contacto contacto) {
        service.agregarContactoABasededatos(contacto);
        return mostrarLista();
    }

    @GET
    @Path("/{nombres}")
    public Contacto obtenerContactoPorNombre(@PathParam("nombres") String nombre) {
        Contacto contacto = service.obtenerContactoPorNombre(nombre);

        if (contacto != null) {
            return contacto;
        } else {
            return null;
        }
    }
    @DELETE
    @Path("/{nombres}")
    public void borrar(@PathParam("nombres") String nombre){
        service.borrarContacto(nombre);
    
    }
    
    @PUT
    @Path("/{nombres}")
    public void actualizar(@PathParam("nombres") String nombre, Contacto objContacto){
         service.actualizarContacto(nombre, objContacto);
    }
         
}


