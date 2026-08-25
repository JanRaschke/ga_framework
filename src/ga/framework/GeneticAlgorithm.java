package ga.framework;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ga.framework.model.NoSolutionException;
import ga.framework.model.Problem;
import ga.framework.model.Solution;
import ga.framework.operators.EvolutionException;
import ga.framework.operators.EvolutionaryOperator;
import ga.framework.operators.FitnessEvaluator;
import ga.framework.operators.SurvivalOperator;

public class GeneticAlgorithm {

    private Problem problem;
    private int populationSize;
    private List<EvolutionaryOperator> evolutionaryOperators = new ArrayList<>();
    private FitnessEvaluator fitnessEvaluator;
    private SurvivalOperator survivalOperator;
    private int maxIterations;

    public GeneticAlgorithm(Problem problem, int populationSize, List<EvolutionaryOperator> evolutionaryOperators,
            FitnessEvaluator fitnessEvaluator, SurvivalOperator survivalOperator, int maxIterations) {
        this.problem = problem;
        this.populationSize = populationSize;
        this.evolutionaryOperators = evolutionaryOperators;
        this.fitnessEvaluator = fitnessEvaluator;
        this.survivalOperator = survivalOperator;
        this.maxIterations = maxIterations;
    }

    public List<Solution> runOptimization() {
        Random random = new Random();
        try {
            List<Solution> population = new ArrayList<>();
            for (int i = 0; i < populationSize; i++) {
                population.add(problem.createNewSolution());
            }

            fitnessEvaluator.evaluate(population);

            for (int iteration = 0; iteration < maxIterations; iteration++) {
                int randomIndex = random.nextInt(evolutionaryOperators.size());
                EvolutionaryOperator operator = evolutionaryOperators.get(randomIndex);

                List<Solution> children = new ArrayList<>();
                for (Solution solution : population) {
                    children.add(operator.evolve(solution));
                }
            }

            return population;

        } catch (EvolutionException e) {
            System.err.println("Fehler bei Evolution: " + e.getMessage());
            return null;
        } catch (NoSolutionException e) {
            System.err.println("Fehler Startpopulation " + e.getMessage());
            return null;
        }
    }
}
