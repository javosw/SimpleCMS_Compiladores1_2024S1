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
            System.out.println("@while");
            try
            {
                Socket miCliente = miServer.accept();
                System.out.println("@miServer.accept()");

                InputStream readStream = miCliente.getInputStream();
                //InputStreamReader miReader = new InputStreamReader(readStream);
                //BufferedReader miBufferedReader = new BufferedReader(miReader);
                System.out.println("@readStream");
                String mensajeCliente = new String(readStream.readAllBytes(), StandardCharsets.UTF_16);
                System.out.println("@mensajeCliente");

                OutputStream writeStream = miCliente.getOutputStream();
                //OutputStreamWriter miWriter = new OutputStreamWriter(writeStream);
                //BufferedWriter miBufferedWriter = new BufferedWriter(miWriter);
                PrintWriter miWriter = new PrintWriter(writeStream, true, StandardCharsets.UTF_16);

                String mensajeServidor = "@servidor: cliente=" + mensajeCliente;
                miWriter.print(mensajeServidor);
                System.out.println(mensajeServidor);
                //miCliente.close();
            }
            catch (Exception e)
            {
                System.out.println("@servir: " + e.getMessage());
            }
        }
        //miServer.close();

    }
}
