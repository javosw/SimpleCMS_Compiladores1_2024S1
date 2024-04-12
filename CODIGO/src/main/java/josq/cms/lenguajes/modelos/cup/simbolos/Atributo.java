/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package josq.cms.lenguajes.modelos.cup.simbolos;

import josq.cms.lenguajes.modelos.Indicador;

/**
 *
 * @author JavierOswaldo
 */
public class Atributo
{
    Indicador tipo;
    Par contenido;

    public Atributo(Indicador tipo, Par contenido)
    {
        this.tipo = tipo;
        this.contenido = contenido;
    }

    public Indicador getTipo()
    {
        return tipo;
    }

    public Par getContenido()
    {
        return contenido;
    }
}
