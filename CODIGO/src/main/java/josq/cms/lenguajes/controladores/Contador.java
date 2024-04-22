/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package josq.cms.lenguajes.controladores;

/**
 *
 * @author JavierOswaldo
 */
public class Contador
{
    private int views = 0;
    
    public void addViews(int views)
    {
        this.views = this.views + views;
    }

    public int getViews()
    {
        return views;
    }
    
}
