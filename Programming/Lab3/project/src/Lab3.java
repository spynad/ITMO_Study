public class Lab3 {
    public static void main(String[] args) {
        Beast[] beasts = new Beast[5];
        Narrator narrator = new Narrator("Narrator");
        UnicellularOrganism org2 = new UnicellularOrganism("org2");
        Origin beastOrigin;

        if(Math.random() > 0.2) {
            beastOrigin = Origin.MARINE;
            System.out.println("The beasts are of marine origin.");
        } else {
            beastOrigin = Origin.TERRESTRIAL;
            System.out.println("The beasts are of terrestrial origin.");
        }
        for (int i = 0; i < beasts.length; i++) {
            beasts[i] = new Beast("Beast" + i);
            beasts[i].changeOrigin(beastOrigin);
        }

        narrator.thinkAboutBeastOrigin(beasts[0], org2);

        for (int i = 0; i < beasts.length; i++) {
            if (Math.random() > 0.5) {
                beasts[i].setCorruptState(true);
            }
        }

        beasts = narrator.compareAncientWithBeasts(beasts);

        Human Dyer = new Human("Dyer");
        Human Pebody = new Human("Pebody");

        Book book = new Book("Necronomicon");

        Dyer.read(book);
        Pebody.read(book);

        Picture pic = new Picture("Picture", "Clark Ashton Smith");

        Dyer.lookAt(pic);
        Pebody.lookAt(pic);

        Elder elder = new Elder();

        narrator.talkAbout(elder);
        narrator.remember("Acolytes of Cthulhu");


    }
}
