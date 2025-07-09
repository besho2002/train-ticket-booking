package com.example.demo4;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

class ReservationForm extends VBox {
    private ComboBox<String> sourceComboBox;
    private ComboBox<String> destinationComboBox;

    static final List<String> LOCATIONS = Arrays.asList("Assiut", "Banyswif", "Cairo", "Alexandria");


    public ReservationForm() {
        setSpacing(20);
        setPadding(new Insets(20));
        setAlignment(Pos.CENTER);
        setStyle("-fx-background-color: #f0f0f0; -fx-font-family: 'Arial', sans-serif;");

        Text title = new Text("Reserve Ticket");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        title.setFill(Color.web("#333333"));



        Label idLabel = new Label("Passenger ID:");
        idLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        TextField idField = new TextField();
        idField.setPromptText("Enter Passenger ID");
        idField.setStyle("-fx-font-size: 18px;");



                 Label nameLabel = new Label("Passenger Name:");
        nameLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        TextField nameField = new TextField();
        nameField.setPromptText("Enter Passenger Name");
        nameField.setStyle("-fx-font-size: 18px;");

        Label trainLabel = new Label("Select Train:");
               trainLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        ComboBox<String> trainComboBox = new ComboBox<>();
        trainComboBox.setPromptText("Select Train");
        trainComboBox.setStyle("-fx-font-size: 18px;");

        Label trainTypeLabel = new Label("Train Type:");
               trainTypeLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        Label trainTypeDisplay = new Label("");
        trainTypeDisplay.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #333333;");

        Label sourceLabel = new Label("Select Source:");
        sourceLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        sourceComboBox = new ComboBox<>();
        sourceComboBox.setPromptText("Select Source");
               sourceComboBox.setStyle("-fx-font-size: 18px;");

        Label destinationLabel = new Label("Select Destination:");
        destinationLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
              destinationComboBox = new ComboBox<>();
        destinationComboBox.setPromptText("Select Destination");
        destinationComboBox.setStyle("-fx-font-size: 18px;");



        Label ticketsLabel = new Label("Number of Tickets:");
        ticketsLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        TextField ticketsField = new TextField();
          ticketsField.setPromptText("Enter Number of Tickets");
        ticketsField.setStyle("-fx-font-size: 18px;");



            Label fareLabel = new Label("Total Fare:");
        fareLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        Label fareDisplay = new Label("");
    fareDisplay.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #333333;");



        Label travelTimeLabel = new Label("Expected Travel Time:");
        travelTimeLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
     Label travelTimeDisplay = new Label("");
        travelTimeDisplay.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #333333;");



        Button reserveButton = new Button("Reserve");
     reserveButton.setStyle("-fx-font-size: 18px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 10 20;");




   Button backButton = new Button("Back");
        backButton.setStyle("-fx-font-size: 18px; -fx-background-color: #f44336; -fx-text-fill: white; -fx-padding: 10 20;");

        HBox buttonBox = new HBox(20, reserveButton, backButton);
    buttonBox.setAlignment(Pos.CENTER);

        for (Train train : TrainReservationSystem.trains) {
            trainComboBox.getItems().add(train.trainCode + " - " + train.type);
        }

              sourceComboBox.getItems().addAll(LOCATIONS);
        destinationComboBox.getItems().addAll(LOCATIONS);

        trainComboBox.setOnAction(e -> {
            String selectedTrain = trainComboBox.getValue();
            if (selectedTrain != null) {
                String trainCode = selectedTrain.split(" - ")[0];
           Train train = TrainReservationSystem.trains.stream()
                        .filter(t -> t.trainCode.equals(trainCode))
                 .findFirst()
                        .orElse(null);
                if (train != null) {
                    trainTypeDisplay.setText(train.type);
                }
            }
        });

        reserveButton.setOnAction(e -> {
            try {
                String id = idField.getText().trim();
                String name = nameField.getText().trim();

       if (id.isEmpty()) {
                    throw new Exception("Passenger ID cannot be empty. Please enter a valid ID.");
                }

            if (name.isEmpty()) {
                    throw new Exception("Passenger Name cannot be empty. Please enter a valid name.");
                }

        if (!id.matches("\\d+")) {
                        throw new Exception("Please enter a valid numeric ID.");
        }

      if (!name.matches("[a-zA-Z\\s]+")) {
                    throw new Exception("Please enter a valid name in English.");
                }

                String selectedTrain = trainComboBox.getValue();
                        String source = sourceComboBox.getValue();
                String destination = destinationComboBox.getValue();
                  int tickets;

                try {
                    tickets = Integer.parseInt(ticketsField.getText().trim());
                    if (tickets <= 0 || tickets > 4) {
                        throw new NumberFormatException("Number of tickets must be between 1 and 4.");
                    }
                } catch (NumberFormatException ex) {
                     throw new Exception("Please enter a valid number of tickets (1-4).");
                }

                if (selectedTrain == null || source == null || destination == null) {
                    throw new Exception("Please select a train, source, and destination.");
                }

                if (source.equals(destination)) {
                    throw new Exception("Source and Destination cannot be the same.");
                }

                String trainCode = selectedTrain.split(" - ")[0];
                Train train = TrainReservationSystem.trains.stream()
                        .filter(t -> t.trainCode.equals(trainCode))
                        .findFirst()
                        .orElse(null);

                Passenger existingPassenger = TrainReservationSystem.passengers.stream()
                        .filter(p -> p.id.equals(id))
                        .findFirst()
                        .orElse(null);

                if (existingPassenger != null && existingPassenger.getTotalTicketsReserved() + tickets > 4) {
                    throw new Exception("You have already reserved 4 tickets. Cannot reserve more.");
                }

                if (train != null && train.availableSeats() >= tickets) {
                    int numberOfStations = Math.abs(LOCATIONS.indexOf(destination) - LOCATIONS.indexOf(source));
                    int totalFare = train.ticketPrice * numberOfStations * tickets;
                    int travelTime = numberOfStations * 60;

                    String bookingDetails = String.format(
                            "Reservation Details:\nPassenger ID: %s\nPassenger Name: %s\nTrain: %s\nFrom: %s\nTo: %s\nTickets Reserved: %d\nTotal Fare: %d EGP\nExpected Travel Time: %d minutes\nEmployee Name: %s\nEmployee ID: %d",
                            id, name, train.type, source, destination, tickets, totalFare, travelTime, TrainReservationSystem.currentEmployee.name, TrainReservationSystem.currentEmployee.id);

                           Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, bookingDetails, ButtonType.YES, ButtonType.NO);
                    confirmation.setTitle("Confirm Reservation");
                         confirmation.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.YES) {
                            train.reservedSeats += tickets;
                            Passenger.Reservation reservation = new Passenger.Reservation(tickets, train, source, destination);

                            if (existingPassenger != null) {
                                existingPassenger.addReservation(reservation);
                            } else {
                                Passenger newPassenger = new Passenger(id, name, TrainReservationSystem.currentEmployee.name, TrainReservationSystem.currentEmployee.id);
                                newPassenger.addReservation(reservation);
                                TrainReservationSystem.passengers.add(newPassenger);
                            }
                            new Alert(Alert.AlertType.INFORMATION, "Reservation Successful!").show();
                        } else {
                            new Alert(Alert.AlertType.INFORMATION, "Reservation Canceled.").show();
                        }
                    });
                } else {
                      throw new Exception("Invalid Reservation Details or Insufficient Seats.");
                }
            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
            }
        });


        backButton.setOnAction(e -> ((Stage) getScene().getWindow()).close());

        VBox formBox = new VBox(10, idLabel, idField, nameLabel, nameField, trainLabel, trainComboBox, trainTypeLabel, trainTypeDisplay,
                sourceLabel, sourceComboBox, destinationLabel, destinationComboBox, ticketsLabel, ticketsField, fareLabel, fareDisplay,
                travelTimeLabel, travelTimeDisplay);
        formBox.setAlignment(Pos.CENTER);

        idField.requestFocus();

          addFadeTransition(title);
        addScaleTransition(idLabel);
        addScaleTransition(idField);
        addScaleTransition(nameLabel);
        addScaleTransition(nameField);
        addScaleTransition(trainLabel);
        addScaleTransition(trainComboBox);
        addScaleTransition(trainTypeLabel);
        addScaleTransition(trainTypeDisplay);
        addScaleTransition(sourceLabel);
         addScaleTransition(sourceComboBox);
        addScaleTransition(destinationLabel);
          addScaleTransition(destinationComboBox);
        addScaleTransition(ticketsLabel);
          addScaleTransition(ticketsField);
        addScaleTransition(fareLabel);
        addScaleTransition(fareDisplay);
        addScaleTransition(travelTimeLabel);
        addScaleTransition(travelTimeDisplay);
        addScaleTransition(reserveButton);
        addScaleTransition(backButton);

        getChildren().addAll(title, formBox, buttonBox);

        Scene scene = new Scene(this, 600, 600);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Reserve Ticket");
        stage.show();
    }

    private void addFadeTransition(javafx.scene.Node node) {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), node);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

    private void addScaleTransition(javafx.scene.Node node) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1), node);
        scaleTransition.setFromX(0.5);
        scaleTransition.setFromY(0.5);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);
        scaleTransition.play();
    }
}