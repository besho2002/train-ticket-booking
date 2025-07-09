package com.example.demo4;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

class HomeForm extends VBox {

    public HomeForm() {

        setSpacing(20);
        setPadding(new Insets(30));
        setAlignment(Pos.CENTER);
        setStyle("-fx-background-color: #f5f5f5;"); // لون رمادي


        Image logoImage = new Image(getClass().getResource("/images.jpeg").toExternalForm());
        ImageView logoView = new ImageView(logoImage);
        logoView.setFitWidth(100);
        logoView.setPreserveRatio(true);

        Text title = new Text("Welcome to Train Reservation System");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        title.setFill(Color.web("#34495E")); // لون اسود برمادي باين

        Button reserveButton = createStyledButton("Reserve Ticket");
        Button cancelButton = createStyledButton("Cancel Reservation");
        Button inquireButton = createStyledButton("Inquire about Trains");
          Button bookingDetailsButton = createStyledButton("View Reservation Details");
        Button exitButton = createStyledButton("Exit");

                  reserveButton.setOnAction(e -> new ReservationForm());
        cancelButton.setOnAction(e -> new CancelForm());
        inquireButton.setOnAction(e -> new InquiryForm());
           bookingDetailsButton.setOnAction(e -> new ReservationInquiryForm());
        exitButton.setOnAction(e -> System.exit(0));





        getChildren().addAll(logoView, title, reserveButton, cancelButton, inquireButton, bookingDetailsButton, exitButton);

        Scene scene = new Scene(this, 600, 500);
        Stage stage = new Stage();
        stage.setScene(scene);


        stage.setTitle("Train Reservation System");
        stage.show();
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));
              button.setTextFill(Color.WHITE);
        button.setStyle("-fx-background-color: #5DADE2; -fx-background-radius: 15; -fx-border-radius: 15;");
          button.setPrefWidth(260);
        button.setPrefHeight(50);

           button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #3498DB; -fx-background-radius: 15; -fx-border-radius: 15;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #5DADE2; -fx-background-radius: 15; -fx-border-radius: 15;"));

        return button;
    }

    public static void main(String[] args) {
        javafx.application.Application.launch(args);
    }
}

