/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package josq.cms;

import java.util.ArrayList;
import josq.cms.archivos.MiArchivo;
import josq.cms.archivos.Ruta;
import josq.cms.lenguajes.automatas.modelos.cup.Accion;
import josq.cms.lenguajes.controladores.Instruccion;
import josq.cms.lenguajes.controladores.Procesar;

/**
 *
 * @author JavierOswaldo
 */
public class Main
{
    public static void main(String[] args) throws Exception 
    {
        //VistaInicio.main(args);
        //readLenguaje("newSitio.xml");
        
        
        //testAccion("newSitio.xml");
        //testAccion("newPagina.xml");
        testAccion("delPagina.xml");
        //testAccion("delSitio.xml");

        
        
    }
    static void testAccion(String accion) 
    {
        String ruta = Ruta.acciones+accion;
        try
        {
            Instruccion inst = new Instruccion();
                inst.procesarDesdeArchivo(ruta);
        }
        catch (Exception ex)
        {
            print("\n<%#%#%#%#%#%#%#% ERRORES %#%#%#%#%#%#%#%>\n");
            ex.printStackTrace();
            //print(ex.getMessage());
        }
        print("<<<<<<<<<<<<<<<<");
    }
    static void readLenguaje(String xml) 
    {
        String ruta = Ruta.acciones+xml;
        try
        {
            ArrayList<Accion> acciones = Procesar.accionesDesdeArchivo(ruta);
            print(acciones.toString());
        }
        catch (Exception ex)
        {
            print("\n<+#%#%#%#%#%#%#% ERRORES %#%#%#%#%#%#%#+>\n");
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
            MiArchivo.writeString(file, " 111 ");
            MiArchivo.writeStringAtEnd(file, "222 ");
            MiArchivo.writeStringAtEnd(file, "333 ");
            MiArchivo.writeStringAtEnd(file, "444 ");
        }
        catch (Exception ex)
        {
        }
        
    }
    private static void print(String txt){ System.out.println(txt); }
    
}
