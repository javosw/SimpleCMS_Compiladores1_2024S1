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


    boolean accioncEsInpar = false;
    boolean parametroEsInpar = false;
    boolean atributoEsInpar = false;

    void setContextoDe(int sym)
    {
        if(sym == ParserAccionesSym.ACCI)
        { 
            if (accioncEsInpar) { yybegin(YYINITIAL); }
            else { yybegin(MI_ACCION); }
            accioncEsInpar = !accioncEsInpar;
        }
        if(sym == ParserAccionesSym.PARAM)
        { 
            if (parametroEsInpar) { yybegin(YYINITIAL); }
            else { yybegin(MI_PARAMETRO); }
            parametroEsInpar = !parametroEsInpar;
        }
        if(sym == ParserAccionesSym.ATRIB)
        { 
            if (atributoEsInpar) { yybegin(YYINITIAL); }
            else { yybegin(MI_ATRIBUTO); }
            atributoEsInpar = !atributoEsInpar;
        }
    }

    void resetContextos()
    {
        accioncEsInpar = false;
        parametroEsInpar = false;
        atributoEsInpar = false;
    }


%}

// estados lexicos
//%xstate CONTEXTO_1
%state MI_ACCION, MI_PARAMETRO, MI_ATRIBUTO
%state MI_ID, MI_ID_USER
%state MI_NUMERO
%state MI_TEXTO, MI_TITULO, MI_ETIQUETA, MIS_ETIQUETAS
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

miTexto  =  [a-zA-Z0-9]({_}|[a-zA-Z0-9])+
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
"acciones"    { symbol("",ParserAccionesSym.ACCIS); }
"parametros"  { symbol("",ParserAccionesSym.PARAMS); }
"atributos"   { symbol("",ParserAccionesSym.ATRIBS); }

"accion"      { setContextoDe(ParserAccionesSym.ACCI);   symbol("",ParserAccionesSym.ACCI); }
"parametro"   { setContextoDe(ParserAccionesSym.PARAM);  symbol("",ParserAccionesSym.PARAM); }
"atributo"    { setContextoDe(ParserAccionesSym.ATRIB);  symbol("",ParserAccionesSym.ATRIB); }

"etiquetas"   { symbol("",ParserAccionesSym.ETIQS); }
"etiqueta"    { symbol("",ParserAccionesSym.ETIQ); }
// etiqueta.valor
"valor"       { yybegin(MI_ETIQUETA); symbol("",ParserAccionesSym.VALOR); }
}


<MI_ACCION, MI_PARAMETRO, MI_ATRIBUTO> {
// nodo.nombre
"nombre" { symbol("",ParserAccionesSym.NOMBRE); }
}

<MI_ACCION>{
// accion.nombre="MI_ACCION"
"NUEVO_SITIO_WEB"       {  yybegin(YYINITIAL);   symbol("",ParserAccionesSym.SITE_NEW); }
"BORRAR_SITIO_WEB"      {  yybegin(YYINITIAL);   symbol("",ParserAccionesSym.SITE_DEL); }
"NUEVA_PAGINA"          {  yybegin(YYINITIAL);   symbol("",ParserAccionesSym.PAGE_NEW); }
"MODIFICAR_PAGINA"      {  yybegin(YYINITIAL);   symbol("",ParserAccionesSym.PAGE_MOD); }
"BORRAR_PAGINA"         {  yybegin(YYINITIAL);   symbol("",ParserAccionesSym.PAGE_DEL); }
"AGREGAR_COMPONENTE"    {  yybegin(YYINITIAL);   symbol("",ParserAccionesSym.COMP_NEW); }
"MODIFICAR_COMPONENTE"  {  yybegin(YYINITIAL);   symbol("",ParserAccionesSym.COMP_MOD); }
"BORRAR_COMPONENTE"     {  yybegin(YYINITIAL);   symbol("",ParserAccionesSym.COMP_DEL); }
}

<MI_PARAMETRO>{
// parametro.nombre="MI_PARAMETRO"
"ID"                    { yybegin(MI_ID);      symbol("",ParserAccionesSym.P_ID); }
"SITIO"                 { yybegin(MI_ID);      symbol("",ParserAccionesSym.P_SITIO); }
"PADRE"                 { yybegin(MI_ID);      symbol("",ParserAccionesSym.P_PADRE); }
"PAGINA"                { yybegin(MI_ID);      symbol("",ParserAccionesSym.P_PAGINA); }
"USUARIO_CREACION"      { yybegin(MI_ID);      symbol("",ParserAccionesSym.P_USER_NEW); }
"USUARIO_MODIFICACION"  { yybegin(MI_ID);      symbol("",ParserAccionesSym.P_USER_MOD); }
"TITULO"                { yybegin(MI_TEXTO);   symbol("",ParserAccionesSym.P_TITULO); }
"FECHA_CREACION"        { yybegin(MI_FECHA);   symbol("",ParserAccionesSym.P_FECHA_NEW); }
"FECHA_MODIFICACION"    { yybegin(MI_FECHA);   symbol("",ParserAccionesSym.P_FECHA_MOD); }
"CLASE"                 { yybegin(UI_WEB);     symbol("",ParserAccionesSym.P_CLASE); }
}

