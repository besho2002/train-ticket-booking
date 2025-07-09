package com.example.demo4;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Passenger {

    String id;
    String name;
    List<Reservation> reservations;
    String employeeName;
    int employeeId;

    Passenger(String id, String name, String employeeName, int employeeId) {
        this.id = id;
        this.name = name;
        this.reservations = new ArrayList<>();
        this.employeeName = employeeName;
        this.employeeId = employeeId;
    }

    void addReservation(Reservation reservation) {
        this.reservations.add(reservation);
    }

    int getTotalTicketsReserved() {
        return reservations.stream().mapToInt(Reservation::getTickets).sum();
    }

    static class Reservation {
        int tickets;
        Train train;
        LocalDateTime reservationTime;
        String source;
        String destination;
        String ticketNumber;

        Reservation(int tickets, Train train, String source, String destination) {
            this.tickets = tickets;
                 this.train = train;
            this.reservationTime = LocalDateTime.now();
              this.source = source;
            this.destination = destination;
            this.ticketNumber = generateTicketNumber();
        }

        int getTickets() {
            return tickets;
        }

        private String generateTicketNumber() {
            Random random = new Random();
            return "TKT " + (100000 + random.nextInt(400));
        }
    }
}
