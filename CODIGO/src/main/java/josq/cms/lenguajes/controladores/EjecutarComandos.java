/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package josq.cms.lenguajes.controladores;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import josq.cms.archivos.MiArchivo;
import josq.cms.archivos.Ruta;
import josq.cms.lenguajes.automatas.modelos.Indicador;
import josq.cms.lenguajes.automatas.modelos.cup.Comando;
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
public class EjecutarComandos
{

    public EjecutarComandos()
    {
       viewsSite = new HashSet<>();
       viewsPage = new HashSet<>();
       popPage = new HashSet<>();
       widgetAll = new HashSet<>();
       widgetMenu = new HashSet<>();
       widgetParrafo = new HashSet<>();
       widgetImagen = new HashSet<>();
       widgetVideo = new HashSet<>();
       widgetTitulo = new HashSet<>();
    }
    
    // modelos usados para la ejecucion de comandos
    Set<String> viewsSite;
    Set<String> viewsPage;
    Set<String> popPage;
    Set<String> widgetAll;
    Set<String> widgetMenu;
    Set<String> widgetParrafo;
    Set<String> widgetImagen;
    Set<String> widgetVideo;
    Set<String> widgetTitulo;
    
    private void clearComandos()
    {
        viewsSite.clear();
        viewsPage.clear();
        popPage.clear();
        widgetAll.clear();
        widgetMenu.clear();
        widgetParrafo.clear();
        widgetImagen.clear();
        widgetVideo.clear();
        widgetTitulo.clear();
    }
    
    public void desdeString(String texto)
    {
        clearComandos();
        try
        {
            ArrayList<Comando> comandos = Procesar.comandosDesdeString(texto);
            for(Comando c : comandos) abstraerComando(c);
            iniciarEjecucion();
        }
        catch (Exception ex)
        {
            EjecutarComandos.logSinSentido.append("@EjecutarComandos.desdeString: ").append(ex.getMessage()).append("\n");
        }
    }

    private void abstraerComando(Comando miComando)
    {
        Indicador tipo = miComando.getOperador();
        switch (tipo)
        {
            case SQ_SITE_VIEWS:
                modelViewsSite(miComando);
                break;
            case SQ_PAGE_VIEWS:
                modelViewsPage(miComando);
                break;
            case SQ_PAGE_POP:
                modelPopPage(miComando);
                break;
            case SQ_TODOS:
                modelWidgetAll(miComando);
                break;
            case SQ_TITULO:
                modelWidgetTitulo(miComando);
                break;
            case SQ_MENU:
                modelWidgetMenu(miComando);
                break;
            case SQ_IMAGEN:
                modelWidgetImagen(miComando);
                break;
            case SQ_VIDEO:
                modelWidgetVideo(miComando);
                break;
            case SQ_PARRAFO:
                modelWidgetParrafo(miComando);
                break;
        }
    }
    void iniciarEjecucion()
    {
        for(String s : viewsSite) exeSiteViews(s);
        for(String s : viewsPage) exePageViews(s);
        for(String s : widgetAll) exeWidgetAll(s);
        for(String s : widgetMenu) exeWidgetMenu(s);
        for(String s : widgetParrafo) exeWidgetParrafo(s);
        for(String s : widgetImagen) exeWidgetImagen(s);
        for(String s : widgetVideo) exeWidgetVideo(s);
        for(String s : widgetTitulo) exeWidgetTitulo(s);
        //for(String s : popPage)        
    }
    
    private void modelViewsSite(Comando miComando)
    {
        Object rawSiteList = miComando.getOperando();
        ArrayList<String> siteList = (ArrayList<String>) rawSiteList;
        
        viewsSite.addAll(siteList);
    }
    private void modelViewsPage(Comando miComando)
    {
        Object rawPathList = miComando.getOperando();
        ArrayList<ArrayList<String>> pathList = (ArrayList<ArrayList<String>>) rawPathList;
        
        for(ArrayList<String> path : pathList)
        {
            viewsPage.add(path.get(path.size()-1));
        }
    }
    private void modelPopPage(Comando miComando)
    {
        Object rawPage = miComando.getOperando();
        String page = (String) rawPage;
        
        popPage.add(page);
    }
    private void modelWidgetAll(Comando miComando)
    {
        Object rawPath = miComando.getOperando();
        ArrayList<String> path = (ArrayList<String>) rawPath;
        widgetAll.add(path.get(path.size()-1));
    }
    private void modelWidgetMenu(Comando miComando)
    {
        Object rawPath = miComando.getOperando();
        ArrayList<String> path = (ArrayList<String>) rawPath;
        widgetMenu.add(path.get(path.size()-1));
    }
    private void modelWidgetParrafo(Comando miComando)
    {
        Object rawPath = miComando.getOperando();
        ArrayList<String> path = (ArrayList<String>) rawPath;
        widgetParrafo.add(path.get(path.size()-1));
    }
    private void modelWidgetImagen(Comando miComando)
    {
        Object rawPath = miComando.getOperando();
        ArrayList<String> path = (ArrayList<String>) rawPath;
        widgetImagen.add(path.get(path.size()-1));
    }
    private void modelWidgetVideo(Comando miComando)
    {
        Object rawPath = miComando.getOperando();
        ArrayList<String> path = (ArrayList<String>) rawPath;
        widgetVideo.add(path.get(path.size()-1));
    }
    private void modelWidgetTitulo(Comando miComando)
    {
        Object rawPath = miComando.getOperando();
        ArrayList<String> path = (ArrayList<String>) rawPath;
        widgetTitulo.add(path.get(path.size()-1));
    }

