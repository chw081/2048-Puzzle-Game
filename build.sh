#!/bin/bash
# Recompile
javac -d build --module-path lib/javafx --add-modules javafx.controls,javafx.fxml src/*.java
# Repackage
jar cfe dist/2048.jar Gui2048 -C build .
# Test
java --module-path lib/javafx --add-modules javafx.controls,javafx.fxml -jar dist/2048.jar
