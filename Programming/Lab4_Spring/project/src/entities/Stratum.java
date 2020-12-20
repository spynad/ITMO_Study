package entities;

import enums.Era;

import java.util.Objects;

public class Stratum {
    private final Era era;
    private final String material;

    public Stratum(Era era, String material) {
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

    public static class Stalagmite {
        private final boolean isMassive;
        private boolean chippedOff;
        private final int strength;
        private int damage;

        public Stalagmite(boolean massive, int strength, boolean chippedOff) {
            this.isMassive = massive;
            this.strength = strength;
            this.chippedOff = chippedOff;
        }

        public int getStrength() {
            return strength;
        }

        public boolean isMassive() {
            return isMassive;
        }

        public boolean isChippedOff() {
            return chippedOff;
        }

        public void takeDamage(int damage) {
            this.damage += damage;

            if (!chippedOff) {
                if (this.damage >= strength) {
                    chippedOff = true;
                    System.out.println("The stalagmite got chipped off");
                }
            }
            else {
                System.out.println("The stalagmite is already chipped off");
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Stalagmite that = (Stalagmite) o;
            return isMassive() == that.isMassive() && isChippedOff() == that.isChippedOff() && getStrength() == that.getStrength() && damage == that.damage;
        }

        @Override
        public int hashCode() {
            return Objects.hash(isMassive(), isChippedOff(), getStrength(), damage);
        }

        @Override
        public String toString() {
            return "Stalagmite{" +
                    "isMassive=" + isMassive +
                    ", chippedOff=" + chippedOff +
                    ", strength=" + strength +
                    ", damage=" + damage +
                    '}';
        }
    }
}
