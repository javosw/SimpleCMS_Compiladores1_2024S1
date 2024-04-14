/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package josq.cms.web.modelos.componentes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;
import josq.cms.web.modelos.Pagina;
/**
 *
 * @author JavierOswaldo
 */
public class Menu implements Serializable
{
    private static final long serialVersionUID = 1L;


    String idPageRoot;
    Set<String> paginas;
    
    public void addPagina(String pagina)
    {
        paginas.add(pagina);
    }

    //Pagina refPage;
    
 /*
    parametros: {
            * ID: [idComponente]
            * PAGINA: [idPagina]
            * CLASE: [MENU]
    }
    atributos: {
            * PADRE: [idPagina]
            * ETIQUETAS: [ grupo1 | grupo2 | ... ]
    }
*/   

    public Menu(String idPageRoot)
    {
        this.idPageRoot = idPageRoot;
    }
}
