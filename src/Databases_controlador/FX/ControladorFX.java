package Databases_controlador.FX;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControladorFX implements Initializable {

    @FXML
    private Button btnShowCustomer;
    @FXML
    private Button btnShowInvoice;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void showCustomers(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Databases_vista/FX/ClienteVistaFX.fxml"));
            Parent root = loader.load();

            ClientesControladorFX controller = loader.getController();

            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            stage.setOnCloseRequest(e -> controller.closeWindows());

            Stage myStage = (Stage) this.btnShowCustomer.getScene().getWindow();
            myStage.close();
        } catch (Exception e) {

        }
    }
    @FXML
    private void showInvoices(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Databases_vista/FX/PedidoVistaFX.fxml"));
            Parent root = loader.load();

            ClientesControladorFX controller = loader.getController();

            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            stage.setOnCloseRequest(e -> controller.closeWindows());

            Stage myStage = (Stage) this.btnShowInvoice.getScene().getWindow();
            myStage.close();
        } catch (Exception e) {

        }
    }
}
