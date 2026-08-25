package ga.framework.operators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ga.framework.model.Solution;

public class TopKSurvival implements SurvivalOperator {

    private int k;

    public TopKSurvival(int k) {
        this.k = k;
    }

    @Override
    public List<Solution> selectPopulation(List<Solution> candidates, int populationSize)
            throws SurvivalException {

        Random random = new Random();

        if (k > populationSize) {
            throw new SurvivalException("PopulationSize kleiner k");
        }

        candidates.sort((s1, s2) -> Double.compare(s2.getFitness(), s1.getFitness()));

        List<Solution> newPopulation = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            newPopulation.add(candidates.get(i));
        }

        while (newPopulation.size() < populationSize) {
            int j = random.nextInt(candidates.size());
            if (j >= k) {
                newPopulation.add(candidates.get(j));
            }
        
        }

        return newPopulation;
    }

}
