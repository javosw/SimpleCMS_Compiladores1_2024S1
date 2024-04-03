module josq.paginador {
    requires javafx.controls;
    requires javafx.fxml;

    opens josq.paginador to javafx.fxml;
    exports josq.paginador;
}
