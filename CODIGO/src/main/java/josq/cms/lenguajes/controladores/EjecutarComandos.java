/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package josq.cms.lenguajes.controladores;

import java.util.ArrayList;
import josq.cms.lenguajes.automatas.modelos.cup.Comando;

/**
 *
 * @author JavierOswaldo
 */
public class EjecutarComandos
{
    public final static StringBuilder logSintaxis = new StringBuilder();
    public final static StringBuilder logConSentido = new StringBuilder();
    public final static StringBuilder logSinSentido = new StringBuilder();
    
    public static void clearLogs()
    {
        logSintaxis.delete(0, logSintaxis.length());
        logConSentido.delete(0, logConSentido.length());
        logSinSentido.delete(0, logSinSentido.length());
    }
    public void desdeString(String texto)
    {
        //clearAcciones();
        try
        {
            ArrayList<Comando> comandos = Procesar.comandosDesdeString(texto);
        }
        catch (Exception ex)
        {
            EjecutarComandos.logSinSentido.append("@EjecutarComandos.desdeString: ").append(ex.getMessage()).append("\n");
        }
    }

    public void desdeArchivo(String file)
    {
        //clearAcciones();
        try
        {
            ArrayList<Comando> comandos = Procesar.comandosDesdeArchivo(file);
        }
        catch (Exception ex)
        {
            EjecutarComandos.logSinSentido.append("@EjecutarComandos.desdeArchivo: ").append(ex.getMessage()).append("\n");
        }
    }

}
