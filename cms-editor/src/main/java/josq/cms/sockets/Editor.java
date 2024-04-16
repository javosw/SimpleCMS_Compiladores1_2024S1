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
            cliente.consumir("localhost", 7654,"[mi pubicacion]");
            cliente.consumir("localhost", 7654,"[PAGINA-NUEVA]");
            cliente.consumir("localhost", 7654,"[<accion></accion>]");
        }
        catch (Exception e) { System.out.println("@Editor.main: "+e.getMessage()); }
    }
    
    
    public void consumir(String host, int port, String writeString) throws Exception
    {
        Socket miCliente = new Socket(host, port);
        System.out.println("@editor");
        
        String readString = "";
        try
        {
            OutputStream writeStream = miCliente.getOutputStream();
            //OutputStreamWriter miWriter = new OutputStreamWriter(writeStream);
            //BufferedWriter miBufferedWriter = new BufferedWriter(miWriter);
            boolean autoFlush = true;
            PrintWriter miWriter = new PrintWriter(writeStream, autoFlush, StandardCharsets.UTF_16);
            miWriter.print(writeString);
            //miWriter.flush();
            
            System.out.println("  @writeString=" + writeString);
            miWriter.close();
        }
        catch (Exception e) { System.out.println("  @writeStream: "+e.getMessage()); }
        
        try
        {
            InputStream readStream = miCliente.getInputStream();
            //InputStreamReader miReader = new InputStreamReader(readStream);
            //BufferedReader miBufferedReader = new BufferedReader(miReader);
            readString = new String(readStream.readAllBytes(), StandardCharsets.UTF_16);
            
            System.out.println("  @readString="+readString);
        }
        catch (Exception e) { System.out.println("  @readStream: "+e.getMessage()); }
        
        miCliente.close();
    }
}
