package ga.framework.operators;

import java.util.List;
import java.util.Random;
import ga.framework.model.Solution;

public class TournamentSelection implements SelectionOperator {

    private Random random;

    public TournamentSelection() {
        this.random = new Random();
    }

    @Override
    public Solution selectParent(List<Solution> candidates) {

        Solution can1 = candidates.get(random.nextInt(candidates.size()));
        Solution can2 = candidates.get(random.nextInt(candidates.size()));

        if (can1.getFitness() >= can2.getFitness()) {
            return can1;
        } else {
            return can2;
        }
    }

}
