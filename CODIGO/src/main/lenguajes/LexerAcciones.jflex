// meta characters:  |  (  )  {  }  [  ]  < >  \  .  *  +  ?  ^  $  / . " ~ !

// codigo antes de la clase lexer
package josq.cms.lenguajes.lexer;

import java.io.Reader;

//import java_cup.runtime.*;
import java_cup.runtime.Symbol;
import java_cup.runtime.SymbolFactory;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.ComplexSymbolFactory.Location;

import josq.cms.lenguajes.parser.ParserAccionesSym;
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
    ComplexSymbolFactory myFactory = null;

    public LexerAcciones(Reader in, ComplexSymbolFactory sf)
    { this(in); myFactory = sf; }

    private Symbol symbol(String name, int sym) {
        Location izq = new Location(yyline+1, yycolumn+1, (int)yychar);
        Location der = new Location(yyline+1, yycolumn+yylength(), (int)yychar+yylength());
        Symbol mySymbol = myFactory.newSymbol(name, sym, izq, der);
        print(sym);
        return mySymbol;
    }
    private Symbol symbol(String name, int sym, Object val) {
        Location izq = new Location(yyline+1, yycolumn+1, (int)yychar);
        Location der = new Location(yyline+1, yycolumn+yylength(), (int)yychar+yylength());
        Symbol mySymbol = myFactory.newSymbol(name, sym, izq, der, val);
        print(sym);
        return mySymbol;
    }
    private void error(String message) {
        System.out.println("Error at line "+(yyline+1)+", column "+(yycolumn+1)+" : "+message);
    }

    void print(String txt){ System.out.print(txt); }
    void print(int sym){ print(yytext()+":"+ParserAccionesSym.terminalNames[sym]+" "); }


    StringBuffer buff = new StringBuffer();

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
//%xstate CONTEXTO_1
%state MI_ACCION, MI_PARAMETRO, MI_ETIQUETA, MI_ATRIBUTO
%state MI_ID, MI_ID_USER
%state MI_NUMERO
%state MI_TEXTO, MI_TITULO, MIS_ETIQUETAS
%state MI_COLOR, MI_FECHA, MI_URL
%state UI_WEB, MI_ALIGN

// macros para regex

InvisiblesVertical    =  \r|\n|\r\n
InvisiblesHorizontal  =  [ \t\f]
Invisibles            =  ({InvisiblesHorizontal} | {InvisiblesVertical})+
_                     =  {Invisibles}

pre  =  _ | - | \$
id   =  {pre}([a-zA-Z0-9]|{pre})*
//idSitio 
//idPagina 
//idComponente 
//idUsuario

miTexto  =  ({_}|[a-zA-Z0-9])+
//miTitulo

miNumero      =  [1-9][0-9]*
miColor       =  #[0-9a-fA-F]{6}
miURL         =  ((http|https)\:\/\/)?[a-zA-Z]+(\.[a-zA-Z0-9]+)*(\/|\/[a-zA-Z0-9]+)*
miFecha       =  [0-9]{4}\-[0-9]{2}\-[0-9]{2}
miEtiqueta    =  [a-zA-Z0-9]+

%%
// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% %%%%%%%%%%%%%%%%%%%%

<YYINITIAL> {

// nodos
"acciones"    {                        return symbol("",ParserAccionesSym.ACCIS); }
"parametros"  {                        return symbol("",ParserAccionesSym.PARAMS); }
"atributos"   {                        return symbol("",ParserAccionesSym.ATRIBS); }
"etiquetas"   {                        return symbol("",ParserAccionesSym.ETIQS); }
"accion"      { yybegin(MI_ACCION);    return symbol("",ParserAccionesSym.ACCI); }
"parametro"   { yybegin(MI_PARAMETRO); return symbol("",ParserAccionesSym.PARAM); }
"atributo"    { yybegin(MI_ATRIBUTO);  return symbol("",ParserAccionesSym.ATRIB); }
"etiqueta"    {                        return symbol("",ParserAccionesSym.ETIQ); }

// nodo.atributo
"valor"  { yybegin(MI_ETIQUETA);       return symbol("",ParserAccionesSym.VALOR); }
"nombre" {                             return symbol("",ParserAccionesSym.NOMBRE); }

}
<MI_ACCION>{
// accion.nombre
"NUEVO_SITIO_WEB"       {                     return symbol("",ParserAccionesSym.SITE_NEW); }
"BORRAR_SITIO_WEB"      {                     return symbol("",ParserAccionesSym.SITE_DEL); }
"NUEVA_PAGINA"          {                     return symbol("",ParserAccionesSym.PAGE_NEW); }
"MODIFICAR_PAGINA"      {                     return symbol("",ParserAccionesSym.PAGE_MOD); }
"BORRAR_PAGINA"         {                     return symbol("",ParserAccionesSym.PAGE_DEL); }
"AGREGAR_COMPONENTE"    {                     return symbol("",ParserAccionesSym.COMP_NEW); }
"MODIFICAR_COMPONENTE"  {                     return symbol("",ParserAccionesSym.COMP_MOD); }
"BORRAR_COMPONENTE"     {                     return symbol("",ParserAccionesSym.COMP_DEL); }
\"                      { yybegin(YYINITIAL); return symbol("",ParserAccionesSym.COMI); }
}

