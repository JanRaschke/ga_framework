package ga;
import ga.framework.model.NoSolutionException;
import ga.framework.model.Solution;
import ga.problems.knapsack.*;
import ga.framework.operators.*;

import java.util.ArrayList;
import java.util.List;



public class ConcreteProblem {
    public static void main(String[] args) {
        KnapsackItem g1 = new KnapsackItem(5,10);
        KnapsackItem g2 = new KnapsackItem(4,8);
        KnapsackItem g3 = new KnapsackItem(4,6);
        KnapsackItem g4 = new KnapsackItem(4,4);
        KnapsackItem g5 = new KnapsackItem(3,7);
        KnapsackItem g6 = new KnapsackItem(3,4);
        KnapsackItem g7 = new KnapsackItem(2,6);
        KnapsackItem g8 = new KnapsackItem(2,3);
        KnapsackItem g9 = new KnapsackItem(1,3);
        KnapsackItem g10 = new KnapsackItem(1,1);

        ArrayList<KnapsackItem> items = new ArrayList<>();
        items.add(g1);
        items.add(g2);
        items.add(g3);
        items.add(g4);
        items.add(g5);
        items.add(g6);
        items.add(g7);
        items.add(g8);
        items.add(g9);
        items.add(g10);

        KnapsackProblem problem = new KnapsackProblem(11, items);
        List<Solution> population = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            try {
                population.add(problem.createNewSolution());
            } catch (Exception e) {
                throw new ArrayIndexOutOfBoundsException();
            }
        }
        System.out.println(population);

        TopKSurvival topKSurvival = new TopKSurvival(3);

        for (int i = 1; i <= 10; i++) {
            try {
                List<Solution> newPopulation = topKSurvival.selectPopulation(population, population.size());
                ArrayList<KnapsackSolution> newPopulationArrayList = new ArrayList<>();
                for (Solution solution : newPopulation) {
                    KnapsackSolution knapsackSolution = (KnapsackSolution) solution;
                    newPopulationArrayList.add(knapsackSolution);
                }

                KnapsackFitnessEvaluator fitness = new KnapsackFitnessEvaluator(newPopulationArrayList);
                fitness.evaluate();
            } catch (Exception e) {
                throw new ArrayIndexOutOfBoundsException();
            }
        }



    }
}
