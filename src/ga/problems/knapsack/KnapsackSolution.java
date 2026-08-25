package ga.problems.knapsack;

import java.util.ArrayList;

public class KnapsackSolution  {
    private ArrayList<KnapsackItem> contents;
    private int totalValue;
    private int totalWeight;

    public KnapsackSolution() {
        contents = new ArrayList<>();
        totalValue = 0;
        totalWeight = 0;
    }

    // Content Getter
    public ArrayList<KnapsackItem> getContents() {
        return contents;
    }

    // TotalValue Getter
    public int getTotalValue() {
        return totalValue;
    }

    // TotalWeight Getter
    public int getTotalWeight() {
        return totalWeight;
    }

    public void addItemToContents(KnapsackItem item) {
        this.contents.add(item);
        this.totalValue += item.getValue();
        this.totalWeight += item.getWeight();
    }
}
