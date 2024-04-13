/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package josq.cms.lenguajes.controladores.acciones;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import josq.cms.lenguajes.controladores.Instruccion;
import josq.cms.archivos.Objetos;
import josq.cms.web.modelos.Sitio;

/**
 *
 * @author JavierOswaldo
 */
public class SitioDel implements Instruccion
{
    String idSite;

    public SitioDel(String idSite)
    {
        this.idSite = idSite;
    }

    // ejecutar
    // buscar archivo, !null? -> eliminar de forma recursiva todas las paginas
    
    private String ruta = "C:\\CMS";
    
    @Override
    public void ejecutar()
    {
        String file = ruta+idSite;
        File sitio = new File(file); 
        if (!sitio.exists()) return;
        
        try
        {
            Sitio miSitio = (Sitio) Objetos.readObject(file);
            // eliminar cada pagina del sitio de forma rrecursiva
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
}
