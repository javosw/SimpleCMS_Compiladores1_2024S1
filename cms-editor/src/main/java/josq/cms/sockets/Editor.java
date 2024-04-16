/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package josq.cms.sockets;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.Charset;
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
            cliente.publicar("localhost", 7654,"[mi pubicacion]");
            //cliente.consumir("localhost", 7654,"[asaasddas 79879878978]");

            Editor cliente2 = new Editor();
            cliente2.publicar("localhost", 7654,"[PAGINA-NUEVA]");
        }
        catch (Exception e) { System.out.println("@Editor.main: "+e.getMessage()); }
    }

    Charset miCharset = StandardCharsets.UTF_8;

    Socket miCliente;
    
    public void publicar(String host, int port, String writeString) throws Exception
    {
        miCliente = new Socket(host, port);
        System.out.println("@editor");
        
        StringBuilder readerString = new StringBuilder();
        
        try
        {
            OutputStream writerStream = miCliente.getOutputStream();
            InputStream readerStream = miCliente.getInputStream();
            
            boolean autoFlush = true;
            PrintWriter miWriter = new PrintWriter(writerStream, autoFlush, miCharset);
                
            InputStreamReader readerStream2 = new InputStreamReader(readerStream, miCharset);
            BufferedReader miReader = new BufferedReader(readerStream2);

            miWriter.print(writeString);
            System.out.println("  @writeString=" + writeString);
            

            while (true)
            { 
                String l = miReader.readLine();
                if (l == null) break;
                readerString.append(l);
            }
            System.out.println("  @readString="+readerString.toString());
            
            miCliente.close();
        }
        catch (Exception e) { System.out.println("  @publicar: "+e.getMessage()); }
    }
}
