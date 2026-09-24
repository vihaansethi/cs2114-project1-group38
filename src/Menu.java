package src;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu
{

    private Pantry pantry;
    private RecipeBook recipeBook;
    private Recommender recommender;
    private Scanner scanner;

    public Menu(Pantry pantry, RecipeBook recipeBook, Recommender recommender)
    {

        this.pantry = pantry;
        this.recipeBook = recipeBook;
        this.recommender = recommender;
        this.scanner = new Scanner(System.in);
    }


    public void start()
    {

        boolean running = true;

        while (running)
        {

            System.out.println();
            System.out.println("=== PantryPal ===");
            System.out.println("1. Add ingredient");
            System.out.println("2. Remove ingredient");
            System.out.println("3. View pantry");
            System.out.println("4. Get recipe recommendations");
            System.out.println("5. Quit");
            System.out.print("Choose an option: ");

            String input = scanner.nextLine();

            int choice;

            try
            {
                choice = Integer.parseInt(input);
            }
            catch (NumberFormatException e)
            {
                System.out.println("Invalid option, please try 1-5.");
                continue;
            }

            switch (choice)
            {
                case 1:
                    handleAddIngredient();
                    break;

                case 2:
                    handleRemoveIngredient();
                    break;

                case 3:
                    handleViewPantry();
                    break;

                case 4:
                    handleRecommendations();
                    break;

                case 5:
                    running = false;
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option, please try 1-5.");
            }
        }

        scanner.close();
    }


    public void handleAddIngredient()
    {

        System.out.print("Enter ingredient to add: ");
        String name = scanner.nextLine();

        pantry.addIngredient(name);
    }


    public void handleRemoveIngredient()
    {

        System.out.print("Enter ingredient to remove: ");
        String name = scanner.nextLine();

        boolean removed = pantry.removeIngredient(name);

        if (removed)
        {
            System.out.println(name + " removed from pantry.");
        }
        else
        {
            System.out.println("That item isn't in your pantry.");
        }
    }


    public void handleViewPantry()
    {

        if (pantry.isEmpty())
        {
            System.out.println("Your pantry is empty.");
            return;
        }

        System.out.println("Your pantry:");

        for (Ingredient item : pantry.getItems())
        {
            System.out.println("- " + item.getName());
        }
    }


    public void handleRecommendations()
    {

        if (pantry.isEmpty())
        {
            System.out.println("Add some ingredients first!");
            return;
        }

        ArrayList<Recipe> fullMatches =
            recommender.getFullMatches(recipeBook, pantry);

        ArrayList<Recipe> almostMatches =
            recommender.getAlmostMatches(recipeBook, pantry, 2);

        System.out.println();
        System.out.println("Recipes you can make:");

        if (fullMatches.isEmpty())
        {
            System.out.println("No full matches found.");
        }
        else
        {
            for (Recipe recipe : fullMatches)
            {
                System.out.println("- " + recipe.getName());
            }
        }

        System.out.println();
        System.out.println("Recipes you're close to making:");

        if (almostMatches.isEmpty())
        {
            System.out.println("No almost-matches found.");
        }
        else
        {
            for (Recipe recipe : almostMatches)
            {

                System.out.println("- " + recipe.getName());

                ArrayList<Ingredient> missing =
                    recommender.findMissing(recipe, pantry);

                System.out.print("  Missing: ");

                for (int i = 0; i < missing.size(); i++)
                {

                    System.out.print(missing.get(i).getName());

                    if (i < missing.size() - 1)
                    {
                        System.out.print(", ");
                    }
                }

                System.out.println();
            }
        }
    }
}
