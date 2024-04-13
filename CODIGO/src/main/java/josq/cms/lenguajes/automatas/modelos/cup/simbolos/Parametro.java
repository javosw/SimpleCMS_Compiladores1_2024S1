/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package josq.cms.lenguajes.automatas.modelos.cup.simbolos;

import josq.cms.lenguajes.automatas.modelos.Indicador;

/**
 *
 * @author JavierOswaldo
 */
public class Parametro
{
    Indicador tipo;
    Object contenido;

    public Parametro(Indicador tipo, Object contenido)
    {
        this.tipo = tipo;
        this.contenido = contenido;
    }

    public Indicador getTipo()
    {
        return tipo;
    }

    public Object getContenido()
    {
        return contenido;
    }
    
}