<MI_ATRIBUTO>{
// atributo.nombre="MI_ATRIBUTO"
"PADRE"       { yybegin(MI_ID);          symbol("",ParserAccionesSym.A_PADRE); }
"TEXTO"{_}    { yybegin(MI_TEXTO);       symbol("",ParserAccionesSym.A_TEXTO); }
"ALTURA"      { yybegin(MI_NUMERO);      symbol("",ParserAccionesSym.A_ALTO); }
"ANCHO"       { yybegin(MI_NUMERO);      symbol("",ParserAccionesSym.A_ANCHO); }
"COLOR"       { yybegin(MI_COLOR);       symbol("",ParserAccionesSym.A_COLOR); }
"ORIGEN"      { yybegin(MI_URL);         symbol("",ParserAccionesSym.A_ORIGEN); }
"ETIQUETAS"   { yybegin(MIS_ETIQUETAS);  symbol("",ParserAccionesSym.A_ETIQS); }
"ALINEACION"  { yybegin(MI_ALIGN);       symbol("",ParserAccionesSym.A_ALIGN); }
}

<MI_ID, MI_FECHA, MI_TEXTO, MI_COLOR, MI_NUMERO, MI_URL, MIS_ETIQUETAS, MI_ALIGN, UI_WEB>{
"]"  { yybegin(YYINITIAL);  symbol("",ParserAccionesSym.DERCOR); }
}

<UI_WEB> {
// parametro.nombre="clase".[UI_WEB]
"TITULO"   {  symbol("",ParserAccionesSym.UI_TITULO); }
"PARRAFO"  {  symbol("",ParserAccionesSym.UI_PARRAFO); }
"IMAGEN"   {  symbol("",ParserAccionesSym.UI_IMAGEN); }
"VIDEO"    {  symbol("",ParserAccionesSym.UI_VIDEO); }
"MENU"     {  symbol("",ParserAccionesSym.UI_MENU); }
}

<MI_ALIGN>{
// atributo.nombre="alineacion".[MI_ALIGN]
"IZQUIERDA"   {  symbol("",ParserAccionesSym.T_IZQUIERDA); }
"CENTRAR"     {  symbol("",ParserAccionesSym.T_CENTRAR); }
"DERECHA"     {  symbol("",ParserAccionesSym.T_DERECHA); }
"JUSTIFICAR"  {  symbol("",ParserAccionesSym.T_JUSTIFICAR); }
}

<MI_ID> {
    {id}          {  symbol("",ParserAccionesSym.MI_ID); }
}
<MI_FECHA> {
    {miFecha}     {  symbol("",ParserAccionesSym.MI_FECHA); }
}
<MI_TEXTO> {
    {miTexto}     {  symbol("",ParserAccionesSym.MI_TEXTO); }
}
<MI_COLOR> {
    {miColor}     {  symbol("",ParserAccionesSym.MI_COLOR); }
}
<MI_NUMERO> {
    {miNumero}    {  symbol("",ParserAccionesSym.MI_NUMERO); }
}
<MI_URL> {
    {miURL}       {  symbol("",ParserAccionesSym.MI_URL); }
}
<MI_ETIQUETA>{
{miEtiqueta}  { yybegin(YYINITIAL);  symbol("",ParserAccionesSym.MI_ETIQUETA);  }
}
<MIS_ETIQUETAS> {
{miEtiqueta}  {  symbol("",ParserAccionesSym.MI_ETIQUETA);  }
\|            {  symbol("",ParserAccionesSym.OR); }
}

// puntuacion
"<"  { symbol("",ParserAccionesSym.IZQ); }
">"  { symbol("",ParserAccionesSym.DER); }
"/"  { symbol("",ParserAccionesSym.BARRA); }
\"   { symbol("",ParserAccionesSym.COMI); }
"["  { symbol("",ParserAccionesSym.IZQCOR); }
"]"  { symbol("",ParserAccionesSym.DERCOR); }
\=   { symbol("",ParserAccionesSym.IGUAL); }

// ignorados
{Invisibles}  { }

// error
[^]  { symbol("",ParserAccionesSym.error); }
