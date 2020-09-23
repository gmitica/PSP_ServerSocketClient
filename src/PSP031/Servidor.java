package PSP031;

import PSP03.Global;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 * Clase que corresponde al Ejecricio 1
 * Clase encargada de verificar la entrada del usuario.
 * @author george
 */
public class Servidor {

    public static void main(String[] args) {
        new Servidor();
    }

    public Servidor() {
        try {
            ServerSocket sk = new ServerSocket(Global.PUERTO_1);
            Random r = new Random();
            int num = r.nextInt(101);
            System.out.println(num);
            Socket s = sk.accept();
            OutputStream os1 = s.getOutputStream();
            DataOutput flujo_salida = new DataOutputStream(os1);
            flujo_salida.writeUTF("Esperando número...");

            InputStream is = s.getInputStream();
            DataInputStream flujo_entrada = new DataInputStream(is);

            
            while (true) {
                int input = flujo_entrada.readInt();
                if (input == num) {
                    flujo_salida.writeUTF("FIN");
                    break;
                } else if(input<num){
                    flujo_salida.writeUTF("El número es mayor");
                } else{
                    flujo_salida.writeUTF("El número es menor");
                }
            }

            s.close();;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
