package net.jay.pluto.world.tracking;

public class SavedTracker {
    public static final int DefaultArrayLength = 8;

    /**
     * Order: Angler, Goblin, Mechanic, Golfer, Bartender, Wizard, Stylist, Tax Collector
     */
    private boolean[] savedData;

    public SavedTracker() {
        savedData = new boolean[DefaultArrayLength];
    }

    public SavedTracker(boolean[] savedData) {
        this.savedData = savedData;
    }

    public boolean isAnglerSaved() {
        return savedData[0];
    }

    public boolean isGoblinSaved() {
        return savedData[1];
    }

    public boolean isMechanicSaved() {
        return savedData[2];
    }

    public boolean isGolferSaved() {
        return savedData[3];
    }

    public boolean isBartenderSaved() {
        return savedData[4];
    }

    public boolean isWizardSaved() {
        return savedData[1];
    }

    public boolean isStylistSaved() {
        return savedData[1];
    }

    public boolean isTaxCollectorSaved() {
        return savedData[7];
    }

    public void setAnglerSaved(boolean saved) {
        savedData[0] = saved;
    }

    public void setGoblinSaved(boolean saved) {
        savedData[1] = saved;
    }

    public void setMechanicSaved(boolean saved) {
        savedData[2] = saved;
    }

    public void setGolferSaved(boolean saved) {
        savedData[3] = saved;
    }

    public void setBartenderSaved(boolean saved) {
        savedData[4] = saved;
    }

    public void setWizardSaved(boolean saved) {
        savedData[5] = saved;
    }

    public void setStylistSaved(boolean saved) {
        savedData[6] = saved;
    }

    public void setTaxCollectorSaved(boolean saved) {
        savedData[7] = saved;
    }
}
