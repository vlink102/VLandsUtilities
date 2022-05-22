package net.vlands.util;

public class MinecraftName {

    private String name;
    private String lowerCase;

    public MinecraftName(String name) {
        this.name = name;
        this.lowerCase = name.toLowerCase();
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MinecraftName)) return false;
        MinecraftName mcName = ((MinecraftName) o);
        return mcName.lowerCase.equals(this.lowerCase);
    }

    @Override
    public int hashCode() {
        return this.lowerCase.hashCode();
    }
}
