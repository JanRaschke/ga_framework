package ga.framework;

import java.util.ArrayList;
import java.util.List;

import ga.framework.model.NoSolutionException;
import ga.framework.model.Problem;
import ga.framework.operators.EvolutionaryOperator;
import ga.framework.operators.FitnessEvaluator;
import ga.framework.operators.SurvivalOperator;
import ga.framework.model.Solution;

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
        try {
            List<Solution> population = new ArrayList<>();
            for (int i = 0; i < populationSize; i++) {

                population.add(problem.createNewSolution());
            }

            fitnessEvaluator.evaluate(population);

            for (int iteration = 0; iteration < maxIterations; iteration++) {
                int randomIndex = random.nextInt(evolutionaryOperators.size()) - 1;
                EvolutionaryOperator operator = evolutionaryOperators.get(randomIndex);
            }

            return population;

        } catch (NoSolutionException e) {
            System.err.println("Fehler Startpopulation " + e.getMessage());
            return null;
        }

    }

}
