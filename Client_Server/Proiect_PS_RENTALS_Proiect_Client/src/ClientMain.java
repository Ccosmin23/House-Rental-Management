import contact.ProxyServer;
import contact.SClient;
import contact.SEmployee;
import contact.SRentalHouse;
import controller.CAdmin;
import controller.CClient;
import controller.CEmployee;
import controller.CSignIn;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.MAdmin;
import model.MClient;
import model.MEmployee;
import model.MSignIn;

import java.io.IOException;

public class ClientMain extends Application {

    private static ProxyServer proxyServer;




    @Override
    public void start(Stage primaryStage) throws Exception {
        String host = "localhost";
        int port = 5555;

        proxyServer = new ProxyServer(host, port);
        proxyServer.connect();

        SRentalHouse sRentalHouse = new SRentalHouse(proxyServer);
        SClient sClient = new SClient(proxyServer);
        SEmployee sEmployee = new SEmployee(proxyServer);


        MClient mClient = new MClient(sRentalHouse);
        MEmployee mEmployee = new MEmployee(sRentalHouse, sClient);
        MAdmin mAdmin = new MAdmin(sEmployee);
        MSignIn mSignIn = new MSignIn(sEmployee);


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


        mClient.addObserver(cClient);
        mEmployee.addObserver(cEmployee);
        mAdmin.addObserver(cAdmin);
        mSignIn.addObserver(cSignIn);




        Scene clientScene = new Scene(rootClient, 1000, 600);
        cClient.setModel(mClient);
        cClient.init();
        cClient.setClientScene(clientScene);

        Scene employeeScene = new Scene(rootEmployee, 1000, 600);
        cEmployee.setModel(mEmployee);
        cEmployee.init();
        cEmployee.setEmployeeScene(employeeScene);

        Scene adminScene = new Scene(rootAdmin, 1000, 600);
        cAdmin.setModel(mAdmin);
        cAdmin.init();
        cAdmin.setAdminScene(adminScene);

        Scene signInScene = new Scene(rootSignIn, 1000, 600);
        cSignIn.setModel(mSignIn);
        cSignIn.init();
        cSignIn.setSignInScene(signInScene);



        primaryStage.setTitle("House rental management");
        primaryStage.setScene(employeeScene);
        primaryStage.show();



    }


    public static void main(String[] args) throws IOException {
        launch(args);
        // stop the server
        proxyServer.getRealServer().getObjectOutputStream().writeObject("stop");
    }

}
