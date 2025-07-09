package com.example.demo4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TrainReservationSystem {
    static List<Train> trains = new ArrayList<>();
    static List<Passenger> passengers = new ArrayList<>();
    static List<Employee> employees = new ArrayList<>();
    static Employee currentEmployee;

    public static void initializeTrains() {
        employees.add(new Employee("Beshoy", 23, "Admin Address", "2002", 10000000.0));
           employees.add(new Employee("admin", 20, "Admin Address", "admin", 50000.0));

        employees.add(new Employee("beshoy", 2, "sohag", "2002", 5000.0));
           trains.add(new Train("VIP001", "VIP Train", "Air Conditioning, Wheelchair Accessibility, Spacious",
                "banyswif", "Assiut", "02:30 AM - 05:30 PM", 80, 100, 5, Arrays.asList("Assiut 02:30 PM","banyswif 3:30 PM", "Cairo 4:30 AM", "Alexandria 05:30 PM")));


                            trains.add(new Train("TALGO001", "Talgo Train (Category-1)", "Air Conditioning, Wheelchair Accessibility",
                "Cairo", "Alexandria", "06:00 AM - 10:00 PM", 65, 100, 5, Arrays.asList( "Assiut 06:00 AM","banyswif 07:00 AM", "Cairo 08:00 AM", "Alexandria 10:00 AM")));

        trains.add(new Train("TALGO002", "Talgo Train (Category-2)", "Good Seating, no air conditioning",
                "Alexandria", "banyswif", "07:00 AM - 01:00 PM", 65, 100, 5, Arrays.asList("Assuit 07:00 AM,banyswif 09:00 AM", "Cairo 11:00 AM", "Alexandria 01:00 PM")));

            trains.add(new Train("RUS001", "Russian Train", "Fixed Seats, Smaller, no air conditioning",
                "Assiut", "Cairo", " 09:10 AM - 02:00 AM", 40, 100, 5, Arrays.asList("Assiut 09:10 AM","banyswif 10:10 AM", "Cairo 12:30 PM", "Alexandria 02:00 PM")));
    }
}

