// meta characters:  |  (  )  {  }  [  ]  < >  \  .  *  +  ?  ^  $  / . " ~ !

// codigo antes de la clase lexer
package josq.cms.lenguajes.automatas;

import java.io.Reader;

//import java_cup.runtime.*;
import java_cup.runtime.Symbol;
import java_cup.runtime.SymbolFactory;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.ComplexSymbolFactory.Location;
import java_cup.runtime.DefaultSymbolFactory;

import josq.cms.archivos.MiArchivo;
import josq.cms.lenguajes.automatas.ParserAccionesSym;
import josq.cms.lenguajes.automatas.modelos.jflex.Punto;
import josq.cms.lenguajes.controladores.EjecutarComandos;

%%
// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% %%%%%%%%%%%%%%%%%%%%

// configuracion de jflex
//%debug
%16bit
// configuracion de la clase lexer
%class LexerComandos
%public
//%apiprivate 
%cupsym ParserComandosSym
%cup
%line
%column
%char

// codigo dentro de la clase lexer
%{
    public LexerComandos(Reader myReader, DefaultSymbolFactory myFactory) { this(myReader); this.myFactory = myFactory; }

    private DefaultSymbolFactory myFactory;

    private Symbol symbol(String name, int sym) {
        int izq = (int)yychar+1;
        int der = (int)yychar+yylength();
        Symbol mySymbol = myFactory.newSymbol(name, sym, izq, der);
        return mySymbol;
    }
    private Symbol symbol(String name, int sym, Object val) {
        int izq = (int)yychar+1;
        int der = (int)yychar+yylength();
        Symbol mySymbol = myFactory.newSymbol(name, sym, izq, der, val);
        return mySymbol;
    }

    // para errores lexicos
    public Punto getPunto(){ return new Punto(yycolumn+1, yyline+1, yylength(), (int)yychar+1); };
    StringBuilder log(String text) 
    {
        EjecutarComandos.logSintaxis.append(text); 
        return EjecutarComandos.logSintaxis; 
    }

    // para cambio de contexto lexico

%}

// estados lexicos
//%xstate CONTEXTO_1
%state MI_ID

// macros para regex

InvisiblesVertical    =  \r|\n|\r\n
InvisiblesHorizontal  =  [ \t\f]
Invisibles            =  ({InvisiblesHorizontal} | {InvisiblesVertical})+
_                     =  {Invisibles}

pre    =  _ | - | \$
miID   =  {pre}([a-zA-Z0-9]|{pre})*

consultar            =  [cC][oO][nN][sS][uU][lL][tT][aA][rR]
visitas_sitio        =  [vV][iI][sS][iI][tT][aA][sS][_][sS][iI][tT][iI][oO]
visitas_pagina       =  [vV][iI][sS][iI][tT][aA][sS][_][pP][aA][gG][iI][nN][aA]
paginas_populares    =  [pP][aA][gG][iI][nN][aA][sS][_][pP][oO][pP][uU][lL][aA][rR][eE][sS]
componente           =  [cC][oO][mM][pP][oO][nN][eE][nN][tT][eE]
todos                =  [tT][oO][dD][oO][sS]
titulo               =  [tT][iI][tT][uU][lL][oO]
menu                 =  [mM][eE][nN][uU]
parrafo              =  [pP][aA][rR][rR][aA][fF][oO]
imagen               =  [iI][mM][aA][gG][eE][nN]
video                =  [vV][iI][dD][eE][oO]

%%
// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% %%%%%%%%%%%%%%%%%%%%

<YYINITIAL> {
{consultar}              { return symbol("",ParserComandosSym.CONSULT); }
{visitas_sitio}          { return symbol("",ParserComandosSym.SITE_VIEWS); }
{visitas_pagina}         { return symbol("",ParserComandosSym.PAGE_VIEWS); }
{paginas_populares}      { return symbol("",ParserComandosSym.PAGE_POP); }
{componente}             { return symbol("",ParserComandosSym.COMP); }
{todos}                  { return symbol("",ParserComandosSym.TODOS); }
{titulo}                 { return symbol("",ParserComandosSym.TITULO); }
{menu}                   { return symbol("",ParserComandosSym.MENU); }
{parrafo}                { return symbol("",ParserComandosSym.PARRAFO); }
{imagen}                 { return symbol("",ParserComandosSym.IMAGEN); }
{video}                  { return symbol("",ParserComandosSym.VIDEO); }

}

<MI_ID> {
    {miID}     { return symbol("",ParserComandosSym.ID,yytext()); }
    \"         { yybegin(YYINITIAL); return symbol("",ParserComandosSym.COMI); }
}

\" { yybegin(MI_ID); return symbol("",ParserComandosSym.COMI); }
\, { return symbol("",ParserComandosSym.COMA); }
\. { return symbol("",ParserComandosSym.DOT); }
\; { return symbol("",ParserComandosSym.PUNCOM); }

// ignorados
{Invisibles}  { }

// error
[^]  { log("@lexer: ").append(getPunto().toString()).append("\n"); return symbol("",ParserComandosSym.error); }
