package recipe;

import exceptions.GitException;
import exceptions.emptyinput.EmptyInputException;
import exceptions.nosuch.NoSuchObjectException;
import git.Ui;

import java.util.ArrayList;

public class RecipeList {
    private ArrayList<Recipe> recipeArr;

    /**
     * Constructs RecipeList with recipe as an empty ArrayList.
     */
    public RecipeList() {
        recipeArr = new ArrayList<>();
    }

    /**
     * Adds a recipe to the recipe list.
     *
     * @param recipe Recipe to be added.
     */
    public void addRecipe(Recipe recipe) {
        try {
            recipeArr.add(recipe);
            Ui.printRecipeAdded(recipe);
            assert recipeArr.contains(recipe) : "Grocery should be added to the list";
        } catch (NullPointerException e) {
            System.out.println("Failed to add recipe: the recipe is null.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred while adding the recipe: " + e.getMessage());
        }

    }

    /**
     * Lists all the user's recipes.
     */
    public void listRecipes() {
        int size = recipeArr.size();
        if (size == 0) {
            Ui.printNoRecipe();
        } else {
            Ui.printRecipeList(recipeArr);
        }
    }

    /**
     * Returns the desired recipe.
     *
     * @param title Title of the recipe.
     * @return The specific recipe.
     * @throws NoSuchObjectException If the selected grocery does not exist.
     */
    public Recipe getRecipe(String title) throws NoSuchObjectException {
        int index = -1;
        for (Recipe recipe : recipeArr) {
            if(recipe.getTitle().equalsIgnoreCase(title)) {
                index = recipeArr.indexOf(recipe);
                break;
            }
        }

        if (index != -1) {
            assert recipeArr != null : "Found grocery should not be null";
            return recipeArr.get(index);
        } else {
            throw new NoSuchObjectException("recipe");
        }
    }

    /**
     * Removes a recipe.
     *
     * @param title Recipe title from user input.
     * @throws GitException If recipe is empty.
     */
    public void removeRecipe(String title) throws GitException {
        if (title.isEmpty()) {
            throw new EmptyInputException("recipe");
        }

        Recipe currRecipe = getRecipe(title);
        recipeArr.remove(currRecipe);
        Ui.printRecipeRemoved(currRecipe);
    }
}