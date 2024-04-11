package grocery;

import exceptions.commands.CommandWrongFormatException;
import exceptions.CannotUseException;
import exceptions.GitException;

import exceptions.nosuch.NoSuchObjectException;
import grocery.location.Location;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class GroceryListTest {
    @Test
    public void editExpiration_success() {
        GroceryList gl = new GroceryList();
        try {
            gl.addGrocery(new Grocery("Meat", 0, 0, LocalDate.now(),"Meat", 0, new Location("Freezer")));
            gl.editExpiration("Meat d/2024-07-19");
        } catch (GitException e) {
            fail("editExpiration should not throw an exception");
        }
    }
    
    @Test
    public void editExpiration_noSuchGrocery_exceptionThrown() {
        try {
            GroceryList gl = new GroceryList();
            gl.editExpiration("nonexistentGrocery d/2024-07-19");
            fail("Expected NoSuchGroceryException not thrown");
        } catch (NoSuchObjectException e) {
            assertEquals("The grocery (nonexistentGrocery) does not exist!", e.getMessage());
        } catch (GitException e) {
            fail("Expected NoSuchGroceryException, but another GitException was thrown");
        }
    }

    @Test
    public void editExpiration_wrongFormat_exceptionThrown() {
        try {
            GroceryList gl = new GroceryList();
            gl.addGrocery(new Grocery("Meat", 0, 0, LocalDate.now(), "Meat", 0, new Location("Freezer")));
            gl.editExpiration("Meat d/2024-07-19");
        } catch (GitException e) {
            String message = "Command is in the wrong format, type \"help\" for more information." +
                    System.lineSeparator() +
                    "exp needs 'd/'";
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public void addGrocery_throwNULL_exceptionThrown() {
        try {
            GroceryList gl = new GroceryList();
            gl.addGrocery(new Grocery(null));
            fail("Expected IllegalArgumentException was not thrown.");
        } catch (NullPointerException e) {
            assertNull(e.getMessage());
        }
    }

    @Test
    public void removeGrocery_groceryDelete_exceptionThrown() {
        try {
            GroceryList gl = new GroceryList();
            gl.addGrocery(new Grocery("fooood", 0, 0, null, "Meat", 0,new Location("Freezer")));
            gl.removeGrocery("food");
            fail("Expected NoSuchGroceryException not thrown");
        } catch (GitException e) {
            // NoSuchGroceryException
            assertEquals("The grocery (food) does not exist!", e.getMessage());
        }
    }

    @Test
    public void editAmount_wrongFormat_exceptionThrown() {
        try {
            GroceryList gl = new GroceryList();
            gl.addGrocery(new Grocery("Meat", 0, 0, LocalDate.now(), "Meat", 0,new Location("Freezer")));
            gl.editAmount("Meat", false);
            fail("Expected a WrongFormatException to be thrown");
        } catch (CommandWrongFormatException e) {
            String expectedMessage = "Command is in the wrong format, type \"help\" for more information." +
                    System.lineSeparator() +
                    "amt needs 'a/'";
            assertEquals(expectedMessage.trim(), e.getMessage().trim());
        } catch (GitException e) {
            fail("Expected a WrongFormatException, but another GitException was thrown");
        }
    }

    @Test
    public void editAmountUseTrue_amountReaches0_success() {
        GroceryList gl = new GroceryList();
        try {
            gl.addGrocery(new Grocery("Meat", 5, 0, LocalDate.now(), "Meat", 0,new Location("Freezer")));
            gl.editAmount("Meat a/5", true);
        } catch (GitException e) {
            fail("editAmount_useTrue should not throw an exception");
        }
    }
    

    @Test
    public void editAmountUseTrue_noAmountCannotUse_exceptionThrown() {
        try {
            GroceryList gl = new GroceryList();
            gl.addGrocery(new Grocery("Meat", 0, 0, LocalDate.now(), "Meat", 0,new Location("Freezer")));
            gl.editAmount("Meat a/5", true);
            fail("Expected a CannotUseException to be thrown");
        } catch (CannotUseException e) {
            String expectedMessage = "The grocery you want to use is already out of stock - time to replenish!";
            assertEquals(expectedMessage, e.getMessage());
        } catch (GitException e) {
            fail("Expected a CannotUseException, but another GitException was thrown");
        }
    }

}
