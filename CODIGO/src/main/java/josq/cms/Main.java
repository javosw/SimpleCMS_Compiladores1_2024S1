/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package josq.cms;

import java.util.logging.Level;
import java.util.logging.Logger;
import josq.cms.archivos.Texto;
import josq.cms.lenguajes.Procesar;

/**
 *
 * @author JavierOswaldo
 */
public class Main
{
    public static void main(String[] args) throws Exception 
    {
        //VistaInicio.main(args);
        pruebas1();
    }

    static void pruebas1() 
    {
        String xml = "acciones.xml";
        String ruta = "C:\\Users\\JavierOswaldo\\Desktop\\PROCESADORES-LENGUAJE\\CODIFICACION\\PROYECTO-1\\PRUEBAS\\"+xml;
        try
        {
            Procesar.accionesDesdeArchivo(ruta);
        }
        catch (Exception ex)
        {
            print("\n    <%#%#%#%#%#%#%#% ERRORES %#%#%#%#%#%#%#%>\n");
            print(ex.getMessage());
        }
    }
    
    static void pruebas2()
    {
        try
        {
            String file = "C:\\Users\\JavierOswaldo\\Desktop\\LEXER.txt";
            Texto.newTexto(file, " 111 ");
            Texto.addTexto(file, "222 ");
            Texto.addTexto(file, "333 ");
            Texto.newTexto(file, "444 ");
        }
        catch (Exception ex)
        {
        }
        
    }
    
    private static void print(String txt){ System.out.println(txt); }
    
}
