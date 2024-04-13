/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package josq.cms.web.modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import josq.cms.web.modelos.Sitio;

/**
 *
 * @author JavierOswaldo
 */
public class Pagina implements Serializable
{
    private static final long serialVersionUID = 1L;

    String idPage;
    String idPageRoot;
    String idSite;
    
    String title;
    
    String userNew;
    String userMod;
    String dateNew;
    String dateMod;
    
    
    Map<String,Pagina> paginas;
    Map<String,Object> componentes;
    Set<String> etiquetas;
    
    //Sitio refSite;
    //Pagina refPage;

    // Componente[]

    public Pagina()
    {
        paginas = new HashMap<>();
        componentes = new HashMap<>();
        etiquetas = new HashSet<>();
    }

    public Pagina(String idPage)
    {
        this();
        this.idPage = idPage;
    }

    public Pagina(String idPage, String idSite)
    {
        this();
        this.idPage = idPage;
        this.idSite = idSite;
    }
    
    public boolean hasEtiqueta(String etiqueta)
    {
        return etiquetas.contains(etiqueta);
    }
    
    public boolean hasPagina(String pagina)
    {
        return paginas.keySet().contains(pagina);
    }
    void addComponente(String idComponente, Object miComponente)
    {
        boolean existe = componentes.containsKey(idComponente);
        if(existe) return;

        componentes.put(idComponente, miComponente);
    }
    
    void putComponente(String idComponente, Object miComponente)
    {
        componentes.put(idComponente, miComponente);
    }
    
    void addPagina(String idPagina, Pagina newPagina)
    {
        boolean existe = paginas.containsKey(idPagina);
        if(existe) return;

        paginas.put(idPagina, newPagina);
    }
    
    void putPagina(String idPagina, Pagina newPagina)
    {
        paginas.put(idPagina, newPagina);
    }

    public void addEtiqueta(String etiqueta)
    {
        etiquetas.add(etiqueta);
    }


    // GETTERS
    public Map<String, Pagina> getPaginas()
    {
        return paginas;
    }
    public Map<String, Object> getComponentes()
    {
        return componentes;
    }
    public Set<String> getEtiquetas()
    {
        return etiquetas;
    }

    public String getIdPage()
    {
        return idPage;
    }

    // SETTERS
    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setIdPageRoot(String idPageRoot)
    {
        this.idPageRoot = idPageRoot;
    }

    public void setUserNew(String userNew)
    {
        this.userNew = userNew;
    }

    public void setUserMod(String userMod)
    {
        this.userMod = userMod;
    }

    public void setDateNew(String dateNew)
    {
        this.dateNew = dateNew;
    }

    public void setDateMod(String dateMod)
    {
        this.dateMod = dateMod;
    }
}
