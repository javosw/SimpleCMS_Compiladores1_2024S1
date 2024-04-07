// meta characters:  |  (  )  {  }  [  ]  < >  \  .  *  +  ?  ^  $  / . " ~ !

// codigo antes de la clase lexer
package josq.cms.lenguajes.lexer;

import java.io.Reader;

//import java_cup.runtime.Symbol;
import java_cup.runtime.SymbolFactory;
//import java_cup.runtime.*;
import josq.cms.lenguajes.parser.ParserXMLSym;
%%
// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% %%%%%%%%%%%%%%%%%%%%

// configuracion de jflex
//%debug

// configuracion de la clase lexer
%class LexerAcciones
%public
//%apiprivate 
%cupsym ParserAccionesSym
%cup
%line
%column
%char

// codigo dentro de la clase lexer
%{
    ComplexSymbolFactory fact = null;

    public LexerAcciones(Reader in, ComplexSymbolFactory sf)
    { this(in); fact = sf; }

    private Symbol symbol(String name, int sym) {
        Location izq = new Location(yyline+1, yycolumn+1,yychar);
        Location der = new Location(yyline+1, yycolumn+yylength(), yychar+yylength());
        return fact.newSymbol(name, sym, izq, der);
    }
    private Symbol symbol(String name, int sym, Object val) {
        Location izq = new Location(yyline+1, yycolumn+1,yychar);
        Location der = new Location(yyline+1, yycolumn+yylength(), yychar+yylength());
        return fact.newSymbol(name, sym, izq, der, val);
    }
    private void error(String message) {
        System.out.println("Error at line "+(yyline+1)+", column "+(yycolumn+1)+" : "+message);
    }



    StringBuffer buff = new StringBuffer();

    void print(String texto){ System.out.print(texto); }
    void print(){ print(yytext()+" "); }
    void cleanBuffer()
    {
        buff.delete(0, buff.length());
        buff.trimToSize();
    }
    String reduceBuffer(String texto)
    {
        buff.append(texto);
        buff.deleteCharAt(buff.length()-1);
        buff.deleteCharAt(0);
        String temp = buff.toString();
        cleanBuffer();
        return temp;
    }

%}

// estados lexicos
%state MI_ETIQUETA, ID, MI_TITULO, ID_USUARIO, MI_FECHA //, MI_COMPONENTE
%state MI_TEXTO, MI_COLOR, MI_URL, MI_NUMERO, MIS_ETIQUETAS //, MI_ALINEACION
//%xstate CONTEXTO_1

// macros para regex

LineTerminator  =  \r|\n|\r\n
Invisibles      =  ({LineTerminator} | [ \t\f])+
_               =  {Invisibles}

pre  =  _ | - | $
id   =  {pre}([a-zA-Z0-9]|{pre})*
//idSitio 
//idPagina 
//idComponente 
//idUsuario

miTexto  =  [a-zA-Z0-9]+
//miTitulo

miNumero      =  [1-9][0-9]*
miColor       =  #[0-9a-fA-F]{6}
miURL         =  ((http|https)\:\/\/)?[a-zA-Z]+(\.[a-zA-Z0-9]+)*(\/|\/[a-zA-Z0-9]+)*
miFecha       =  [0-9]{4}\-[0-9]{2}\-[0-9]{2}
miEtiqueta    =  [a-zA-Z0-9]+
//misEtiquetas

//miComponente  =  "TITULO"|"PARRAFO"|"IMAGEN"|"VIDEO"|"MENU"
//miAlineacion  =  "CENTRAR"|"IZQUIERDA"|"DERECHA"|"JUSTIFICAR"

%%
// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% %%%%%%%%%%%%%%%%%%%%

<YYINITIAL> {

// nodos
"acciones"    { print(); }
"accion"      { print(); }
"parametros"  { print(); }
"parametro"   { print(); }
"atributos"   { print(); }
"atributo"    { print(); }
"etiquetas"   { print(); }
"etiqueta"    { print(); }

// nodo.atributo
"valor"  { print(); yybegin(MI_ETIQUETA); }
"nombre" { print(); }

// accion.nombre
"NUEVO_SITIO_WEB"       { print(); }
"BORRAR_SITIO_WEB"      { print(); }
"NUEVA_PAGINA"          { print(); }
"MODIFICAR_PAGINA"      { print(); }
"BORRAR_PAGINA"         { print(); }
"AGREGAR_COMPONENTE"    { print(); }
"MODIFICAR_COMPONENTE"  { print(); }
"BORRAR_COMPONENTE"     { print(); }

// parametro.nombre
"ID"                    { print(); yybegin(ID);}
"TITULO"                { print(); yybegin(MI_TITULO);}
"SITIO"                 { print(); yybegin(ID);}
"PADRE"                 { print(); yybegin(ID);}
"PAGINA"                { print(); yybegin(ID);}
"CLASE"                 { print(); }
"USUARIO_CREACION"      { print(); yybegin(ID_USUARIO);}
"FECHA_CREACION"        { print(); yybegin(MI_FECHA);}
"USUARIO_MODIFICACION"  { print(); yybegin(ID_USUARIO);}
"FECHA_MODIFICACION"    { print(); yybegin(MI_FECHA);}

// atributo.nombre
"TEXTO"       { print(); yybegin(MI_TEXTO);}
"ALINEACION"  { print(); }
"COLOR"       { print(); yybegin(MI_COLOR);}
"ORIGEN"      { print(); yybegin(MI_URL);}
"ALTURA"      { print(); yybegin(MI_NUMERO);}
"ANCHO"       { print(); yybegin(MI_NUMERO);}
"PADRE"       { print(); yybegin(ID);}
"ETIQUETAS"   { print(); yybegin(MIS_ETIQUETAS);}

// miComponente
//"TITULO"   {}
"PARRAFO"  { print(); }
"IMAGEN"   { print(); }
"VIDEO"    { print(); }
"MENU"     { print(); }

// miAlineacion 
"CENTRAR"     { print(); }
"IZQUIERDA"   { print(); }
"DERECHA"     { print(); }
"JUSTIFICAR"  { print(); }

}

<ID> {
    {id}  { print(); }
}
<ID_USUARIO> {
    {id}  { print(); }
}
<MI_FECHA> {
    {miFecha}  { print(); }
}
<MI_TEXTO> {
    {miTexto}  { print(); }
}
<MI_TITULO> {
    {miTexto}  { print(); }
}
<MI_COLOR> {
    {miColor}  { print(); }
}
<MI_NUMERO> {
    {miNumero}  { print(); }
}
<MI_URL> {
    {miURL}  { print(); }
}
<MI_ETIQUETA> {
    {miEtiqueta}  { print(); yybegin(YYINITIAL); }
}
<MIS_ETIQUETAS> {
    {miEtiqueta}  { print(); }
    \|  { print(); }
}

// puntuacion
"<"  { print(); }
">"  { print(); }
"/"  { print(); }
\"   { print(); }
"["  { print(); }
"]"  { print(); yybegin(YYINITIAL);}
\=   { print(); }

// ignorados
{Invisibles}  { }

// error
[^]  { }
