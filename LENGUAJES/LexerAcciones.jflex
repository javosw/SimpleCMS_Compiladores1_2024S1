// meta characters:  |  (  )  {  }  [  ]  < >  \  .  *  +  ?  ^  $  / . " ~ !

// codigo antes de la clase lexer
package josq.cms.lenguajes.lexer;

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

    public LexerAcciones(java.io.Reader in, ComplexSymbolFactory sf)
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

miTexto  =  [a-zA-Z0-9]+

miNumero      =  [1-9][0-9]*
miColor       =  #[0-9a-fA-F]{6}
miURL         =  ((http|https)\:\/\/)?[a-zA-Z]+(\.[a-zA-Z0-9]+)*(\/|\/[a-zA-Z0-9]+)*
miFecha       =  [0-9]{4}\-[0-9]{2}\-[0-9]{2}
miEtiqueta    =  [a-zA-Z0-9]+
misEtiquetas  =  {miEtiqueta}(\|{miEtiqueta})*

miComponente  =  "TITULO"|"PARRAFO"|"IMAGEN"|"VIDEO"|"MENU"
miAlineacion  =  "CENTRAR"|"IZQUIERDA"|"DERECHA"|"JUSTIFICAR"

tipoAccion = 
tipoParam = 
tipoAtrib = 

%%
// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% %%%%%%%%%%%%%%%%%%%%

<YYINITIAL> {

// nodos
"acciones"    {}
"accion"      {}
"parametros"  {}
"parametro"   {}
"atributos"   {}
"atributo"    {}
"etiquetas"   {}
"etiqueta"    {}

// nodo.atributo
"valor" //miEtiqueta
"nombre"

// accion.nombre
"NUEVO_SITIO_WEB"       {}
"BORRAR_SITIO_WEB"      {}
"NUEVA_PAGINA"          {}
"MODIFICAR_PAGINA"      {}
"BORRAR_PAGINA"         {}
"AGREGAR_COMPONENTE"    {}
"MODIFICAR_COMPONENTE"  {}
"BORRAR_COMPONENTE"     {}

// parametro.nombre
"ID"                    {yybegin(ID);}
"TITULO"                {yybegin(MI_TITULO);}
"SITIO"                 {yybegin(ID);}
"PADRE"                 {yybegin(ID);}
"PAGINA"                {yybegin(ID);}
"CLASE"                 {}
"USUARIO_CREACION"      {yybegin(ID_USUARIO);}
"FECHA_CREACION"        {yybegin(MI_FECHA);}
"USUARIO_MODIFICACION"  {yybegin(ID_USUARIO);}
"FECHA_MODIFICACION"    {yybegin(MI_FECHA);}

// atributo.nombre
"TEXTO"       {yybegin(MI_TEXTO);}
"ALINEACION"  {}
"COLOR"       {yybegin(MI_COLOR);}
"ORIGEN"      {yybegin(MI_URL);}
"ALTURA"      {yybegin(MI_NUMERO);}
"ANCHO"       {yybegin(MI_NUMERO);}
"PADRE"       {yybegin(ID);}
"ETIQUETAS"   {yybegin(MIS_ETIQUETAS);}

// miComponente
//"TITULO"   {}
"PARRAFO"  {}
"IMAGEN"   {}
"VIDEO"    {}
"MENU"     {}

// miAlineacion 
"CENTRAR"     {}
"IZQUIERDA"   {}
"DERECHA"     {}
"JUSTIFICAR"  {}

}

<ID> {
    {id} {}
}
<ID_USUARIO> {
    {id} {}

}
<MI_FECHA> {
    {miFecha} {}
}
<MI_TEXTO> {
    {miTexto} {}
}
<MI_TITULO> {
    {miTexto} {}
}
<MI_COLOR> {
    {miColor} {}
}
<MI_NUMERO> {
    {miNumero} {}
}
<MI_URL> {
    {miURL} {}
}
<MIS_ETIQUETAS> {
    {miEtiqueta} {}
    \| {}
}

// puntuacion
"<"
">"
"/"
\"
"["
"]"
\=

// ignorados
{Invisibles}  { }

// error
[^]  { }
