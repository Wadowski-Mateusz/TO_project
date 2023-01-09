package org.shop.classes;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Converts .json type to .csv like String
 * Converts .csv type to .json like String
 * */
public class AdapterProductDBC {
    public static String jsonToCsv(/*?*/){
        throw new UnsupportedOperationException();
    }

    public static String csvToJson(/*?*/){
        throw new UnsupportedOperationException();
    }

}

//import org.json.JSONArray;
//        import org.json.JSONObject;
//
//        import java.io.FileWriter;
//        import java.io.IOException;
//        import java.nio.file.Files;
//        import java.nio.file.Paths;
//        import java.util.ArrayList;
//        import java.util.TreeMap;
//
//
//public class jsonTest {
//
//    static void convertToString(){
//
//        JSONObject j = new JSONObject();
//        j.put("id", "4");
//        j.put("name", "Micro");
//
//        TreeMap<String, String> treeMap = new TreeMap<>();
//        treeMap.put("cecha1", "value1");
//        treeMap.put("cecha2", "value2");
//        treeMap.put("cecha3", "value3");
//        treeMap.put("cecha4", "value4");
//        treeMap.put("cecha5", "value5");
//
//        JSONArray j2 = new JSONArray();
//        treeMap.values().forEach(j2::put);
//
//        System.out.println("\nj: " + j);
//        System.out.println("\nj2: " + j2);
//        j.put("characteristics", j2);
//        System.out.println("\nj: " + j);
//
//        String fileName = "test.json";
//        try(FileWriter file = new FileWriter(fileName)){
//            file.write(j.toString());
//        }catch(IOException e){
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) throws IOException {
////        convertToString();
//        String first = "test.json";
//
//        String content = new String((Files.readAllBytes(Paths.get(first))));
//        JSONObject j = new JSONObject(content);
//
//        JSONArray characteristicsJSON = j.getJSONArray("characteristics");
//        ArrayList<String> characteristics = new ArrayList<>();
//
//        for(int i = 0; i < characteristicsJSON.length(); i++) {
//            characteristics.add((String) characteristicsJSON.get(i));
//            System.out.println(characteristics.get(i));
//        }
//        System.out.println(j.getString("id"));
//
//    }
//}
