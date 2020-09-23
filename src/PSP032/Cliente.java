package PSP032;
import PSP03.Global;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
/**
 * Clase correspondiente al ejercicio 2
 * Esta clase enviara al servidor el nombre de fichero para descargarlo y leerlo
 * @author george
 */
public class Cliente {

    public static void main(String[] args) {
        new Cliente();
    }

    public Cliente() {
        try {
            Socket s = new Socket(Global.HOST, Global.PUERTO_2);
            OutputStream os = s.getOutputStream();
            DataOutput salida = new DataOutputStream(os);

            System.out.println("Introduzca el nombre del ");
            Scanner sc = new Scanner(System.in);
            String nameFile = sc.nextLine();
            salida.writeUTF(nameFile);

            InputStream is = s.getInputStream();
            DataInputStream entrada = new DataInputStream(is);
            String ent1 = entrada.readUTF();
            if(ent1!=null && ent1.equalsIgnoreCase("OK")){
                int longitud = entrada.readInt();
                byte b[] = new byte[longitud];
                String path = System.getProperty("user.home") + File.separator + "2" + nameFile;
                FileOutputStream fos = new FileOutputStream(path);
                is.read(b, 0, b.length);
                fos.write(b, 0, b.length);

                BufferedReader br = new BufferedReader(new FileReader(new File(path)));
                String ln;
                while ((ln = br.readLine()) != null) {
                    System.out.println(ln);
                }
            }else{
                System.out.println("ERROR");
            }
             s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}