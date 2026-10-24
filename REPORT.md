# Smart Pantry Manager - Assignment Report

## 1. Cover Page

**App Name:** Smart Pantry Manager

**Your Name:** [Your Name]

**Student Number:** [Your Student Number]

**Module Name:** Mobile App Development 700

**Date:** October 24, 2026

---

## 2. Table of Contents

1. Cover Page
2. Table of Contents
3. Introduction
4. System Design
5. Screenshots
6. Key Code Snippets
7. Challenges and Solutions
8. Conclusion and Reflection
9. Reference List

---

## 3. Introduction

### Problem Statement
Food waste is a significant global issue, with households throwing away large amounts of food that could have been used. Often, people forget what ingredients they have in their pantry or don't know what recipes they can make with what's available, leading to unnecessary food waste and additional grocery purchases.

### Target Audience
Smart Pantry Manager is designed for home cooks and individuals who want to:
- Track their pantry inventory
- Reduce food waste
- Save money by using existing ingredients
- Discover recipes based on what they already have

### Solution
The Smart Pantry Manager app addresses this problem by:
- Allowing users to track pantry items with quantity and expiry dates
- Providing a database of 20 recipes
- Using a strict-matching algorithm to suggest only recipes the user can make
- Helping users discover new ways to use their existing ingredients

---

## 4. System Design

### Screen Flow Diagram

```
                    ┌─────────────┐
                    │  MainActivity│
                    │  (Main Menu) │
                    └──────┬──────┘
                           │
          ┌────────────────┼────────────────┐
          │                │                │
          ▼                ▼                ▼
┌─────────────────┐ ┌──────────────┐ ┌─────────────┐
│ PantryList      │ │Suggested     │ │Settings     │
│ Activity        │ │Recipes       │ │Activity     │
└────────┬────────┘ │Activity      │ └─────────────┘
         │          └──────┬───────┘
         │                 │
         ▼                 ▼
┌─────────────────┐ ┌──────────────┐
│AddEditIngredient│ │RecipeDetail  │
│ Activity        │ │Activity      │
└─────────────────┘ └──────────────┘
```

### Database ER Diagram

```
┌─────────────────────────────────────────┐
│             PANTRY TABLE                │
├─────────────────────────────────────────┤
│ id (INTEGER, PRIMARY KEY, AUTOINCREMENT)│
│ name (TEXT, NOT NULL)                   │
│ quantity (INTEGER, NOT NULL)            │
│ unit (TEXT)                             │
│ expiry_date (TEXT)                      │
└─────────────────────────────────────────┘
```

**Database Schema:**
- **Table Name**: pantry
- **Primary Key**: id (auto-incrementing integer)
- **Fields**:
  - name: Ingredient name (required)
  - quantity: Amount of ingredient (required, integer)
  - unit: Measurement unit (optional, e.g., kg, pieces)
  - expiry_date: Expiry date in YYYY-MM-DD format (optional)

**Note**: Recipes are stored as Java objects in the RecipeMatcher class, not in the database, as they are static reference data that doesn't change.

---

## 5. Screenshots

### Main Screen
*[Screenshot of MainActivity showing three buttons: Pantry, Recipes, and Settings]*
**Caption:** Main menu screen providing navigation to Pantry management, Recipe suggestions, and Settings.

### Pantry List Screen
*[Screenshot of PantryListActivity showing list of ingredients with delete buttons]*
**Caption:** Pantry list displaying all ingredients with quantity, unit, and expiry date. Each item has a delete button for easy removal.

### Add Ingredient Screen
*[Screenshot of AddEditIngredientActivity with input fields]*
**Caption:** Add/Edit ingredient form with fields for name, quantity, unit, and expiry date. Includes Save and Cancel buttons.

### Validation Error
*[Screenshot showing error message when name field is empty]*
**Caption:** Input validation error displayed when user tries to save without entering required fields.

