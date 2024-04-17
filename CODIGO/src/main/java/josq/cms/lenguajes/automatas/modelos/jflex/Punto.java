/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package josq.cms.lenguajes.automatas.modelos.jflex;

/**
 *
 * @author JavierOswaldo
 */
public class Punto
{
    private int yycolumn;
    private int yyline;
    private int yylength;
    private int yychar;
    
    public Punto(int yycolumn, int yyline, int yylength, int yychar)
    {
        this.yycolumn = yycolumn;
        this.yyline = yyline;
        this.yylength = yylength;
        this.yychar = yychar;
    }

    @Override
    public String toString()
    {
        return "h="+yycolumn+",v="+yyline+",l="+yylength+",c="+yychar;
    }
}
