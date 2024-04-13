/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package josq.cms.lenguajes.controladores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import josq.cms.lenguajes.controladores.acciones.ComponenteDel;
import josq.cms.lenguajes.controladores.acciones.ComponenteImagen;
import josq.cms.lenguajes.controladores.acciones.ComponenteMenu;
import josq.cms.lenguajes.controladores.acciones.ComponenteParrafo;
import josq.cms.lenguajes.controladores.acciones.ComponenteVideo;
import josq.cms.lenguajes.controladores.acciones.PaginaDel;
import josq.cms.lenguajes.controladores.acciones.PaginaMod;
import josq.cms.lenguajes.controladores.acciones.PaginaNew;
import josq.cms.lenguajes.controladores.acciones.SitioDel;
import josq.cms.lenguajes.controladores.acciones.SitioNew;
import josq.cms.lenguajes.modelos.Indicador;
import josq.cms.lenguajes.modelos.cup.simbolos.Accion;
import josq.cms.lenguajes.modelos.cup.simbolos.Atributo;
import josq.cms.lenguajes.modelos.cup.simbolos.Par;
import josq.cms.lenguajes.modelos.cup.simbolos.Parametro;
import josq.cms.web.modelos.Componente;
import josq.cms.web.modelos.Pagina;
import josq.cms.web.modelos.Sitio;

/**
 *
 * @author JavierOswaldo
 */
public class AccionFactory
{
    /*
    Instruccion newAccion(Accion miAccion)
    {
        Indicador tipo = miAccion.getTipo();
        if(tipo == Indicador.SITE_NEW) return newSitioNew(miAccion);
        else if(tipo == Indicador.SITE_DEL) return newSitioDel(miAccion);
        else if(tipo == Indicador.PAGE_NEW) return newPaginaNew(miAccion);
        else if(tipo == Indicador.PAGE_MOD) return newPaginaMod(miAccion);
        else if(tipo == Indicador.PAGE_DEL) return newPaginaDel(miAccion);
        else if(tipo == Indicador.COMP_NEW | tipo == Indicador.COMP_MOD) return newComponente(miAccion);
        else if(tipo == Indicador.COMP_DEL) return newComponenteDel(miAccion);
        
        return null;
    }
    */
    
    Sitio newSitioNew(Accion miAccion)
    {
        ArrayList<Parametro> listParametros = miAccion.getParametros();
        Map<Indicador,Object> mapParametros = new HashMap<>();
        for (Parametro p : listParametros) mapParametros.put(p.getTipo(), p.getContenido());
        
        Object idSite = mapParametros.get(Indicador.P_ID);
        Object userNew = mapParametros.get(Indicador.P_USER_NEW);
        Object userMod = mapParametros.get(Indicador.P_USER_MOD);
        Object dateNew = mapParametros.get(Indicador.P_FECHA_NEW);
        Object dateMod = mapParametros.get(Indicador.P_FECHA_MOD);

        boolean is_idSite = idSite != null && idSite instanceof String;
        boolean is_userNew = userNew != null && userNew instanceof String;
        boolean is_userMod = userMod != null && userMod instanceof String;
        boolean is_dateNew = dateNew != null && dateNew instanceof String;
        boolean is_dateMod = dateMod != null && dateMod instanceof String;

        Sitio miSitio = null;
        if (is_idSite) 
        {
            miSitio = new Sitio((String) idSite);
            if(is_userNew) miSitio.setUserNew((String) userNew);
            if(is_userMod) miSitio.setUserMod((String) userMod);
            if(is_dateNew) miSitio.setDateNew((String) dateNew);
            if(is_dateMod) miSitio.setDateMod((String) dateMod);
        }
        
        return miSitio;
    }
    
    Sitio newSitioDel(Accion miAccion)
    {
        ArrayList<Parametro> listParametros = miAccion.getParametros();
        Map<Indicador,Object> mapParametros = new HashMap<>();
        for (Parametro p : listParametros) mapParametros.put(p.getTipo(), p.getContenido());
        
        Object idSite = mapParametros.get(Indicador.P_ID);

        boolean is_idSite = idSite != null && idSite instanceof String;

        Sitio miSitio = null;
        if (is_idSite) 
        {
            miSitio = new Sitio((String) idSite);
        }
        
        return miSitio;
    }

