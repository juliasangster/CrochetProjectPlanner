package tests;

import model.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

     void getColorCodeTestBlack() {
        assertEquals("\u001b[30m",
                black.getColorCode());
    }

    @Test

    void getColorCodeTestDarkRed() {
        assertEquals("\u001b[31m",
                darkRed.getColorCode());
    }

    @Test

    void getColorCodeTestDarkGreen() {
        assertEquals("\u001b[32m",
                darkGreen.getColorCode());
    }

    @Test

    void getColorCodeTestDarkYellow() {
        assertEquals("\u001b[33m",
                darkYellow.getColorCode());
    }

    @Test

    void getColorCodeTestDarkBlue() {
        assertEquals("\u001b[34m",
                darkBlue.getColorCode());

    }

    @Test

    void getColorCodeTestDarkMagenta() {
        assertEquals("\u001b[35m",
                darkMagenta.getColorCode());

    }

    @Test

    void getColorCodeTestDarkCyan() {
        assertEquals("\u001b[36m",
                darkCyan.getColorCode());

    }

    @Test

    void getColorCodeTestDarkWhite() {
        assertEquals("\u001b[37m",
                darkWhite.getColorCode());

    }

    @Test

    void getColorCodeTestBrightBlack() {
        assertEquals("\u001b[90m",
                brightBlack.getColorCode());
    }

    @Test

    void getColorCodeTestBrightRed() {
        assertEquals("\u001b[91m",
                brightRed.getColorCode());

    }

    @Test

    void getColorCodeTestBrightGreen() {
        assertEquals("\u001b[92m",
                brightGreen.getColorCode());

    }

    @Test

    void getColorCodeTestBrightYellow() {
        assertEquals("\u001b[93m",
                brightYellow.getColorCode());
    }

    @Test

    void getColorCodeTestBrightBlue() {
        assertEquals("\u001b[94m",
                brightBlue.getColorCode());
    }

    @Test

    void getColorCodeTestBrightMagenta() {
        assertEquals("\u001b[95m",
                brightMagenta.getColorCode());
    }

    @Test

    void getColorCodeTestBrightCyan() {
        assertEquals("\u001b[96m",
                brightCyan.getColorCode());
    }

    @Test

    void getColorCodeTestWhite() {
        assertEquals("\u001b[97m",
                white.getColorCode());
    }

}
