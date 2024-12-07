///////////////////////////////////////////////////////////////////////////////
// Main Class File:    Gui2048
// File:               Constans2048.java
// Quarter:            Winter 2020
//
// Author:             Rachel Chung, Juan Dominguez
// Instructor's Name:  Christine Alvarado, Greg Miranda
//
//////////////////////////// 80 columns wide //////////////////////////////////

import javafx.application.*;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.text.*;
import javafx.geometry.*;
import java.util.*;
import java.io.*;

/**
 * Defines constants that are used for 2048 GUI.
 * This file is to be used only in PA5 of CSE8B in Winter 2021.
 *
 * Bugs: None known
 *
 * @author Juan Dominguez, Rachel Chung
 */
public class Constants2048 {

    /* Width of each tile */
    public static final int TILE_WIDTH = 106;

    /* Text size for low value tiles (2, 4, 8) */
    public static final int TEXT_SIZE_LOW = 55;

    /* Text size for mid value tiles (128, 256, 512) */
    public static final int TEXT_SIZE_MID = 45;

    /* Text size for high value tiles 1024, 2048, Higher) */
    public static final int TEXT_SIZE_HIGH = 35;

    /* Tile colors for each of the tile values */
    public static final Color COLOR_EMPTY = Color.rgb(238, 228, 218, 0.35);
    public static final Color COLOR_2 = Color.rgb(238, 228, 218);
    public static final Color COLOR_4 = Color.rgb(237, 224, 200);
    public static final Color COLOR_8 = Color.rgb(242, 177, 121);
    public static final Color COLOR_16 = Color.rgb(245, 149, 99);
    public static final Color COLOR_32 = Color.rgb(246, 124, 95);
    public static final Color COLOR_64 = Color.rgb(246, 94, 59);
    public static final Color COLOR_128 = Color.rgb(237, 207, 114);
    public static final Color COLOR_256 = Color.rgb(237, 204, 97);
    public static final Color COLOR_512 = Color.rgb(237, 200, 80);
    public static final Color COLOR_1024 = Color.rgb(237, 197, 63);
    public static final Color COLOR_2048 = Color.rgb(237, 194, 46);
    public static final Color COLOR_OTHER = Color.BLACK;
    public static final Color COLOR_GAME_OVER = Color.rgb(238, 228, 218, 0.73);

    /* Text colors for tiles with value >= 8  */
    public static final Color COLOR_VALUE_LIGHT = Color.rgb(249, 246, 242); 
                        
    /* Text colors for tiles with value < 8  */
    public static final Color COLOR_VALUE_DARK = Color.rgb(119, 110, 101);  
}