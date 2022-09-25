package com.haitomns.redsoil;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class redsoilMainController implements Initializable {

    @FXML
    private ProgressBar splashProgressBar;
    @FXML
    public Label splashProgressLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        splashProgressBar.setStyle("-fx-accent:  #C93F3E;");
        splashProgressLabel.setText("Connecting to database...");
    }

    public void showDatabaseConnectivityAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("RedSoil");
        alert.setHeaderText("RedSoil Error");
        alert.setContentText("Database Connectivity Error :(");
        alert.showAndWait();
    }

    public void startLoginOrSignup(String userStatus) {
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("loginAndSignup-view.fxml"));
        try {
            loginLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent parent = loginLoader.getRoot();
        Stage loginStage = new Stage();
        loginStage.setScene(new Scene(parent));
        loginStage.initStyle(StageStyle.DECORATED);
        loginStage.setTitle("RedSoil Dashboard");
        loginStage.show();

        if(userStatus.equals("login")){
            loginAndSignupController loginController = loginLoader.getController();
            loginController.setLoginHbox();
        }
        else if(userStatus.equals("signup")){
            loginAndSignupController loginController = loginLoader.getController();
            loginController.setRegisterHbox();
        }
    }

    public static String checkUser() throws FileNotFoundException {
        File userFile = new File("redSoilUser.rdfs");
        if(userFile.exists()){
            Scanner scanner = new Scanner(userFile);
            String userStatus = scanner.nextLine();
            return userStatus;
        }
        else{
            return null;
        }
    }
}
