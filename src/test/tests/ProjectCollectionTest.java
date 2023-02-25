package tests;

import model.Graphghan;
import model.ProjectCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// CLASS COMMENT:

public class ProjectCollectionTest {

    ProjectCollection projectCollection;

    final static String TEST_STRING_1 = "Test";
    final static String TEST_STRING_2 = "WideBlanket";
    final static String TEST_STRING_3 = "NarrowBlanket";

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

//    @Test

//    void addProjectTestSingleAdd() {
//        assertTrue(projectCollection.addProject(TEST_STRING_1, TEST_ROW_1, TEST_COL_1));
//        Graphghan g = projectCollection.get(0);
//        assertEquals(TEST_STRING_1, g.getName());
//        assertEquals(TEST_ROW_1, g.getRows());
//        assertEquals(TEST_COL_1, g.getColumns());
//        assertFalse(projectCollection.isEmpty());
//    }

//    @Test
//
//    void addProjectTestMultipleAdd() {
//        assertTrue(projectCollection.addProject(TEST_STRING_1, TEST_ROW_1, TEST_COL_1));
//        assertTrue(projectCollection.addProject(TEST_STRING_2, TEST_ROW_2, TEST_COL_2));
//        Graphghan graphghan1 = projectCollection.get(0);
//        Graphghan graphghan2 = projectCollection.get(1);
//        assertEquals(TEST_STRING_1, graphghan1.getName());
//        assertEquals(TEST_ROW_1, graphghan1.getRows());
//        assertEquals(TEST_COL_1, graphghan1.getColumns());
//        assertEquals(TEST_STRING_2, graphghan2.getName());
//        assertEquals(TEST_ROW_2, graphghan2.getRows());
//        assertEquals(TEST_COL_2, graphghan2.getColumns());
//        assertFalse(projectCollection.isEmpty());
//    }

    @Test

    void removeProjectTestSingleRemoveEmpty() {
        boolean result = projectCollection.removeProject(TEST_STRING_1);
        assertFalse(result);
        assertTrue(projectCollection.isEmpty());
    }

//    @Test
//    // TODO: Could add more to this test
//    void removeProjectTestSingleRemoveNonEmptySuccess() {
//        projectCollection.addProject(TEST_STRING_1,TEST_COL_1, TEST_ROW_1);
//
//        assertFalse(projectCollection.isEmpty());
//
//        boolean result = projectCollection.removeProject(TEST_STRING_1);
//
//        assertTrue(result);
//        assertTrue(projectCollection.isEmpty());
//
//    }

    @Test

    void removeProjectTestMultipleRemoveEmptySuccess() {

    }

    @Test

    void removeProjectTestJustAddedProjectSuccess() {

    }

    @Test

    void removeProjectSingleUnsuccessfulNotInList() {

    }

    @Test

    void removeProjectMultipleUnsuccessfulNotInList() {

    }

    @Test

    void removeProjectMultipleSomeSuccessSomeFails() {

    }

}
