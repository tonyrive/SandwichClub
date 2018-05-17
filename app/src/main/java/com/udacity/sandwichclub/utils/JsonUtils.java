package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        String mainName = null;
        List<String> alsoKnownAs = null;
        String placeOfOrigin = null;
        String description = null;
        String image = null;
        List<String> ingredients = null;

        try {
            JSONObject data = new JSONObject(json);
            JSONObject name = data.getJSONObject("name");
            mainName = name.optString("mainName");
            JSONArray knownAs = name.getJSONArray("alsoKnownAs");
            alsoKnownAs = new ArrayList<>();
            if(knownAs != null) {
                for (int i = 0; i < knownAs.length(); i++) {
                    alsoKnownAs.add(knownAs.optString(i));
                }
            }

            placeOfOrigin = data.optString("placeOfOrigin");
            description = data.optString("description");
            image = data.optString("image");
            JSONArray ing = data.getJSONArray("ingredients");
            ingredients = new ArrayList<>();
            if(ing != null) {
                for (int i = 0; i < ing.length(); i++) {
                    ingredients.add(ing.optString(i));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new Sandwich(mainName, alsoKnownAs, placeOfOrigin,
                description, image, ingredients);
    }
}
