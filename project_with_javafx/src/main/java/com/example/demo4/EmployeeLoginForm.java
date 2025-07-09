package com.example.demo4;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

class EmployeeLoginForm extends VBox {
    EmployeeLoginForm() {
        setSpacing(10);
        setPadding(new Insets(20));
        setAlignment(Pos.CENTER);

        Label idLabel = new Label("Employee ID:");
        TextField idField = new TextField();
        Label passwordLabel = new Label("Password:");
           PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");
        Button cancelButton = new Button("Cancel");

        loginButton.setOnAction(e -> {
               try {
                String idInput = idField.getText().trim();
                if (idInput.isEmpty()) {
                    throw new Exception("Employee ID cannot be empty.");
                }
                int id = Integer.parseInt(idInput);

                String password = passwordField.getText();
                if (password.isEmpty()) {
                    throw new Exception("Password cannot be empty.");
                }

                Employee employee = TrainReservationSystem.employees.stream()

                        .filter(emp -> emp.id == id && emp.password.equals(password))
                        .findFirst()
                        .orElse(null);

                if (employee == null) {
                    throw new Exception("Invalid Employee ID or Password.");
                }

                TrainReservationSystem.currentEmployee = employee;


                Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "Login Successful!");
                successAlert.setTitle("Success");
                successAlert.setHeaderText(null);
                successAlert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {

                        ((Stage) getScene().getWindow()).close();
                        new HomeForm();
                    }
                });

            } catch (NumberFormatException ex) {
                new Alert(Alert.AlertType.ERROR, "Please enter a valid Employee ID.").show();
            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
            }
        });

        cancelButton.setOnAction(e -> System.exit(0));

        getChildren().addAll(idLabel, idField, passwordLabel, passwordField, loginButton, cancelButton);

        Scene scene = new Scene(this, 300, 200);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Employee Login");
        stage.show();
    }
}