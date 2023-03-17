import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuCliente {

    public void mostrarMenuCliente() {
        System.out.println("1- Mostrar todos los productos");
        System.out.println("2- Buscar producto");
        System.out.println("3- Agregar productos");
        System.out.println("4- Comprar productos");
        System.out.println("5- Salir del menú");
        System.out.println("Seleccione una de las opciones anteriores");
    }

    public void salirMenuCliente() {
        System.out.println("\n Ha salido del menu");
    }

    public void opcionesMenuCliente() {
        Scanner teclado = new Scanner(System.in);
        ArrayList<Producto> productosParaComprar = new ArrayList<>();
        Tienda tienda = new Tienda();
        int opcion = 0;
        do {
            try{
                mostrarMenuCliente();
                opcion = teclado.nextInt();
                if(opcion < 1 || opcion > 5){
                    teclado.nextLine();
                    System.err.println("Opción ingresada no valida, por favor intente de nuevo");
                }
                switch (opcion){
                    case 1:{
                        tienda.opcion1MenuCliente();
                        break;
                    }
                    case 2:{
                        tienda.opcion2MenuCliente();
                        break;
                    }
                    case 3:{
                        productosParaComprar = tienda.opcion3MenuCliente();
                        break;
                    }
                    case 4:{
                        if(productosParaComprar == null){
                            System.out.println("No ha agregado ningún producto aún");
                        }else{
                            tienda.opcion4MenuCliente(productosParaComprar);
                        }
                        break;
                    }
                }
            }catch(InputMismatchException e){
                teclado.nextLine();
                System.err.println("Opción ingresada no valida, por favor intente de nuevo");
            }
        }while(opcion != 5);
        salirMenuCliente();
    }
}