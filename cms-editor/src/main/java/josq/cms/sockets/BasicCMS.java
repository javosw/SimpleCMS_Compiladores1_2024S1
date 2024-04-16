/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package josq.cms.sockets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author JavierOswaldo
 */
public class BasicCMS
{

    public static void main(String[] args)
    {
        try
        {
            BasicCMS servidor = new BasicCMS();
            servidor.servir(7654);
        }
        catch (Exception e)
        {
            System.out.println("@BasicCMS.main: " + e.getMessage());
        }
    }

    ServerSocket miServer;

    public void servir(int port) throws Exception
    {
        miServer = new ServerSocket(port);

        while (true)
        {
            Socket miCliente = miServer.accept();
            System.out.println("@while");
            
            String readString = "";
            String writeString = "[producto]";
            try
            {
                InputStream readStream = miCliente.getInputStream();
                //InputStreamReader miReader = new InputStreamReader(readStream);
                //BufferedReader miBufferedReader = new BufferedReader(miReader);
                
                readString = new String(readStream.readAllBytes(), StandardCharsets.UTF_16);
                System.out.println("  @readString="+readString);
            }
            catch (Exception e) { System.out.println("  @readStream: " + e.getMessage()); }
            
            try
            {
                OutputStream writeStream = miCliente.getOutputStream();
                //OutputStreamWriter miWriter = new OutputStreamWriter(writeStream);
                //BufferedWriter miBufferedWriter = new BufferedWriter(miWriter);
                boolean autoFlush = true;
                PrintWriter miWriter = new PrintWriter(writeStream, autoFlush, StandardCharsets.UTF_16);
                miWriter.print(writeString);
                //miWriter.flush();

                System.out.println("  @writeString="+writeString);
                miWriter.close();
            }
            catch (Exception e) { System.out.println("  @writeStream: " + e.getMessage()); }
            
            //miCliente.close();
        }
        //miServer.close();

    }
}
