package com.example.demo4;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

class CancelForm extends VBox {
    CancelForm() {
        setSpacing(10);
        setPadding(new Insets(20));
        setAlignment(Pos.CENTER);

        Label idLabel = new Label("Passenger ID:");
        TextField idField = new TextField();

        Label ticketsLabel = new Label("Number of Tickets to Cancel:");
        TextField ticketsField = new TextField();

        Button cancelButton = new Button("Cancel");
        Button backButton = new Button("Back");

        cancelButton.setOnAction(e -> {
            String id = idField.getText().trim();
            try {
                int ticketsToCancel = Integer.parseInt(ticketsField.getText().trim());

                if (ticketsToCancel <= 0 || ticketsToCancel >= 5) {
                    throw new Exception("Number of tickets to cancel must be greater than 0 and less than 5.");
                }

                Passenger passenger = TrainReservationSystem.passengers.stream()
                        .filter(p -> p.id.equals(id))
                        .findFirst()
                        .orElse(null);

                if (passenger != null) {
                    boolean found = false;
                    for (Passenger.Reservation reservation : passenger.reservations) {
                        if (reservation.tickets >= ticketsToCancel) {
                            reservation.train.reservedSeats -= ticketsToCancel;
                 reservation.tickets -= ticketsToCancel;
                            found = true;
                  if (reservation.tickets == 0) {
                                passenger.reservations.remove(reservation);
                             }
                            break;
                        }
                    }

                    if (found) {
                        if (passenger.reservations.isEmpty()) {
                    TrainReservationSystem.passengers.remove(passenger);
                        }
                            new Alert(Alert.AlertType.INFORMATION, "Reservation Canceled Successfully!").show();
                    } else {
              throw new Exception("You cannot cancel more tickets than you have reserved.");
                    }
       } else {
                    throw new Exception("No Reservation Found for the ID.");
                }
            } catch (NumberFormatException ex) {
                new Alert(Alert.AlertType.ERROR, "Please enter a valid number of tickets.").show();
            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
            }
        });



        backButton.setOnAction(e -> ((Stage) getScene().getWindow()).close());

        getChildren().addAll(idLabel, idField, ticketsLabel, ticketsField, cancelButton, backButton);

        Scene scene = new Scene(this, 400, 300);
           Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Cancel Reservation");
        stage.show();
    }
}

