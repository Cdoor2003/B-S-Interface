import java.util.ArrayList;

public class Tienda {
    private String nombreTienda;
    private static ArrayList<Producto> listaProductos;

    public Tienda(String nombreTienda){
        this.nombreTienda = nombreTienda;
        this.listaProductos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        String nombre = producto.getNombre();
        if(!listaProductos.equals(nombre)){
            listaProductos.add(producto);
            GestorDatos.registrarDatos(getListaProductos(),"Productos.txt");
            System.out.println("\n Se ha agregado un producto al inventario");
        }
        else {
            System.out.println("\n El producto ya existe en el inventario");
        }
    }

    public Producto buscarProducto(String nombre) {
        Producto producto;
        GestorDatos.leerArchivoProductos();
        if(listaProductos.equals(nombre)){
            producto = (Producto) listaProductos.get(Integer.parseInt(nombre));
            System.out.println("\n El articulo ha sido encontrado");
            return producto;
        }
        else{
            System.out.println("\n El articulo no ha sido encontrado");
        }
        return null;
    }

    public double venderProducto(String nombre, int numero) {
        Producto producto = buscarProducto(nombre);
        double vendido = 0;
        if(producto == null){
            vendido = -1;
        } else if (producto.vendido(numero)) {
            vendido = numero * producto.getPrecioUnidad();
            if(producto.getCantidad() == 0){
                listaProductos.remove(nombre);
            }
        }
        return vendido;
    }

    public void mostrarProductos(){
        for(Producto producto : this.getListaProductos()){
            System.out.println("Producto : "+producto.getNombre()+"\nCantidad : "+producto.getCantidad()+"\nPrecio : "+producto.getPrecioUnidad()+"$");
        }
    }

    public String getNombreTienda() {
        return this.nombreTienda;
    }

    public void setNombreTienda(String nombreTienda) {
        this.nombreTienda = nombreTienda;
    }

    public static ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(ArrayList<Producto> listaProductos) {
       this.listaProductos = listaProductos;
    }
}