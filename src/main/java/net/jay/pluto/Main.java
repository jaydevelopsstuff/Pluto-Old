package net.jay.pluto;

import net.jay.pluto.util.AsyncHelper;

public class Main {
    public static void main(String[] args) {
        new AsyncHelper<String>().runAsync(() -> { return "woah"; }, (response) -> {});
    }
}
