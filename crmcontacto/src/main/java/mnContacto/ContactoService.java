package mnContacto;

import com.mongodb.BasicDBObject;
import modelos.Contacto;
import java.util.ArrayList;
import java.util.List;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import io.quarkus.runtime.StartupEvent;
import io.vertx.core.http.HttpMethod;
import io.vertx.mutiny.ext.web.Router;
import io.vertx.mutiny.ext.web.handler.CorsHandler;
import org.bson.Document;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import java.util.Arrays;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

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
                contacto.setfechaDeNacimiento(document.getString("fechadenacimiento"));
                contacto.setDireccion(document.getString("direccion"));
                contacto.setTipoDeContacto(document.getString("tipoDeContacto"));
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
                .append("fechadenacimiento", contacto.getfechaDeNacimiento())
                .append("direccion", contacto.getDireccion())
                .append("tipoDeContacto", contacto.getTipoDeContacto())
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
                contacto.setfechaDeNacimiento(document.getString("fechadenacimiento"));
                contacto.setDireccion(document.getString("direccion"));
                contacto.setTipoDeContacto(document.getString("tipoDeContacto"));
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
                contacto.setfechaDeNacimiento(document.getString("fechadenacimiento"));
                contacto.setDireccion(document.getString("direccion"));
                contacto.setTipoDeContacto(document.getString("tipoDeContacto"));
                contacto.setOrigen(document.getString("origen"));
                obtenerColeccion().findOneAndDelete(document);

            }
        } finally {
            cursor.close();
        }
        return null;
    }

    public void actualizarContacto(String nombres, Contacto objContacto) {

        Bson filter = Filters.eq("nombres", nombres);
        Bson update = Updates.combine(new Document("$set", new Document("nombres", objContacto.getNombres())));
        obtenerColeccion().replaceOne(
                filter,
                new Document("nombres", nombres)
                        .append("nombres", objContacto.getNombres())
                        .append("apellidos", objContacto.getApellidos())
                        .append("email", objContacto.getEmail())
                        .append("celular", objContacto.getCelular())
                        .append("fechaDeNacimiento", objContacto.getfechaDeNacimiento())
                        .append("direccion", objContacto.getDireccion())
                        .append("origen", objContacto.getOrigen())
                        .append("tipoDeContacto", objContacto.getTipoDeContacto())
                        .append("tareas", objContacto.getTareas())
                        .append("comentarios", objContacto.getComentarios())
        );

    }

    private MongoCollection obtenerColeccion() {
        return mongoCliente.getDatabase("CRM").getCollection("RegistroContacto");

    }

    public void configureCors(@Observes StartupEvent ev, Router router) {
        CorsHandler corsHandler = CorsHandler.create("http://localhost:4200")
                .allowedMethod(HttpMethod.GET)
                .allowedMethod(HttpMethod.POST)
                .allowedMethod(HttpMethod.PUT)
                .allowedMethod(HttpMethod.DELETE);

        router.route().handler(corsHandler);
    }

}
