package net.vlands.util.cooldown;

import lombok.Getter;

@Getter
public class Cooldown {
    private final Long duration;
    private final Long lastUsed;

    /**
     * @param duration The cooldown duration (in ms)
     * @param lastUsed The cooldown start point (System milliseconds)
     */
    public Cooldown(Long duration, Long lastUsed) {
        this.duration = duration;
        this.lastUsed = lastUsed;
    }
}
