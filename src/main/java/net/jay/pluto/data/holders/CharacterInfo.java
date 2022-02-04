package net.jay.pluto.data.holders;

import lombok.Getter;
import lombok.Setter;
import net.jay.pluto.data.enums.CharacterSkinVariant;
import net.jay.pluto.util.TColor;

@Getter
@Setter
public class CharacterInfo {
    private CharacterSkinVariant skinVariant;
    private int hairType;
    private TColor hairColor;
    private TColor skinColor;
    private TColor eyeColor;
    private TColor shirtColor;
    private TColor underShirtColor;

    public CharacterInfo(CharacterSkinVariant skinVariant, int hairType, TColor hairColor, TColor skinColor, TColor eyeColor, TColor shirtColor, TColor underShirtColor) {
        this.skinVariant = skinVariant;
        this.hairType = hairType;
        this.hairColor = hairColor;
        this.skinColor = skinColor;
        this.eyeColor = eyeColor;
        this.shirtColor = shirtColor;
        this.underShirtColor = underShirtColor;
    }

    public CharacterSkinVariant getSkinVariant() {
        return skinVariant;
    }

    public void setSkinVariant(CharacterSkinVariant skinVariant) {
        this.skinVariant = skinVariant;
    }

    public int getHairType() {
        return hairType;
    }

    public void setHairType(int hairType) {
        this.hairType = hairType;
    }

    public TColor getHairColor() {
        return hairColor;
    }

    public void setHairColor(TColor hairColor) {
        this.hairColor = hairColor;
    }

    public TColor getSkinColor() {
        return skinColor;
    }

    public void setSkinColor(TColor skinColor) {
        this.skinColor = skinColor;
    }

    public TColor getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(TColor eyeColor) {
        this.eyeColor = eyeColor;
    }

    public TColor getShirtColor() {
        return shirtColor;
    }

    public void setShirtColor(TColor shirtColor) {
        this.shirtColor = shirtColor;
    }

    public TColor getUnderShirtColor() {
        return underShirtColor;
    }

    public void setUnderShirtColor(TColor underShirtColor) {
        this.underShirtColor = underShirtColor;
    }
}
