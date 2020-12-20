package items;

import exceptions.InvalidExperienceException;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class StudyMaterialTest {

    @Test
    public void getExpAmount() {
        int expected = 500;
        StudyMaterial studyMaterial = new StudyMaterial(500);
        int actual = studyMaterial.getExpAmount();
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = InvalidExperienceException.class)
    public void getExpAmount_NEGATIVE() {
        StudyMaterial studyMaterial = new StudyMaterial(-500);
    }
}