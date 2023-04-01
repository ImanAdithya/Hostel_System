package lk.ijse.hostel.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {

    private static AnchorPane anchorPane;

    public static void navigate(Routes routes, AnchorPane anchorPane) throws IOException {
        Navigation.anchorPane=anchorPane;
        Navigation.anchorPane.getChildren().clear();
        Stage window=(Stage)Navigation.anchorPane.getScene().getWindow();

        switch (routes) {
            case STUDENT:
                window.setTitle ("STUDENT");
                iniUi("StudentManage.fxml");
                break;
            case ROOM:
                window.setTitle ("ROOM");
                //iniUi("StudentManageForm.fxml");
                break;
            case RESERVATION:
                window.setTitle ("RESERVATION");
                //iniUi("EmployeManageForm.fxml");
                break;
            case SETTING:
                window.setTitle ("SETTING");
                //iniUi("SalaryManageForm.fxml");
                break;
            case DASHBOARD:
                window.setTitle ("DASBOARD");
                //iniUi("SalaryManageForm.fxml");
                break;
            default:
                System.out.println ("Something Wrong");
        }

    }
    private static void iniUi(String location) throws IOException {
        Navigation.anchorPane.getChildren().add(FXMLLoader.load(Navigation.class.getResource("/lk/ijse/hostel/view/"+location)));
    }
}