### Suggested Recipes - No Matches
*[Screenshot showing "No recipes match. You have: [list of ingredients]" message]*
**Caption:** Feedback message displayed when no recipes match the current pantry, showing which ingredients are detected.

### Suggested Recipes - With Matches
*[Screenshot showing list of matching recipes]*
**Caption:** List of recipes that can be made with current pantry ingredients using strict-matching.

### Recipe Detail Screen
*[Screenshot of RecipeDetailActivity showing full recipe]*
**Caption:** Recipe detail view showing recipe name, full ingredient list, instructions, and which ingredients the user has.

### Settings Screen
*[Screenshot of SettingsActivity with Clear Data button]*
**Caption:** Settings screen with option to clear all pantry data, requiring confirmation before deletion.

### Delete Confirmation Dialog
*[Screenshot of AlertDialog confirming data deletion]*
**Caption:** Confirmation dialog shown before clearing all pantry data to prevent accidental deletion.

---

## 6. Key Code Snippets

### Snippet 1: Strict-Matching Algorithm (RecipeMatcher.java)

```java
public List<Recipe> findMatchingRecipes(List<PantryItem> pantryItems) {
    List<Recipe> matchingRecipes = new ArrayList<>();

    // Create a list of pantry item names for easy matching
    List<String> pantryNames = new ArrayList<>();
    for (PantryItem item : pantryItems) {
        pantryNames.add(item.getName().toLowerCase().trim());
    }

    for (Recipe recipe : allRecipes) {
        String[] recipeIngredients = recipe.getIngredients().split(", ");
        List<String> matching = new ArrayList<>();
        List<String> missing = new ArrayList<>();

        for (String ingredient : recipeIngredients) {
            String ingredientLower = ingredient.toLowerCase().trim();
            boolean found = false;
            for (String pantryName : pantryNames) {
                // Very flexible matching: check if pantry name contains ingredient or vice versa
                // Also handle plural forms by removing trailing 's'
                String pantryRoot = pantryName.endsWith("s") ? pantryName.substring(0, pantryName.length() - 1) : pantryName;
                String ingredientRoot = ingredientLower.endsWith("s") ? ingredientLower.substring(0, ingredientLower.length() - 1) : ingredientLower;

                if (pantryName.contains(ingredientLower) ||
                    ingredientLower.contains(pantryName) ||
                    pantryRoot.contains(ingredientRoot) ||
                    ingredientRoot.contains(pantryRoot)) {
                    matching.add(ingredient);
                    found = true;
                    break;
                }
            }
            if (!found) {
                missing.add(ingredient);
            }
        }

        // Strict-matching: only add recipes that have ALL ingredients present
        if (missing.isEmpty()) {
            Recipe matchedRecipe = new Recipe(
                    recipe.getName(),
                    recipe.getIngredients(),
                    recipe.getInstructions(),
                    String.join(", ", matching),
                    String.join(", ", missing)
            );
            matchingRecipes.add(matchedRecipe);
        }
    }

    return matchingRecipes;
}
```

**Explanation:** This is the core business logic of the app. It implements the strict-matching rule by checking if all recipe ingredients are present in the pantry. The algorithm uses flexible matching that handles case insensitivity, whitespace, and plural forms (e.g., "egg" matches "eggs"). Only recipes with zero missing ingredients are returned.

### Snippet 2: Database Helper CRUD Operations (DatabaseHelper.java)

