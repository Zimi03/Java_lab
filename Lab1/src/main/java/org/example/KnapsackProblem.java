package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KnapsackProblem {
    private int n;
    private int seed;
    private int lowerBound;
    private int upperBound;
    private List<Item> items;

    public KnapsackProblem(int n, int seed, int lowerBound, int upperBound) {
        this.n = n;
        this.seed = seed;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.items = new ArrayList<Item>();
        Random random = new Random(seed);

        for (int i = 0; i < n; i++) {
            int weight = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            int value = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            items.add(new Item(i+1, weight, value));
        }
    }

    public void printItems() {
        for(Item item : items) {
            System.out.println(item);
        }
    }
}
