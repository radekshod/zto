package org.zto;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class main {
    public static void main( String[] args ) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        DatabaseConnector dbc = new DatabaseConnector();
        dbc.connect();
        boolean repeat = true;

        while (repeat) {
            List<String> asList = Arrays.asList(
                    "0. Add Person",
                    "1. Read Person",
                    "2. Update Person",
                    "3. Delete Person",
                    "4. Predict age based on a name",
                    "5. Add predicted age",
                    "6. Select predicted age",
                    "7. Remove predicted age",
                    "8. Exit");

            for (int i = 0, asListSize = asList.size(); i < asListSize; i++) {
                String s = asList.get(i);
                System.out.println(s);
            }
            System.out.print("Type (0-8): \n");
            int choose = scanner.nextInt();
            scanner.nextLine();

            switch (choose) {
                case 0 -> {
                    System.out.print("Type username: ");
                    String username = scanner.nextLine();
                    System.out.print("Type email: ");
                    String email = scanner.nextLine();
                    System.out.print("Type password: ");
                    String password = scanner.nextLine();
                    System.out.print("Type enabled (true/false): ");
                    boolean enabled = scanner.nextBoolean();
                    scanner.nextLine();
                    Person person0 = new Person(0, username, email, password, enabled);
                    person0.create(dbc);
                    break;
                }
                case 1 -> {
                    System.out.print("Type username: ");
                    String username = scanner.nextLine();
                    Person person1 = new Person(1, username, "", "", false);
                    person1.read(dbc);
                    break;
                }
                case 2 -> {
                    System.out.print("Type username: ");
                    String username2 = scanner.nextLine();
                    System.out.print("Type email: ");
                    String email2 = scanner.nextLine();
                    System.out.print("Type password: ");
                    String pass2 = scanner.nextLine();
                    System.out.print("Type enabled (true/false): ");
                    boolean enabled2 = scanner.nextBoolean();
                    scanner.nextLine();
                    Person person2 = new Person(2, username2, email2, pass2, enabled2);
                    person2.update(dbc);
                    break;
                }
                case 3 -> {
                    Person person3;
                    String username;
                    System.out.print("Type username: ");
                    username = scanner.nextLine();
                    person3 = new Person(3, username, "", "", false);
                    person3.delete(dbc);
                    break;
                }
                case 4 -> {
                    System.out.println("Enter your name:");
                    Scanner sc = new Scanner(System.in);
                    String username = sc.nextLine();
                    int age = GetApiUrl.getAge(username);
                    System.out.println("Your age: " + age + "\n");
                    break;
                }
                case 5 -> {
                    System.out.println("Enter your name: \n");
                    Scanner sc = new Scanner(System.in);
                    String username = sc.nextLine();
                    int age = GetApiUrl.getAge(username);
                    Person.addAge(dbc, username, age);
                    break;
                }
                case 6 -> {
                    System.out.println("Enter your name: \n");
                    Scanner sc = new Scanner(System.in);
                    String username = sc.nextLine();
                    Person.getAge(dbc, username);
                    break;
                }
                case 7 -> {
                    System.out.println("Enter your name: \n");
                    Scanner sc = new Scanner(System.in);
                    String username = sc.nextLine();
                    Person.deleteAge(dbc, username);
                    break;
                }
                case 8 -> {
                    repeat = false;
                }
                default -> System.out.println("That is not a valid option. Please choose again.");

            }

        }
    }
}

