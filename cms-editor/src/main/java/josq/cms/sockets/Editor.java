/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package josq.cms.sockets;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JavierOswaldo
 */
public class Editor
{
    public static void main(String[] args) 
    {
        try
        {
            Editor cliente = new Editor();
            cliente.consumir("localhost", 7654, "[sockets en java]");
            cliente.consumir("localhost", 7654,"12321");
            cliente.consumir("localhost", 7654,"asda6sd66565");
        }
        catch (Exception e) { System.out.println("@Editor.main: "+e.getMessage()); }
    }
    
    
    public void consumir(String host, int port, String mensaje) throws Exception
    {
        Socket miCliente = new Socket(host, port);
        System.out.println("@miCliente.isConnected(): "+miCliente.isConnected());

        try
        {
            OutputStream writeStream = miCliente.getOutputStream();
            //OutputStreamWriter miWriter = new OutputStreamWriter(writeStream);
            //BufferedWriter miBufferedWriter = new BufferedWriter(miWriter);
            PrintWriter miWriter = new PrintWriter(writeStream, true, StandardCharsets.UTF_16);
            miWriter.print(mensaje);
            miWriter.close();

            InputStream readStream = miCliente.getInputStream();
            //InputStreamReader miReader = new InputStreamReader(readStream);
            //BufferedReader miBufferedReader = new BufferedReader(miReader);
            String mensajeRecibido = new String(readStream.readAllBytes(), StandardCharsets.UTF_16);
            
            String log = "@cliente: "+mensajeRecibido;
            System.out.println(log);
        }
        catch (Exception e) { System.out.println("@consumir: "+e.getMessage()); }
        
        miCliente.close();
    }
}
