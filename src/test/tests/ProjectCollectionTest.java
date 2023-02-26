package tests;

import model.Graphghan;
import model.ProjectCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// CLASS COMMENT: Testing suite for model.ProjectCollection class

public class ProjectCollectionTest {

    ProjectCollection projectCollection;

    final static String TEST_STRING_1 = "Test";
    final static String TEST_STRING_2 = "WideBlanket";
    final static String TEST_STRING_3 = "NarrowBlanket";
    final static String TEST_STRING_4 = "Quiz";

    final static int TEST_ROW_1 = 10;
    final static int TEST_COL_1 = 10;

    final static int TEST_ROW_2 = 10;
    final static int TEST_COL_2 = 15;

    final static int TEST_ROW_3 = 20;
    final static int TEST_COL_3 = 10;


    @BeforeEach
    void projectCollectionTestSetup() {
        projectCollection = new ProjectCollection();
    }

    @Test

    void addProjectTestEmptyListSingle() {
        boolean result = projectCollection.addProject(TEST_STRING_1,TEST_ROW_1,TEST_COL_1);
        assertTrue(result);

        Graphghan g = projectCollection.getGraphghanSpecificName(TEST_STRING_1);

        assertEquals(g.getName(),TEST_STRING_1);
        assertEquals(g.getRows(), TEST_ROW_1);
        assertEquals(g.getColumns(), TEST_COL_1);

    }

    @Test

    void addProjectTestEmptyListMultiple() {
        boolean result1 = projectCollection.addProject(TEST_STRING_1,TEST_ROW_1,TEST_COL_1);
        boolean result2 = projectCollection.addProject(TEST_STRING_2,TEST_ROW_2,TEST_COL_2);
        assertTrue(result1);
        assertTrue(result2);

        Graphghan g1 = projectCollection.getGraphghanSpecificName(TEST_STRING_1);
        Graphghan g2 = projectCollection.getGraphghanSpecificName(TEST_STRING_2);

        assertEquals(g1.getName(),TEST_STRING_1);
        assertEquals(g1.getRows(), TEST_ROW_1);
        assertEquals(g1.getColumns(), TEST_COL_1);
        assertEquals(g2.getName(),TEST_STRING_2);
        assertEquals(g2.getRows(), TEST_ROW_2);
        assertEquals(g2.getColumns(), TEST_COL_2);
    }

    @Test

    void addProjectTestNamePrevGivenSingle() {
        projectCollection.addProject(TEST_STRING_1,TEST_ROW_1,TEST_COL_1);

        boolean result = projectCollection.addProject(TEST_STRING_1,TEST_ROW_2,TEST_COL_3);

        assertEquals(1, projectCollection.size());
        assertFalse(result);


    }

    @Test

    void addProjectTestNamePrevGivenMult() {
        projectCollection.addProject(TEST_STRING_1,TEST_ROW_1,TEST_COL_1);

        boolean result1 = projectCollection.addProject(TEST_STRING_1,TEST_ROW_2,TEST_COL_3);
        boolean result2 = projectCollection.addProject(TEST_STRING_1,TEST_ROW_3,TEST_COL_2);

        assertEquals(1, projectCollection.size());

        assertFalse(result1);
        assertFalse(result2);
    }

    @Test

    void addProjectTestSuccessSingle() {
        projectCollection.addProject(TEST_STRING_1,TEST_ROW_1,TEST_COL_1);

        boolean result1 = projectCollection.addProject(TEST_STRING_2,TEST_ROW_2,TEST_COL_2);

        Graphghan g = projectCollection.getGraphghanSpecificName(TEST_STRING_2);

        assertTrue(result1);
        assertEquals(TEST_STRING_2, g.getName());
        assertEquals(TEST_ROW_2, g.getRows());
        assertEquals(TEST_COL_2, g.getColumns());

        assertEquals(2, projectCollection.size());
    }

    @Test

    void addProjectTestSuccessMultiple() {
        projectCollection.addProject(TEST_STRING_1,TEST_ROW_1,TEST_COL_1);

        boolean result1 = projectCollection.addProject(TEST_STRING_2,TEST_ROW_2,TEST_COL_2);
        boolean result2 = projectCollection.addProject(TEST_STRING_3,TEST_ROW_3,TEST_COL_3);

        Graphghan g1 = projectCollection.getGraphghanSpecificName(TEST_STRING_2);
        Graphghan g2 = projectCollection.getGraphghanSpecificName(TEST_STRING_3);

        assertTrue(result1);
        assertEquals(TEST_STRING_2, g1.getName());
        assertEquals(TEST_ROW_2, g1.getRows());
        assertEquals(TEST_COL_2, g1.getColumns());

        assertTrue(result2);
        assertEquals(TEST_STRING_3, g2.getName());
        assertEquals(TEST_ROW_3, g2.getRows());
        assertEquals(TEST_COL_3, g2.getColumns());

        assertEquals(3, projectCollection.size());
    }

