/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package josq.cms.lenguajes.controladores;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import josq.cms.lenguajes.automatas.modelos.Indicador;
import josq.cms.lenguajes.automatas.modelos.cup.Comando;

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
            for(Comando c : comandos) modelar(c);
        }
        catch (Exception ex)
        {
            EjecutarComandos.logSinSentido.append("@EjecutarComandos.desdeString: ").append(ex.getMessage()).append("\n");
        }
    }

    private void modelar(Comando miComando)
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

}
