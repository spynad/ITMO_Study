import creatures.*;
import entities.Expedition;
import entities.Stratum;
import enums.Era;
import enums.Origin;
import enums.PlaceOfLiving;
import items.Book;
import items.Picture;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import places.Base;
import places.Lab;
import transport.Sleigh;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public Elder[] beasts() {
        Elder[] elders = new Elder[14];
        Origin elderOrigin;
        if(Math.random() > 0.2) {
            elderOrigin = Origin.MARINE;
            System.out.println("The elders are of marine origin.");
        } else {
            elderOrigin = Origin.TERRESTRIAL;
            System.out.println("The elders are of terrestrial origin.");
        }
        for (int i = 0; i < elders.length; i++) {
            elders[i] = new Elder("Elder" + i);
            elders[i].changeOrigin(elderOrigin);
        }

        return elders;
    }

    @Bean
    public Narrator narrator() {
        return new Narrator("Narrator");
    }

    @Bean
    public UnicellularOrganism org1() {
        return new UnicellularOrganism("org1");
    }

    @Bean("dyer")
    public Human dyer() {
        return new Human("Dyer");
    }

    @Bean("pebody")
    public Human pebody() {
        return new Human("Pebody");
    }

    @Bean("wilmart")
    public Human wilmart() {
        return new Human("Wilmart");
    }

    @Bean
    public Picture pic() {
        return new Picture("Picture", "Clark Ashton Smith", "Scary");
    }

    @Bean
    public Book book() {
        return new Book("Necronomicon");
    }

    @Bean
    public Mind mind() {
        return new Mind();
    }

    @Bean
    public AncientStar ancientStar() {
        return new AncientStar("star", true, Origin.MARINE, PlaceOfLiving.UNKNOWN, false);
    }
    @Bean
    public Human[] humans() {
        Human[] humans = new Human[9];
        for (int i = 0; i < humans.length; i++) {
            humans[i] = new Human("Human" + i);
        }
        return humans;
    }

    @Bean
    public Creature creature() {
        return new Creature("Creature");
    }

    @Bean
    public Stratum stratum() {
        if(Math.random() > 0.5)
            return new Stratum(Era.LATE_MESOZOIC, "Limestone");
        else
            return new Stratum(Era.LATE_CENOZOIC, "Limestone");
    }

    @Bean
    public Stratum.Stalagmite stalagmite() {
        return new Stratum.Stalagmite(true, 1500, false);
    }

    @Bean
    public Expedition expedition() {
        return new Expedition();
    }

    @Bean
    public Sleigh[] sleighs() {
        Sleigh[] sleighs = new Sleigh[3];
        for (int i = 0; i < sleighs.length; i++) {
            sleighs[i] = new Sleigh("Sleigh" + i);
        }
        return sleighs;
    }

    @Bean
    public Dog[] dogs() {
        Dog[] dogs = new Dog[3];
        for (int i = 0; i < dogs.length; i++) {
            dogs[i] = new Dog("Dog" + i);
        }
        return dogs;
    }

    @Bean
    public Base base() {
        return new Base();
    }

    @Bean
    public Lab lab() {
        return new Lab(true);
    }
}
