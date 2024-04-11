/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package josq.cms.lenguajes.modelos.jflex;

/**
 *
 * @author JavierOswaldo
 */
public class Punto
{
    int yycolumn;
    int yyline;
    int yylength;
    int yychar;
    
    public Punto(int yycolumn, int yyline, int yylength, int yychar)
    {
        this.yycolumn = yycolumn;
        this.yyline = yyline;
        this.yylength = yylength;
        this.yychar = yychar;
    }
}
