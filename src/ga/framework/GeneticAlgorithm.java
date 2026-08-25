package ga.framework;

import java.util.ArrayList;
import java.util.List;
import ga.framework.model.Problem;
import ga.framework.operators.EvolutionaryOperator;
import ga.framework.operators.FitnessEvaluator;
import ga.framework.operators.SurvivalOperator;

/**
 * Grundgeruest fuer den Genetischen Algorithmus (Aufgabe 1.1)
 */
public class GeneticAlgorithm {
    // Hier erarbeiten wir gleich gemeinsam die Attribute, Konstruktoren und
    // Methoden!'

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

}