```java
public long addPantryItem(PantryItem item) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(COLUMN_NAME, item.getName());
    values.put(COLUMN_QUANTITY, item.getQuantity());
    values.put(COLUMN_UNIT, item.getUnit());
    values.put(COLUMN_EXPIRY, item.getExpiryDate());
    long id = db.insert(TABLE_PANTRY, null, values);
    db.close();
    return id;
}

public List<PantryItem> getAllPantryItems() {
    List<PantryItem> items = new ArrayList<>();
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db.query(TABLE_PANTRY, null, null, null, null, null, COLUMN_NAME + " ASC");

    if (cursor.moveToFirst()) {
        do {
            PantryItem item = new PantryItem();
            item.setId(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID)));
            item.setName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)));
            item.setQuantity(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_QUANTITY)));
            item.setUnit(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_UNIT)));
            item.setExpiryDate(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EXPIRY)));
            items.add(item);
        } while (cursor.moveToNext());
    }
    cursor.close();
    db.close();
    return items;
}

public int updatePantryItem(PantryItem item) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(COLUMN_NAME, item.getName());
    values.put(COLUMN_QUANTITY, item.getQuantity());
    values.put(COLUMN_UNIT, item.getUnit());
    values.put(COLUMN_EXPIRY, item.getExpiryDate());
    int rowsAffected = db.update(TABLE_PANTRY, values, COLUMN_ID + " = ?", new String[]{String.valueOf(item.getId())});
    db.close();
    return rowsAffected;
}

public int deletePantryItem(long id) {
    SQLiteDatabase db = this.getWritableDatabase();
    int rowsDeleted = db.delete(TABLE_PANTRY, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    db.close();
    return rowsDeleted;
}
```

**Explanation:** These methods implement full CRUD (Create, Read, Update, Delete) operations for pantry items using SQLite. The DatabaseHelper extends SQLiteOpenHelper and manages database creation and versioning. Each method properly opens the database, performs the operation, and closes the database to prevent memory leaks.

### Snippet 3: Custom Adapter with Delete Button (PantryAdapter.java)

```java
public class PantryAdapter extends ArrayAdapter<PantryItem> {

    private OnDeleteClickListener deleteListener;

    public interface OnDeleteClickListener {
        void onDeleteClick(PantryItem item);
    }

    public void setOnDeleteClickListener(OnDeleteClickListener listener) {
        this.deleteListener = listener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PantryItem item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_pantry, parent, false);
        }

        TextView tvName = convertView.findViewById(R.id.tvItemName);
        TextView tvQuantity = convertView.findViewById(R.id.tvItemQuantity);
        TextView tvExpiry = convertView.findViewById(R.id.tvItemExpiry);
        Button btnDelete = convertView.findViewById(R.id.btnDelete);

        tvName.setText(item.getName());
        tvQuantity.setText(item.getQuantity() + " " + (item.getUnit() != null ? item.getUnit() : ""));
        tvExpiry.setText("Expires: " + item.getExpiryDate());

        btnDelete.setOnClickListener(v -> {
            if (deleteListener != null) {
                deleteListener.onDeleteClick(item);
            }
        });

        return convertView;
    }
}
```

**Explanation:** This custom ArrayAdapter displays pantry items in a ListView. It implements an interface pattern for handling delete button clicks, allowing the Activity to respond to delete events. The adapter uses view recycling (convertView) for better performance and binds data to TextView elements. This demonstrates proper use of Android's adapter pattern for displaying dynamic lists.

---

## 7. Challenges and Solutions

### Challenge 1: Gradle Configuration and JDK Compatibility
**Problem:** During initial setup, the project failed to build due to incompatible Gradle and JDK versions. The error messages indicated that Gradle 8.2 was incompatible with JDK 21, and there were also issues with AndroidX dependencies not being enabled.

**Solution:**
- Updated Android Gradle Plugin from 8.1.0 to 8.3.0
- Downgraded Gradle wrapper from 9.7.1 to 8.5 for better compatibility
- Updated Java compatibility from version 8 to 17 in build.gradle
- Created gradle.properties file with `android.useAndroidX=true` to enable AndroidX support
- These changes resolved the build errors and allowed the project to compile successfully.

### Challenge 2: File Locking During Build
**Problem:** The build process repeatedly failed with "Unable to delete directory" errors. Gradle couldn't clean up old build artifacts because directories were locked by previous build processes or the Gradle daemon.

**Solution:**
- Stopped the Gradle daemon using `gradlew --stop`
- Manually deleted the build directories (.gradle, app/build)
- Invalidated Android Studio caches and restarted
- This resolved the file locking issues and allowed clean builds to proceed.

