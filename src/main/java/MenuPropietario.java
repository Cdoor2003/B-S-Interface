import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuPropietario {
    private String contraseña;

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
        Tienda tienda = new Tienda("Casino Las Araucarias");
        int opcion = 0;
        String auxiliar;
        do {
            System.out.println("Ingrese la contraseña : ");
            auxiliar = teclado.next();
            contraseña = leerContraseña("Contraseña.txt");
            if (contraseña.equals(auxiliar)) {
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
                                cambiarContraseña();
                                break;
                            }
                            case 2:{
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
                                break;
                            }
                        }
                    } catch (InputMismatchException | IOException e) {
                        teclado.nextLine();
                        System.err.println("Opción ingresada no valida, por favor intente de nuevo");
                    }
                }while (opcion != 3) ;
                salirMenuPropietario();
            } else {
                System.out.println("Contraseña incorrecta");
                auxiliar = "";
            }
        } while (!contraseña.equals(auxiliar));
    }
    public void cambiarContraseña() throws IOException {
        Scanner teclado = new Scanner(System.in);
        String auxiliar;
        String nuevaContraseña;
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
            System.out.println("La contraseña no coincide");
        }
    }
    public void guardarContraseña(String nuevaContraseña) throws IOException {
        boolean lineaVacia = false;
        File file = new File("Contraseña.txt");
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
    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
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
}