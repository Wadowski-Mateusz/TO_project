package org.shop.classes.stubs;

import org.json.JSONArray;
import org.json.JSONObject;
import org.shop.interfaces.Convertible;
import org.shop.interfaces.DbcAdapter;

import java.util.ArrayList;

public class MicrowaveAdapterStub implements DbcAdapter<JSONObject> {

    @Override
    public JSONObject loadData(int id, Class convertible) {

        JSONObject json = new JSONObject();

        json.put("id", id);
        json.put("category", "microwave");
        json.put("name", "nameStub" + id);
        json.put("mark", "markStub" + id);
        json.put("price", id);
        json.put("oldPrice", id);
        json.put("howManyStock", id);

        ArrayList<String> characteristics = new ArrayList<>();
        characteristics.add("charStub1");
        characteristics.add("charStub1");

        json.put("characteristics", characteristics);

        return json;
    }

    public String adaptDataToDBFormat(JSONObject jsonObject) {
        String record = jsonObject.getInt("id") + "," +
                jsonObject.getString("category") + "," +
                jsonObject.getString("name") + "," +
                jsonObject.getString("mark") + "," +
                jsonObject.getFloat("price") + "," +
                jsonObject.getFloat("oldPrice") + "," +
                jsonObject.getInt("howManyStock") + ",";

        JSONArray jsonArray = new JSONArray(jsonObject.getJSONArray("characteristics"));
        for(int i = 0; i < jsonArray.length() - 1; i++)
            record += jsonArray.get(i) + ";";
        record += jsonArray.get(jsonArray.length() - 1);
        return record;
    }

    @Override
    public void updateInBase(Convertible convertible) {
        System.out.println("No DB touchy");
    }
}