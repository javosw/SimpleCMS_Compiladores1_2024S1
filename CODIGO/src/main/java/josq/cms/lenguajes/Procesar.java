/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package josq.cms.lenguajes;

import java.io.FileInputStream;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;

import java_cup.runtime.Symbol;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.DefaultSymbolFactory;
import java_cup.runtime.Scanner;
import josq.cms.lenguajes.lexer.LexerAcciones;
import josq.cms.lenguajes.modelos.cup.simbolos.Accion;
import josq.cms.lenguajes.parser.ParserAcciones;


/**
 *
 * @author JavierOswaldo
 */
public class Procesar
{
    private static ArrayList<Accion> acciones(Reader myReader) throws Exception
    {
        // procesadores de lenguaje
        DefaultSymbolFactory myFactory = new DefaultSymbolFactory();
        Scanner lexer = new LexerAcciones(myReader, myFactory);
        ParserAcciones parser = new ParserAcciones(lexer, myFactory);

        // resultado de procesamiento
        Symbol mySymbol = parser.parse();
        
        //return (MyType) (mySymbol.value);
        return (ArrayList<Accion>) mySymbol.value;
    }
    
    public static ArrayList<Accion> accionesDesdeArchivo(String ruta) throws Exception
    {
        // acceder al archivo con instrucciones sql
        FileInputStream myStream = new FileInputStream(ruta);
        Reader myReader = new InputStreamReader(myStream);

        //return acciones(myReader);
        return acciones(myReader);
    }

    public static ArrayList<Accion> accionesDesdeString(String texto) throws Exception
    {
        // acceder a la cadena con instrucciones sql
        Reader myReader = new StringReader(texto);

        //return acciones(myReader);
        return acciones(myReader);
    }
}
