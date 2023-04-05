package ibf2022.batch1.csf.assessment.server.utils;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class JsonParse {
    public static JsonObject stringToJson(String input){
        StringReader stringReader = new StringReader(input);
        JsonReader jsonReader = Json.createReader(stringReader);
        JsonObject jsonObject =  jsonReader.readObject();
        return jsonObject;
    }
}
