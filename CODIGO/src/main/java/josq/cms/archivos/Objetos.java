/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package josq.cms.archivos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author JavierOswaldo
 */
public class Objetos
{
    public static void writeObjeto(String file, Object myObject) throws Exception
    {
        FileOutputStream myFileStream = new FileOutputStream(file);
        ObjectOutputStream myObjectStream = new ObjectOutputStream(myFileStream);

        myObjectStream.writeObject(myObject);

        myObjectStream.close();    
    }
    
    
    public static Object readObject(String file) throws Exception
    {
        FileInputStream myFileStream = new FileInputStream(file);
        ObjectInputStream myObjectStream = new ObjectInputStream(myFileStream);

        Object myObject = myObjectStream.readObject();

        myObjectStream.close();
        
        return myObject;
    }
}
