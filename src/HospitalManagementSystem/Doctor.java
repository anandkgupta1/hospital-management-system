package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctor {

    private Connection connection;

    public Doctor(Connection connection) {
        this.connection = connection;
    }

    public void viewDoctors() {
        String query = "SELECT * FROM doctors";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            System.out.println("\nDoctors:");
            System.out.println("+------------+---------------------+---------------------+");
            System.out.println("| Doctor Id  | Name                | Specialization      |");
            System.out.println("+------------+---------------------+---------------------+");

            boolean hasResults = false;
            while (resultSet.next()) {
                hasResults = true;
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String specialization = resultSet.getString("specialization");
                System.out.printf("| %-10d | %-19s | %-19s |%n", id, name, specialization);
                System.out.println("+------------+---------------------+---------------------+");
            }

            if (!hasResults) {
                System.out.println("| No doctors found.                                      |");
                System.out.println("+------------+---------------------+---------------------+");
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving doctors: " + e.getMessage());
        }
    }

    public boolean getDoctorById(int id) {
        String query = "SELECT id FROM doctors WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            System.out.println("Error finding doctor: " + e.getMessage());
        }
        return false;
    }
}
