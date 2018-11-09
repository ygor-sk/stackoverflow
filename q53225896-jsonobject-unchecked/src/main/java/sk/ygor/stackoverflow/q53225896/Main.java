package sk.ygor.stackoverflow.q53225896;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Collections;


public class Main {

    public static void main(String[] args) {
        JSONObject obj = new JSONObject();
        obj.put("hello", "world");
        obj.put("collection", new JSONArray(Arrays.asList(1, "two", Collections.singletonMap("three", 30))));
        System.out.println("obj.toString() = " + obj.toString());
    }
}
