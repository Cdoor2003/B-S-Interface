import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Tienda {
    private String nombreTienda;
    private String contraseña;
    private static ArrayList<Producto> listaProductos;

    public Tienda(){
        this.listaProductos = new ArrayList<>();
    }

    public void opcion1MenuPropietario() throws IOException {
        Scanner teclado = new Scanner(System.in);
        String auxiliar;
        String nuevaContraseña;
        int contador = 0;
        do{
            System.out.println("Ingrese su contraseña actual : ");
            auxiliar = teclado.nextLine();
            if(auxiliar.equals(contraseña)){
                System.out.println("Ingrese la nueva contraseña : ");
                nuevaContraseña = teclado.nextLine();
                if(nuevaContraseña != null && !nuevaContraseña.equals("")){
                    System.out.println("Su nueva contraseña es : "+nuevaContraseña);
                    guardarContraseña(nuevaContraseña);
                }
            }else{
                contador++;
                if(contador != 3){
                    System.out.println("La contraseña no coincide, intente de nuevo (Tiene "+(3-contador)+" oportunidades)");
                }else {
                    System.out.println("La contraseña no coincide");
                }
            }
        }while(contador < 3);
    }

    public void opcion2MenuPropietario(){
        Scanner teclado = new Scanner(System.in);
        Tienda tienda = new Tienda();
        String numero;
        teclado.nextLine();
        System.out.println("Ingrese el nombre del producto : ");
        String nombre = teclado.nextLine();

        System.out.println("Ingrese la cantidad del producto : ");
        numero = teclado.nextLine();
        int cantidad = Integer.parseInt(numero);

        System.out.println("Ingrese el precio por unidad del producto : ");
        numero = teclado.nextLine();
        int precioUnidad = Integer.parseInt(numero);

        Producto producto = new Producto(nombre, precioUnidad, cantidad);
        tienda.agregarProducto(producto);
    }

    public void opcion1MenuCliente(){
        ArrayList<Producto> listaDeProductos = new ArrayList<>();
        listaDeProductos = GestorDatos.leerArchivoProductos();
        for(Producto producto : listaDeProductos){
            System.out.println("Producto : "+producto.getNombre()+"\nCantidad : "+producto.getCantidad()+"\nPrecio : "+producto.getPrecioUnidad()+"$");
        }
    }

    public void opcion2MenuCliente(){
        Scanner teclado = new Scanner(System.in);
        Tienda tienda = new Tienda();
        ArrayList<Producto> listaDeProductos = new ArrayList<>();
        listaDeProductos = GestorDatos.leerArchivoProductos();
        String nombreProducto;
        System.out.println("Ingrese el nombre del producto que desea buscar : ");
        nombreProducto = teclado.nextLine();
        Producto producto = tienda.buscarProducto(nombreProducto,listaDeProductos);
        if(producto != null){
            System.out.println("\nCantidad en inventario = "+producto.getCantidad()+"\nPrecio del producto = "+producto.getPrecioUnidad()+"$\n");
        }
        else{
            System.out.println("El producto no ha sido encontrado.");
        }
    }

    public ArrayList<Producto> opcion3MenuCliente() {
        ArrayList<Producto> listaDeProductos = new ArrayList<>();
        listaDeProductos = GestorDatos.leerArchivoProductos();
        ArrayList<Producto> productosParaComprar = new ArrayList<>();
        Scanner teclado = new Scanner(System.in);
        int opcion = 1;
        while (opcion != 2){
            int cantidad;
            int precio = 0;
            String nombreProducto;
            System.out.println("Ingrese el nombre del producto que desea comprar : ");
            nombreProducto = teclado.nextLine();
            Producto producto = buscarProducto(nombreProducto, listaDeProductos);
            if (producto == null) {
                System.out.println("El producto no se encuentra disponible");
            } else {
                do {
                    System.out.println("Ingrese la cantidad que desea comprar : ");
                    cantidad = teclado.nextInt();
                    if(cantidad <= producto.getCantidad()){
                        precio = cantidad * producto.getPrecioUnidad();
                        System.out.println("El precio de la compra es de : " + precio + "$.\n");
                        productosParaComprar.add(new Producto(producto.getNombre(),producto.getPrecioUnidad(),cantidad));
                    }else{
                        System.out.println("La cantidad ingresada no es valida");
                    }
                    opcion = deseaContinuar();
                } while (precio == 0);
            }
        }
        return productosParaComprar;
    }

    public void opcion4MenuCliente(ArrayList<Producto> productosParaComprar){
        Scanner teclado = new Scanner(System.in);
        int opcion;
        int precioTotal = 0;
        do{
            for (Producto producto: productosParaComprar){
                precioTotal += producto.getPrecioUnidad() * producto.getCantidad();
            }
            System.out.println("El precio total de la compra es: "+precioTotal);
            System.out.println("¿Lo comprará?\n1) Si\n2) No");
            opcion = teclado.nextInt();
            if(opcion < 1 || opcion > 2){
                System.out.println("La opción ingresada no es valida, por favor intente de nuevo");
            }
        }while(opcion <  1 || opcion > 2);
        if(opcion == 1){
            for (Producto producto: productosParaComprar){
                Producto producto1 = buscarProducto(producto.getNombre(),productosParaComprar);
                venderProducto(producto1);
            }
            System.out.println("La compra ha sido realizada con éxito");
        }else{
            System.out.println("La compra ha sido cancelada");
        }
    }

    public void agregarProducto(Producto producto) {
        String nombre = producto.getNombre();
        if(buscarProducto(nombre,listaProductos) == null){
            listaProductos.add(producto);
            GestorDatos.registrarDatos(getListaProductos(),"Productos.txt");
            System.out.println("\n Se ha agregado un producto al inventario");
        }
        else {
            System.out.println("\n El producto ya existe en el inventario");
        }
    }

    public Producto buscarProducto(String nombre, ArrayList<Producto> listaDeProductos) {
        for(Producto producto : listaDeProductos){
            if(producto.getNombre().equals(nombre)){
                return producto;
            }
        }
        return null;
    }

    public void venderProducto(Producto producto) {
        ArrayList<Producto> listaDeProductos = new ArrayList<>();
        listaDeProductos = GestorDatos.leerArchivoProductos();
        Producto producto1 = buscarProducto(producto.getNombre(), listaDeProductos);
        if(producto.getNombre().equals(producto1.getNombre())){
            producto1.setCantidad(producto1.getCantidad()-producto.getCantidad());
            GestorDatos.registrarDatos(listaDeProductos,"Productos.txt");
        }
        if(producto.getCantidad() == 0){
            listaDeProductos.remove(producto1);
            GestorDatos.registrarDatos(listaDeProductos,"Productos.txt");
        }

    }

    public void guardarContraseña(String nuevaContraseña) throws IOException {
        boolean lineaVacia = false;
        File file = new File("Contraseña.txt");
        Files.deleteIfExists(Paths.get("Contraseña.txt"));
        if (!file.exists()) {
            file.createNewFile();
            lineaVacia = true;
        }
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        if (lineaVacia == false) {
            bw.newLine();
        }
        bw.write(nuevaContraseña);
        bw.close();
        fw.close();

    }

    public String leerContraseña(String direccionArchivo) {
        String textoArchivo = "";
        try {
            File archivo = new File(direccionArchivo);
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);
            while ((textoArchivo = br.readLine()) != null) {
                String[] data = textoArchivo.split(",");
                contraseña = data[0];
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Documento no disponible, favor contactar con administrador");
        }
        return contraseña;
    }

    public int deseaContinuar(){
        Scanner teclado = new Scanner(System.in);
        int opcion = 1;
        do{
            try{
                System.out.println("¿Desea agregar otro producto? \n1) Si\n2) No");
                opcion = teclado.nextInt();
                if(opcion < 1 || opcion > 2){
                    teclado.nextLine();
                    System.err.println("Opción ingresada no valida, por favor intente de nuevo");
                }
            }catch(InputMismatchException e){
                teclado.nextLine();
                System.err.println("Opción ingresada no valida, por favor intente de nuevo");
            }
        }while(opcion < 1 || opcion > 2);
        return opcion;
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

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}