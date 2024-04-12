/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package josq.cms.lenguajes.controladores.acciones;

import josq.cms.lenguajes.controladores.Instruccion;

/**
 *
 * @author JavierOswaldo
 */
public class SitioNew implements Instruccion
{
    String idSite;
    String userNew;
    String userMod;
    String dateNew;
    String dateMod;

    public SitioNew(String idSite, String userNew, String userMod, String dateNew, String dateMod)
    {
        this.idSite = idSite;
        this.userNew = userNew;
        this.userMod = userMod;
        this.dateNew = dateNew;
        this.dateMod = dateMod;
    }
}