<MI_PARAMETRO>{
// parametro.nombre
"ID"                    { yybegin(MI_ID);     return symbol("",ParserAccionesSym.P_ID); }
"SITIO"                 { yybegin(MI_ID);     return symbol("",ParserAccionesSym.P_SITIO); }
"PADRE"                 { yybegin(MI_ID);     return symbol("",ParserAccionesSym.P_PADRE); }
"PAGINA"                { yybegin(MI_ID);     return symbol("",ParserAccionesSym.P_PAGINA); }
"USUARIO_CREACION"      { yybegin(MI_ID);     return symbol("",ParserAccionesSym.P_USER_NEW); }
"USUARIO_MODIFICACION"  { yybegin(MI_ID);     return symbol("",ParserAccionesSym.P_USER_MOD); }
"TITULO"                { yybegin(MI_TEXTO);  return symbol("",ParserAccionesSym.P_TITULO); }
"FECHA_CREACION"        { yybegin(MI_FECHA);  return symbol("",ParserAccionesSym.P_FECHA_NEW); }
"FECHA_MODIFICACION"    { yybegin(MI_FECHA);  return symbol("",ParserAccionesSym.P_FECHA_MOD); }
"CLASE"                 { yybegin(UI_WEB);    return symbol("",ParserAccionesSym.P_CLASE); }
\"                      { yybegin(YYINITIAL); return symbol("",ParserAccionesSym.COMI); }
}

<MI_ATRIBUTO>{
// atributo.nombre
"PADRE"       { yybegin(MI_ID);         return symbol("",ParserAccionesSym.A_PADRE); }
"TEXTO"       { yybegin(MI_TEXTO);      return symbol("",ParserAccionesSym.A_TEXTO); }
"ALTURA"      { yybegin(MI_NUMERO);     return symbol("",ParserAccionesSym.A_ALTO); }
"ANCHO"       { yybegin(MI_NUMERO);     return symbol("",ParserAccionesSym.A_ANCHO); }
"COLOR"       { yybegin(MI_COLOR);      return symbol("",ParserAccionesSym.A_COLOR); }
"ORIGEN"      { yybegin(MI_URL);        return symbol("",ParserAccionesSym.A_ORIGEN); }
"ETIQUETAS"   { yybegin(MIS_ETIQUETAS); return symbol("",ParserAccionesSym.A_ETIQS); }
"ALINEACION"  {                         return symbol("",ParserAccionesSym.A_ALIGN); }
\"            { yybegin(YYINITIAL);     return symbol("",ParserAccionesSym.COMI); }
}

<MI_ETIQUETA> {
{miEtiqueta}  { yybegin(YYINITIAL); return symbol("",ParserAccionesSym.MI_ETIQUETA);  }
\"            { yybegin(YYINITIAL); return symbol("",ParserAccionesSym.COMI); }
}

<UI_WEB> {
// parametro.nombre.clase
"TITULO"   { return symbol("",ParserAccionesSym.UI_TITULO); }
"PARRAFO"  { return symbol("",ParserAccionesSym.UI_PARRAFO); }
"IMAGEN"   { return symbol("",ParserAccionesSym.UI_IMAGEN); }
"VIDEO"    { return symbol("",ParserAccionesSym.UI_VIDEO); }
"MENU"     { return symbol("",ParserAccionesSym.UI_MENU); }
}

<MI_ALIGN>{
// atributo.nombre.alineacion 
"IZQUIERDA"   { return symbol("",ParserAccionesSym.T_IZQUIERDA); }
"CENTRAR"     { return symbol("",ParserAccionesSym.T_CENTRAR); }
"DERECHA"     { return symbol("",ParserAccionesSym.T_DERECHA); }
"JUSTIFICAR"  { return symbol("",ParserAccionesSym.T_JUSTIFICAR); }
}

<MI_ID> {
    {id}          { return symbol("",ParserAccionesSym.MI_ID); }
}
<MI_FECHA> {
    {miFecha}     { return symbol("",ParserAccionesSym.MI_FECHA); }
}
<MI_TEXTO> {
    {miTexto}     { return symbol("",ParserAccionesSym.MI_TEXTO); }
}
<MI_COLOR> {
    {miColor}     { return symbol("",ParserAccionesSym.MI_COLOR); }
}
<MI_NUMERO> {
    {miNumero}    { return symbol("",ParserAccionesSym.MI_NUMERO); }
}
<MI_URL> {
    {miURL}       { return symbol("",ParserAccionesSym.MI_URL); }
}
<MIS_ETIQUETAS> {
    {miEtiqueta}  { return symbol("",ParserAccionesSym.MI_ETIQUETA); }
    \|            { return symbol("",ParserAccionesSym.OR); }
}

// puntuacion
"<"  {                     return symbol("",ParserAccionesSym.IZQ); }
">"  {                     return symbol("",ParserAccionesSym.DER); }
"/"  {                     return symbol("",ParserAccionesSym.BARRA); }
\"   {                     return symbol("",ParserAccionesSym.COMI); }
"["  {                     return symbol("",ParserAccionesSym.IZQCOR); }
"]"  { yybegin(YYINITIAL); return symbol("",ParserAccionesSym.DERCOR); }
\=   {                     return symbol("",ParserAccionesSym.IGUAL); }

// ignorados
{Invisibles}  { }

// error
[^]  { yybegin(YYINITIAL); }
