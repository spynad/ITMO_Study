package entities;

import creatures.Human;
import enums.ExpeditionResults;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Expedition {
    private final ArrayList<Human> expeditionMembers = new ArrayList<>();
    private ExpeditionResults expRes;

    public List<Human> getExpeditionMembers() {
        return expeditionMembers;
    }

    public ExpeditionResults getExpRes() {
        return expRes;
    }

    public void setExpRes(ExpeditionResults expRes) {
        switch (expRes){
            case FAILURE:
                System.out.println("The expedition is a failure");
                break;
            case SUCCESS:
                System.out.println("The expedition is a success");
                break;
            case SUPER_SUCCESS:
                System.out.println("The expedition is a success; Such finds would do " +
                    "honor to any expedition. A great contribution to science has been made.");
                break;
        }
    }

    public void addToExpedition(Human human) {
        expeditionMembers.add(human);
        System.out.println(human.getName() + " has been successfully added to expedition");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expedition that = (Expedition) o;
        return Objects.equals(getExpeditionMembers(), that.getExpeditionMembers()) && getExpRes() == that.getExpRes();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getExpeditionMembers(), getExpRes());
    }

    @Override
    public String toString() {
        return "Expedition{" +
                "expeditionMembers=" + expeditionMembers +
                ", expRes=" + expRes +
                '}';
    }
}
