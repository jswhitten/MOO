package org.whitten.MOO.object;

/**
 *
 * @author Jed Whitten <jed@whitten.org>
 */
public class ObjectFlags {
    private Boolean player = false;
    private Boolean programmer = false;
    private Boolean wizard = false;

    public ObjectFlags() {
    }

    public ObjectFlags(Boolean player, Boolean programmer, Boolean wizard) {
        this.player = player;
        this.programmer = programmer;
        this.wizard = wizard;
    }

    public Boolean getPlayer() {
        return player;
    }

    public void setPlayer(Boolean player) {
        this.player = player;
    }

    public Boolean getProgrammer() {
        return programmer;
    }

    public void setProgrammer(Boolean programmer) {
        // TODO - check player flag
        this.programmer = programmer;
    }

    public Boolean getWizard() {
        return wizard;
    }

    public void setWizard(Boolean wizard) {
        // TODO - check player flag
        this.wizard = wizard;
    }
}
