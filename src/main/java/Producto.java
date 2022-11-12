public class Producto {
    private String nombre;
    private int precioUnidad;
    private int cantidad;

    public Producto (String nombre,int precioUnidad,int cantidad){
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precioUnidad = precioUnidad;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecioUnidad() {
        return this.precioUnidad;
    }

    public void setPrecioUnidad(int precioUnidad) {
        throw new UnsupportedOperationException();
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public boolean vendido(int numero) {
        if(numero <= cantidad){
            numero -= cantidad;
        }
        else {
            System.out.println("No hay suficientes productos");
            return false;
        }
        return true;
    }

    public String toString() {
        return this.nombre+","+this.precioUnidad+","+this.cantidad;
    }
}