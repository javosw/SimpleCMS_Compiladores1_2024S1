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
        Button bSQCMS = new Button("SQCMS");
        Label lJOSQ = new Label("Javier Oswaldo Sacor Quijivix");
        bSQCMS.setOnAction((e)->{ vistaSQCMS();});
        bAcciones.setOnAction((e)->{ vistaAcciones(); });
        
        //VBox miLayout = new VBox();
        GridPane lGrid = new GridPane();
        lGrid.setPadding(new Insets(6,4,6,4));

        
        lGrid.add(lJOSQ, 0, 0);
        lGrid.add(bAcciones, 0, 1);
        lGrid.add(bSQCMS, 0, 2);

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
        myStage.setTitle("EDITOR - UTF-8");
        myStage.show();
    }

    void ejecutarAcciones(TextArea uiInstr, TextArea uiResult, TextArea uiGrammar, TextArea uiError)
    {
        EjecutarAcciones.clearLogs();
        
        String acciones = uiInstr.getText();
        EjecutarAcciones ejecucion = new EjecutarAcciones();
        ejecucion.procesarDesdeString(acciones);
                        
        uiResult.clear();
        uiGrammar.clear();
        uiError.clear();
        
        uiResult.appendText(EjecutarAcciones.logResultados.toString());
        uiGrammar.appendText(EjecutarAcciones.logGramaticas.toString());
        uiError.appendText(EjecutarAcciones.logErrores.toString());
    }

    void vistaAcciones()
    {
        TextArea instr = new TextArea();
        TextArea resut = new TextArea();
        TextArea error = new TextArea();
        TextArea pasos = new TextArea();
        Button ejecutar = new Button("EJECUTAR");

        Label lResul = new Label("RESULTADOS");
        Label lError = new Label("ERRORES");
        Label lPasos = new Label("LEXER/PARSER");

        ejecutar.setOnAction(e->{ejecutarAcciones(instr, resut, pasos, error);});
        
        GridPane lGrid = new GridPane();
        
        lGrid.add(ejecutar, 0, 0);
        lGrid.add(lResul, 2, 0);
        lGrid.add(lPasos, 0, 2);
        lGrid.add(lError, 2, 2);
                
        lGrid.add(instr, 0, 1, 2, 1);
        lGrid.add(resut, 2, 1, 2, 1);
        lGrid.add(pasos, 0, 3, 2, 1);
        lGrid.add(error, 2, 3, 2, 1);
        
        // codigo repetitivo
        Scene yaScene = new Scene(lGrid);
        
        Stage yaStage = new Stage();
        yaStage.setTitle("ACCIONES");
        yaStage.setScene(yaScene);
        yaStage.sizeToScene();

        yaStage.show();  
    }

    void vistaSQCMS()
    {
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