import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuCliente {

    public void mostrarMenuCliente() {
        System.out.println("1- Mostrar todos los productos");
        System.out.println("2- Buscar producto");
        System.out.println("3- Comprar producto");
        System.out.println("4- Salir del menu");
        System.out.println("Seleccione una de las opciones anteriores");
    }

    public void salirMenuCliente() {
        System.out.println("\n Ha salido del menu");
    }

    public void opcionesMenuCliente() {
        Scanner teclado = new Scanner(System.in);
        Tienda tienda = new Tienda("Casino Las Araucarias");
        int opcion = 0;
        do {
            try{
                mostrarMenuCliente();
                opcion = teclado.nextInt();
                if(opcion < 1 || opcion > 4){
                    teclado.nextLine();
                    System.err.println("Opción ingresada no valida, por favor intente de nuevo");
                }
                switch (opcion){
                    case 1:{
                        GestorDatos.leerArchivoProductos();
                        tienda.mostrarProductos();
                        break;
                    }
                    case 2:{
                        String nombreProducto;
                        teclado.nextLine();
                        System.out.println("Ingrese el nombre del producto que desea buscar : ");
                        nombreProducto = teclado.nextLine();
                        Producto producto = tienda.buscarProducto(nombreProducto);
                        if(producto != null){
                            System.out.println(" Nombre producto = "+producto.getNombre()+"\n Cantidad en inventario = "+producto.getCantidad()+"\n Precio del producto = "+producto.getPrecioUnidad()+"$\n");
                        }
                        break;
                    }
                    case 3:{
                        int cantidad;
                        double vendido;
                        String nombreProducto;
                        teclado.nextLine();
                        System.out.println("Ingrese el nombre del producto que desea comprar : ");
                        nombreProducto = teclado.nextLine();
                        System.out.println("Ingrese la cantidad que desea comprar : ");
                        cantidad = teclado.nextInt();
                        vendido = tienda.venderProducto(nombreProducto,cantidad);
                        if(vendido != 0 && vendido != 1){
                            System.out.println("El precio de la compra es de : "+vendido+"$.\n");
                        }
                        break;
                    }
                }
            }catch(InputMismatchException e){
                teclado.nextLine();
                System.err.println("Opción ingresada no valida, por favor intente de nuevo");
            }
        }while(opcion != 4);
        salirMenuCliente();
    }
}