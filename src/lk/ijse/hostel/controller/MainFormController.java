package lk.ijse.hostel.controller;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.hostel.util.Navigation;
import lk.ijse.hostel.util.Routes;

import java.io.IOException;

public class MainFormController {

    public AnchorPane contecxtPane;
    public Button settingId;
    public Button stManageId;
    public Button dashboardId;
    public Button roomId;
    public Button resId;

    public void onActionLogOut(ActionEvent actionEvent) throws IOException {
       /* FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("/lk/ijse/hostel/view/LoginForm.fxml"));
        Parent parent=fxmlLoader.load();
        Stage stage=new Stage();
        stage.setScene(new Scene (parent));
        stage.show();*/


    }

    public void onActionDashboard(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.DASHBOARD,contecxtPane);
        slideButton (dashboardId);

    }

    public void onActionRervation(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RESERVATION,contecxtPane);
        slideButton (resId);
    }

    public void onActionRoom(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ROOM,contecxtPane);
        slideButton (roomId);
    }

    public void onActionStudent(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.STUDENT,contecxtPane);
        slideButton (stManageId);
    }

    public void onActionSetting(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SETTING,contecxtPane);
        slideButton (settingId);
    }

    public void slideButton(Node node){
        TranslateTransition slider = new TranslateTransition();
        slider.setDuration(Duration.seconds(0.4));
        slider.setNode(node);

        slider.setToX(28);
        slider.play();
        slider.setToX (0);
        slider.play();
    }
}
