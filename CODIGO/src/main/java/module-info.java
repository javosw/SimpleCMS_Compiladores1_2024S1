module josq.cms {
    exports josq.cms;
    
    opens josq.cms to javafx.fxml;

    requires javafx.controls;
    requires javafx.fxml;    
}
