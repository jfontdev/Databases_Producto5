## Uso de JavaFX como libreria externa

To use JavFX as an external library do the next steps:

## IntelliJ IDEA

1- In the "Project structure" dialog in IntelliJ IDEA add the location including the "lib" in the "Modules > Dependencies" screen, e.g. /opt/javafx-sdk-16/lib. By doing this, your IDE will be able to provide you the correct syntax highlighting.

![modules](https://foojay.io/wp-content/uploads/2020/11/Screenshot-from-2020-11-16-08-33-50.png)

2- To run your application, we also need to add the startup arguments to point to the javafx-modules. Open the Configurations dialog and provide the "VM options", including all the modules required for your project, e.g. --module-path /opt/javafx-sdk-16/lib --add-modules javafx.controls,javafx.fxml.

![vmoptions](https://foojay.io/wp-content/uploads/2020/11/Screenshot-from-2020-11-16-08-41-09.png)

Source:
https://foojay.io/today/starting-a-javafx-project-with-gluon-tools/





