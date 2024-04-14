/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package josq.cms.lenguajes.automatas.modelos.cup;

import java.util.ArrayList;
import josq.cms.lenguajes.automatas.modelos.Indicador;

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

    public Accion()
    {
        this.parametros = new ArrayList<>();
        this.atributos = new ArrayList<>();
        this.etiquetas = new ArrayList<>();
    }
    public Accion(Indicador tipo)
    {
        this();
        this.tipo = tipo;
    }

    public void setTipo(Indicador tipo)
    {
        this.tipo = tipo;
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
    
    
    void test(Parametro p){parametros.addAll(parametros);}

}
