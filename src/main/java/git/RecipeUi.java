package git;

import recipe.Recipe;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with interactions with the user in Recipe mode.
 */
public class RecipeUi {
    // ATTRIBUTES
    public static final String DIVIDER = "- - - - -";
    private static Scanner in;

    // METHODS

    /**
     * Constructs Ui and initialises Scanner to read input.
     */
    public RecipeUi() {
        in = new Scanner(System.in);
    }


    /**
     * Prompts user for title when adding recipe in RECIPE mode.
     *
     * @return the title of the recipe
     */
    public String promptForTitle() {
        String title = null;
        while (title == null) {
            System.out.println("Please enter the title of the recipe (e.g. fried egg):");
            title = in.nextLine().trim();
            if (title.isEmpty()) {
                System.out.println("Invalid input. Title cannot be empty.");
                title = null;
            }
        }
        return title;
    }

    /**
     * Prompts user for ingredients when adding recipe in RECIPE mode.
     *
     * @return the ingredients in a single line, trimmed only
     */
    public String promptForIngredients() {
        String ingredients = null;
        while (ingredients == null) {
            System.out.println("Please enter the ingredients for this recipe in one line (e.g. egg, salt):");
            ingredients = in.nextLine().trim();
            if (ingredients.isEmpty()) {
                System.out.println("Invalid input. Ingredients cannot be empty.");
                ingredients = null;
            }
        }
        return ingredients;
    }

    /**
     * Prompts user for steps when adding recipe in RECIPE mode.
     *
     * @return the steps in a single line, trimmed only
     */
    public String promptForSteps() {
        String steps = null;
        while (steps == null) {
            System.out.println("Please enter the steps for this recipe in one line " +
                    "(e.g. Fry the egg. Add salt. Serve.):");
            steps = in.nextLine().trim();
            if (steps.isEmpty()) {
                System.out.println("Invalid input. Steps cannot be empty.");
                steps = null;
            }
        }
        return steps;
    }

    /**
     * Informs the user that the recipe has been added to the recipe list.
     *
     * @param recipe Recipe added.
     */
    public static void printRecipeAdded(Recipe recipe) {
        assert !(recipe.getTitle().isEmpty()) : "grocery name should not be empty";
        System.out.println(recipe.getTitle() + " added!");
    }

    /**
     * Prints out when there are no recipe.
     */
    public static void printNoRecipe() {
        System.out.println("There's no recipe!");
    }

    /**
     * Prints all recipes.
     *
     * @param recipeArr An array list of groceries.
     */
    public static void printRecipeList(ArrayList<Recipe> recipeArr) {
        assert !recipeArr.isEmpty() : "recipe list should not be empty";
        System.out.println("Here are your recipe titles!");
        int num = 1;
        for (Recipe recipe : recipeArr) {
            System.out.println(num + ". " + recipe.getTitle());
            num += 1;
        }
    }

    /**
     * Prints output when the selected recipe is removed.
     *
     * @param recipe The recipe that is removed.
     */
    public static void printRecipeRemoved(Recipe recipe) {
        assert recipe != null : "Recipe does not exist";
        System.out.println(recipe.getTitle() + " is removed from the recipe list.");
    }
}