/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package josq.cms.lenguajes.modelos.cup;

import java.util.ArrayList;
import josq.cms.lenguajes.modelos.Indicador;
import josq.cms.lenguajes.modelos.cup.simbolos.Atributo;
import josq.cms.lenguajes.modelos.cup.simbolos.Parametro;

/**
 *
 * @author JavierOswaldo
 */
public class Accion
{
    Indicador tipo;
    ArrayList<Parametro> parametros;
    ArrayList<Atributo> atributos;
    ArrayList<String> etiquetas;
}
