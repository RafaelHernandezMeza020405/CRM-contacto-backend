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
    @Path("/{nombres}")
    public Contacto obtenerContactoPorNombre(@PathParam("nombres") String nombres) {
        Contacto contacto = service.obtenerContactoPorNombre(nombres);
        return service.verificarSiExisteContacto(contacto);
    }
    @DELETE
    @Path("/{nombres}")
    public void borrar(@PathParam("nombres") String nombres){
        service.borrarContacto(nombres);
    
    }
    
    
    @PUT
    @Path("/{nombres}")
    public Contacto actualizar(@PathParam("nombres") String nombres, Contacto objContact){
          Contacto contactoActualizar = service.actualizarContacto(nombres, objContact);
          if(contactoActualizar == null){
              return null;
          }else{
              return contactoActualizar;
          }
    }
          
}


