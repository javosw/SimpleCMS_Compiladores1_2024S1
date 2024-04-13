/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package josq.cms.lenguajes.controladores.acciones;

import java.util.ArrayList;
import josq.cms.lenguajes.controladores.Instruccion;
import josq.cms.lenguajes.modelos.Indicador;

/**
 *
 * @author JavierOswaldo
 */
public class ComponenteMenu implements Instruccion
{
    String idComp;
    String idPage;
    String idPageRoot;
    ArrayList<String> labels;
    Indicador accion;

    public ComponenteMenu(String idComp, String idPage, String idPageRoot, ArrayList<String> labels, Indicador accion)
    {
        this.idComp = idComp;
        this.idPage = idPage;
        this.idPageRoot = idPageRoot;
        this.labels = labels;
        this.accion = accion;
    }
    
    // ejecutar
    // buscar idPage.bin
    // crear objeto menu
    // agregar this menu by using idComp

    @Override
    public void ejecutar()
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
