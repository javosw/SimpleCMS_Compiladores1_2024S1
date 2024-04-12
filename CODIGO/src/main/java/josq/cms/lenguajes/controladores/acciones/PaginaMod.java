/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package josq.cms.lenguajes.controladores.acciones;

import java.util.ArrayList;
import josq.cms.lenguajes.controladores.Instruccion;

/**
 *
 * @author JavierOswaldo
 */
public class PaginaMod implements Instruccion
{
    String idPage;
    String title;
    ArrayList<String> labels;

    public PaginaMod(String idPage, String title, ArrayList<String> labels)
    {
        this.idPage = idPage;
        this.title = title;
        this.labels = labels;
    }
}
