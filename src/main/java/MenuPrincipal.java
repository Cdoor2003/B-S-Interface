import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuPrincipal {

    public void mostrarMenuPrincipal(){
        System.out.println("1- Menu propietario");
        System.out.println("2- Menu cliente");
        System.out.println("3- Salir");
        System.out.println("Seleccione una de las opciones");
    }
    public void salirMenuPrincipal() {
        System.out.println("\n Ha salido del menu");
    }
    public void opcionesMenuPrincipal() throws IOException {
        Scanner teclado = new Scanner(System.in);
        MenuCliente menuCliente = new MenuCliente();
        MenuPropietario menuPropietario = new MenuPropietario();
        int opcion = 0;
        String nuevaContraseña;
        nuevaContraseña = menuPropietario.leerContraseña("Contraseña.txt");
        do{
            if(menuPropietario.getContraseña() != null){
                do{
                    try{
                        mostrarMenuPrincipal();
                        opcion = teclado.nextInt();
                        if(opcion < 1 || opcion > 3){
                            teclado.nextLine();
                            System.err.println("Opción ingresada no valida, por favor intente de nuevo");
                        }
                        switch (opcion){
                            case 1: {
                                menuPropietario.opcionesMenuPropietario();
                                break;
                            }
                            case 2:{
                                menuCliente.opcionesMenuCliente();
                                break;
                            }
                        }
                    }catch(InputMismatchException e) {
                        teclado.nextLine();
                        System.err.println("Opción ingresada no valida, por favor intente de nuevo");
                    }
                }while(opcion != 3);
                salirMenuPrincipal();
            }else{
                do{
                    System.out.println("Ingrese la contraseña para poder ingresar al menú propietario : ");
                    nuevaContraseña = teclado.nextLine();
                    menuPropietario.setContraseña(nuevaContraseña);
                    menuPropietario.guardarContraseña(nuevaContraseña);
                }while(menuPropietario.getContraseña() == null);
            }
        }while (opcion != 3);
    }
}
