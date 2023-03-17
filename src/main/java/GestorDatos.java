import java.awt.image.renderable.RenderableImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
public class GestorDatos {
    public static boolean registrarDatos(List objetos, String direccionArchivo) {
        try {
            File file = new File(direccionArchivo);
            Files.deleteIfExists(Paths.get(direccionArchivo));
            File fichero = new File(direccionArchivo);
            fichero.createNewFile();
            FileWriter fw = new FileWriter(fichero, true);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Object objeto : objetos) {
                bw.write(objeto.toString());
                bw.newLine();
            }
            bw.close();
            fw.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public static ArrayList<Producto> leerArchivoProductos() {
        ArrayList<Producto> listaDeProductos = new ArrayList<>();
        String textoArchivo = "";
        try {
            File archivo = new File("Productos.txt");
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);
            while ((textoArchivo = br.readLine()) != null) {
                String[] data = textoArchivo.split(",");
                listaDeProductos.add(new Producto(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2])));
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            System.out.println("Documento no disponible, favor contactar con administrador");
        }
        return listaDeProductos;
    }

}
