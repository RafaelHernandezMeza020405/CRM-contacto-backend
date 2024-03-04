package Servicio;

import modelos.Contacto;
import java.util.ArrayList;
import java.util.List;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.conversions.Bson;

@ApplicationScoped
public class ContactoService {

    @Inject
    MongoClient mongoCliente;

    public List<Contacto> lista() {
        List<Contacto> listaDeContacto = new ArrayList<>();
        MongoCursor<Document> cursor = obtenerColeccion().find().iterator();
        try {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                Contacto contacto = new Contacto();
                contacto.setNombres(document.getString("nombres"));
                contacto.setApellidos(document.getString("apellidos"));
                contacto.setEmail(document.getString("email"));
                contacto.setCelular(document.getString("celular"));
                contacto.setFechadenacimiento(document.getString("fechadenacimiento"));
                contacto.setDireccion(document.getString("direccion"));
                contacto.setTipodecontacto(document.getString("tipoDeContacto"));
                contacto.setOrigen(document.getString("origen"));
                contacto.setTareas(document.getString("tareas"));
                contacto.setComentarios(document.getString("comentarios"));
                listaDeContacto.add(contacto);
            }

        } finally {
            cursor.close();
        }
        return listaDeContacto;

    }

    public void agregarContactoABasededatos(Contacto contacto) {
        Document document = new Document()
                .append("nombres", contacto.getNombres())
                .append("apellidos", contacto.getApellidos())
                .append("email", contacto.getEmail())
                .append("celular", contacto.getCelular())
                .append("fechadenacimiento", contacto.getFechadenacimiento())
                .append("direccion", contacto.getDireccion())
                .append("tipoDeContacto", contacto.getTipodecontacto())
                .append("origen", contacto.getOrigen())
                .append("tareas", contacto.getTareas())
                .append("comentarios", contacto.getComentarios());

        obtenerColeccion().insertOne(document);

    }

    public Contacto obtenerContactoPorNombre(String nombres) {
        MongoCursor<Document> cursor = obtenerColeccion().find(eq("nombres", nombres)).iterator();
        try {
            if (cursor.hasNext()) {
                Document document = cursor.next();
                Contacto contacto = new Contacto();
                contacto.setNombres(document.getString("nombres"));
                contacto.setApellidos(document.getString("apellidos"));
                contacto.setEmail(document.getString("email"));
                contacto.setCelular(document.getString("celular"));
                contacto.setFechadenacimiento(document.getString("fechadenacimiento"));
                contacto.setDireccion(document.getString("direccion"));
                contacto.setTipodecontacto(document.getString("tipoDeContacto"));
                contacto.setOrigen(document.getString("origen"));
                return contacto;
            }
        } finally {
            cursor.close();
        }
        return null;
    }

    public Contacto borrarContacto(String nombres) {
        MongoCursor<Document> cursor = obtenerColeccion().find(eq("nombres", nombres)).iterator();
        try {
            if (cursor.hasNext()) {
                Document document = cursor.next();

                Contacto contacto = new Contacto();
                contacto.setNombres(document.getString("nombres"));
                contacto.setApellidos(document.getString("apellidos"));
                contacto.setEmail(document.getString("email"));
                contacto.setCelular(document.getString("celular"));
                contacto.setFechadenacimiento(document.getString("fechadenacimiento"));
                contacto.setDireccion(document.getString("direccion"));
                contacto.setTipodecontacto(document.getString("tipoDeContacto"));
                contacto.setOrigen(document.getString("origen"));
                obtenerColeccion().findOneAndDelete(document);

            }
        } finally {
            cursor.close();
        }
        return null;
    }

  
    public Contacto actualizarContacto(String nombres, Contacto objContacto) {

        Bson filter = Filters.eq("nombres", nombres);
        Bson update = Updates.combine(new Document("$set", new Document("nombres", objContacto.getNombres())
                .append("nombres", objContacto.getNombres())
                .append("apellidos", objContacto.getApellidos())
                .append("email", objContacto.getEmail())
                .append("celular", objContacto.getCelular())
                .append("fechaDeNacimiento", objContacto.getFechadenacimiento())
                .append("direccion", objContacto.getDireccion())
                .append("origen", objContacto.getOrigen())
                .append("tipoDeContacto", objContacto.getTipodecontacto())
                .append("tareas", objContacto.getTareas())
                .append("comentarios", objContacto.getComentarios())
        ));
        obtenerColeccion().findOneAndUpdate(filter, update);
      
        return objContacto;
    }
    private MongoCollection obtenerColeccion() {
        return mongoCliente.getDatabase("CRM").getCollection("RegistroContacto");

    }

    

    public Contacto verificarSiExisteContacto(Contacto contacto) {
        if (contacto != null) {
            return contacto;
        } else {
            return null;
        }
    }

}
