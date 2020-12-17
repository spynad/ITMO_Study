package entities;

import creatures.Human;
import enums.ExpeditionResults;

import java.util.ArrayList;

public class Expedition {
    private ArrayList<Human> expeditionMembers = new ArrayList<>();
    private ExpeditionResults expRes;

    public ArrayList<Human> getExpeditionMembers() {
        return expeditionMembers;
    }

    public ExpeditionResults getExpRes() {
        return expRes;
    }

    public void setExpRes(ExpeditionResults expRes) {
        switch (expRes){
            case FAILURE -> System.out.println("The expedition is a failure");
            case SUCCESS -> System.out.println("The expedition is a success");
            case SUPER_SUCCESS -> System.out.println("The expedition is a success; Such finds would do " +
                    "honor to any expedition. A great contribution to science has been made.");
        }
    }

    public void addToExpedition(Human human) {
        expeditionMembers.add(human);
        System.out.println(human.getName() + "has been successfully added to expedition");
    }
}
