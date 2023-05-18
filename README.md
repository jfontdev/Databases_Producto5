## Uso de JavaFX como libreria externa

To use JavFX as an external library do the next steps:

## IntelliJ IDEA

1- To run your application, we also need to add the startup arguments to point to the javafx-modules. Open the Configurations dialog and provide the "VM options", including all the modules required for your project. Use this options:

--module-path lib/JavaFX/javafx-sdk-17.0.7/lib --add-modules javafx.controls,javafx.fxml

![vmoptions](https://foojay.io/wp-content/uploads/2020/11/Screenshot-from-2020-11-16-08-41-09.png)

Source:
https://foojay.io/today/starting-a-javafx-project-with-gluon-tools/





