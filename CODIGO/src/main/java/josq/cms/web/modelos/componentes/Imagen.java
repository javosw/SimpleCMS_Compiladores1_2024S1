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
public class Imagen implements Serializable
{
    private static final long serialVersionUID = 1L;


    String url;
    int sizeX;
    int sizeY;
    Indicador align;

    public void setAlign(Indicador align)
    {
        this.align = align;
    }
    
/*
    parametros: {
            * ID: [idComponente]
            * PAGINA: [idPagina]
            * CLASE: [IMAGEN]
    }
    atributos: {
            * ORIGEN: [https://example.com/x.png]
            ALINEACION: [CENTRAR] | [IZQUIERDA] | [DERECHA] | [JUSTIFICAR]
            * ALTURA: [240]
            * ANCHO: [360]
    }

*/

    public void setArea(int sizeX, int sizeY)
    {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }
    public Imagen(String url)
    {
        this.url = url;
    }

    public String getUrl()
    {
        if(this.url == null) return "";
        return url;
    }

    public int getSizeX()
    {
        if(this.sizeX == 0) return 100;
        
        return sizeX;
    }

    public int getSizeY()
    {
        if(this.sizeY == 0) return 100;
        return sizeY;
    }

    public Indicador getAlign()
    {
        if (this.align == null) return Indicador.T_IZQUIERDA;
        return align;
    }
}
