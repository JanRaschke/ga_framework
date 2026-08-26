package ga.problems.knapsack;

import java.util.ArrayList;
import java.util.Comparator;

public class KnapsackFitnessEvaluator {
    ArrayList<KnapsackSolution> population = new ArrayList<>();

    public KnapsackFitnessEvaluator(ArrayList<KnapsackSolution> population) {
        this.population = population;
    }

    public void evaluate() {
        int maxFitness = population.stream()
                .mapToInt(KnapsackSolution::getTotalValue)
                .max()
                .orElse(0);

        KnapsackSolution bestSolution = population.stream()
                        .max(Comparator.comparingInt(KnapsackSolution::getTotalValue))
                        .orElse(null);

        double avgFitness = population.stream()
                .mapToInt(KnapsackSolution::getTotalValue)
                .average()
                .orElse(0.0);

        int totalPopulationFitness = population.stream()
                .mapToInt(KnapsackSolution::getTotalValue)
                .sum();

        System.out.println("Max-Fitness: " + maxFitness + "\nAvg-Fitness: " + avgFitness + "\nTotal-Fitness: " + totalPopulationFitness);
        System.out.println("Best Solution: " + bestSolution);
    }
}
