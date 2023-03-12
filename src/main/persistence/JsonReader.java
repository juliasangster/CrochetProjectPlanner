package persistence;

import model.Color;
import model.GraphghanSquare;
import model.Graphghan;
import model.ProjectCollection;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

public class JsonReader {
    private String source;

    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads project collection from file and returns it
    //          throws IOException if error occurs during reading
    public ProjectCollection read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCollection(jsonObject);
    }

    // EFFECTS: Reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses project collection from JSON object and returns it

    private ProjectCollection parseCollection(JSONObject jsonObject) {
        ProjectCollection projectCollection = new ProjectCollection();
        addGraphghans(projectCollection, jsonObject);
        return projectCollection;

    }

    // MODIFIES: projColl
    // EFFECTS:  parses graphghan squares from JSON object and adds them to
    //           project collection
    private void addGraphghans(ProjectCollection projColl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Project Collection");
        for (Object json: jsonArray) {
            JSONObject nextGraphghan = (JSONObject) json;
            addProject(projColl, nextGraphghan);
        }
    }

    private void addProject(ProjectCollection projectCollection, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int columns = jsonObject.getInt("columns");
        int rows = jsonObject.getInt("rows");

        Graphghan g = new Graphghan(name, rows, columns);

        JSONArray squares = jsonObject.getJSONArray("squares");
        g.setSquares(getSquares(squares, rows, columns));

        projectCollection.add(g);

    }

    private GraphghanSquare[][] getSquares(JSONArray jsonArray, int rows, int columns) {
        Graphghan graphghanModel = new Graphghan("test", rows, columns);

        GraphghanSquare[][] squares = graphghanModel.getSquares();

        for (Object json: jsonArray) {
            JSONObject nextSquare = (JSONObject) json;
            int row = nextSquare.getInt("row");
            int col = nextSquare.getInt("col");
            Color color = Color.valueOf(nextSquare.getString("color"));
            GraphghanSquare square = graphghanModel.getSquare(row,col);
            square.changeColor(color);
        }
        return squares;

    }

}
