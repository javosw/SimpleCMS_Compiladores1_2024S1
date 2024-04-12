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

/**
 *
 * @author JavierOswaldo
 */
public class AccionFactory
{
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
    
    
    Instruccion newSitioNew(Accion miAccion)
    {
        ArrayList<Parametro> parametros = miAccion.getParametros();
        Map<Indicador,Par> pares = new HashMap<>();
        for (Parametro p : parametros) pares.put(p.getTipo(), p.getContenido());
        
        String idSite = pares.get(Indicador.P_ID).getValueString();
        String userNew = pares.get(Indicador.P_USER_NEW).getValueString();
        String userMod = pares.get(Indicador.P_USER_MOD).getValueString();
        String dateNew = pares.get(Indicador.P_FECHA_NEW).getValueString();
        String dateMod = pares.get(Indicador.P_FECHA_MOD).getValueString();

        return new SitioNew(idSite, userNew, userMod, dateNew, dateMod);
    }
    
    Instruccion newSitioDel(Accion miAccion)
    {
        ArrayList<Parametro> parametros = miAccion.getParametros();
        Map<Indicador,Par> pares = new HashMap<>();
        for (Parametro p : parametros) pares.put(p.getTipo(), p.getContenido());
        
        String idSite = pares.get(Indicador.P_ID).getValueString();

        return new SitioDel(idSite);
    }

    // ++++++++++++
    Instruccion newPaginaNew(Accion miAccion)
    {
        ArrayList<Parametro> parametros = miAccion.getParametros();
        Map<Indicador,Par> pares = new HashMap<>();
        for (Parametro p : parametros) pares.put(p.getTipo(), p.getContenido());
        
        String idPage = pares.get(Indicador.P_ID).getValueString();
        String idPageRoot = pares.get(Indicador.P_ID).getValueString();
        String idSite = pares.get(Indicador.P_ID).getValueString();
        String title = pares.get(Indicador.P_ID).getValueString();
        ArrayList<String> labels = miAccion.getEtiquetas();
        String userNew = pares.get(Indicador.P_USER_NEW).getValueString();
        String userMod = pares.get(Indicador.P_USER_MOD).getValueString();
        String dateNew = pares.get(Indicador.P_FECHA_NEW).getValueString();
        String dateMod = pares.get(Indicador.P_FECHA_MOD).getValueString();
        
        return  new PaginaNew(idPage, idPageRoot, idSite, title, labels, userNew, userMod, dateNew, dateMod);
    }

    Instruccion newPaginaMod(Accion miAccion)
    {
        ArrayList<Parametro> parametros = miAccion.getParametros();
        Map<Indicador,Par> pares = new HashMap<>();
        for (Parametro p : parametros) pares.put(p.getTipo(), p.getContenido());
        
        String idPage = pares.get(Indicador.P_ID).getValueString();
        String title = pares.get(Indicador.P_ID).getValueString();
        ArrayList<String> labels = miAccion.getEtiquetas();
        
        return new PaginaMod(idPage, title, labels);
    }
    
    Instruccion newPaginaDel(Accion miAccion)
    {
        ArrayList<Parametro> parametros = miAccion.getParametros();
        Map<Indicador,Par> pares = new HashMap<>();
        for (Parametro p : parametros) pares.put(p.getTipo(), p.getContenido());
        
        String idPage = pares.get(Indicador.P_ID).getValueString();
        
        return new PaginaDel(idPage);
    }
    
    Instruccion newComponente(Accion miAccion)
    {
        ArrayList<Parametro> parametros = miAccion.getParametros();
        Map<Indicador,Par> paresParam = new HashMap<>();
        for (Parametro p : parametros) paresParam.put(p.getTipo(), p.getContenido());
        
        Indicador clase = paresParam.get(Indicador.P_CLASE).getValueIndicador();

        if(clase == Indicador.UI_TITULO | clase == Indicador.UI_PARRAFO) return newComponenteParrafo(miAccion);
        else if(clase == Indicador.UI_IMAGEN) return newComponenteImagen(miAccion);
        else if(clase == Indicador.UI_VIDEO) return newComponenteVideo(miAccion);
        else if(clase == Indicador.UI_MENU) return newComponenteMenu(miAccion);

        return null;
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
