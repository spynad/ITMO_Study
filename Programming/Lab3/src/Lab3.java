public class Lab3 {
    public static void main(String[] args) {
        Beast beasts[] = new Beast[5];
        Origin beastOrigin;

        if(Math.random() > 0.2) {
            beastOrigin = Origin.MARINE;
            System.out.println("The beasts are of marine origin.");
        } else {
            beastOrigin = Origin.TERRESTRIAL;
            System.out.println("The beasts are of TERRESTRIAL origin.");
        }
        for (int i = 0; i < 5; i++) {
            beasts[i] = new Beast("Beast" + i);
            beasts[i].changeOrigin(beastOrigin);
        }

        beasts[0].swim();
    }
}
