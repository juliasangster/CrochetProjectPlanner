package tests;

import java.awt.Color;
import model.Graphghan;
import model.ProjectCollection;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            ProjectCollection projectCollection = new ProjectCollection();
            JsonWriter writer = new JsonWriter("./data/\0:.json");
            writer.open();
            fail("Should have caught an IOException");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyProjectCollection() {
        try {
            ProjectCollection projectCollection = new ProjectCollection();
            JsonWriter writer = new JsonWriter("./data/sample_projects_empty_write.json");
            writer.open();
            writer.write(projectCollection);
            writer.close();

            JsonReader reader = new JsonReader("./data/sample_projects_empty_read.json");
            projectCollection = reader.read();
            assertEquals(0, projectCollection.size());
            assertTrue(projectCollection.isEmpty());
        } catch (IOException e) {
            fail("Should nt have caught an IOException");
        }
    }

    @Test
    void testWriterGeneralProjectCollection() {
        try {
            ProjectCollection projectCollection = new ProjectCollection();
            projectCollection.addProject("test1",1,2);
            Graphghan g = projectCollection.getGraphghanSpecificName("test1");
            g.changeColorSingleSquare(Color.RED,0,0);
            g.changeColorSingleSquare(Color.GREEN, 0,1);
            JsonWriter writer = new JsonWriter("./data/sample_projects_write.json");
            writer.open();
            writer.write(projectCollection);
            writer.close();

            JsonReader reader = new JsonReader("./data/sample_projects_write.json");
            projectCollection = reader.read();
            assertEquals(1, projectCollection.size());
            Graphghan g2 = projectCollection.getGraphghanSpecificName("test1");
            assertTrue(g2.getSquare(0,0).isGivenColor(Color.RED));
            assertTrue(g2.getSquare(0,1).isGivenColor(Color.GREEN));
        } catch (IOException e) {
            fail("Should not have caught IOException");
        }
    }

}
