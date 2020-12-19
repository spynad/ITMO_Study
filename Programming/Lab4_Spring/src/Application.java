import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import creatures.*;
import entities.Expedition;
import entities.Stratum;
import enums.ExpeditionResults;
import enums.Location;
import exceptions.InvalidExperienceException;
import exceptions.NotEnoughPeopleException;
import items.Book;
import items.Picture;
import items.StudyMaterial;
import places.Base;
import places.Lab;
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
        Mind mind  = ctx.getBean(Mind.class);
        AncientStar ancientStar = ctx.getBean("ancientStar", AncientStar.class);
        Creature creature = ctx.getBean(Creature.class);
        Stratum stratum = ctx.getBean(Stratum.class);
        Stratum.Stalagmite stalagmite = ctx.getBean(Stratum.Stalagmite.class);
        Expedition expedition = ctx.getBean(Expedition.class);
        Sleigh[] sleighs = ctx.getBean(Sleigh[].class);
        Dog[] dogs = ctx.getBean(Dog[].class);
        Base base = ctx.getBean(Base.class);
        Lab lab = ctx.getBean(Lab.class);

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

        narrator.talkAbout(Elder.description());
        mind.setHurt(true);

        mind.transformFantastically(ancientStar);

        creature.setFromPrehistoricFolk(true);
        humans[2].writeAbout(creature);
        narrator.remember("Acolytes of Cthulhu");
        narrator.say(stratum.toString() + " belongs to " + stratum.getEra());
        narrator.chipOffStalagmite(stalagmite);
        narrator.surprise("Everything is well preserved here because of the stratum consists of " + stratum.getMaterial());


        for (int i = 0; i < 11; i++) {
            dogs[0].bark();
        }

        for (int i = 0; i < dogs.length; i++) {
            humans[i+6].watchOverADog(dogs[i]);
        }

        sleighs[0].prepareSleigh(narrator, dyer, pebody, elders[0]);
        sleighs[1].prepareSleigh(humans[0], humans[1], humans[2], elders[1]);
        sleighs[2].prepareSleigh(humans[3], humans[4], humans[5], elders[2]);

        try {
            sleighs[0].sendToBay();
            sleighs[1].sendToBay();
            sleighs[2].sendToBay();
        } catch (NotEnoughPeopleException e) {
            System.out.println(e.getMessage());
            return;
        }


        for (int i = 3; i < elders.length; i += 3) {
            sleighs[0].addCargo(elders[i]);
            sleighs[1].addCargo(elders[i + 1]);
            if(i != 12)
                sleighs[2].addCargo(elders[i + 2]);

            try {
                sleighs[0].sendToBay();
                sleighs[1].sendToBay();
                sleighs[2].sendToBay();
            } catch (NotEnoughPeopleException e) {
                System.out.println(e.getMessage());
                return;
            }

            sleighs[0].returnBack();
            sleighs[1].returnBack();
            sleighs[2].returnBack();
        }

        base.connect();

        for (Elder elder : elders) {
            narrator.transport(Location.SHIP, elder);
        }

        narrator.dissect(elders[0]);
        narrator.sleep();
        if(lab.isFake()) {
            narrator.say("Too bad there is no real laboratory here");
        }

        dyer.shame("He objected to an expedition to the west");

        narrator.makeADiscovery("The highest mountain");
        narrator.makeADiscovery("Elders");
        expedition.setExpRes(ExpeditionResults.SUPER_SUCCESS);
        narrator.giveThanks(pebody, "drilling device");
        narrator.say("Repeat verbatim description of the found individuals");
    }


}
