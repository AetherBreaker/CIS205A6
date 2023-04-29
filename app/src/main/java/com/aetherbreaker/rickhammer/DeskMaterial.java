package com.aetherbreaker.rickhammer;

public enum DeskMaterial {
    MAHOGANY(150, "Mahogany"),
    OAK(125, "Oak"),
    PINE(0, "Pine");


    private final int price;

    DeskMaterial(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
    private String text;

    DeskMaterial(int price, String text) {
        this.price = price;
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static DeskMaterial fromString(String text) {
        for (DeskMaterial b : DeskMaterial.values()) {
            if (b.text.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}