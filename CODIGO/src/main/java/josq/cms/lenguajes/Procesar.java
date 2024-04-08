/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package josq.cms.lenguajes;

import java.io.FileInputStream;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.StringReader;

import java_cup.runtime.Symbol;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.Scanner;
import josq.cms.lenguajes.lexer.LexerAcciones;
import josq.cms.lenguajes.parser.ParserAcciones;


/**
 *
 * @author JavierOswaldo
 */
public class Procesar
{
    private static void acciones(Reader myReader) throws Exception
    {
        // procesadores de lenguaje
        ComplexSymbolFactory symFactory = new ComplexSymbolFactory();
        Scanner lexer = new LexerAcciones(myReader, symFactory);
        ParserAcciones parser = new ParserAcciones(lexer, symFactory);

        // resultado de procesamiento
        Symbol mySymbol = parser.parse();
        
        //return (MyType) (mySymbol.value);
    }
    
    public static void accionesDesdeArchivo(String ruta) throws Exception
    {
        // acceder al archivo con instrucciones sql
        FileInputStream myStream = new FileInputStream(ruta);
        Reader myReader = new InputStreamReader(myStream);

        //return acciones(myReader);
        acciones(myReader);
    }

    public static void accionesDesdeString(String texto) throws Exception
    {
        // acceder a la cadena con instrucciones sql
        Reader myReader = new StringReader(texto);

        //return acciones(myReader);
        acciones(myReader);
    }
}
