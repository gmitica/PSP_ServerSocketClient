package PSP031;
import PSP03.Global;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Clase que corresponde al ejercicio 1.
 * Al inicializar esta clase, solicitara y enviara un número al servidor 
 * hasta que este envié una respuesta "FIN"
 * @author george
 */
public class Cliente {

    public static void main(String[] args) {
        new Cliente();
    }

    public Cliente() {
        try {
            Socket sCliente = new Socket(Global.HOST, Global.PUERTO_1);
            InputStream is = sCliente.getInputStream();
            DataInputStream flujo_entrada = new DataInputStream(is);

            OutputStream os = sCliente.getOutputStream();
            DataOutput flujo_salida = new DataOutputStream(os);
            
            String input;
            while ((input = flujo_entrada.readUTF()) != null && !input.equalsIgnoreCase("FIN")) {
                System.out.println(input);
                System.out.println("Introduce un número");
                Scanner scanner = new Scanner(System.in);
                int numSend = scanner.nextInt();
                flujo_salida.writeInt(numSend);
            }
            System.out.println(input);
            sCliente.close();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
