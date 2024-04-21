/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package josq.cms.web.modelos.componentes;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
/**
 *
 * @author JavierOswaldo
 */
public class Menu implements Serializable
{
    private static final long serialVersionUID = 1L;
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

    String idPageRoot;
    Set<String> paginas;
    
    public Menu()
    {
        this.paginas = new HashSet<>();
    };
    public Menu(String idPageRoot) 
    {
        this();
        this.idPageRoot = idPageRoot; 
    }
    
    public void addPagina(String pagina)
    {
        paginas.add(pagina);
    }

    public Set<String> getPaginas()
    {
        return paginas;
    }

    public String getIdPageRoot()
    {
        return idPageRoot;
    }
}
