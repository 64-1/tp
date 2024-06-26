package git;

import exceptions.GitException;
import exceptions.InvalidCommandException;
import grocery.Grocery;
import grocery.GroceryList;

/**
 * Represents the Grocery in Time (GiT) program, allowing users to store and track their groceries!
 */
public class Git {
    // ATTRIBUTES
    private GroceryList groceryList;
    private Ui ui;
    private boolean isRunning;

    // METHODS
    /**
     * Initialise Git.
     */
    public Git() {
        groceryList = new GroceryList();
        ui = new Ui();
        isRunning = true;
    }

    /**
     * Handles commands.
     *
     * @param commandParts Command and its details.
     * @throws GitException Exception thrown depending on specific error.
     */
    public void executeCommand(String[] commandParts) throws GitException {
        assert commandParts.length == 2 : "Command passed in wrong format";

        switch (commandParts[0]) {
        case "add":
            // Assuming the format is "add GROCERY"
            Grocery grocery = new Grocery(commandParts[1], "", "");
            groceryList.addGrocery(grocery);
            break;

        case "exp":
            // if (commandParts.length < 2) throw new EmptyGroceryException();
            groceryList.setExpiration(commandParts[1]);
            break;

        case "amt":
            groceryList.setAmount(commandParts[1]);
            break;

        case "del":
            groceryList.removeGrocery(commandParts[1]);
            break;

        case "list":
            groceryList.listGroceries();
            break;

        case "help":
            ui.displayHelp();
            break;

        case "exit":
            System.out.println("bye bye!");
            isRunning = false;
            break;

        default:
            throw new InvalidCommandException();
        }
    }

    /**
     * Runs Git.
     */
    private void run() {
        ui.printWelcome();

        while (isRunning) {
            try {
                String[] commandParts = ui.processInput();
                executeCommand(commandParts);
            } catch (GitException e) {
                System.out.println(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
    }

    /**
     * Main for GiT.
     */
    public static void main(String[] args) {
        new Git().run();
    }

}
