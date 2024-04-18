/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package josq.cms;

import java.util.ArrayList;
import josq.cms.archivos.MiArchivo;
import josq.cms.archivos.Ruta;
import josq.cms.lenguajes.automatas.modelos.cup.Accion;
import josq.cms.lenguajes.controladores.EjecutarAcciones;
import josq.cms.lenguajes.controladores.Procesar;

/**
 *
 * @author JavierOswaldo
 */
public class Main
{
    public static void main(String[] args) throws Exception 
    {
        VistaInicio.main(args);
    }
    
    private static void print(String txt){ System.out.println(txt); }
}
