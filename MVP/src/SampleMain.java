import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SampleMain extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            //Parent root = FXMLLoader.load(getClass().getResource("view/employee/employee_view.fxml"));
            Parent root = FXMLLoader.load(getClass().getResource("view/client/client_view.fxml"));
            primaryStage.setTitle("Client");
            primaryStage.setScene(new Scene(root, 1000, 600));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
