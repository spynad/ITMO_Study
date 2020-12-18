import creatures.*;
import entities.Expedition;
import entities.Stratum;
import exceptions.InvalidExperienceException;
import items.Book;
import items.Picture;
import items.StudyMaterial;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        Elder[] elders = ctx.getBean(Elder[].class);
        Narrator narrator = ctx.getBean(Narrator.class);
        UnicellularOrganism org1 = ctx.getBean(UnicellularOrganism.class);
        Human dyer = ctx.getBean("dyer", Human.class);
        Human pebody = ctx.getBean("pebody", Human.class);
        Human[] humans = ctx.getBean(Human[].class);
        Book book = ctx.getBean(Book.class);
        Picture pic = ctx.getBean(Picture.class);
        ElderLegacy elderLegacy = ctx.getBean("elder", ElderLegacy.class);
        Mind mind  = ctx.getBean(Mind.class);
        AncientStar ancientStar = ctx.getBean("ancientStar", AncientStar.class);
        Creature creature = ctx.getBean(Creature.class);
        Stratum stratum = ctx.getBean(Stratum.class);
        Stratum.Stalagmite stalagmite = ctx.getBean(Stratum.Stalagmite.class);
        Expedition expedition = ctx.getBean(Expedition.class);

        for (Human human : humans) {
            expedition.addToExpedition(human);
        }

        narrator.thinkAboutElderOrigin(elders[0], org1);

        for (int i = 0; i < elders.length; i++) {
            if (Math.random() > 0.5) {
                elders[i].setCorruptState(true);
            }
        }

        
        elders = narrator.compareAncientWithElders(elders);

        //human[0] - Dyer; human[1] - Pebody, human[2] - Wilmart
        humans[0].read(book);
        humans[1].read(book);

        humans[0].lookAt(pic);
        humans[1].lookAt(pic);

        narrator.talkAbout(elderLegacy);
        mind.setHurt(true);

        mind.transformFantastically(ancientStar);

        creature.setFromPrehistoricFolk(true);
        humans[2].writeAbout(creature);
        narrator.remember("Acolytes of Cthulhu");
        narrator.say(stratum.toString() + " belongs to " + stratum.getEra());
        narrator.chipOffStalagmite(stalagmite);
        narrator.surprise("Everything is well preserved here because of the stratum consists of " + stratum.getMaterial());

        /*try {
            StudyMaterial sm = new StudyMaterial(-1000);
            humans[0].studyMaterial(sm);
        } catch(InvalidExperienceException e) {
            System.out.println(e.getMessage() + e.getExp());
            return;
        }*/

        StudyMaterial studyMaterial = new StudyMaterial(-200);
    }

}
