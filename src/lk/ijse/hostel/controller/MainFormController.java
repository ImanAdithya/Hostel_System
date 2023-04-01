package lk.ijse.hostel.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hostel.util.Navigation;
import lk.ijse.hostel.util.Routes;

import java.io.IOException;

public class MainFormController {

    public AnchorPane contecxtPane;

    public void onActionLogOut(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("/lk/ijse/hostel/view/LoginForm.fxml"));
        Parent parent=fxmlLoader.load();
        Stage stage=new Stage();
        stage.setScene(new Scene (parent));
        stage.show();
    }

    public void onActionDashboard(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.DASHBOARD,contecxtPane);
    }

    public void onActionRervation(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RESERVATION,contecxtPane);
    }

    public void onActionRoom(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ROOM,contecxtPane);
    }

    public void onActionStudent(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.STUDENT,contecxtPane);
    }

    public void onActionSetting(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SETTING,contecxtPane);
    }
}
