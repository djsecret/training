package com.damon.json.list;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by dongjun.wei on 16/2/23.
 */
public class Zoo {
    private String name;
    private String city;

    public Zoo() {

    }

    public Zoo(String name, String city) {
        this.name = name;
        this.city = city;
    }

    private List<Animal> animals = Lists.newArrayList();

    public List<Animal> addAnimal(Animal animal) {
        animals.add(animal);
        return animals;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    @Override
    public String toString() {
        return "Zoo{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", animals=" + animals +
                '}';
    }
}