    Pagina newPaginaNew(Accion miAccion)
    {
        ArrayList<Parametro> listParametros = miAccion.getParametros();
        Map<Indicador,Object> mapParametros = new HashMap<>();
        for (Parametro p : listParametros) mapParametros.put(p.getTipo(), p.getContenido());
        
        Object idPage = mapParametros.get(Indicador.P_ID);
        Object idPageRoot = mapParametros.get(Indicador.P_PADRE);
        Object idSite = mapParametros.get(Indicador.P_SITIO);
        Object title = mapParametros.get(Indicador.P_TITULO);
        Object userNew = mapParametros.get(Indicador.P_USER_NEW);
        Object userMod = mapParametros.get(Indicador.P_USER_MOD);
        Object dateNew = mapParametros.get(Indicador.P_FECHA_NEW);
        Object dateMod = mapParametros.get(Indicador.P_FECHA_MOD);
        ArrayList<String> labels = miAccion.getEtiquetas();

        boolean is_idPage = idPage != null && idPage instanceof String;
        boolean is_idSite = idSite != null && idSite instanceof String;
        boolean is_idPageRoot = idPageRoot != null && idPageRoot instanceof String;
        boolean is_title = title != null && title instanceof String;
        boolean is_userNew = userNew != null && userNew instanceof String;
        boolean is_userMod = userMod != null && userMod instanceof String;
        boolean is_dateNew = dateNew != null && dateNew instanceof String;
        boolean is_dateMod = dateMod != null && dateMod instanceof String;

        Pagina miPagina = null;
        if (is_idPage && is_idSite) 
        {
            miPagina = new Pagina((String) idPage, (String) idSite);
            if(is_idPageRoot) miPagina.setIdPageRoot((String) idPageRoot);
            if(is_title) miPagina.setTitle((String) title);
            if(is_userNew) miPagina.setUserNew((String) userNew);
            if(is_userMod) miPagina.setUserMod((String) userMod);
            if(is_dateNew) miPagina.setDateNew((String) dateNew);
            if(is_dateMod) miPagina.setDateMod((String) dateMod);
            for (String l : labels) miPagina.addEtiqueta(l);
        }
        
        return miPagina;
    }
    
    Pagina newPaginaMod(Accion miAccion)
    {
        ArrayList<Parametro> listParametros = miAccion.getParametros();
        Map<Indicador,Object> mapParametros = new HashMap<>();
        for (Parametro p : listParametros) mapParametros.put(p.getTipo(), p.getContenido());
        
        Object idPage = mapParametros.get(Indicador.P_ID);
        Object title = mapParametros.get(Indicador.P_TITULO);
        ArrayList<String> labels = miAccion.getEtiquetas();

        boolean is_idPage = idPage != null && idPage instanceof String;
        boolean is_title = title != null && title instanceof String;

        Pagina miPagina = null;
        if (is_idPage) 
        {
            miPagina = new Pagina((String) idPage);
            if(is_title) miPagina.setTitle((String) title);
            for (String l : labels) miPagina.addEtiqueta(l);
        }
        
        return miPagina;
    }

    Pagina newPaginaDel(Accion miAccion)
    {
        ArrayList<Parametro> listParametros = miAccion.getParametros();
        Map<Indicador,Object> mapParametros = new HashMap<>();
        for (Parametro p : listParametros) mapParametros.put(p.getTipo(), p.getContenido());
        
        Object idPage = mapParametros.get(Indicador.P_ID);

        boolean is_idPage = idPage != null && idPage instanceof String;

        Pagina miPagina = null;
        if (is_idPage) 
        {
            miPagina = new Pagina((String) idPage);
        }
        
        return miPagina;
    }
    
    Componente newComponente(Accion miAccion)
    {
        ArrayList<Parametro> listParametros = miAccion.getParametros();
        Map<Indicador,Object> mapParametros = new HashMap<>();
        for (Parametro p : listParametros) mapParametros.put(p.getTipo(), p.getContenido());
                
        Object idComp = mapParametros.get(Indicador.P_ID);
        Object idPage = mapParametros.get(Indicador.P_PAGINA);
        Object clase = mapParametros.get(Indicador.P_CLASE);
        
        boolean is_idComp = idComp != null && idComp instanceof String;
        boolean is_idPage = idPage != null && idPage instanceof String;
        boolean is_clase = clase != null && clase instanceof Indicador;

        Componente miComp = null;
        if(is_idComp && is_idPage && is_clase)
        {
            miComp = new Componente((String)idComp,(String)idPage);
            
            ArrayList<Atributo> listAtributos = miAccion.getAtributos();
            Map<Indicador,Object> mapAtributos = new HashMap<>();
            for (Atributo a : listAtributos) mapParametros.put(a.getTipo(), a.getContenido());

            Object widget = getWidget((Indicador) clase, mapAtributos);
            miComp.setWidget(widget);
        }
        
        return miComp;
    }
    
    Object getWidget(Indicador clase, Map<Indicador,Object> mapAtributos)
    {
        if(clase == Indicador.UI_TITULO | clase == Indicador.UI_PARRAFO) return newComponenteParrafo(miAccion);
        else if(clase == Indicador.UI_IMAGEN) return newComponenteImagen(miAccion);
        else if(clase == Indicador.UI_VIDEO) return newComponenteVideo(miAccion);
        else if(clase == Indicador.UI_MENU) return newComponenteMenu(miAccion);
        
        return  null;
    }
    
