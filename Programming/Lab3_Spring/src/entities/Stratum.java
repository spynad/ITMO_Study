package entities;

import enums.Era;

import java.util.Objects;

public class Stratum {
    private final Era era;
    private final String material;

    Stratum(Era era, String material) {
        this.era = era;
        this.material = material;
    }

    public Era getEra() {
        return era;
    }

    public String getMaterial() {
        return material;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stratum stratum = (Stratum) o;
        return getEra() == stratum.getEra();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEra());
    }

    @Override
    public String toString() {
        return "Stratum{" +
                "era=" + era +
                '}';
    }
}
