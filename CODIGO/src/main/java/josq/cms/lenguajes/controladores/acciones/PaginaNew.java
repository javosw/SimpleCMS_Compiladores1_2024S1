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
public class PaginaNew implements Instruccion
{
    String idPage;
    String idPageRoot;
    String idSite;
    String title;
    ArrayList<String> labels;
    String userNew;
    String userMod;
    String dateNew;
    String dateMod;

    // esto se puede abstraer mas: params de identificacion, params de diferenciacion, parms de tiempo
    public PaginaNew(String idPage, String idPageRoot, String idSite, String title, ArrayList<String> labels, String userNew, String userMod, String dateNew, String dateMod)
    {
        this.idPage = idPage;
        this.idPageRoot = idPageRoot;
        this.idSite = idSite;
        this.title = title;
        this.labels = labels;
        this.userNew = userNew;
        this.userMod = userMod;
        this.dateNew = dateNew;
        this.dateMod = dateMod;
    }

    // ejecutar
    // archivo? -> ignorar
    // sino -> crear objeto pagina
    // buscar pageroot -> add this page to pageroot
    // crear archivo pagina

    @Override
    public void ejecutar()
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
