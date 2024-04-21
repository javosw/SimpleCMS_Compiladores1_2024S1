/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package josq.cms.lenguajes.automatas.modelos.cup;

import josq.cms.lenguajes.automatas.modelos.Indicador;

/**
 *
 * @author JavierOswaldo
 */
public class Comando
{
    private Indicador operador;
    private Indicador operandoTipo;
    private Object operando;

    public Comando()
    {
        this.operador = Indicador.ERROR;
        this.operandoTipo = Indicador.ERROR;
        this.operando = null;
    }

    public Comando(Indicador operador, Indicador operandoTipo, Object operando)
    {
        this.operador = operador;
        this.operandoTipo = operandoTipo;
        this.operando = operando;
        checkOperando();
    }

    public Comando(Indicador operador, Par operando)
    {
        this.operador = operador;
        this.operandoTipo = operando.getKey();
        this.operando = operando.getValue();
        checkOperando();
    }
    private void checkOperando()
    {
        boolean usaSite = operador == Indicador.SQ_PAGE_POP;
        boolean usaSiteList = operador == Indicador.SQ_SITE_VIEWS;
        boolean usaPath = operador == Indicador.SQ_TODOS || operador == Indicador.SQ_TITULO 
                || operador == Indicador.SQ_MENU || operador == Indicador.SQ_IMAGEN 
                || operador == Indicador.SQ_VIDEO || operador == Indicador.SQ_PARRAFO;
        boolean usaPathList = operador == Indicador.SQ_PAGE_VIEWS;
        
        if(usaSite)
        {
            boolean esOperandoCorrecto = operandoTipo == Indicador.SQ_ARG_SITE;
            if(!esOperandoCorrecto) operador = Indicador.ERROR;
        }
        else if(usaSiteList)
        {
            boolean esOperandoCorrecto = operandoTipo == Indicador.SQ_ARG_SITES;
            if(!esOperandoCorrecto) operador = Indicador.ERROR;
        }
        else if(usaPath)
        {
            boolean esOperandoCorrecto = operandoTipo == Indicador.SQ_ARG_PATH;
            if(!esOperandoCorrecto) operador = Indicador.ERROR;
        }
        else if(usaPathList)
        {
            boolean esOperandoCorrecto = operandoTipo == Indicador.SQ_ARG_PATHS;
            if(!esOperandoCorrecto) operador = Indicador.ERROR;
        }
        else { operador = Indicador.ERROR; }
    }

    public Indicador getOperador()
    {
        return operador;
    }

    public Object getOperando()
    {
        return operando;
    }

}
