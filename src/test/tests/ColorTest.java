package tests;

import model.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// CLASS COMMENT:

public class ColorTest {

    Color black;
    Color darkRed;
    Color darkGreen;
    Color darkYellow;
    Color darkBlue;
    Color darkMagenta;
    Color darkCyan;
    Color darkWhite;
    Color brightBlack;
    Color brightRed;
    Color brightGreen;
    Color brightYellow;
    Color brightBlue;
    Color brightMagenta;
    Color brightCyan;
    Color white;

    @BeforeEach

    void colorTestSetup() {
        black = Color.BLACK;
        darkRed = Color.DARK_RED;
        darkGreen = Color.DARK_GREEN;
        darkYellow = Color.DARK_YELLOW;
        darkBlue = Color.DARK_BLUE;
        darkMagenta = Color.DARK_MAGENTA;
        darkCyan = Color.DARK_CYAN;
        darkWhite = Color.DARK_WHITE;
        brightBlack = Color.BRIGHT_BLACK;
        brightRed = Color.BRIGHT_RED;
        brightGreen = Color.BRIGHT_GREEN;
        brightYellow = Color.BRIGHT_YELLOW;
        brightBlue = Color.BRIGHT_BLUE;
        brightMagenta = Color.BRIGHT_MAGENTA;
        brightCyan = Color.BRIGHT_CYAN;
        white = Color.WHITE;
    }

    @Test
    // CASE: getColorCode() called on Color.BLACK
     void getColorCodeTestBlack() {
        assertEquals("\u001b[30m",
                black.getColorCode());
    }

    @Test
    // CASE: getColorCode() called on Color.DARK_RED
    void getColorCodeTestDarkRed() {
        assertEquals("\u001b[31m",
                darkRed.getColorCode());
    }

    @Test
    // CASE: getColorCode() called on Color.DARK_GREEN
    void getColorCodeTestDarkGreen() {
        assertEquals("\u001b[32m",
                darkGreen.getColorCode());
    }

    @Test
    // CASE: getColorCode() called on Color.DARK_YELLOW
    void getColorCodeTestDarkYellow() {
        assertEquals("\u001b[33m",
                darkYellow.getColorCode());
    }

    @Test
    // CASE: getColorCode() called on Color.DARK_BLUE
    void getColorCodeTestDarkBlue() {
        assertEquals("\u001b[34m",
                darkBlue.getColorCode());

    }

    @Test
    // CASE: getColorCode() called on Color.DARK_MAGENTA
    void getColorCodeTestDarkMagenta() {
        assertEquals("\u001b[35m",
                darkMagenta.getColorCode());

    }

    @Test
    // CASE: getColorCode() called on Color.DARK_CYAN
    void getColorCodeTestDarkCyan() {
        assertEquals("\u001b[36m",
                darkCyan.getColorCode());

    }

    @Test
    // CASE: getColorCode() called on Color.DARK_WHITE
    void getColorCodeTestDarkWhite() {
        assertEquals("\u001b[37m",
                darkWhite.getColorCode());

    }

    @Test
    // CASE: getColorCode() called on Color.BRIGHT_BLACK
    void getColorCodeTestBrightBlack() {
        assertEquals("\u001b[90m",
                brightBlack.getColorCode());
    }

    @Test
    // CASE: getColorCode() called on Color.BRIGHT_RED
    void getColorCodeTestBrightRed() {
        assertEquals("\u001b[91m",
                brightRed.getColorCode());

    }

    @Test
    // CASE: getColorCode() called on Color.BRIGHT_GREEN
    void getColorCodeTestBrightGreen() {
        assertEquals("\u001b[92m",
                brightGreen.getColorCode());

    }

    @Test
    // CASE: getColorCode() called on Color.BRIGHT_YELLOW
    void getColorCodeTestBrightYellow() {
        assertEquals("\u001b[93m",
                brightYellow.getColorCode());
    }

    @Test
    // CASE: getColorCode() called on Color.BRIGHT_BLUE
    void getColorCodeTestBrightBlue() {
        assertEquals("\u001b[94m",
                brightBlue.getColorCode());
    }

    @Test
    // CASE: getColorCode() called on Color.BRIGHT_MAGENTA
    void getColorCodeTestBrightMagenta() {
        assertEquals("\u001b[95m",
                brightMagenta.getColorCode());
    }

    @Test
    // CASE: getColorCode() called on Color.BRIGHT_CYAN
    void getColorCodeTestBrightCyan() {
        assertEquals("\u001b[96m",
                brightCyan.getColorCode());
    }

    @Test
    // CASE: getColorCode() called on Color.WHITE
    void getColorCodeTestWhite() {
        assertEquals("\u001b[97m",
                white.getColorCode());
    }

}
