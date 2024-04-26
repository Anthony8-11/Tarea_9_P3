public class Nodo {
    private Telefono telefono;
    private Nodo next;

    public Nodo(Telefono telefono) {
        this.telefono = telefono;
        this.next = null;
    }

    public Telefono getTelefono() {
        return telefono;
    }

    public Nodo getNext() {
        return next;
    }

    public void setNext(Nodo next) {
        this.next = next;
    }
}
