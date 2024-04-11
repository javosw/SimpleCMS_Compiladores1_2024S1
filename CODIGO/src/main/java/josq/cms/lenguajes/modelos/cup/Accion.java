/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package josq.cms.lenguajes.modelos.cup;

import java.util.ArrayList;
import josq.cms.lenguajes.modelos.Indicador;
import josq.cms.lenguajes.modelos.cup.simbolos.Atributo;
import josq.cms.lenguajes.modelos.cup.simbolos.Parametro;

/**
 *
 * @author JavierOswaldo
 */
public class Accion
{
    private Indicador tipo;
    private ArrayList<Parametro> parametros;
    private ArrayList<Atributo> atributos;
    private ArrayList<String> etiquetas;

    public Accion() {}

    public void setTipo(Indicador tipo)
    {
        this.tipo = tipo;
    }

    public void setParametros(ArrayList<Parametro> parametros)
    {
        this.parametros = parametros;
    }

    public void setAtributos(ArrayList<Atributo> atributos)
    {
        this.atributos = atributos;
    }

    public void setEtiquetas(ArrayList<String> etiquetas)
    {
        this.etiquetas = etiquetas;
    }

    public Indicador getTipo()
    {
        return tipo;
    }

    public ArrayList<Parametro> getParametros()
    {
        return parametros;
    }

    public ArrayList<Atributo> getAtributos()
    {
        return atributos;
    }

    public ArrayList<String> getEtiquetas()
    {
        return etiquetas;
    }

}
