package com.example.demo4;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

class InquiryForm extends VBox {
    InquiryForm() {
        setSpacing(10);
        setPadding(new Insets(20));
        setAlignment(Pos.CENTER);

        Label trainLabel = new Label("Select Train:");
        ComboBox<String> trainComboBox = new ComboBox<>();

        for (Train train : TrainReservationSystem.trains) {
            trainComboBox.getItems().add(train.trainCode + " - " + train.type);
        }

        Button inquireButton = new Button("Inquire");

        inquireButton.setOnAction(e -> {
            String selectedTrain = trainComboBox.getValue();
            String trainCode = selectedTrain.split(" - ")[0];

            Train train = TrainReservationSystem.trains.stream()
                    .filter(t -> t.trainCode.equals(trainCode))
                    .findFirst()
                    .orElse(null);

            if (train != null) {
                int availableSeats = train.availableSeats();
                StringBuilder stopsInfo = new StringBuilder("Stops:\n");
                 for (String stop : train.stops) {
                    stopsInfo.append(stop).append("\n");
                }
                new Alert(Alert.AlertType.INFORMATION, String.format(
                        "Train Type: %s\nFeatures: %s\nSchedule: %s\nTicket Price for One Station: %d EGP\nAvailable Seats: %d\n%s",
                        train.type, train.features, train.schedule, train.ticketPrice, availableSeats, stopsInfo.toString()
                )).show();
            }
        });

        getChildren().addAll(trainLabel, trainComboBox, inquireButton);

        Scene scene = new Scene(this, 400, 300);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Train Inquiry");
        stage.show();
    }
}
