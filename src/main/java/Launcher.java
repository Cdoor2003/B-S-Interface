import java.io.IOException;

public class Launcher {
    public static void main(String[] args) throws IOException {
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        Tienda tienda = new Tienda("Casino Las Araucarias");
        System.out.println("************************************");
        System.out.println("Bienvenidos a "+tienda.getNombreTienda());
        System.out.println("************************************");
        menuPrincipal.opcionesMenuPrincipal();
    }
}