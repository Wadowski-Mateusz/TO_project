package org.shop.classes;

import org.json.JSONArray;
import org.json.JSONObject;
import org.shop.interfaces.Convertible;
import org.shop.interfaces.DbcAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class DbcAdapterRecordJSON implements DbcAdapter<JSONObject> {

    DatabaseConnector db = DatabaseConnector.getInstance();

    @Override
    public JSONObject loadData(int id, Class convertible) {

        DatabaseConnector db = DatabaseConnector.getInstance();
        String[] data = db.loadData(id, convertible).split(",");
        JSONObject json = new JSONObject();

        int i = 0;
        json.put("id", Integer.parseInt(data[i++]));
        json.put("category", data[i++]);
        json.put("name", data[i++]);
        json.put("mark", data[i++]);
        json.put("price", data[i++]);
        json.put("oldPrice", data[i++]);
        json.put("howManyStock", data[i++]);

        String[] characteristicsTemp = data[i].split(";");
        ArrayList<String> characteristics = new ArrayList<>(Arrays.asList(characteristicsTemp));

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
        db.updateRecord(convertible);
    }

}
