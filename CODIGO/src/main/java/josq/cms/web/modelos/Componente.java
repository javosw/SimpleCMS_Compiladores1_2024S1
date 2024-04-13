/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package josq.cms.web.modelos;

/**
 *
 * @author JavierOswaldo
 */
public class Componente
{
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
    
}
