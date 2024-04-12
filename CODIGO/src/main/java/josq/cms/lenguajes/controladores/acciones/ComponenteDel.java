/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package josq.cms.lenguajes.controladores.acciones;

import josq.cms.lenguajes.controladores.Instruccion;

/**
 *
 * @author JavierOswaldo
 */
public class ComponenteDel implements  Instruccion
{
    String idComp;
    String idPage;

    public ComponenteDel(String idComp, String idPage)
    {
        this.idComp = idComp;
        this.idPage = idPage;
    }
    
}
