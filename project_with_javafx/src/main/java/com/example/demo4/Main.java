package com.example.demo4;
import javafx.stage.Stage;
import javafx.application.Application;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        TrainReservationSystem.initializeTrains();
        new EmployeeLoginForm();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
