# cs2114-project1-group38
# PantryPal

A terminal-based Java program that recommends recipes based on the ingredients you have on hand.

## How to Compile and Run

1. Clone the repository:
   git clone <your-repo-url>
   cd PantryPal

2. Compile all source files:
   javac -d bin src/*.java

3. Run the program:
   java -cp bin Main

## Features
- Add and remove pantry ingredients
- View current pantry
- Get recipe recommendations: full matches and "almost" matches (missing 1-2 ingredients)
- Handles bad input (blank names, duplicates, invalid menu choices) without crashing

## System Diagram
See `system-diagram.png` in this repo.

## Team
- [Fernando Zuniga] — Pantry, Ingredient
- [Preston Moore] — Recipe, RecipeBook
- [Muna] — Recommender
- [Preston,Muna,Fernando] — Menu, Main, integration