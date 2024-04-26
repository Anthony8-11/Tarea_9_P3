import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class TelefonoCRUD {
    private final MongoClient mongoClient;
    private final MongoDatabase database;
    private final MongoCollection<Document> collection;



    public TelefonoCRUD() {
        // Conectar a la base de datos y obtener la colección
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("test");
        collection = database.getCollection("telefonos");
    }

    // Método para llenar la colección con varios documentos
    public void llenarColeccion() {
        // Crear varios objetos Telefono y convertirlos en documentos MongoDB
        collection.insertOne(new Document("marca", "Samsung").append("modelo", "Galaxy S21").append("sistemaOperativo", "Android")
                .append("tamanoPantalla", 6.2).append("memoriaRAM", 8).append("almacenamientoInterno", 128)
                .append("tieneCamara", true).append("resolucionCamara", 108).append("esSmartphone", true)
                .append("imei", "123456789012345"));
        collection.insertOne(new Document("marca", "Apple").append("modelo", "iPhone 12").append("sistemaOperativo", "iOS")
                .append("tamanoPantalla", 6.1).append("memoriaRAM", 4).append("almacenamientoInterno", 64)
                .append("tieneCamara", true).append("resolucionCamara", 12).append("esSmartphone", true)
                .append("imei", "987654321098765"));
        collection.insertOne(new Document("marca", "Xiaomi").append("modelo", "Redmi Note 10").append("sistemaOperativo", "Android")
                .append("tamanoPantalla", 6.43).append("memoriaRAM", 4).append("almacenamientoInterno", 64)
                .append("tieneCamara", true).append("resolucionCamara", 48).append("esSmartphone", true)
                .append("imei", "543216789012345"));
        collection.insertOne(new Document("marca", "Samsung").append("modelo", "Galaxy A52").append("sistemaOperativo", "Android")
                .append("tamanoPantalla", 6.5).append("memoriaRAM", 6).append("almacenamientoInterno", 128)
                .append("tieneCamara", true).append("resolucionCamara", 64).append("esSmartphone", true)
                .append("imei", "123456789012345"));
        collection.insertOne(new Document("marca", "Apple").append("modelo", "iPhone 11").append("sistemaOperativo", "iOS")
                .append("tamanoPantalla", 6.1).append("memoriaRAM", 4).append("almacenamientoInterno", 64)
                .append("tieneCamara", true).append("resolucionCamara", 12).append("esSmartphone", true)
                .append("imei", "987654321098765"));
        collection.insertOne(new Document("marca", "Xiaomi").append("modelo", "Redmi Note 9").append("sistemaOperativo", "Android")
                .append("tamanoPantalla", 6.53).append("memoriaRAM", 3).append("almacenamientoInterno", 64)
                .append("tieneCamara", true).append("resolucionCamara", 48).append("esSmartphone", true)
                .append("imei", "543216789012345"));
    }



    // Método para insertar todos los registros en un ArrayList y LinkedList utilizando la clase Nodo
    public Nodo insertarEnListaEnlazadaConNodo() {

        List<Telefono> telefonos = new ArrayList<>();
        for (Document documento : collection.find()) {
            telefonos.add(documentToTelefono(documento));
        }

        Nodo head = null;
        Nodo current = null;
        for (Document documento : collection.find()) {
            Telefono telefono = documentToTelefono(documento);
            Nodo newNode = new Nodo(telefono);
            if (head == null) {
                head = newNode;
            } else {
                current.setNext(newNode);
            }
            current = newNode;
        }
        return head;
    }


    //Metodo para actualizar un telefono
    public void actualizarTelefono( String imei, String marca, String modelo, String sistemaOperativo, double tamanoPantalla, int memoriaRAM, int almacenamientoInterno, boolean tieneCamara, int resolucionCamara, boolean esSmartphone){
        collection.updateOne(Filters.eq("imei", imei), Updates.set("marca", marca));
        collection.updateOne(Filters.eq("imei", imei), Updates.set("modelo", modelo));
        collection.updateOne(Filters.eq("imei", imei), Updates.set("sistemaOperativo", sistemaOperativo));
        collection.updateOne(Filters.eq("imei", imei), Updates.set("tamanoPantalla", tamanoPantalla));
        collection.updateOne(Filters.eq("imei", imei), Updates.set("memoriaRAM", memoriaRAM));
        collection.updateOne(Filters.eq("imei", imei), Updates.set("almacenamientoInterno", almacenamientoInterno));
        collection.updateOne(Filters.eq("imei", imei), Updates.set("tieneCamara", tieneCamara));
        collection.updateOne(Filters.eq("imei", imei), Updates.set("resolucionCamara", resolucionCamara));
        collection.updateOne(Filters.eq("imei", imei), Updates.set("esSmartphone", esSmartphone));
        System.out.println("Telefono actualizado correctamente.");
    }

    //Metodo para eliminar un telefono
    public void eliminarTelefono(String imei){
        collection.deleteOne(Filters.eq("imei", imei));
        System.out.println("Telefono eliminado correctamente.");
    }

    // Método auxiliar para convertir un documento MongoDB en un objeto Telefono
    private Telefono documentToTelefono(Document documento) {
        return new Telefono(
                documento.getString("marca"),
                documento.getString("modelo"),
                documento.getString("sistemaOperativo"),
                documento.getDouble("tamanoPantalla"),
                documento.getInteger("memoriaRAM"),
                documento.getInteger("almacenamientoInterno"),
                documento.getBoolean("tieneCamara"),
                documento.getInteger("resolucionCamara"),
                documento.getBoolean("esSmartphone"),
                documento.getString("imei")
        );
    }

    // Método para cerrar la conexión con MongoDB
    public void cerrarConexion() {
        mongoClient.close();
    }
}
