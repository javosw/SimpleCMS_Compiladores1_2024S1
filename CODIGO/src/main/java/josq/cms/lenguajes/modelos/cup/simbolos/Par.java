/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package josq.cms.lenguajes.modelos.cup.simbolos;

import java.util.ArrayList;
import josq.cms.lenguajes.modelos.Indicador;

/**
 *
 * @author JavierOswaldo
 */
public class Par
{
    private Indicador key;
    private Indicador valueIndicador;
    private String valueString;
    private ArrayList<String> valueStrings;

    public Par(Indicador key, String val)
    {
        this.key = key;
        this.valueString = val;
    }
    public Par(Indicador key, Indicador val)
    {
        this.key = key;
        this.valueIndicador = val;
    }
    public Par(Indicador key, ArrayList<String> val)
    {
        this.key = key;
        this.valueStrings = val;
    }

    public Indicador getKey()
    {
        return key;
    }

    public Indicador getValueIndicador()
    {
        return valueIndicador;
    }

    public String getValueString()
    {
        return valueString;
    }

    
    
}
