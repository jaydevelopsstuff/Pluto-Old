package net.jay.pluto.util;

public class TColor {
    private int red;
    private int green;
    private int blue;
    private int alpha;

    public TColor(byte red, byte green, byte blue) {
        if(!areInputsValid(red & 0xFF, green & 0xFF, blue & 0xFF)) throw new IllegalArgumentException("RGB can only be from 0 - 255");
        this.red = red & 0xFF;
        this.green = green & 0xFF;
        this.blue = blue & 0xFF;
        this.alpha = 255;
    }

    public TColor(byte red, byte green, byte blue, byte alpha) {
        if(!areInputsValid(red & 0xFF, green & 0xFF, blue & 0xFF, alpha & 0xFF)) throw new IllegalArgumentException("RGBA can only be from 0 - 255");
        this.red = red & 0xFF;
        this.green = green & 0xFF;
        this.blue = blue & 0xFF;
        this.alpha = alpha & 0xFF;
    }

    public TColor(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = 255;
    }

    public TColor(int red, int green, int blue, int alpha) {
        this.red = (byte)red;
        this.green = (byte)green;
        this.blue = (byte)blue;
        this.alpha = (byte)alpha;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public static boolean isInputValid(int input) {
        return input >= 0 && input <= 255;
    }

    public static boolean areInputsValid(int... integers) {
        for(int i : integers) {
            if(!isInputValid(i)) return false;
        }
        return true;
    }
}
