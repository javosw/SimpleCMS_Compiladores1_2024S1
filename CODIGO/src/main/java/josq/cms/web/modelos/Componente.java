/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package josq.cms.web.modelos;

import java.io.Serializable;

/**
 *
 * @author JavierOswaldo
 */
public class Componente implements Serializable
{
    private static final long serialVersionUID = 1L;

    String idComponente;
    String idPagina;
    Object widget;

    public Componente(String idComponente, String idPagina)
    {
        this.idComponente = idComponente;
        this.idPagina = idPagina;
    }

    public void setWidget(Object widget)
    {
        this.widget = widget;
    }

    public String getIdComponente()
    {
        return idComponente;
    }

    public String getIdPagina()
    {
        return idPagina;
    }

    public Object getWidget()
    {
        return widget;
    }
    
}
