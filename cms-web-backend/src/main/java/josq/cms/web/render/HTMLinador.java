/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package josq.cms.web.render;

import java.io.File;
import java.util.Map;
import java.util.Set;
import josq.cms.archivos.MiArchivo;
import josq.cms.archivos.Ruta;
import josq.cms.web.modelos.Pagina;
import josq.cms.web.modelos.componentes.Imagen;
import josq.cms.web.modelos.componentes.Menu;
import josq.cms.web.modelos.componentes.Parrafo;
import josq.cms.web.modelos.componentes.Titulo;
import josq.cms.web.modelos.componentes.Video;

/**
 *
 * @author JavierOswaldo
 */
public class HTMLinador
{
    public static void getWebPage(String idPagina)
    {
        String ruta = Ruta.cms+idPagina;
        
        try
        {
            File paginaFile = new File(ruta);
            if (!paginaFile.exists()) return; // generar NOT FOUND

            Object rawPagina = MiArchivo.readObject(ruta);
            boolean isPagina = rawPagina != null && rawPagina instanceof Pagina;
            if(!isPagina) return;
            Pagina miPagina = (Pagina) rawPagina;
            
            Map<String,Object> componentes = miPagina.getComponentes();
            Set<String> idsComponentes = miPagina.getComponentes().keySet();
            Set<String> etiquetas = miPagina.getEtiquetas();
            Set<String> paginas = miPagina.getPaginas();
            System.out.println("componentes: "+idsComponentes.toString());
            for(String c : idsComponentes) System.out.println(getWidget(componentes.get(c)));
            System.out.println("etiquetas: "+etiquetas.toString());
            System.out.println("paginas: "+paginas.toString());
        }
        catch (Exception ex)
        {
            System.out.print("@getWebPage: ");
            System.out.println(ex.getMessage());
        }
    }
    
    
    private static String getWidget(Object miWidget)
    {
        if(miWidget==null) return "@getWidget";
        if (miWidget instanceof Imagen)
        {
            Imagen w = (Imagen) miWidget;
            return "Imagen: "+w.getUrl();
        }
        else if (miWidget instanceof Menu)
        {
            Menu w = (Menu) miWidget;
            return "Menu: "+w.getPaginas().toString();
        }
        else if (miWidget instanceof Parrafo)
        {
            Parrafo w = (Parrafo) miWidget;
            return "Parrafo: "+w.getText();
        }
        else if (miWidget instanceof Titulo)
        {
            Titulo w = (Titulo) miWidget;
            return "Titulo: "+w.getText();
        }
        else if (miWidget instanceof Video)
        {
            Video w = (Video) miWidget;
            return "Video: "+w.getUrl();
        }
        return "@getWidget";
    }
}
