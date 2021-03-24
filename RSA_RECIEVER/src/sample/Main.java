package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Reciever");
        primaryStage.setScene(new Scene(root, 700   , 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        /*int a[]={72};
        int b=a[0];
        char c=(char)b;
System.out.println(c);
*/
        launch(args);
    }
}
