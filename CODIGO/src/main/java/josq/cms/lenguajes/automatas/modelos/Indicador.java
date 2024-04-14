/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package josq.cms.lenguajes.automatas.modelos;

/**
 *
 * @author JavierOswaldo
 */
public enum Indicador
{
    ERROR,
    // puntuacion
    IZQ, DER, BARRA, IGUAL, IZQCOR, DERCOR, COMI, OR,
    // nodos
    ACCIS, ACCI, PARAMS, PARAM, ATRIBS, ATRIB, ETIQS, ETIQ,
    // nodo.atributo
    VALOR, NOMBRE,
    // acciones.nombre
    SITE_NEW, SITE_DEL, PAGE_NEW, PAGE_DEL, PAGE_MOD, COMP_NEW, COMP_DEL, COMP_MOD,
    // parametro.nombre
    P_ID, P_TITULO, P_SITIO, P_PADRE, P_PAGINA, P_CLASE,
    P_FECHA_NEW, P_FECHA_MOD, P_USER_NEW, P_USER_MOD,
    // parametro.nombre.clase
    UI_TITULO, UI_PARRAFO, UI_IMAGEN, UI_VIDEO, UI_MENU,
    // atributo.nombre
    A_TEXTO, A_ALIGN, A_COLOR, A_ORIGEN, A_ALTO, A_ANCHO, A_PADRE, A_ETIQS,
    // atributo.nombre.alineacion
    T_CENTRAR, T_IZQUIERDA, T_DERECHA, T_JUSTIFICAR,
    // literales
    MI_ID, MI_TEXTO, MI_NUMERO, MI_COLOR, MI_URL, MI_FECHA, MI_ETIQUETA, MIS_ETIQUETAS, MI_COMPONENTE, MI_ALIGN ;

}
