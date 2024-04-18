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
import josq.cms.lenguajes.controladores.EjecutarAcciones;

%%
// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% %%%%%%%%%%%%%%%%%%%%

// configuracion de jflex
//%debug
%16bit
// configuracion de la clase lexer
%class LexerSQCMS
%public
//%apiprivate 
%cupsym ParserSQCMSSym
%cup
%line
%column
%char

// codigo dentro de la clase lexer
%{
    public LexerSQCMS(Reader myReader, DefaultSymbolFactory myFactory) { this(myReader); this.myFactory = myFactory; }

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
    public Punto getPunto(){ return new Punto(yycolumn, yyline, yylength(), (int)yychar+1); };
    StringBuilder log(String text) 
    {
        EjecutarSQCMS.logSintaxis.append(text); 
        return EjecutarSQCMS.logSintaxis; 
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
{consultar}              { return symbol("",ParserSQCMSSym.CONSULT); }
{visitas_sitio}          { return symbol("",ParserSQCMSSym.SITE_VIEWS); }
{visitas_pagina}         { return symbol("",ParserSQCMSSym.PAGE_VIEWS); }
{paginas_populares}      { return symbol("",ParserSQCMSSym.PAGE_POP); }
{componente}             { return symbol("",ParserSQCMSSym.COMP); }
{todos}                  { return symbol("",ParserSQCMSSym.TODOS); }
{titulo}                 { return symbol("",ParserSQCMSSym.TITULO); }
{menu}                   { return symbol("",ParserSQCMSSym.MENU); }
{parrafo}                { return symbol("",ParserSQCMSSym.PARRAFO); }
{imagen}                 { return symbol("",ParserSQCMSSym.IMAGEN); }
{video}                  { return symbol("",ParserSQCMSSym.VIDEO); }

}

<MI_ID> {
    {miID}     { return symbol("",ParserSQCMSSym.ID,yytext()); }
    \"         { yybegin(YYINITIAL); return symbol("",ParserSQCMSSym.COMI); }
}

\" { yybegin(MI_ID); return symbol("",ParserSQCMSSym.COMI); }
\, { return symbol("",ParserSQCMSSym.COMA); }
\. { return symbol("",ParserSQCMSSym.DOT); }
\; { return symbol("",ParserSQCMSSym.PUNCOM); }

// ignorados
{Invisibles}  { }

// error
[^]  { log("@lexer: ").append(getPunto().toString()).append("\n"); return symbol("",ParserAccionesSym.error); }