    Instruccion newComponenteParrafo(Accion miAccion)
    {
        ArrayList<Parametro> parametros = miAccion.getParametros();
        Map<Indicador,Par> paresParam = new HashMap<>();
        for (Parametro p : parametros) paresParam.put(p.getTipo(), p.getContenido());
        
        ArrayList<Atributo> atributos = miAccion.getAtributos();
        Map<Indicador,Par> paresAtrib = new HashMap<>();
        for (Atributo a : atributos) paresAtrib.put(a.getTipo(), a.getContenido());

        String idComp = paresParam.get(Indicador.P_ID).getValueString();
        String idPage = paresParam.get(Indicador.P_PAGINA).getValueString();
        String text = paresAtrib.get(Indicador.A_TEXTO).getValueString();
        Indicador align = paresAtrib.get(Indicador.A_ALIGN).getValueIndicador();
        String color = paresAtrib.get(Indicador.A_COLOR).getValueString();
        Indicador clase = paresParam.get(Indicador.P_CLASE).getValueIndicador();
        Indicador accion = miAccion.getTipo();
        
        return new ComponenteParrafo(idComp, idPage, text, align, color, clase, accion);
    }
    
    Instruccion newComponenteImagen(Accion miAccion)
    {
        ArrayList<Parametro> parametros = miAccion.getParametros();
        Map<Indicador,Par> paresParam = new HashMap<>();
        for (Parametro p : parametros) paresParam.put(p.getTipo(), p.getContenido());
        
        ArrayList<Atributo> atributos = miAccion.getAtributos();
        Map<Indicador,Par> paresAtrib = new HashMap<>();
        for (Atributo a : atributos) paresAtrib.put(a.getTipo(), a.getContenido());

        String idComp = paresParam.get(Indicador.P_ID).getValueString();
        String idPage = paresParam.get(Indicador.P_PAGINA).getValueString();
        String url = paresAtrib.get(Indicador.A_ORIGEN).getValueString();
        int sizeX = Integer.parseInt(paresAtrib.get(Indicador.A_ANCHO).getValueString());
        int sizeY = Integer.parseInt(paresAtrib.get(Indicador.A_ALTO).getValueString());
        Indicador align = paresAtrib.get(Indicador.A_ALIGN).getValueIndicador();
        Indicador accion = miAccion.getTipo();

        return new ComponenteImagen(idComp, idPage, url, sizeX, sizeY, align, accion);
    }

    Instruccion newComponenteMenu(Accion miAccion)
    {
        ArrayList<Parametro> parametros = miAccion.getParametros();
        Map<Indicador,Par> paresParam = new HashMap<>();
        for (Parametro p : parametros) paresParam.put(p.getTipo(), p.getContenido());
        
        ArrayList<Atributo> atributos = miAccion.getAtributos();
        Map<Indicador,Par> paresAtrib = new HashMap<>();
        for (Atributo a : atributos) paresAtrib.put(a.getTipo(), a.getContenido());

        String idComp = paresParam.get(Indicador.P_ID).getValueString();
        String idPage = paresParam.get(Indicador.P_PAGINA).getValueString();
        String idPageRoot = paresAtrib.get(Indicador.A_PADRE).getValueString();
        ArrayList<String> labels = paresAtrib.get(Indicador.A_ETIQS).getValueStrings();
        Indicador accion = miAccion.getTipo();
        
        return new ComponenteMenu(idComp, idPage, idPageRoot, labels, accion);
    }

    Instruccion newComponenteVideo(Accion miAccion)
    {
        ArrayList<Parametro> parametros = miAccion.getParametros();
        Map<Indicador,Par> paresParam = new HashMap<>();
        for (Parametro p : parametros) paresParam.put(p.getTipo(), p.getContenido());
        
        ArrayList<Atributo> atributos = miAccion.getAtributos();
        Map<Indicador,Par> paresAtrib = new HashMap<>();
        for (Atributo a : atributos) paresAtrib.put(a.getTipo(), a.getContenido());

        String idComp = paresParam.get(Indicador.P_ID).getValueString();
        String idPage = paresParam.get(Indicador.P_PAGINA).getValueString();
        String url = paresAtrib.get(Indicador.A_ORIGEN).getValueString();
        int sizeX = Integer.parseInt(paresAtrib.get(Indicador.A_ANCHO).getValueString());
        int sizeY = Integer.parseInt(paresAtrib.get(Indicador.A_ALTO).getValueString());
        Indicador accion = miAccion.getTipo();

        return new ComponenteVideo(idComp, idPage, url, sizeX, sizeY, accion);
    }
    Instruccion newComponenteDel(Accion miAccion)
    {
        ArrayList<Parametro> parametros = miAccion.getParametros();
        Map<Indicador,Par> paresParam = new HashMap<>();
        for (Parametro p : parametros) paresParam.put(p.getTipo(), p.getContenido());
        
        String idComp = paresParam.get(Indicador.P_ID).getValueString();
        String idPage = paresParam.get(Indicador.P_PAGINA).getValueString();

        return new ComponenteDel(idComp, idPage);
    }

}
