package org.example;
import java.sql.*;
import java.util.Scanner;

public class Main {
    private static String jdbcUrl = "jdbc:postgresql://localhost/PersonDB";
   private static  String username = "postgres";
    private static String password = "12345";

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            Statement statement = connection.createStatement();

            Scanner scanner = new Scanner(System.in);
            boolean running = true;

            while (running) {
                System.out.println("Enter command (sp / ru / shp / exit): ");
                String command = scanner.nextLine();

                switch (command) {
                    case "sp":
                        savePerson(connection, scanner);
                        break;
                    case "ru":
                        registerUser(connection, scanner);
                        break;
                    case "shp":
                        showPeople(statement);
                        break;
                    case "exit":
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid command. Try again.");
                }
            }

            connection.close();
            scanner.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void savePerson(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("Enter name: ");
        String name = scanner.nextLine();

        System.out.println("Enter surname: ");
        String surname = scanner.nextLine();

        System.out.println("Enter father name: ");
        String fatherName = scanner.nextLine();

        System.out.println("Enter age: ");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter gender (MALE/FEMALE): ");
        String gender = scanner.nextLine();

        String insertQuery = "INSERT INTO person (name, surname, father_name, age, gender) VALUES ('?', '?', '?', '?', '?')";

        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, surname);
        preparedStatement.setString(3, fatherName);
        preparedStatement.setInt(4, age);
        preparedStatement.setString(5, gender);

        preparedStatement.executeUpdate();

        System.out.println("Person information saved.");
    }

    private static void registerUser(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("Enter Person ID: ");
        int personId = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter username: ");
        String username = scanner.nextLine();

        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        String insertQuery = "INSERT INTO User (person_id, username, password) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
        preparedStatement.setInt(1, personId);
        preparedStatement.setString(2, username);
        preparedStatement.setString(3, password);
        preparedStatement.executeUpdate();
        System.out.println("User registered.");
    }

    private static void showPeople(Statement statement) throws SQLException {
        String selectQuery = "SELECT * FROM Person";
        ResultSet resultSet = statement.executeQuery(selectQuery);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String fatherName = resultSet.getString("father_name");
            int age = resultSet.getInt("age");
            String gender = resultSet.getString("gender");

            System.out.println("ID: " + id + ", Name: " + name + ", Surname: " + surname + ", Father Name: " +
                    fatherName + ", Age: " + age + ", Gender: " + gender);
        }
    }
}
