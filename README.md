# 2048 Puzzle Game

## About

The **2048 Puzzle Game** is a modern implementation of the classic sliding tile game where players combine numbers to reach the coveted 2048 tile. Developed using Java and JavaFX, this project showcases the integration of graphical interfaces and logical gameplay mechanics, providing an engaging and interactive experience.

Welcome to the **2048 Puzzle Game**! This is a Java-based implementation of the classic sliding puzzle game, enhanced with a graphical user interface using JavaFX.

---

## Project Structure

```
2048-Puzzle-Game/
├── src/                     # Source files
│   ├── Gui2048.java
│   ├── Board.java
│   ├── Constants2048.java
├── lib/                     # Libraries and dependencies
│   ├── javafx/              # JavaFX JARs
│   │   ├── javafx.base.jar
│   │   ├── javafx.controls.jar
│   │   ├── ...
│   ├── natives/             # Native files (optional)
│   │   ├── libdecora_sse.dylib
│   │   ├── libfxplugins.dylib
│   │   ├── ...
├── dist/                    # Packaged JAR file (generated during packaging)
│   ├── 2048.jar
├── run.sh                   # Script for Linux/macOS
├── run.bat                  # Script for Windows
├── README.md                # Project instructions (this file)
```

---

## Prerequisites

To run the game, you need:

1. **Java Runtime Environment (JRE):**
   - Version: 17 or higher
   - [Download Java](https://www.oracle.com/java/technologies/javase-downloads.html)

2. **JavaFX Libraries:**
   - Already included in the `lib/javafx` folder.

---

## How to Run

### Option 1: Using Scripts

- **Windows:**
  1. Double-click `run.bat`.
  2. Or, open a terminal and run:
     ```cmd
     run.bat
     ```

- **Linux/macOS:**
  1. Open a terminal and run:
     ```bash
     ./run.sh
     ```

### Option 2: Using Java Command

1. Open a terminal or command prompt.
2. Navigate to the project directory.
3. Run the following command:
   ```bash
   java --module-path lib/javafx --add-modules javafx.controls,javafx.fxml -jar dist/2048.jar
   ```

---

## Game Features

- **Interactive GUI:** Built with JavaFX, the game includes a responsive grid and smooth animations.
- **Save and Load Boards:** Save your progress and continue later.
- **Dynamic Resizing:** The game adjusts for larger board sizes.
- **Customizable Colors:** Tile colors change dynamically based on their value.

---

## How to Play

- Use **arrow keys** to move tiles:
  - Up, Down, Left, Right.
- Combine tiles with the same value to merge them (e.g., `2 + 2 = 4`).
- Reach the `2048` tile to win the game!
- Game ends when no moves are possible.

---

## Deployment

### Download

- [Download 2048 Puzzle Game (ZIP)](https://github.com/chw081/2048-Puzzle-Game/releases/download/v1.0.0/2048-Puzzle-Game.zip)

This project is packaged as an executable JAR. It can also be built into platform-specific executables using tools like `jpackage`.

To package for your platform:
```bash
jpackage --input dist --name 2048PuzzleGame --main-class Gui2048 --main-jar dist/2048.jar \
         --module-path lib/javafx --add-modules javafx.controls,javafx.fxml --output dist
```

---

## Troubleshooting

- **Error: Unable to find JavaFX runtime:**
  Ensure you include the JavaFX libraries using the `--module-path` and `--add-modules` flags.

- **Error: Unsupported Java Version:**
  Verify you have Java 17 or higher installed.

---

## License

This project is licensed under the MIT License. Feel free to use and modify the code.

---

## Author

- **Christine Wu**
