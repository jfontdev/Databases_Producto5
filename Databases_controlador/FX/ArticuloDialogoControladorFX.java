package Databases_controlador.FX;

import java.net.URL;
import java.util.ResourceBundle;

import Databases_modelo.Articulo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ArticuloDialogoControladorFX implements Initializable {

    @FXML
    private TextField txtCode;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextField txtPrice;
    @FXML
    private TextField txtShipping;
    @FXML
    private TextField txtPrepTime;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;


    private Articulo article;
    private ObservableList<Articulo> articles;


    /**
     * Initializes the controller class.
     */


    public void inicializeVariables(ObservableList<Articulo> articulos){
        this.articles = articulos;
    }
    public void inicializeVariables(ObservableList<Articulo> articulos, Articulo articulo){
        this.articles = articulos;
        this.article = articulo;

        txtCode.setText(articulo.getCodigoArticulo());
        txtDescription.setText(articulo.getDescripcion());
        txtPrice.setText(String.valueOf(articulo.getGastosEnvio()));
        txtShipping.setText(String.valueOf(articulo.getGastosEnvio()));
        txtPrepTime.setText(String.valueOf(articulo.getTiempoPreparacion()));
    }

    @FXML
    private void cancelar(ActionEvent event) {
        this.article = null;
        Stage stage = (Stage) this.btnCancelar.getScene().getWindow();
        stage.close();
    }

    public Articulo getArticle() {
        return article;
    }

    public void setArticle(Articulo article) {
        this.article = article;
    }

    @FXML
    private void guardar(ActionEvent event) {
        int codigo_articulo = Integer.parseInt(this.txtCode.getText());
        String descripcion = this.txtDescription.getText();
        float precio_venta = Float.parseFloat(this.txtPrice.getText());
        float gastos_envio = Float.parseFloat(this.txtShipping.getText());
        String tiempo_preparacion = this.txtPrepTime.getText();

        Articulo articulo = new Articulo(tiempo_preparacion, descripcion, precio_venta, gastos_envio, codigo_articulo);

        if(!articles.contains(articulo)){
            //Modificar
            if(this.article != null){
                this.article.setCodigoArticulo(String.valueOf(codigo_articulo));
                this.article.setDescripcion(descripcion);
                this.article.setPrecioVenta(precio_venta);
                this.article.setGastosEnvio(gastos_envio);
                this.article.setTiempoPreparacion(Integer.parseInt(tiempo_preparacion));
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Información");
                alert.setContentText("Se ha modificado correctamente");
                alert.showAndWait();
            }
            else{
                this.article = articulo;
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Información");
                alert.setContentText("Se ha añadido correctamente");
                alert.showAndWait();
            }
            Stage stage = (Stage) this.btnCancelar.getScene().getWindow();
            stage.close();
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Article already exists");
            alert.showAndWait();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
