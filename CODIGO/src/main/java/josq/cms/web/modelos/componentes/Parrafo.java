/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package josq.cms.web.modelos.componentes;

import java.io.Serializable;
import josq.cms.lenguajes.automatas.modelos.Indicador;

/**
 *
 * @author JavierOswaldo
 */
public class Parrafo implements Serializable
{
    private static final long serialVersionUID = 1L;


    String text;
    Indicador align;
    String color;

    public void setAlign(Indicador align)
    {
        this.align = align;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

/*
    parametros: {
            * ID: [idComponente]
            * PAGINA: [idPagina]
            * CLASE: [TITULO] | [PARRAFO]
    }
    atributos: {
            * TEXTO: [miTextoAZ09]
            ALINEACION: [CENTRAR] | [IZQUIERDA] | [DERECHA] | [JUSTIFICAR]
            COLOR: [#5A5A5A]
    }
*/

    public Parrafo(String text)
    {
        this.text = text;
    }
}