    @Test

    void removeProjectTestEmptyListSingle() {
        boolean result = projectCollection.removeProject(TEST_STRING_1);
        assertFalse(result);
        assertEquals(0, projectCollection.size());

    }

    @Test

    void removeProjectTestEmptyListMultiple() {
        boolean result1 = projectCollection.removeProject(TEST_STRING_1);
        boolean result2 = projectCollection.removeProject(TEST_STRING_1);
        assertFalse(result1);
        assertFalse(result2);
        assertEquals(0, projectCollection.size());

    }

    @Test

    void removeProjectTestInListSingle() {
        projectCollection.addProject(TEST_STRING_1,TEST_ROW_1,TEST_COL_1);

        int originalSize = projectCollection.size();

        boolean result = projectCollection.removeProject(TEST_STRING_1);

        int newSize = projectCollection.size();

        Graphghan g = projectCollection.getGraphghanSpecificName(TEST_STRING_1);

        assertTrue(result);
        assertEquals(1, originalSize);
        assertEquals(0, newSize);
        assertNull(g);

    }

    @Test

    void removeProjectTestInListMultiple() {
        projectCollection.addProject(TEST_STRING_1,TEST_ROW_1,TEST_COL_1);
        projectCollection.addProject(TEST_STRING_2,TEST_ROW_2,TEST_COL_2);

        int originalSize = projectCollection.size();

        boolean result1 = projectCollection.removeProject(TEST_STRING_1);
        boolean result2 = projectCollection.removeProject(TEST_STRING_2);

        int newSize = projectCollection.size();

        Graphghan g1 = projectCollection.getGraphghanSpecificName(TEST_STRING_1);
        Graphghan g2 = projectCollection.getGraphghanSpecificName(TEST_STRING_2);

        assertTrue(result1);
        assertTrue(result2);
        assertEquals(2, originalSize);
        assertEquals(0, newSize);
        assertNull(g1);
        assertNull(g2);
    }

    @Test

    void removeProjectTestNotInListSingle() {
        projectCollection.addProject(TEST_STRING_1,TEST_ROW_1,TEST_COL_1);
        projectCollection.addProject(TEST_STRING_2,TEST_ROW_2,TEST_COL_2);

        int originalSize = projectCollection.size();

        boolean result1 = projectCollection.removeProject(TEST_STRING_3);

        int newSize = projectCollection.size();

        Graphghan g1 = projectCollection.getGraphghanSpecificName(TEST_STRING_3);

        assertFalse(result1);
        assertEquals(2, originalSize);
        assertEquals(2, newSize);
        assertNull(g1);
    }

    @Test

    void removeProjectTestNotInListMultiple() {
        projectCollection.addProject(TEST_STRING_1,TEST_ROW_1,TEST_COL_1);
        projectCollection.addProject(TEST_STRING_2,TEST_ROW_2,TEST_COL_2);

        int originalSize = projectCollection.size();

        boolean result1 = projectCollection.removeProject(TEST_STRING_3);
        boolean result2 = projectCollection.removeProject(TEST_STRING_4);

        int newSize = projectCollection.size();

        Graphghan g1 = projectCollection.getGraphghanSpecificName(TEST_STRING_3);
        Graphghan g2 = projectCollection.getGraphghanSpecificName(TEST_STRING_4);

        assertFalse(result1);
        assertFalse(result2);
        assertEquals(2, originalSize);
        assertEquals(2, newSize);
        assertNull(g1);
        assertNull(g2);
    }

    @Test

    void getAllNamesTestEmptyList() {

        ArrayList<String> result = projectCollection.getAllNames();

        assertNotNull(result);
        assertEquals(0, result.size());
        assertTrue(result.isEmpty());

    }

    @Test

    void getAllNamesTestSingleInList() {
        projectCollection.addProject(TEST_STRING_1,TEST_ROW_1,TEST_COL_1);

        ArrayList<String> sampleResult = new ArrayList<>();
        sampleResult.add(TEST_STRING_1);

        ArrayList<String> realResult = projectCollection.getAllNames();

        boolean areEqual = realResult.equals(sampleResult);

        assertTrue(areEqual);
    }

    @Test

    void getAllNamesTestManyInList() {
        projectCollection.addProject(TEST_STRING_1,TEST_ROW_1,TEST_COL_1);
        projectCollection.addProject(TEST_STRING_2,TEST_ROW_1,TEST_COL_1);
        projectCollection.addProject(TEST_STRING_3,TEST_ROW_1,TEST_COL_1);

        ArrayList<String> sampleResult = new ArrayList<>();
        sampleResult.add(TEST_STRING_1);
        sampleResult.add(TEST_STRING_2);
        sampleResult.add(TEST_STRING_3);

        ArrayList<String> realResult = projectCollection.getAllNames();

        boolean areEqual = realResult.equals(sampleResult);

        assertTrue(areEqual);
    }

