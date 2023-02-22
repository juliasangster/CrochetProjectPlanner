package model;

public enum Color {
    BLACK("\u001b[30m"),
    DARK_RED("\u001b[31m"),
    DARK_GREEN("\u001b[32m"),
    DARK_YELLOW("\u001b[33m"),
    DARK_BLUE("\u001b[34m"),
    DARK_MAGENTA("\u001b[35m"),
    DARK_CYAN("\u001b[36m"),
    DARK_WHITE("\u001b[37m"),
    BRIGHT_BLACK("\u001b[90m"),
    BRIGHT_RED("\u001b[91m"),
    BRIGHT_GREEN("\u001b[92m"),
    BRIGHT_YELLOW("\u001b[93m"),
    BRIGHT_BLUE("\u001b[94m"),
    BRIGHT_MAGENTA("\u001b[95m"),
    BRIGHT_CYAN("\u001b[96m"),
    WHITE("\u001b[97m");

    private String colorCode;

    private Color(String colorCode) {
        this.colorCode = colorCode;
    }


    public String getColorCode() {
        return this.colorCode;
    }

}