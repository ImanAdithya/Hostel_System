package lk.ijse.hostel.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Setting {
    public void onActionManageUser(ActionEvent actionEvent) throws IOException {
        Stage stage1=new Stage();
        stage1.setScene(new Scene (FXMLLoader.load(getClass().getResource("/lk/ijse/hostel/view/ChangePassForm.fxml"))));
        stage1.show();
    }

    public void onActionAddUser(ActionEvent actionEvent) throws IOException {
        Stage stage1=new Stage();
        stage1.setScene(new Scene (FXMLLoader.load(getClass().getResource("/lk/ijse/hostel/view/UserAcc.fxml"))));
        stage1.show();
    }
}
