import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuPropietario {

    public void mostrarMenuPropietario() {
        System.out.println("1- Cambiar contraseña");
        System.out.println("2- Añadir producto");
        System.out.println("3- Salir del menu");
        System.out.println("Seleccione una de las opciones anteriores");
    }

    public void salirMenuPropietario() {
        System.out.println("\n Ha salido del menu");
    }

    public void opcionesMenuPropietario() {
        Scanner teclado = new Scanner(System.in);
        Tienda tienda = new Tienda();
        int opcion = 0;
        String auxiliar;
        do {
            try {
                mostrarMenuPropietario();
                opcion = teclado.nextInt();
                if(opcion < 1 || opcion > 3){
                    teclado.nextLine();
                    System.err.println("Opción ingresada no valida, por favor intente de nuevo");
                }
                switch (opcion){
                    case 1:{
                        tienda.opcion1MenuPropietario();
                        break;
                    }
                    case 2:{
                        tienda.opcion2MenuPropietario();
                        break;
                    }

                }
            } catch (InputMismatchException | IOException e) {
                teclado.nextLine();
                System.err.println("Opción ingresada no valida, por favor intente de nuevo");
            }
        }while (opcion != 3) ;
        salirMenuPropietario();
    }
}