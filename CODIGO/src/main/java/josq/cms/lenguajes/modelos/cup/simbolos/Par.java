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
public class Par
{
    private Indicador key;
    private Object value;

    public Par(Indicador key, Object value)
    {
        this.key = key;
        this.value = value;
    }

    public Indicador getKey()
    {
        return key;
    }

    public Object getValue()
    {
        return value;
    }

    boolean hasValue()
    {
        return value != null;
    }

}
