package prathamm;
import java.util.Scanner;

class TreasureRoom {
    String name;
    String description;
    TreasureRoom[] exits;
    boolean hasTreasure;

    public TreasureRoom(String name, String description, boolean hasTreasure) {
        this.name = name;
        this.description = description;
        this.hasTreasure = hasTreasure;
        this.exits = new TreasureRoom[4]; // Assuming four possible directions (north, south, east, west)
    }
}

public class TreasureHuntAdventure {
    private TreasureRoom currentRoom;
    private Scanner scanner;

    public TreasureHuntAdventure() {
        // Initialize rooms
        TreasureRoom startRoom = new TreasureRoom("Start", "You stand at the entrance of a mysterious forest.", false);
        TreasureRoom clearing = new TreasureRoom("Clearing", "You find yourself in a peaceful clearing.", false);
        TreasureRoom caveEntrance = new TreasureRoom("Cave Entrance", "You discover the entrance to a dark cave.", false);
        TreasureRoom undergroundRiver = new TreasureRoom("Underground River", "You wade through an underground river.", false);
        TreasureRoom treasureChamber = new TreasureRoom("Treasure Chamber", "You enter a glittering chamber with a hidden treasure!", true);

        // Connect rooms
        startRoom.exits[0] = clearing;           // North
        clearing.exits[1] = startRoom;           // South
        clearing.exits[2] = caveEntrance;       // East
        caveEntrance.exits[3] = clearing;       // West
        caveEntrance.exits[2] = undergroundRiver; // East
        undergroundRiver.exits[3] = caveEntrance; // West
        undergroundRiver.exits[0] = treasureChamber; // North
        treasureChamber.exits[1] = undergroundRiver; // South

        // Set starting room
        currentRoom = startRoom;

        // Initialize scanner
        scanner = new Scanner(System.in);
    }

    public void play() {
        System.out.println("Welcome to the Treasure Hunt Adventure!");

        while (true) {
            // Display current room information
            System.out.println(currentRoom.description);

            // Check if the room has treasure
            if (currentRoom.hasTreasure) {
                System.out.println("Congratulations! You found the hidden treasure!");
                break;
            }

            // Display available exits
            System.out.print("Exits: ");
            for (int i = 0; i < currentRoom.exits.length; i++) {
                if (currentRoom.exits[i] != null) {
                    System.out.print(getDirectionName(i) + " ");
                }
            }
            System.out.println();

            // Get user input
            System.out.print("Enter your command: ");
            String command = scanner.nextLine();

            // Process user input
            if (command.equalsIgnoreCase("quit")) {
                System.out.println("Thanks for playing. Goodbye!");
                break;
            } else if (command.equalsIgnoreCase("help")) {
                System.out.println("Available commands: quit, help, and the cardinal directions.");
            } else if (command.equalsIgnoreCase("north")) {
                move(0); // North
            } else if (command.equalsIgnoreCase("south")) {
                move(1); // South
            } else if (command.equalsIgnoreCase("east")) {
                move(2); // East
            } else if (command.equalsIgnoreCase("west")) {
                move(3); // West
            } else {
                System.out.println("Invalid command. Type 'help' for a list of commands.");
            }
        }
    }

    private void move(int direction) {
        if (currentRoom.exits[direction] != null) {
            currentRoom = currentRoom.exits[direction];
        } else {
            System.out.println("You can't go that way.");
        }
    }

    private String getDirectionName(int index) {
        String[] directionNames = {"North", "South", "East", "West"};
        return directionNames[index];
    }

    public static void main(String[] args) {
        TreasureHuntAdventure game = new TreasureHuntAdventure();
        game.play();
    }
}

