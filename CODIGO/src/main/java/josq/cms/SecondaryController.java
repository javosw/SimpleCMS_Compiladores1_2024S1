package josq.cms;

import java.io.IOException;
import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        VistaInicio.setRoot("primary");
    }
}