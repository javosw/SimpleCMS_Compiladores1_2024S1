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
import josq.cms.lenguajes.automatas.modelos.Indicador;
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
            for(String c : idsComponentes) html.append(getWidgetHTML(componentes.get(c)));

            Set<String> etiquetas = miPagina.getEtiquetas();
            Set<String> paginas = miPagina.getPaginas();
            
            html.append("componentes: "+idsComponentes.toString()+"<br/>");
            html.append("etiquetas: "+etiquetas.toString()+"<br/>");
            html.append("paginas: "+paginas.toString()+"<br/>");
            
            html.append("</body></html>");
            
            //System.out.println(html.toString());
            return  html.toString();
            
        }
        catch (Exception ex)
        {
            System.out.println("@getWebPage: "+ex.getMessage());
        }
        return notFound;
    }
    
    private static String getWidgetHTML(Object miWidget)
    {
        if(miWidget==null) return "";
                
        if (miWidget instanceof Imagen)
        {
            Imagen w = (Imagen) miWidget;
            String src = "src=\""+w.getUrl()+"\"";
            String width = "width:"+w.getSizeX()+"px;";
            return "<img "+src+" style=\""+width+"\"/>";
        }
        else if (miWidget instanceof Menu)
        {
            Menu w = (Menu) miWidget;
            StringBuilder menu = new StringBuilder();
            Set<String> paginas = w.getPaginas();
            
            menu.append("<ul>");
            for (String p : paginas)
            {
                menu.append("<li>");
                menu.append("<a href=\"/cms-web-backend/paginas?id=").append(p).append("\">").append(p).append("</a>");
                menu.append("</li>");
            }                        
            menu.append("</ul>");
            
            return menu.toString();
        }
        else if (miWidget instanceof Parrafo)
        {
            Parrafo w = (Parrafo) miWidget;
            String backgroundColor = "background-color:"+w.getColor()+";";
            String textAlign = getAlignStyle(w.getAlign())+";";
            return "<p style=\""+backgroundColor+textAlign+"\">"+w.getText()+"</p>";
        }
        else if (miWidget instanceof Titulo)
        {
            Titulo w = (Titulo) miWidget;
            String backgroundColor = "background-color:"+w.getColor()+";";
            String textAlign = getAlignStyle(w.getAlign())+";";
            return "<title style=\""+backgroundColor+textAlign+"\">"+w.getText()+"</title>";
        }
        else if (miWidget instanceof Video)
        {
            Video w = (Video) miWidget;
            String width = "width: "+w.getSizeX()+";";
            String src = "src=\""+w.getUrl()+"\"";
            return  "<video "+src+" controls style=\""+width+"\"></video>";
        }
        return "";
    }

    private static String getAlignStyle(Indicador align)
    {
        if(align == null) return "";
        switch (align)
        {
            case T_CENTRAR:
                return "text-align: center";
            case T_IZQUIERDA:
                return "text-align: left";
            case T_DERECHA:
                return "text-align: right";
            case T_JUSTIFICAR:
                return "text-align: justify";
        }
        return "";
    }
}