    // logs

    public final static StringBuilder logSintaxis = new StringBuilder();
    public final static StringBuilder logConSentido = new StringBuilder();
    public final static StringBuilder logSinSentido = new StringBuilder();
    
    public static void clearLogs()
    {
        logSintaxis.delete(0, logSintaxis.length());
        logConSentido.delete(0, logConSentido.length());
        logSinSentido.delete(0, logSinSentido.length());
    }

    void exePageViews(String idPage)
    {
        String ruta = Ruta.cms+idPage;
        try
        {
            File modelFile = new File(ruta);
            if (!modelFile.exists()) throw new Exception(ruta);

            Object rawPagina = MiArchivo.readObject(ruta);
            boolean isPagina = rawPagina != null && rawPagina instanceof Pagina;

            if(!isPagina) throw new Exception(ruta);

            Pagina miPagina = (Pagina) rawPagina;
            
            logConSentido.append("@exePageViews: ").append(idPage).append(" =");
            logConSentido.append(miPagina.getVisitas()).append("\n");
        }
        catch (Exception e)
        {
            logSinSentido.append("@exeViewsPage: ").append(e.getMessage()).append("\n");
        }
    }
    
    void exeWidgetAll(String idPage)
    {
        String ruta = Ruta.cms+idPage;
        try
        {
            File modelFile = new File(ruta);
            if (!modelFile.exists()) throw new Exception(ruta);

            Object rawPagina = MiArchivo.readObject(ruta);
            boolean isPagina = rawPagina != null && rawPagina instanceof Pagina;

            if(!isPagina) throw new Exception(ruta);

            Pagina miPagina = (Pagina) rawPagina;
            
            Map<String,Object> rawWidgets = miPagina.getComponentes();
            Set<String> ids = rawWidgets.keySet();
            logConSentido.append("@exeWidgetAll: ").append(idPage).append(" =").append(ids.size()).append("\n");
        }
        catch (Exception e)
        {
            logSinSentido.append("@exeWidgetAll: ").append(e.getMessage()).append("\n");
        }
    }
    
    
    void exeWidgetTitulo(String idPage)
    {
        String ruta = Ruta.cms+idPage;
        try
        {
            File modelFile = new File(ruta);
            if (!modelFile.exists()) throw new Exception(ruta);

            Object rawPagina = MiArchivo.readObject(ruta);
            boolean isPagina = rawPagina != null && rawPagina instanceof Pagina;

            if(!isPagina) throw new Exception(ruta);

            Pagina miPagina = (Pagina) rawPagina;
            
            int contador = 0;
            Map<String,Object> rawWidgets = miPagina.getComponentes();
            Set<String> ids = rawWidgets.keySet();
            for(String id : ids)
            {
                if (rawWidgets.get(id) instanceof Titulo) contador = contador + 1;
            }
            logConSentido.append("@exeWidgetTitle: ").append(idPage).append(" =").append(contador).append("\n");
        }
        catch (Exception e)
        {
            logSinSentido.append("@exeWidgetTitle: ").append(e.getMessage()).append("\n");
        }
    }

