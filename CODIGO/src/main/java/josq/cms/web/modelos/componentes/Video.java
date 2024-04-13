/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package josq.cms.web.modelos.componentes;

/**
 *
 * @author JavierOswaldo
 */
public class Video
{
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

}