    @Test

    void containsGivenProjectTestEmptyList() {

        boolean result = projectCollection.containsGivenProject(TEST_STRING_1);

        assertFalse(result);

    }

    @Test

    void containsGivenProjectTestOnlyItemInList() {

        projectCollection.addProject(TEST_STRING_1,TEST_ROW_1,TEST_COL_1);

        boolean result = projectCollection.containsGivenProject(TEST_STRING_1);

        assertTrue(result);
    }

    @Test

    void containsGivenProjectTestItemsInListSuccess() {
        projectCollection.addProject(TEST_STRING_1,TEST_ROW_1,TEST_COL_1);
        projectCollection.addProject(TEST_STRING_2,TEST_ROW_1,TEST_COL_1);
        projectCollection.addProject(TEST_STRING_3,TEST_ROW_1,TEST_COL_1);

        boolean result = projectCollection.containsGivenProject(TEST_STRING_2);

        assertTrue(result);

    }

    @Test

    void containsGivenProjectTestItemsInListFail() {
        projectCollection.addProject(TEST_STRING_1,TEST_ROW_1,TEST_COL_1);
        projectCollection.addProject(TEST_STRING_2,TEST_ROW_1,TEST_COL_1);
        projectCollection.addProject(TEST_STRING_3,TEST_ROW_1,TEST_COL_1);

        boolean result = projectCollection.containsGivenProject(TEST_STRING_4);

        assertFalse(result);
    }

    @Test

    void getGraphghanSpecificNameTestEmptyList() {
        Graphghan g = projectCollection.getGraphghanSpecificName(TEST_STRING_1);

        assertNull(g);
    }

    @Test

    void getGraphghanSpecificNameTestInListSingle() {
        projectCollection.addProject(TEST_STRING_1,TEST_ROW_1,TEST_COL_1);
        projectCollection.addProject(TEST_STRING_2,TEST_ROW_2,TEST_COL_2);
        projectCollection.addProject(TEST_STRING_3,TEST_ROW_3,TEST_COL_3);

        Graphghan g = projectCollection.getGraphghanSpecificName(TEST_STRING_2);

        assertNotNull(g);
        assertEquals(TEST_STRING_2, g.getName());
        assertEquals(TEST_ROW_2, g.getRows());
        assertEquals(TEST_COL_2, g.getColumns());
        assertEquals(3, projectCollection.size());

    }

    @Test

    void getGraphghanSpecificNameTestInListMultiple() {
        projectCollection.addProject(TEST_STRING_1,TEST_ROW_1,TEST_COL_1);
        projectCollection.addProject(TEST_STRING_2,TEST_ROW_2,TEST_COL_2);
        projectCollection.addProject(TEST_STRING_3,TEST_ROW_3,TEST_COL_3);

        Graphghan g1 = projectCollection.getGraphghanSpecificName(TEST_STRING_2);
        Graphghan g2 = projectCollection.getGraphghanSpecificName(TEST_STRING_3);

        assertNotNull(g1);
        assertNotNull(g2);
        assertEquals(TEST_STRING_2, g1.getName());
        assertEquals(TEST_ROW_2, g1.getRows());
        assertEquals(TEST_COL_2, g1.getColumns());
        assertEquals(TEST_STRING_3, g2.getName());
        assertEquals(TEST_ROW_3, g2.getRows());
        assertEquals(TEST_COL_3, g2.getColumns());
        assertEquals(3, projectCollection.size());
    }

    @Test

    void getGraphghanSpecificNameTestNotInListSingle() {
        projectCollection.addProject(TEST_STRING_1,TEST_ROW_1,TEST_COL_1);
        projectCollection.addProject(TEST_STRING_2,TEST_ROW_2,TEST_COL_2);
        projectCollection.addProject(TEST_STRING_3,TEST_ROW_3,TEST_COL_3);

        Graphghan g = projectCollection.getGraphghanSpecificName(TEST_STRING_4);

        assertNull(g);
    }

    @Test

    void getGraphghanSpecificNameTestNotInListMultiple() {
        projectCollection.addProject(TEST_STRING_1,TEST_ROW_1,TEST_COL_1);
        projectCollection.addProject(TEST_STRING_2,TEST_ROW_2,TEST_COL_2);
        projectCollection.addProject(TEST_STRING_3,TEST_ROW_3,TEST_COL_3);

        Graphghan g1 = projectCollection.getGraphghanSpecificName(TEST_STRING_4);
        Graphghan g2 = projectCollection.getGraphghanSpecificName(TEST_STRING_4);

        assertNull(g1);
        assertNull(g2);
    }





}
