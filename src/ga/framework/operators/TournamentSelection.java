package ga.framework.operators;

import java.util.List;
import java.util.Random;
import ga.framework.model.Solution;

public class TournamentSelection implements SelectionOperator {

    private Random random;

    public TournamentSelection() {
        this.random = new Random();
    }

    // Duell zwischen Elems, fittere größere Chance als Eltern
    // Schwache nicht komplett ausschließen, Variation
    @Override
    public Solution selectParent(List<Solution> candidates) {
        // Zufälliges Elem asu Pop, kann das selbe sein, damit zurücklegen
        Solution can1 = candidates.get(random.nextInt(candidates.size()));
        Solution can2 = candidates.get(random.nextInt(candidates.size()));

        // Vergleich Fitness bei = nimm dem Ersten
        if (can1.getFitness() >= can2.getFitness()) {
            return can1;
        } else {
            return can2;
        }
    }

}
