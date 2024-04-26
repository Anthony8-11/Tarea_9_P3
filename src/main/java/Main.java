import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        TelefonoCRUD crud = new TelefonoCRUD();
        crud.llenarColeccion(); // Llenar la colección con varios documentos



        Scanner scanner = new Scanner(System.in);

        int opcion;
        do {
            System.out.println("\nMenú de opciones:");
            System.out.println("1. Crear teléfono");
            System.out.println("2. Leer todos los teléfonos");
            System.out.println("3. Actualizar teléfono");
            System.out.println("4. Eliminar teléfono");
            System.out.println("5. Salir");
            System.out.print("Ingrese su opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea después del entero

            switch (opcion) {
                case 1:
                    crud.llenarColeccion();
                    System.out.println("¡Teléfono(s) creado(s)!");
                    break;
                case 2:
                    System.out.println("Listado de teléfonos:");
                    Nodo head = crud.insertarEnListaEnlazadaConNodo();
                    Nodo current = head;
                    while (current != null) {
                        System.out.println(current.getTelefono());
                        current = current.getNext();
                    }
                    break;
                case 3:
                    crud.actualizarTelefono("123456789012345", "Samsung", "Galaxy S23", "IOS", 8.2, 20, 128, true, 108, true);
                    break;
                case 4:
                    crud.eliminarTelefono("123456789012345");
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
            }
        } while (opcion != 5);

        crud.cerrarConexion(); // Cerrar la conexión con MongoDB
        scanner.close();
    }

}
