module josq.cms {
    requires javafx.controls;
    requires javafx.fxml;

    opens josq.cms to javafx.fxml;
    exports josq.cms;
}
