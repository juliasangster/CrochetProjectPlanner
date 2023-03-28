package tests;

import model.Graphghan;
import model.ProjectCollection;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.awt.*;
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
            assertTrue(projectCollection.containsGivenProject("test3"));
            Graphghan test1 = projectCollection.getGraphghanSpecificName("test1");
            Graphghan test3 = projectCollection.getGraphghanSpecificName("test3");
            assertEquals(2500, test1.getSize());
            assertEquals(40000, test3.getSize());
            assertEquals(Color.WHITE, test1.getSquare(0, 0).getColor());
            assertEquals(Color.BLACK, test1.getSquare(1, 0).getColor());
        } catch (IOException e) {
            fail("Should not catch IOException");
        }
    }

}

