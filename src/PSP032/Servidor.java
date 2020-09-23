package PSP032;
import PSP03.Global;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
/**
 * Clase correspondiente al ejercicio 2
 * Si el nombre del fichero que ha enviado el cliente existe, se le enviara el fichero.
 * @author george
 */
public class Servidor {

    public static void main(String[] args) {
        new Servidor();
    }

    public Servidor() {
        try {
            ServerSocket sc = new ServerSocket(Global.PUERTO_2);
            Socket s = sc.accept();
            OutputStream os = s.getOutputStream();
            DataOutput salida = new DataOutputStream(os);
            InputStream is = s.getInputStream();
            DataInputStream entrada = new DataInputStream(is);
            String dato = entrada.readUTF();
            File f = new File(System.getProperty("user.home") + File.separator + dato);
            if (f.exists()) {
                salida.writeUTF("OK");
                byte b[] = Files.readAllBytes(f.toPath());
                salida.writeInt(b.length);
                os.write(b, 0, b.length); 
                System.out.println("FIN");
            }else{
                 salida.writeUTF("BAD");
            }
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
