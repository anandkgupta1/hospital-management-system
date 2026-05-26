package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient {

    private Connection connection;
    private Scanner scanner;

    public Patient(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }

    public void addPatient() {
        System.out.print("Enter Patient Name: ");
        scanner.nextLine(); // flush leftover newline
        String name = scanner.nextLine().trim(); // supports full names with spaces

        int age = -1;
        while (age <= 0 || age > 150) {
            System.out.print("Enter Patient Age: ");
            if (scanner.hasNextInt()) {
                age = scanner.nextInt();
                if (age <= 0 || age > 150) {
                    System.out.println("Invalid age. Please enter a value between 1 and 150.");
                }
            } else {
                System.out.println("Invalid input. Please enter a numeric age.");
                scanner.next(); // discard invalid token
            }
        }

        System.out.print("Enter Patient Gender (Male/Female/Other): ");
        String gender = scanner.next().trim();

        String query = "INSERT INTO patients(name, age, gender) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, gender);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Patient added successfully!");
            } else {
                System.out.println("Failed to add patient.");
            }
        } catch (SQLException e) {
            System.out.println("Error adding patient: " + e.getMessage());
        }
    }

    public void viewPatients() {
        String query = "SELECT * FROM patients";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            System.out.println("\nPatients:");
            System.out.println("+------------+---------------------+-------+----------+");
            System.out.println("| Patient Id | Name                | Age   | Gender   |");
            System.out.println("+------------+---------------------+-------+----------+");

            boolean hasResults = false;
            while (resultSet.next()) {
                hasResults = true;
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                System.out.printf("| %-10d | %-19s | %-5d | %-8s |%n", id, name, age, gender);
                System.out.println("+------------+---------------------+-------+----------+");
            }

            if (!hasResults) {
                System.out.println("| No patients found.                                    |");
                System.out.println("+------------+---------------------+-------+----------+");
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving patients: " + e.getMessage());
        }
    }

    public boolean getPatientById(int id) {
        String query = "SELECT id FROM patients WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            System.out.println("Error finding patient: " + e.getMessage());
        }
        return false;
    }
}
