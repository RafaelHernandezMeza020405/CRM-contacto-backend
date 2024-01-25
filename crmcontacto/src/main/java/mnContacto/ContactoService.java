package mnContacto;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.eq;
import org.bson.types.ObjectId;
import io.quarkus.runtime.StartupEvent;
import io.vertx.core.http.HttpMethod;
import io.vertx.mutiny.ext.web.Router;
import io.vertx.mutiny.ext.web.handler.CorsHandler;
import org.bson.Document;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

@ApplicationScoped
public class ContactoService {
        @Inject MongoClient mongoCliente;
        
        
        public List<Contacto> lista(){
            List<Contacto> listaDeContacto = new ArrayList<>();
            MongoCursor<Document> cursor = obtenerColeccion().find().iterator();
            try {
                while(cursor.hasNext()) {
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
                    listaDeContacto.add(contacto);
                }
                
            }finally {
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
                    .append("origen", contacto.getOrigen());
                    
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


        
        
        private MongoCollection obtenerColeccion() {
            return mongoCliente.getDatabase("CRM").getCollection("RegistroContacto");
            
        }

        public void configureCors(@Observes StartupEvent ev, Router router){
            CorsHandler corsHandler = CorsHandler.create("http://localhost:4200")
            .allowedMethod(HttpMethod.GET)
            .allowedMethod(HttpMethod.POST)
            .allowedMethod(HttpMethod.PUT)
            .allowedMethod(HttpMethod.DELETE);
            
            router.route().handler(corsHandler);
        }    
    
}
