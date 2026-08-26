package ga.framework;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ga.framework.model.NoSolutionException;
import ga.framework.operators.SurvivalException;
import ga.framework.model.Problem;
import ga.framework.model.Solution;
import ga.framework.operators.EvolutionException;
import ga.framework.operators.EvolutionaryOperator;
import ga.framework.operators.FitnessEvaluator;
import ga.framework.operators.SelectionOperator;
import ga.framework.operators.SurvivalOperator;

public class GeneticAlgorithm {

    private Problem problem;
    private int populationSize;
    private List<EvolutionaryOperator> evolutionaryOperators = new ArrayList<>();
    private FitnessEvaluator fitnessEvaluator;
    private SurvivalOperator survivalOperator;
    private int maxIterations;

    private SelectionOperator selectionOperator;

    public GeneticAlgorithm(Problem problem, int populationSize, List<EvolutionaryOperator> evolutionaryOperators,
            FitnessEvaluator fitnessEvaluator, SurvivalOperator survivalOperator, int maxIterations,
            SelectionOperator selectionOperator) {
        this.problem = problem;
        this.populationSize = populationSize;
        this.evolutionaryOperators = evolutionaryOperators;
        this.fitnessEvaluator = fitnessEvaluator;
        this.survivalOperator = survivalOperator;
        this.maxIterations = maxIterations;
        this.selectionOperator = selectionOperator;
    }

    public List<Solution> runOptimization() {
        Random random = new Random();
        try {
            List<Solution> population = new ArrayList<>();
            for (int i = 0; i < populationSize; i++) {
                // Startpopulation erstellen
                population.add(problem.createNewSolution());
            }

            fitnessEvaluator.evaluate(population);

            for (int iteration = 0; iteration < maxIterations; iteration++) {
                // int randomIndex = random.nextInt(evolutionaryOperators.size());
                // EvolutionaryOperator operator = evolutionaryOperators.get(randomIndex);

                // über Pop iterieren
                List<Solution> children = new ArrayList<>();
                // Genao so viele Kinder erzeugen wie Popgröße
                for (int i = 0; i < populationSize; i++) {
                    // children.add(operator.evolve(solution));
                    Solution parent = selectionOperator.selectParent(population);// Selection Methode hier Tournament
                    int randomIndex = random.nextInt(evolutionaryOperators.size());// Random Mutation
                    EvolutionaryOperator operator = evolutionaryOperators.get(randomIndex);
                    Solution child = operator.evolve(parent);// Muation + Elternteil = Kind
                    children.add(child);
                }
                fitnessEvaluator.evaluate(children);
                population.addAll(children);

                population = survivalOperator.selectPopulation(population, populationSize);

            }

            return population;

        } catch (EvolutionException e) {
            System.err.println("Fehler bei Evolution: " + e.getMessage());
            return null;
        } catch (NoSolutionException e) {
            System.err.println("Fehler bei Startpopulation: " + e.getMessage());
            return null;
        } catch (SurvivalException e) {
            System.err.println("Fehler bei Selektion: " + e.getMessage());
            return null;
        }
    }
}
