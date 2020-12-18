import creatures.*;
import entities.Expedition;
import entities.Stratum;
import exceptions.InvalidExperienceException;
import items.Book;
import items.Picture;
import items.StudyMaterial;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import transport.Sleigh;

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
        Sleigh[] sleighs = ctx.getBean(Sleigh[].class);

        for (Human human : humans) {
            expedition.addToExpedition(human);
        }

        narrator.thinkAboutElderOrigin(elders[0], org1);

        for (Elder elder : elders) {
            if (Math.random() > 0.5) {
                elder.setCorruptState(true);
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

        //TODO: гооооооовноооооооо коооооооооооооооод, исправь плз на это больно смотреть прошу умоляю
        sleighs[0].addHelper(narrator);
        sleighs[0].addHelper(dyer);
        sleighs[0].addHelper(pebody);
        sleighs[0].addCargo(elders[0]);

        sleighs[1].addHelper(humans[0]);
        sleighs[1].addHelper(humans[1]);
        sleighs[1].addHelper(humans[2]);
        sleighs[1].addCargo(elders[1]);

        sleighs[2].addHelper(humans[3]);
        sleighs[2].addHelper(humans[4]);
        sleighs[2].addHelper(humans[5]);
        sleighs[2].addCargo(elders[2]);

        sleighs[0].sendToBay();
        sleighs[1].sendToBay();
        sleighs[2].sendToBay();

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
