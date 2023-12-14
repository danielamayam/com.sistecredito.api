package com.sistecredito.api.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import io.cucumber.datatable.DataTable;
import net.serenitybdd.screenplay.Performable;

import java.util.*;

public class Member {

    private String name;
    private String gender;

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public static String setData(DataTable dataTable) {
        List<Member> dates = new ArrayList<>();
        List<java.util.Map<String, String>> mapInfo = dataTable.asMaps();
        for (Map<String, String> map : mapInfo) {
            dates.add(new ObjectMapper().convertValue(map, Member.class));
        }
        return toJsonString(dates);
    }

    public static String toJsonString(List<Member> lista) {
        JsonObject json = new JsonObject();
        json.addProperty("name", lista.get(0).getName());
        json.addProperty("gender", lista.get(0).getGender());
        return json.toString();
    }
}
