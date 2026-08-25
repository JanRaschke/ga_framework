package ga.problems.knapsack;

import ga.framework.model.*;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class KnapsackProblem {

    private int capacity;
    private ArrayList<KnapsackItem> items;

    public KnapsackProblem(int capacity, ArrayList<KnapsackItem> items) {
        this.capacity = capacity;
        this.items = items;
    }

    //Implement Getter & Setter-Method for capacity variable
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public KnapsackSolution createNewSolution() throws Exception {
        KnapsackSolution solution = new KnapsackSolution();
        ArrayList<KnapsackItem> remainingItems = items;

        while (!remainingItems.isEmpty()) {
            int randomIndex = ThreadLocalRandom.current().nextInt(0, remainingItems.size());
            KnapsackItem item = remainingItems.get(randomIndex);

            if(solution.getTotalWeight() + item.getWeight() > this.capacity) {
                remainingItems.remove(randomIndex);
            } else {
                solution.addItemToContents(item);
                remainingItems.remove(randomIndex);
            }
        }
        if(solution.getContents().isEmpty()){
            throw new NoSolutionException("Alle Elemente sind zu schwer!");
        }
        return solution;
    }
}
