# Smart Pantry Manager

## Description
Smart Pantry Manager is an Android application that helps users reduce food waste by tracking the ingredients they have at home and suggesting recipes they can cook using strictly those leftover ingredients. The app implements a strict-matching rule - a recipe is only suggested if the user has ALL the required ingredients in their pantry.

## Features
- **Pantry Management**: Add, edit, and delete pantry items with name, quantity, unit, and optional expiry date
- **Recipe Collection**: 20 pre-loaded recipes with ingredients and instructions
- **Strict-Matching Algorithm**: Only suggests recipes when all required ingredients are present
- **Recipe Suggestions**: Dynamic list of recipes you can make right now
- **Recipe Details**: View full ingredient list and preparation steps
- **Settings**: Clear all pantry data with confirmation dialog

## Database Choice: SQLite
This app uses SQLite as the database solution. SQLite was chosen because:
- It's a local, file-based database that doesn't require external servers or internet connectivity
- Built-in Android support through SQLiteOpenHelper makes it easy to implement
- Perfect for the scale of this application (personal pantry tracking)
- Full CRUD operations are implemented (Create, Read, Update, Delete)
- Data persists between app sessions
- No setup or configuration required beyond the app itself

## Screens
1. **Main Screen**: Navigation hub with buttons to Pantry, Recipes, and Settings
2. **Pantry List**: Displays all pantry items with quantity and expiry dates
3. **Add/Edit Ingredient**: Form to add new ingredients or edit existing ones
4. **Suggested Recipes**: List of recipes you can make based on your pantry
5. **Recipe Detail**: Full recipe with ingredients and instructions
6. **Settings**: Options to clear all pantry data

## Technical Details
- **Language**: Java
- **Minimum SDK**: 24 (Android 7.0)
- **Target SDK**: 34 (Android 14)
- **Architecture**: Activity-based with SQLite database
- **UI Components**: ListView with custom adapters, CardView layouts

## Setup Instructions

### Prerequisites
- Android Studio (latest version)
- JDK 17 or higher
- Android SDK (API 24+)

### Building the Project
1. Clone or download this repository
2. Open Android Studio
3. Select "Open an Existing Project"
4. Navigate to the project folder and select it
5. Wait for Gradle sync to complete
6. Click the Run button or press Shift+F10 to build and run

### Running the App
- Connect an Android device via USB or use an emulator
- Ensure USB debugging is enabled on the device
- Click the green Run button in Android Studio
- The app will install and launch on the device/emulator

## Usage
1. **Add Ingredients**: Go to Pantry, click "Add Ingredient", enter name, quantity, unit, and optional expiry date
2. **View Recipes**: Go to Recipes to see which recipes you can make with your current pantry
3. **Recipe Details**: Tap on a recipe to see full ingredients and instructions
4. **Edit/Delete**: Tap an ingredient to edit it, or use the Delete button to remove it
5. **Clear Data**: Go to Settings to clear all pantry items

## Recipe Matching
The app uses strict-matching:
- A recipe is ONLY suggested if you have ALL required ingredients
- Matching is case-insensitive and handles plural forms (e.g., "egg" matches "eggs")
- Quantity is not checked - having at least 1 of an ingredient is sufficient

Example: To make "Grilled Cheese Sandwich", you must have Bread, Cheese, and Butter in your pantry.

## Version Control
This project uses Git for version control with meaningful commit history showing incremental development progress.

## License
This project was created for educational purposes as part of the Mobile App Development 700 course.
