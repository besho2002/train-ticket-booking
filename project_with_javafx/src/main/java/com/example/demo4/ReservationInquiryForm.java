package com.example.demo4;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class ReservationInquiryForm extends VBox {

    public ReservationInquiryForm() {
        setSpacing(20);
        setPadding(new Insets(20));
           setAlignment(Pos.CENTER);
        setStyle("-fx-background-color: #f0f0f0; -fx-font-family: 'Arial', sans-serif;");

        Label titleLabel = new Label("Reservation Details Inquiry");
                  titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Label idLabel = new Label("Passenger ID:");
        idLabel.setStyle("-fx-font-size: 18px;");

            TextField idField = new TextField();
        idField.setPromptText("Enter Passenger ID");
             idField.setStyle("-fx-font-size: 18px;");

        Button searchButton = new Button("Search");
              searchButton.setStyle("-fx-font-size: 18px; -fx-background-color: #4CAF50; -fx-text-fill: white;");                                    //Bosh

        Button backButton = new Button("Back");
         backButton.setStyle("-fx-font-size: 18px; -fx-background-color: #f44336; -fx-text-fill: white;");

               HBox inputBox = new HBox(10, idLabel, idField, searchButton, backButton);
        inputBox.setAlignment(Pos.CENTER);

        TextArea detailsArea = new TextArea();
           detailsArea.setEditable(false);
        detailsArea.setPrefSize(500, 300);
        detailsArea.setStyle("-fx-font-size: 16px;");


        searchButton.setOnAction(e -> {
            String id = idField.getText().trim();
            try {
                 if (id.isEmpty()) {
                    throw new Exception("Passenger ID cannot be empty.");
                }

                List<Passenger> reservations = TrainReservationSystem.passengers.stream()
                          .filter(p -> p.id.equals(id))
                        .collect(Collectors.toList());

                if (!reservations.isEmpty()) {
                    StringBuilder bookingDetails = new StringBuilder();
                           for (Passenger passenger : reservations) {
                        for (Passenger.Reservation reservation : passenger.reservations) {
                            bookingDetails.append(String.format(
                                    "Passenger Name: %s\nTrain: %s\nSource: %s\nDestination: %s\nTickets Reserved: %d\nFare Paid: %d EGP\nEmployee Name: %s\nEmployee ID: %d\nReservation Time: %s\n\n",
                                    passenger.name,
                                          reservation.train.type,
                                    reservation.source,
                                       reservation.destination,
                                      reservation.tickets,
                                    reservation.train.ticketPrice * reservation.tickets,
                                    passenger.employeeName,
                                        passenger.employeeId,
                                    reservation.reservationTime
                            ));
                        }
                    }

                    detailsArea.setText(bookingDetails.toString());
                } else {
                        throw new Exception("No Reservation found for the given Passenger ID.");
                }
            } catch (Exception ex) {
                    new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
            }
        });



        backButton.setOnAction(e -> ((Stage) getScene().getWindow()).close());                                                                                                               //Bosh

               getChildren().addAll(titleLabel, inputBox, detailsArea);

         Scene scene = new Scene(this, 600, 500);
        Stage stage = new Stage();
              stage.setScene(scene);
        stage.setTitle("Reservation Details");
        stage.show();
    }
}
