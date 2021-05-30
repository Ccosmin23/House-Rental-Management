import controller.CAdmin;
import controller.CClient;
import controller.CEmployee;
import controller.CSignIn;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Subject;

public class SampleMain extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Subject subject = new Subject();

        FXMLLoader clientLoader = new FXMLLoader(getClass().getResource("view/client_view.fxml"));
        Parent rootClient = clientLoader.load();

        FXMLLoader employeeLoader = new FXMLLoader(getClass().getResource("view/employee_view.fxml"));
        Parent rootEmployee = employeeLoader.load();

        FXMLLoader adminLoader = new FXMLLoader(getClass().getResource("view/admin_view.fxml"));
        Parent rootAdmin = adminLoader.load();

        FXMLLoader signInLoader = new FXMLLoader(getClass().getResource("view/sign_in.fxml"));
        Parent rootSignIn = signInLoader.load();


        CClient cClient = clientLoader.getController();
        CEmployee cEmployee = employeeLoader.getController();
        CAdmin cAdmin = adminLoader.getController();
        CSignIn cSignIn = signInLoader.getController();

        subject.addObserver(cClient);
        subject.addObserver(cEmployee);
        subject.addObserver(cAdmin);
        subject.addObserver(cSignIn);


        Scene clientScene = new Scene(rootClient, 1000, 600);
        cClient.setSubject(subject);
        cClient.init();
        cClient.setClientScene(clientScene);

        Scene employeeScene = new Scene(rootEmployee, 1000, 600);
        cEmployee.setSubject(subject);
        cEmployee.init();
        cEmployee.setEmployeeScene(employeeScene);

        Scene adminScene = new Scene(rootAdmin, 1000, 600);
        cAdmin.setSubject(subject);
        cAdmin.init();
        cAdmin.setAdminScene(adminScene);

        Scene signInScene = new Scene(rootSignIn, 1000, 600);
        cSignIn.setSubject(subject);
        cSignIn.init();
        cSignIn.setSignInScene(signInScene);



        primaryStage.setTitle("House rental management");
        primaryStage.setScene(clientScene);
        primaryStage.show();

    }



}