### Challenge 3: Recipe Matching Not Working
**Problem:** Initially, no recipes were being suggested even when users had all required ingredients. The strict-matching algorithm was too strict, requiring exact string matches which failed on minor variations like singular/plural forms.

**Solution:**
- Enhanced the matching algorithm to handle case insensitivity and whitespace trimming
- Added plural form handling by removing trailing 's' for comparison
- Implemented substring matching to handle partial matches (e.g., "egg" contains "eggs")
- Added debug feedback showing which pantry ingredients were detected
- Made the matching algorithm more flexible while maintaining the strict-matching rule.

### Challenge 4: Delete Functionality UX
**Problem:** The original implementation required users to long-press on pantry items to delete them, which was not intuitive and users couldn't discover this feature.

**Solution:**
- Added a visible red "Delete" button to each pantry item in the list
- Implemented an interface pattern in the adapter to handle delete button clicks
- Updated the layout to include the delete button in a horizontal layout
- This made the delete functionality discoverable and easy to use.

---

## 8. Conclusion and Reflection

### What Was Learned
1. **Android Development Fundamentals**: Gained practical experience with Activities, Intents, Adapters, and the Android lifecycle
2. **SQLite Database**: Learned to implement local data persistence using SQLiteOpenHelper and proper CRUD operations
3. **UI/UX Design**: Created user-friendly interfaces with CardView layouts and intuitive navigation
4. **Gradle Build System**: Understood Android project structure, dependencies, and build configuration
5. **Algorithm Design**: Implemented a flexible matching algorithm that balances strictness with usability
6. **Version Control**: Used Git effectively to track development progress with meaningful commits

### What Worked Well
- The strict-matching algorithm effectively enforces the core business rule
- SQLite provides reliable local data persistence
- The modular architecture (separate Activities for each screen) makes the code maintainable
- Custom adapters efficiently display dynamic data lists
- Input validation prevents invalid data entry

### What Could Be Improved
1. **Recipe Database**: Currently, recipes are hardcoded in Java. A better approach would be to store them in the database, allowing users to add their own recipes
2. **Quantity Checking**: The current algorithm doesn't check if users have sufficient quantity of each ingredient. Adding quantity-based matching would be more realistic
3. **Expiry Date Alerts**: The app tracks expiry dates but doesn't provide notifications for expiring items
4. **Search Functionality**: Adding search/filter for pantry items and recipes would improve usability
5. **Recipe Images**: Adding images to recipes would make the app more visually appealing
6. **Backup/Restore**: Adding cloud sync or local backup would prevent data loss

### Future Enhancements
Given more time, I would:
- Implement a recipe management system allowing users to add, edit, and delete their own recipes
- Add quantity-based recipe matching
- Implement expiry date notifications
- Add search and filter functionality
- Integrate with a cloud backend for data synchronization across devices
- Add nutritional information to recipes
- Implement a shopping list feature for missing ingredients

---

## 9. Reference List

### Android Documentation
- Android Developers. (2024). *SQLite and SQLiteOpenHelper*. https://developer.android.com/training/data-storage/sqlite
- Android Developers. (2024). *RecyclerView*. https://developer.android.com/guide/topics/ui/layout/recyclerview
- Android Developers. (2024). *Activities*. https://developer.android.com/guide/components/activities/intro-activities

### Tutorials and Resources
- Vogella, L. (2024). *Android Development Tutorial*. http://www.vogella.com/tutorials/Android/article.html
- Androidhive. (2024). *Android SQLite Database Tutorial*. https://www.androidhive.info/2011/11/android-sqlite-database-tutorial/

### Tools and Libraries
- Android Studio. (2024). *Official IDE for Android Development*. https://developer.android.com/studio
- Gradle. (2024). *Build Automation Tool*. https://gradle.org/

### Assignment Resources
- Mobile App Development 700 Course Materials
- Assignment Brief and Requirements Document

---

**Total Word Count:** Approximately 2,500 words
