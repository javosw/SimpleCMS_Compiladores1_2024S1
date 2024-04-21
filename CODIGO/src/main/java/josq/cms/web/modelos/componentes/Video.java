/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package josq.cms.web.modelos.componentes;

import java.io.Serializable;

/**
 *
 * @author JavierOswaldo
 */
public class Video implements Serializable
{
    private static final long serialVersionUID = 1L;


    String url;
    int sizeX;
    int sizeY;

    public Video(String url)
    {
        this.url = url;
    }

    public void setArea(int sizeX, int sizeY)
    {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }
/*
    parametros: {
            * ID: [idComponente]
            * PAGINA: [idPagina]
            * CLASE: [VIDEO]
    }
    atributos: {
            * ORIGEN: [https://example.com/x.mp4]
            * ALTURA: [240]
            * ANCHO: [360]
    }

*/

    public String getUrl()
    {
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

}
