/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package josq.cms.sockets;

import java.net.ServerSocket;
import java.net.Socket;

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
            
            ServicioThread tareas = new ServicioThread(miCliente);
            new Thread(tareas).start();
        }
    }
}
