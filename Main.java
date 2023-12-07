package prathamm;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Expense {
    String description;
    double amount;
    String category;

    public Expense(String description, double amount, String category) {
        this.description = description;
        this.amount = amount;
        this.category = category;
    }
}

class ExpenseTracker {
    ArrayList<Expense> expenses;
    String filename;

    public ExpenseTracker(String filename) {
        this.filename = filename;
        this.expenses = loadExpenses();
    }

    private ArrayList<Expense> loadExpenses() {
        ArrayList<Expense> loadedExpenses = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String description = parts[0];
                double amount = Double.parseDouble(parts[1]);
                String category = parts[2];
                loadedExpenses.add(new Expense(description, amount, category));
            }
        } catch (IOException e) {
            // File doesn't exist or there's an issue reading it, ignore for now
        }
        return loadedExpenses;
    }

    private void saveExpenses() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Expense expense : expenses) {
                writer.println(expense.description + "," + expense.amount + "," + expense.category);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addExpense(String description, double amount, String category) {
        Expense expense = new Expense(description, amount, category);
        expenses.add(expense);
        saveExpenses();
        System.out.println("Expense added: " + description + " - $" + amount + " (" + category + ")");
    }

    public void displayExpenses() {
        System.out.println("Expense Tracker:");
        for (Expense expense : expenses) {
            System.out.println(expense.description + " - $" + expense.amount + " (" + expense.category + ")");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpenseTracker expenseTracker = new ExpenseTracker("expenses.txt");

        while (true) {
            System.out.println("\n1. Add Expense\n2. View Expenses\n3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter expense description: ");
                    String description = scanner.next();
                    System.out.print("Enter expense amount: $");
                    double amount = scanner.nextDouble();
                    System.out.print("Enter expense category: ");
                    String category = scanner.next();
                    expenseTracker.addExpense(description, amount, category);
                    break;
                case 2:
                    expenseTracker.displayExpenses();
                    break;
                case 3:
                    System.out.println("Exiting Expense Tracker. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
