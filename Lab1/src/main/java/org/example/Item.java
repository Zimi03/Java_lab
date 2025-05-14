package org.example;

public class Item implements Comparable<Item> {
    private int id;
    private int value;
    private int weight;
    private double ratio;

    public Item(int id, int value, int weight) {
        this.id = id;
        this.value = value;
        this.weight = weight;
        this.ratio = (double) value / (double) weight;
    }

    public int getValue(){
        return value;
    }

    public int getWeight(){
        return weight;
    }

    @Override
    public int compareTo(Item o) {
        return Double.compare(o.ratio, this.ratio);
    }

    @Override
    public String toString() {
        return "Id: " + id + " Value: " + value + " Weight: " + weight;
    }
}
