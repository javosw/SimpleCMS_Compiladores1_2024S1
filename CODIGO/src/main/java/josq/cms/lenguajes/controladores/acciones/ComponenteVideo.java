/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package josq.cms.lenguajes.controladores.acciones;

import josq.cms.lenguajes.controladores.Instruccion;
import josq.cms.lenguajes.automatas.modelos.Indicador;

/**
 *
 * @author JavierOswaldo
 */
public class ComponenteVideo implements Instruccion
{
    String idComp;
    String idPage;
    String url;
    int sizeX;
    int sizeY;
    Indicador accion;

    public ComponenteVideo(String idComp, String idPage, String url, int sizeX, int sizeY, Indicador accion)
    {
        this.idComp = idComp;
        this.idPage = idPage;
        this.url = url;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.accion = accion;
    }

    @Override
    public void ejecutar()
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
