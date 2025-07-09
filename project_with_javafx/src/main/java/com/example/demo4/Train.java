package com.example.demo4;

import java.util.List;
import java.util.ArrayList;

class Train {
    String trainCode;
    String type;
    String features;
    String source;
    String destination;
    String schedule;
    int ticketPrice;
    int totalSeats;
    int reservedSeats;
    int numberOfCars;
    List<String> stops;
    List<Passenger> passengers; // Add this line

    Train(String trainCode, String type, String features, String source, String destination,
          String schedule, int ticketPrice, int totalSeats, int numberOfCars, List<String> stops) {
        this.trainCode = trainCode;
        this.type = type;
        this.features = features;
        this.source = source;
        this.destination = destination;
        this.schedule = schedule;
        this.ticketPrice = ticketPrice;
        this.totalSeats = totalSeats;
        this.reservedSeats = 0;
        this.numberOfCars = numberOfCars;
        this.stops = stops;
        this.passengers = new ArrayList<>();
    }

    int availableSeats() {
        return totalSeats - reservedSeats;
    }
}
