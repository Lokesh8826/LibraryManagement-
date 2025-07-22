package src;

import java.util.Scanner;

public class LibraryApp {
    public static void main(String[] args) {
        DBHelper.initializeDatabase();
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to the Library Management System");
        System.out.print("Do you have an account? (yes/no): ");
        String hasAccount = sc.nextLine();

        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.print("Enter username: ");
            String username = sc.nextLine();
            System.out.print("Enter password: ");
            String password = sc.nextLine();

            if (hasAccount.equalsIgnoreCase("yes")) {
                loggedIn = User.login(username, password);
                if (!loggedIn) System.out.println("Invalid credentials. Try again.");
            } else {
                if (User.register(username, password)) {
                    System.out.println("Registered successfully. Please login.");
                    hasAccount = "yes";
                }
            }
        }

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    sc.nextLine(); // clear buffer
                    System.out.print("Enter title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter author: ");
                    String author = sc.nextLine();
                    Book.addBook(title, author);
                    break;
                case 2:
                    Book.viewBooks();
                    break;
                case 3:
                    System.out.print("Enter book ID to borrow: ");
                    int borrowId = sc.nextInt();
                    Book.borrowBook(borrowId);
                    break;
                case 4:
                    System.out.print("Enter book ID to return: ");
                    int returnId = sc.nextInt();
                    Book.returnBook(returnId);
                    break;
                case 5:
                    sc.close();
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}