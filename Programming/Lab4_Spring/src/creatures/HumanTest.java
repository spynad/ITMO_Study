package creatures;

import items.StudyMaterial;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class HumanTest {

    @Test
    public void updateLevel() {
        Human human = new Human("hum1");
        StudyMaterial studyMaterial = new StudyMaterial(500);

        int expected = 3;

        human.studyMaterial(studyMaterial);
        int actual = human.getLvl();

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void updateLevel_LVL100() {
        Human human = new Human("hum1");
        StudyMaterial studyMaterial = new StudyMaterial(495000);

        int expected = 100;

        human.studyMaterial(studyMaterial);
        int actual = human.getLvl();

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void updateLevel_LIMIT() {
        Human human = new Human("hum1");
        StudyMaterial studyMaterial = new StudyMaterial(500000);

        int expected = 100;

        human.studyMaterial(studyMaterial);
        int actual = human.getLvl();

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void updateLevel_NOLVLUP() {
        Human human = new Human("hum1");
        StudyMaterial studyMaterial = new StudyMaterial(99);

        int expected = 1;

        human.studyMaterial(studyMaterial);
        int actual = human.getLvl();

        Assert.assertEquals(expected, actual);

    }

}