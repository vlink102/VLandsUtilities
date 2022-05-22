package net.vlands.data.serialization;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class JSONSerializer {

    private static Gson gson = new GsonBuilder().registerTypeAdapter(Map.class, new CooldownMapJSONDeserialzer()).create();

    public static String serializeCooldownMap(Map<String, Long> cooldownMap) {
        return gson.toJson(cooldownMap);
    }

    public static Map<String, Long> deserializeCooldownMap(String json) {
        return gson.fromJson(json, CooldownMap.class);
    }

    private static class CooldownMapJSONDeserialzer implements JsonDeserializer<CooldownMap> {

        @Override
        public CooldownMap deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            CooldownMap map = new CooldownMap();
            for (Map.Entry<String, JsonElement> stringJsonElementEntry : jsonElement.getAsJsonObject().entrySet()) {
                map.put(stringJsonElementEntry.getKey(), stringJsonElementEntry.getValue().getAsLong());
            }
            return map;
        }
    }

    private static class CooldownMap extends HashMap<String, Long>{}
}
