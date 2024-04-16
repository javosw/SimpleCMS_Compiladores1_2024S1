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
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author JavierOswaldo
 */
public class ServicioThread implements Runnable
{
    Socket miCliente;
    Charset miCharset = StandardCharsets.UTF_8;

    public ServicioThread(Socket miCliente)
    {
        this.miCliente = miCliente;
    }
    
    @Override
    public void run()
    {
        System.out.println("@cms");
        
        StringBuilder readerString = new StringBuilder();
        String writerString;
        try
        {
            InputStream readerStream = miCliente.getInputStream();
            OutputStream writerStream = miCliente.getOutputStream();
            
            InputStreamReader readerStream2 = new InputStreamReader(readerStream, miCharset);
            BufferedReader miReader = new BufferedReader(readerStream2);
            
            boolean autoFlush = true;
            PrintWriter miWriter = new PrintWriter(writerStream, autoFlush, miCharset);
            
            while (true)
            { 
                String l = miReader.readLine();
                if (l == null) break;
                System.out.println("  @miReader.readLine="+l);
                
                readerString.append(l);
                try
                {
                    writerString = new SimpleDateFormat("HH:mm:ss:SSSS").format(new Date().getTime());
                    miWriter.print(writerString);
                    miWriter.flush();
                }
                catch (Exception e) { System.out.println("  @!!miReader.readLine(): " + e.getMessage()); }
            }
            System.out.println("  @readString="+readerString.toString());
        }
        catch (Exception e) { System.out.println("  @run: " + e.getMessage()); }
    }
}
