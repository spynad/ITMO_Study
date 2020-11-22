import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        Beast[] beasts = ctx.getBean(Beast[].class);
        Narrator narrator = ctx.getBean(Narrator.class);
        UnicellularOrganism org1 = ctx.getBean(UnicellularOrganism.class);
        Human[] humans = ctx.getBean(Human[].class);
        Book book = ctx.getBean(Book.class);
        Picture pic = ctx.getBean(Picture.class);
        Elder elder = ctx.getBean("elder", Elder.class);
        Mind mind  = ctx.getBean(Mind.class);
        AncientStar ancientStar = ctx.getBean("ancientStar", AncientStar.class);
        Creature creature = ctx.getBean(Creature.class);

        narrator.thinkAboutBeastOrigin(beasts[0], org1);

        for (int i = 0; i < beasts.length; i++) {
            if (Math.random() > 0.5) {
                beasts[i].setCorruptState(true);
            }
        }

        beasts = narrator.compareAncientWithBeasts(beasts);

        //human[0] - Dyer; human[1] - Pebody
        humans[0].read(book);
        humans[1].read(book);

        humans[0].lookAt(pic);
        humans[1].lookAt(pic);

        narrator.talkAbout(elder);
        mind.setHurt(true);

        mind.transformFantastically(ancientStar);

        creature.setFromPrehistoricFolk(true);
        humans[2].writeAbout(creature);
        narrator.remember("Acolytes of Cthulhu");
    }

}
