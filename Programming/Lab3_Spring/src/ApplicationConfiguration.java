import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public Elder[] beasts() {
        Elder[] elders = new Elder[5];
        Origin beastOrigin;
        if(Math.random() > 0.2) {
            beastOrigin = Origin.MARINE;
            System.out.println("The beasts are of marine origin.");
        } else {
            beastOrigin = Origin.TERRESTRIAL;
            System.out.println("The beasts are of terrestrial origin.");
        }
        for (int i = 0; i < elders.length; i++) {
            elders[i] = new Elder("Beast" + i, wing());
            elders[i].changeOrigin(beastOrigin);
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

    @Bean
    public Human dyer() {
        return new Human("Dyer");
    }

    @Bean
    public Human pebody() {
        return new Human("Pebody");
    }

    @Bean
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
    public ElderLegacy elder() {
        return new ElderLegacy("elder");
    }

    @Bean
    public Mind mind() {
        return new Mind();
    }

    @Bean
    public AncientStar ancientStar() {
        return new AncientStar("star", true, Origin.MARINE, false);
    }

    @Bean
    public Human[] humans() {
        Human[] humans = new Human[3];
        humans[0] = dyer();
        humans[1] = pebody();
        humans[2] = wilmart();
        return humans;
    }

    @Bean
    public Wing wing() {
        return new Wing("Wing");
    }

    @Bean
    public Creature creature() {
        return new Creature("Creature");
    }


}
