package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class KnapsackProblemTest {

    @Test
    public void testKnapsackProblem() {
        KnapsackProblem problem = new KnapsackProblem(10, 1, 1, 10);
        Result result = problem.solve(10);
        assertNotNull(result);
        List<Item> items = result.getSelectedItems();
        if(items.stream().anyMatch(item -> item.getWeight() <= 10)) {
            assertFalse(items.isEmpty());
        }
    }

    @Test
    public void testInstanceSize(){
        KnapsackProblem problem = new KnapsackProblem(10, 1, 1, 10);
        List<Item> items = problem.getItems();
        assertEquals(10, items.size());
    }

    @Test
    public void testTooBigItems() {
        List<Item> items = new ArrayList<>();
        items.add(new Item(1, 1,10));
        items.add(new Item(2, 2,10));
        items.add(new Item(3, 3,10));
        KnapsackProblem problem = new KnapsackProblem(10, 1, 1, 10);
        Result result = problem.solve(1);
        List<Item> resultItems = result.getSelectedItems();
        assertEquals(0, resultItems.size());
    }

    @Test
    public void testItemValueAndItemWeightIsInBounds() {
        int lowerBound = 1;
        int upperBound = 10;
        KnapsackProblem problem = new KnapsackProblem(10, 0, lowerBound, upperBound);
        List<Item> items = problem.solve(10).getSelectedItems();
        for(Item item : items) {
            assertTrue(item.getWeight() >= lowerBound && item.getWeight() <= upperBound);
            assertTrue(item.getValue() >= lowerBound && item.getValue() <= upperBound);
        }
    }

    @Test
    public void testExampleInstance(){
        List<Item> items = new ArrayList<>();
        items.add(new Item(1, 6,3));
        items.add(new Item(2, 2,2));
        KnapsackProblem problem = new KnapsackProblem(items);
        Result result = problem.solve(20);
        List<Item> handItems = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            handItems.add(items.getFirst());
        }
        handItems.add(items.get(1));
        int expectedWeight = 20;
        int expectedValue = 38;
        assertEquals(handItems, result.getSelectedItems());
        assertEquals(expectedValue, result.getTotalValue());
        assertEquals(expectedWeight, result.getTotalWeight());
    }

    @Test
    public void maxWeightIsInBounds() {
        KnapsackProblem problem = new KnapsackProblem(10, 1, 1, 10);
        Result result = problem.solve(10);
        assertTrue(result.getTotalWeight() <= 10);
    }

}