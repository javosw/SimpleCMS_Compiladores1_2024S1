/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package josq.cms.archivos;

import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author JavierOswaldo
 */
public class Texto
{
    public static void newTexto(String file, String txt) throws Exception
    {
        FileWriter myWriter = new FileWriter(file,StandardCharsets.UTF_8);
        myWriter.write(txt);
        myWriter.close();
    }
    public static void addTexto(String file, String txt) throws Exception
    {
        FileWriter myWriter = new FileWriter(file,StandardCharsets.UTF_8,true);
        myWriter.write(txt);
        myWriter.close();
    }

}
