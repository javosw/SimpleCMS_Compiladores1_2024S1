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
public class ComponenteParrafo implements Instruccion
{
    String idComp;
    String idPage;
    String text;
    Indicador align;
    String color;
    Indicador clase;
    Indicador accion;

    public ComponenteParrafo(String idComp, String idPage, String text, Indicador align, String color, Indicador clase, Indicador accion)
    {
        this.idComp = idComp;
        this.idPage = idPage;
        this.text = text;
        this.align = align;
        this.color = color;
        this.clase = clase;
        this.accion = accion;
    }

}
