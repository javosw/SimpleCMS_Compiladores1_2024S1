/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package josq.cms.lenguajes.controladores.acciones;

import java.io.File;
import josq.cms.archivos.MyFile;
import josq.cms.archivos.Ruta;
import josq.cms.lenguajes.controladores.Instruccion;
import josq.cms.web.modelos.Sitio;

/**
 *
 * @author JavierOswaldo
 */
public class SitioNew implements Instruccion
{
    String idSite;
    String user;
    String date;

    //String userNew;
    //String userMod;
    //String dateNew;
    //String dateMod;
    
    public SitioNew(String idSite)
    {
        this.idSite = idSite;
    }
    
    // EJECUTAR: 
    // buscar archivo idSite
    // si no existe toncs crear
    // si existe no hacer nada

    
    @Override
    public void ejecutar()
    {
        String file = Ruta.cms+idSite;
        
        // new Sitio
        Sitio newSitio = new Sitio(idSite);

        try
        {
            File oldSitio = new File(file); 
            if (oldSitio.exists()) return;

            MyFile.writeObjeto(file, newSitio);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            
        }
    }

    public void setUser(String user)
    {
        this.user = user;
    }
    
}
