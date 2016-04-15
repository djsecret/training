package com.damon.json.list;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

/**
 * Created by dongjun.wei on 16/2/23.
 */
public class ListDemo {
    public static void main(String[] args) throws IOException {
        Zoo zoo = new Zoo("Zoo", "BJ");
        Lion lion = new Lion("Simba");
        Elephant elephant = new Elephant("Manny");
        zoo.addAnimal(lion).add(elephant);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(zoo);
        System.out.println(json);

        //String json = "{\"name\":\"Zoo\",\"city\":\"BJ\",\"animals\":[{\"@class\":\"com.damon.json.list.Lion\",\"name\":\"Simba\"},{\"@class\":\"com.damon.json.list.Elephant\",\"name\":\"Manny\"}]}";

        System.out.println(objectMapper.readValue(json, Zoo.class));

        System.out.println(objectMapper.writerWithType(new TypeReference<List<Animal>>() {
        }).writeValueAsString(zoo.getAnimals()));
    }
}
