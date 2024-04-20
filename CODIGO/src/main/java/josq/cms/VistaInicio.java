package josq.cms;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import josq.cms.lenguajes.controladores.EjecutarAcciones;
import josq.cms.lenguajes.controladores.EjecutarComandos;

/**
 * JavaFX App
 */
public class VistaInicio extends Application {


    private Stage myStage;
    
    @Override
    public void start(Stage stage)
    {
        myStage = stage;
        
        Button bAcciones = new Button("ACCIONES");
        Button bComandos = new Button("COMANDOS");
        Label lJOSQ = new Label("Javier Oswaldo Sacor Quijivix");
        bComandos.setOnAction((e)->{ vistaComandos();});
        bAcciones.setOnAction((e)->{ vistaAcciones(); });
        
        //VBox miLayout = new VBox();
        GridPane lGrid = new GridPane();
        lGrid.setPadding(new Insets(6,4,6,4));
        
        lGrid.add(lJOSQ, 0, 0);
        lGrid.add(bAcciones, 0, 1);
        lGrid.add(bComandos, 0, 2);

        Insets miMargen = new Insets(4,5,4,5);
        ObservableList<Node> nodos =  lGrid.getChildren();
        for(Node nodo : nodos) 
        {
            GridPane.setHalignment(nodo, HPos.CENTER);
            GridPane.setMargin(nodo, miMargen);
        }
        
        // codigo repetitivo
        Scene myScene = new Scene(lGrid);
        myStage.setScene(myScene);
        myStage.setTitle("BasicCMS (UTF-8)");
        myStage.show();
    }

    void ejecutarAcciones(TextArea uiInstr, TextArea uiSintax, TextArea uiConSentido, TextArea uiSinSentido)
    {
        EjecutarAcciones.clearLogs();
        
        String acciones = uiInstr.getText();
        EjecutarAcciones ejecucion = new EjecutarAcciones();
        ejecucion.desdeString(acciones);
                        
        uiConSentido.clear();
        uiSintax.clear();
        uiSinSentido.clear();
        
        uiConSentido.appendText(EjecutarAcciones.logConSentido.toString());
        uiSintax.appendText(EjecutarAcciones.logSintaxis.toString());
        uiSinSentido.appendText(EjecutarAcciones.logSinSentido.toString());
    }

    void vistaAcciones()
    {
        TextArea instr = new TextArea();
        TextArea conSentido = new TextArea();
        TextArea sinSentido = new TextArea();
        TextArea sintaxis = new TextArea();
        Button ejecutar = new Button("EJECUTAR");

        Label lResul = new Label("SEMANTICA");
        Label lError = new Label("ERRORES SEMANTICOS");
        Label lPasos = new Label("ERRORES SINTACTICOS");

        ejecutar.setOnAction(e->{ejecutarAcciones(instr, sintaxis, conSentido, sinSentido);});
        
        GridPane lGrid = new GridPane();
        
        lGrid.add(ejecutar, 0, 0);
        lGrid.add(lResul, 2, 0);
        lGrid.add(lPasos, 0, 2);
        lGrid.add(lError, 2, 2);
                
        lGrid.add(instr, 0, 1, 2, 1);
        lGrid.add(conSentido, 2, 1, 2, 1);
        lGrid.add(sintaxis, 0, 3, 2, 1);
        lGrid.add(sinSentido, 2, 3, 2, 1);
        
        // codigo repetitivo
        Scene yaScene = new Scene(lGrid);
        
        Stage yaStage = new Stage();
        yaStage.setTitle("ACCIONES");
        yaStage.setScene(yaScene);
        yaStage.sizeToScene();

        yaStage.show();  
    }

    void vistaComandos()
    {
        TextArea instr = new TextArea();
        TextArea conSentido = new TextArea();
        TextArea sinSentido = new TextArea();
        TextArea sintaxis = new TextArea();
        Button ejecutar = new Button("EJECUTAR");

        Label lResul = new Label("SEMANTICA");
        Label lError = new Label("ERRORES SEMANTICOS");
        Label lPasos = new Label("ERRORES SINTACTICOS");

        ejecutar.setOnAction(e->{ejecutarComandos(instr, sintaxis, conSentido, sinSentido);});
        
        GridPane lGrid = new GridPane();
        
        lGrid.add(ejecutar, 0, 0);
        lGrid.add(lResul, 2, 0);
        lGrid.add(lPasos, 0, 2);
        lGrid.add(lError, 2, 2);
                
        lGrid.add(instr, 0, 1, 2, 1);
        lGrid.add(conSentido, 2, 1, 2, 1);
        lGrid.add(sintaxis, 0, 3, 2, 1);
        lGrid.add(sinSentido, 2, 3, 2, 1);
        
        // codigo repetitivo
        Scene yaScene = new Scene(lGrid);
        
        Stage yaStage = new Stage();
        yaStage.setTitle("COMANDOS");
        yaStage.setScene(yaScene);
        yaStage.sizeToScene();

        yaStage.show();  
    }
    
    void ejecutarComandos(TextArea uiInstr, TextArea uiSintax, TextArea uiConSentido, TextArea uiSinSentido)
    {
        EjecutarComandos.clearLogs();
        
        String comandos = uiInstr.getText();
        EjecutarComandos ejecucion = new EjecutarComandos();
        ejecucion.desdeString(comandos);
                        
        uiConSentido.clear();
        uiSintax.clear();
        uiSinSentido.clear();
        
        uiConSentido.appendText(EjecutarComandos.logConSentido.toString());
        uiSintax.appendText(EjecutarComandos.logSintaxis.toString());
        uiSinSentido.appendText(EjecutarComandos.logSinSentido.toString());
    }

    private void showAlerta(AlertType tipo, String msj) 
    {
        Alert alert = new Alert(tipo);
        alert.setContentText(msj);

        alert.showAndWait();
    }
    
    public static void main(String[] args)
    {
        launch(args);
    }


}