/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package josq.cms.web.modelos;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author JavierOswaldo
 */
public class Sitio implements Serializable 
{
    private static final long serialVersionUID = 1L;
    
    String idSite;
    
    String userNew;
    String dateNew;

    String userMod;    
    String dateMod;
    
    ArrayList<Pagina> paginas;

    // ######################################################################
    public Sitio()
    {
        paginas = new ArrayList<>();
    }

    public Sitio(String idSite)
    {
        this();
        this.idSite = idSite;
    }

    // ######################################################################

    public String getIdSite()
    {
        return idSite;
    }

    public String getUserNew()
    {
        return userNew;
    }

    public String getDateNew()
    {
        return dateNew;
    }

    public String getUserMod()
    {
        return userMod;
    }

    public String getDateMod()
    {
        return dateMod;
    }

    public ArrayList<Pagina> getPaginas()
    {
        return paginas;
    }

    
    
    // ######################################################################

    public void setUserNew(String userNew)
    {
        this.userNew = userNew;
    }

    public void setDateNew(String dateNew)
    {
        this.dateNew = dateNew;
    }

    public void setUserMod(String userMod)
    {
        this.userMod = userMod;
    }

    public void setDateMod(String dateMod)
    {
        this.dateMod = dateMod;
    }
   
}
