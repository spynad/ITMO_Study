public class Animal extends MulticellularOrganism {
    Animal(String name) {
        super(name);
        setPartsLocation(true);
        setDirection(Direction.HORIZONTAL);
    }
}
