@echo off
REM Recompile
javac -d build --module-path lib/javafx --add-modules javafx.controls,javafx.fxml src\*.java
REM Repackage
jar cfe dist\2048.jar Gui2048 -C build .
REM Test
java --module-path lib\javafx --add-modules javafx.controls,javafx.fxml -jar dist\2048.jar
pause