    void exeWidgetParrafo(String idPage)
    {
        String ruta = Ruta.cms+idPage;
        try
        {
            File modelFile = new File(ruta);
            if (!modelFile.exists()) throw new Exception(ruta);

            Object rawPagina = MiArchivo.readObject(ruta);
            boolean isPagina = rawPagina != null && rawPagina instanceof Pagina;

            if(!isPagina) throw new Exception(ruta);

            Pagina miPagina = (Pagina) rawPagina;
            
            int contador = 0;
            Map<String,Object> rawWidgets = miPagina.getComponentes();
            Set<String> ids = rawWidgets.keySet();
            for(String id : ids)
            {
                if (rawWidgets.get(id) instanceof Parrafo) contador = contador + 1;
            }
            logConSentido.append("@exeWidgetParrafo: ").append(idPage).append(" =").append(contador).append("\n");
        }
        catch (Exception e)
        {
            logSinSentido.append("@exeWidgetParrafo: ").append(e.getMessage()).append("\n");
        }
    }
    void exeWidgetMenu(String idPage)
    {
        String ruta = Ruta.cms+idPage;
        try
        {
            File modelFile = new File(ruta);
            if (!modelFile.exists()) throw new Exception(ruta);

            Object rawPagina = MiArchivo.readObject(ruta);
            boolean isPagina = rawPagina != null && rawPagina instanceof Pagina;

            if(!isPagina) throw new Exception(ruta);

            Pagina miPagina = (Pagina) rawPagina;
            
            int contador = 0;
            Map<String,Object> rawWidgets = miPagina.getComponentes();
            Set<String> ids = rawWidgets.keySet();
            for(String id : ids)
            {
                if (rawWidgets.get(id) instanceof Menu) contador = contador + 1;
            }
            logConSentido.append("@exeWidgetMenu: ").append(idPage).append(" =").append(contador).append("\n");
        }
        catch (Exception e)
        {
            logSinSentido.append("@exeWidgetMenu: ").append(e.getMessage()).append("\n");
        }
    }
    void exeWidgetImagen(String idPage)
    {
        String ruta = Ruta.cms+idPage;
        try
        {
            File modelFile = new File(ruta);
            if (!modelFile.exists()) throw new Exception(ruta);

            Object rawPagina = MiArchivo.readObject(ruta);
            boolean isPagina = rawPagina != null && rawPagina instanceof Pagina;

            if(!isPagina) throw new Exception(ruta);

            Pagina miPagina = (Pagina) rawPagina;
            
            int contador = 0;
            Map<String,Object> rawWidgets = miPagina.getComponentes();
            Set<String> ids = rawWidgets.keySet();
            for(String id : ids)
            {
                if (rawWidgets.get(id) instanceof Imagen) contador = contador + 1;
            }
            logConSentido.append("@exeWidgetImagen: ").append(idPage).append(" =").append(contador).append("\n");
        }
        catch (Exception e)
        {
            logSinSentido.append("@exeWidgetImagen: ").append(e.getMessage()).append("\n");
        }
    }
    void exeWidgetVideo(String idPage)
    {
        String ruta = Ruta.cms+idPage;
        try
        {
            File modelFile = new File(ruta);
            if (!modelFile.exists()) throw new Exception(ruta);

            Object rawPagina = MiArchivo.readObject(ruta);
            boolean isPagina = rawPagina != null && rawPagina instanceof Pagina;

            if(!isPagina) throw new Exception(ruta);

            Pagina miPagina = (Pagina) rawPagina;
            
            int contador = 0;
            Map<String,Object> rawWidgets = miPagina.getComponentes();
            Set<String> ids = rawWidgets.keySet();
            for(String id : ids)
            {
                if (rawWidgets.get(id) instanceof Video) contador = contador + 1;
            }
            logConSentido.append("@exeWidgetVideo: ").append(idPage).append(" =").append(contador).append("\n");
        }
        catch (Exception e)
        {
            logSinSentido.append("@exeWidgetVideo: ").append(e.getMessage()).append("\n");
        }
    }
    
    private void exeSiteViews(String idSite)
    {
        Contador views = new Contador();
        String homePage = "~"+idSite;        
        collectViews(homePage, views);
        logConSentido.append("@exeSiteViews: ").append(idSite).append(" =").append(views.getViews()).append("\n");
    }
    
    private void collectViews(String idPage, Contador views)
    {
        String ruta = Ruta.cms+idPage;
        try
        {
            File bin = new File(ruta); 
            if (!bin.exists()) throw new Exception(ruta);
            
            Object rawPagina = MiArchivo.readObject(ruta);
            boolean isPagina = rawPagina != null && rawPagina instanceof Pagina;
            
            // si el objeto leido no es una pagina, puede que sea un sitio: NO ELIMINAR EL ARCHIVO
            if(!isPagina) throw new Exception(ruta);
            
            Pagina miPagina = (Pagina)rawPagina;
            
            views.addViews(miPagina.getVisitas());
            
            Set<String> subPaginas = miPagina.getPaginas();
            for(String p : subPaginas) collectViews(p,views);
            
            //EjecutarAcciones.logConSentido.append("@pageViews: ").append(ruta).append("\n");
        }
        catch (Exception ex)
        {
            EjecutarAcciones.logSinSentido.append("@collectViews: ").append(ex.getMessage()).append("\n");
        }
    }
    
}
