/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package josq.cms.lenguajes.controladores.acciones;

import josq.cms.lenguajes.controladores.Instruccion;
import josq.cms.lenguajes.modelos.Indicador;

/**
 *
 * @author JavierOswaldo
 */
public class ComponenteImagen implements Instruccion
{
    String idComp;
    String idPage;
    String url;
    int sizeX;
    int sizeY;
    Indicador align;
    Indicador accion;

    public ComponenteImagen(String idComp, String idPage, String url, int sizeX, int sizeY, Indicador align, Indicador accion)
    {
        this.idComp = idComp;
        this.idPage = idPage;
        this.url = url;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.align = align;
        this.accion = accion;
    }



}
