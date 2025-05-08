package org.example;

import java.util.ArrayList;
import java.util.Collections;
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
        System.out.println("Items: ");
        for(Item item : items) {
            System.out.println(item);
        }
    }

    public Result solve(int capacity) {
        int remainingCapacity = capacity;
        int totalWeight = 0;
        int totalValue = 0;
        List<Item> selectedItems = new ArrayList<>();

        Collections.sort(items);

        for(Item item : items) {
            if(item.getWeight() <= remainingCapacity) {
                int count = remainingCapacity/item.getWeight();
                remainingCapacity -= count * item.getWeight();
                totalWeight += count * item.getWeight();
                totalValue += count*item.getValue();
                for (int i = 0; i < count; i++) {
                    selectedItems.add(item);
                }
            }

        }

        return new Result(totalWeight, totalValue, selectedItems);
    }
}
