package org.example;

import java.util.List;

public class Result {
    private int totalWeight;
    private int totalValue;
    private List<Item> selectedItems;

    public Result(int totalWeight, int totalValue, List<Item> selectedItems) {
        this.totalWeight = totalWeight;
        this.totalValue = totalValue;
        this.selectedItems = selectedItems;
    }

    public List<Item> getSelectedItems() {
        return selectedItems;
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    public int getTotalValue() {
        return totalValue;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("====RESULT=====").append("\n");
        builder.append("totalWeight: ").append(totalWeight).append("\n");
        builder.append("totalValue: ").append(totalValue).append("\n");
        builder.append("selectedItems: ").append(selectedItems).append("\n");

        return builder.toString();
    }

}
