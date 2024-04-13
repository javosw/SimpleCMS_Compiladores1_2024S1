/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package josq.cms;

import java.util.ArrayList;
import josq.cms.archivos.MyFile;
import josq.cms.lenguajes.controladores.Procesar;
import josq.cms.lenguajes.controladores.Instruccion;
import josq.cms.lenguajes.controladores.acciones.SitioNew;
import josq.cms.lenguajes.automatas.modelos.cup.simbolos.Accion;

/**
 *
 * @author JavierOswaldo
 */
public class Main
{
    public static void main(String[] args) throws Exception 
    {
        //VistaInicio.main(args);
        pruebas3();
    }

    static void pruebas3()
    {
        Instruccion inst= new SitioNew("SITIO66545656");
        inst.ejecutar();
    }
    
    static void pruebas1() 
    {
        String xml = "acciones.xml";
        String ruta = "C:\\Users\\JavierOswaldo\\Desktop\\PROCESADORES-LENGUAJE\\CODIFICACION\\PROYECTO-1\\PRUEBAS\\"+xml;
        try
        {
            ArrayList<Accion> acciones = Procesar.accionesDesdeArchivo(ruta);
            print(acciones.toString());
        }
        catch (Exception ex)
        {
            print("\n<%#%#%#%#%#%#%#% ERRORES %#%#%#%#%#%#%#%>\n");
            ex.printStackTrace();
            //print(ex.getMessage());
        }
        print("<<<<<<<<<<<<<<<<");
    }
    
    static void pruebas2()
    {
        ArrayList<String> list = new ArrayList<String>();
        list.add("mnbmnb");
        try
        {
            String file = "C:\\Users\\JavierOswaldo\\Desktop\\TjjkjYhfH-BORRAR.txt";
            MyFile.writeString(file, " 111 ");
            MyFile.writeStringAtEnd(file, "222 ");
            MyFile.writeStringAtEnd(file, "333 ");
            MyFile.writeStringAtEnd(file, "444 ");
        }
        catch (Exception ex)
        {
        }
        
    }
    
    private static void print(String txt){ System.out.println(txt); }
    
}
