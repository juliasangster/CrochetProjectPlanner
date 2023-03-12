package tests;

import model.Color;
import model.Graphghan;
import model.ProjectCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/legitimatelyWrong.json");
        try {
            ProjectCollection projectCollection = reader.read();
            fail("You should have caught an IOException");
        } catch (IOException e) {
            // pass as IOException is caught
        }
    }

    @Test
    void testReaderEmptyCollection() {
        JsonReader reader = new JsonReader("./data/sample_projects_empty_read.json");
        try {
            ProjectCollection projectCollection = reader.read();
            ArrayList<String> allNames = projectCollection.getAllNames();
            assertTrue(allNames.isEmpty());
            assertTrue(projectCollection.isEmpty());
        } catch (IOException e) {
            fail("You should not have caught an IOException");
        }
    }

    @Test

    void testReaderGeneralProjectCollection() {
        JsonReader reader = new JsonReader("./data/sample_projects_read.json");
        try {
            ProjectCollection projectCollection = reader.read();
            assertTrue(projectCollection.containsGivenProject("test1"));
            assertTrue(projectCollection.containsGivenProject("test2"));
            assertTrue(projectCollection.containsGivenProject("test3"));
            Graphghan test1 = projectCollection.getGraphghanSpecificName("test1");
            Graphghan test2 = projectCollection.getGraphghanSpecificName("test2");
            Graphghan test3 = projectCollection.getGraphghanSpecificName("test3");
            assertEquals(4, test1.getSize());
            assertEquals(3, test2.getSize());
            assertEquals(3, test3.getSize());
            assertEquals(Color.DARK_CYAN, test1.getSquare(0, 0).getColor());
            assertEquals(Color.BRIGHT_CYAN, test1.getSquare(1, 0).getColor());
            assertEquals(Color.DARK_GREEN, test1.getSquare(0, 1).getColor());
            assertEquals(Color.BRIGHT_GREEN, test1.getSquare(1, 1).getColor());
        } catch (IOException e) {
            fail("Should not catch IOException");
        }
    }

}

