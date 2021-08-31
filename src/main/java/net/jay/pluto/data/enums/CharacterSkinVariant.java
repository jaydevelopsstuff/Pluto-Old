package net.jay.pluto.data.enums;

/**
 * All the skin variants, ordered as they are in the character creation screen
 * @author Jay
 */
public enum CharacterSkinVariant {
    //
    // Male
    //

    /** The 1st male option in the character creation, you could call this the default male option */
    MaleBeltLongPants(0),
    MaleNormalShirtPants(2),
    MaleNoBeltLongPants(1),
    MaleRobeNormalShoes(3),
    MaleRobeOpenShoes(8),

    //
    // Female
    //

    /** The 1st female option in the character creation, you could call this the default female option */
    FemaleShorts(4),
    FemaleNormalShirtPants(6),
    FemaleLongPants(5),
    FemaleRobeNormalShoes(7),
    FemaleRobeStylish(9);

    /** The "ID" of the skin variant, these are pretty wonky and don't seem to have any specific pattern */
    public final int ID;

    CharacterSkinVariant(int ID) {
        this.ID = ID;
    }
}
