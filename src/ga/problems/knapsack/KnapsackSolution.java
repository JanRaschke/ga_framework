package ga.problems.knapsack;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class KnapsackSolution  {

    private ArrayList<KnapsackItem> contents;
    private int totalValue;
    private int totalWeight;

    public KnapsackSolution() {
        contents = new ArrayList<>();
        totalValue = 0;
        totalWeight = 0;
    }

    public KnapsackSolution(KnapsackSolution other) {
        this.contents = other.contents;
        this.totalValue = other.totalValue;
        this.totalWeight = other.totalWeight;
    }

    // Content Getter & Setter
    public ArrayList<KnapsackItem> getContents() {
        return contents;
    }
    public void setContents(ArrayList<KnapsackItem> contents) {
        this.contents = contents;
    }

    // TotalValue Getter & Setter
    public int getTotalValue() {
        return totalValue;
    }
    public void setTotalValue(int totalValue) {
        this.totalValue = totalValue;
    }

    // TotalWeight Getter & Setter
    public int getTotalWeight() {
        return totalWeight;
    }
    public void setTotalWeight(int totalWeight) {
        this.totalWeight = totalWeight;
    }

    public void addItemToContents(KnapsackItem item) {
        this.contents.add(item);
        this.totalValue += item.getValue();
        this.totalWeight += item.getWeight();
    }

    public void removeItemFromContents(KnapsackItem item) {
        this.contents.remove(item);
        this.totalValue -= item.getValue();
        this.totalWeight -= item.getWeight();
    }
}
