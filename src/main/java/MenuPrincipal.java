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
        Tienda tienda = new Tienda();
        MenuCliente menuCliente = new MenuCliente();
        MenuPropietario menuPropietario = new MenuPropietario();
        int opcion = 0;
        String nuevaContraseña;
        nuevaContraseña = tienda.leerContraseña("Contraseña.txt");
        do{
            if(tienda.getContraseña() != null){
                    try{
                        mostrarMenuPrincipal();
                        opcion = teclado.nextInt();
                        if(opcion < 1 || opcion > 3){
                            teclado.nextLine();
                            System.err.println("Opción ingresada no valida, por favor intente de nuevo");
                        }
                        switch (opcion){
                            case 1: {
                                int contador = 0;
                                do{
                                    String auxiliar;
                                    System.out.println("Ingrese la contraseña : ");
                                    auxiliar = teclado.next();
                                    tienda.setContraseña(tienda.leerContraseña("Contraseña.txt"));
                                    if (tienda.getContraseña().equals(auxiliar)) {
                                        menuPropietario.opcionesMenuPropietario();
                                        break;
                                    } else {
                                        contador++;
                                        if(contador != 3){
                                            System.out.println("La contraseña no coincide, intente de nuevo (Tiene "+(3-contador)+" oportunidades)");
                                        }else {
                                            System.out.println("La contraseña no coincide");
                                        }
                                    }
                                }while(contador < 3);
                                break;
                            }
                            case 2:{
                                menuCliente.opcionesMenuCliente();
                                break;
                            }
                            case 3:{
                                salirMenuPrincipal();
                                break;
                            }
                        }
                    }catch(InputMismatchException e) {
                        teclado.nextLine();
                        System.err.println("Opción ingresada no valida, por favor intente de nuevo");
                    }
            }else{
                do{
                    System.out.println("Ingrese la contraseña para poder ingresar al menú propietario : ");
                    nuevaContraseña = teclado.nextLine();
                    tienda.setContraseña(nuevaContraseña);
                    tienda.guardarContraseña(nuevaContraseña);
                }while(tienda.getContraseña() == null);
            }
        }while (opcion != 3);
    }
}
