/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package josq.cms.web.backend;

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
    public static String getWebPage(String idPagina)
    {
        String ruta = Ruta.cms+idPagina;
        
        String notFound = "<!DOCTYPE html><html><head></head><body><h1>PAGINA NO ENCONTRADA</h1></body></html>";

        try
        {
            File paginaFile = new File(ruta);
            if (!paginaFile.exists()) return notFound; // generar NOT FOUND

            Object rawPagina = MiArchivo.readObject(ruta);
            boolean isPagina = rawPagina != null && rawPagina instanceof Pagina;
            System.out.println("isPagina="+isPagina);
            if(!isPagina) return notFound;
            
            StringBuilder html = new StringBuilder();
            html.append("<!DOCTYPE html><html><head></head><body>");
            
            Pagina miPagina = (Pagina) rawPagina;
 
            Map<String,Object> componentes = miPagina.getComponentes();
            Set<String> idsComponentes = miPagina.getComponentes().keySet();
            //System.out.println("componentes: "+idsComponentes.toString());
            for(String c : idsComponentes) html.append(getWidget(componentes.get(c)));

            Set<String> etiquetas = miPagina.getEtiquetas();
            Set<String> paginas = miPagina.getPaginas();
            
            html.append("componentes: "+idsComponentes.toString()+"<br/>");
            html.append("etiquetas: "+etiquetas.toString()+"<br/>");
            html.append("paginas: "+paginas.toString()+"<br/>");
            
            html.append("</body></html>");
            
            return  html.toString();
            
        }
        catch (Exception ex)
        {
            System.out.println("@getWebPage: "+ex.getMessage());
        }
        return notFound;
    }
    
    private static String getWidget(Object miWidget)
    {
        if(miWidget==null) return "";
                
        if (miWidget instanceof Imagen)
        {
            Imagen w = (Imagen) miWidget;
            return "<img src\""+w.getUrl()+"\"/>";
        }
        else if (miWidget instanceof Menu)
        {
            Menu w = (Menu) miWidget;
            StringBuilder menu = new StringBuilder();
            Set<String> paginas = w.getPaginas();
            menu.append("<ul>");
            for (String p : paginas) menu.append("<li><a href=\"/cms-web-backend/paginas?id=").append(p).append("\">").append(p).append("</a></li>");
            menu.append("</ul>");
            return menu.toString();
        }
        else if (miWidget instanceof Parrafo)
        {
            Parrafo w = (Parrafo) miWidget;
            return "<p>"+w.getText()+"</p>";
        }
        else if (miWidget instanceof Titulo)
        {
            Titulo w = (Titulo) miWidget;
            return "<title>"+w.getText()+"</title>";
        }
        else if (miWidget instanceof Video)
        {
            Video w = (Video) miWidget;
            return  "<video controls><source src=\""+w.getUrl()+"\" type=\"video/webm\"/></video>";
        }
        return "";
    }

}
