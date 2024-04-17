/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package josq.cms.web.modelos;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
    
    
    Set<String> paginas;
    Map<String,Object> componentes;
    Set<String> etiquetas;
    
    //Sitio refSite;
    //Pagina refPage;

    // Componente[]

    public Pagina()
    {
        paginas = new HashSet<>();
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
    public boolean hasPagina(String idPagina)
    {
        return paginas.contains(idPagina);
    }
    public void addComponente(String idComponente, Object miComponente)
    {
        boolean existe = componentes.containsKey(idComponente);
        if(existe) return;

        componentes.put(idComponente, miComponente);
    }
    public void delComponente(String idComponente)
    {
        componentes.remove(idComponente);
    }

    public void putComponente(String idComponente, Object miComponente)
    {
        componentes.put(idComponente, miComponente);
    }
    
    public void addPagina(String idPagina)
    {
        paginas.add(idPagina);
    }
    
    public void addEtiqueta(String etiqueta)
    {
        etiquetas.add(etiqueta);
    }

    // GETTERS

    public Set<String> getPaginas()
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

    public String getIdPageRoot()
    {
        return idPageRoot;
    }

    public String getTitle()
    {
        return title;
    }

    public String getUserNew()
    {
        return userNew;
    }

    public String getUserMod()
    {
        return userMod;
    }

    public String getDateNew()
    {
        return dateNew;
    }

    public String getDateMod()
    {
        return dateMod;
    }

    public String getIdSite()
    {
        return idSite;
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

    public void setIdSite(String idSite)
    {
        this.idSite = idSite;
    }
    
}
