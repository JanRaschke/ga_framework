package ga.problems.knapsack;

import ga.framework.operators.EvolutionException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class KnapsackMutation {

    public KnapsackMutation(){}

    @FunctionalInterface
    private interface MutationVariant {
        boolean apply(KnapsackSolution solution, List<KnapsackItem> allItems, int capacity);
    }

    private class RemoveItemMutation implements MutationVariant {
        @Override
        public boolean apply(KnapsackSolution solution, List<KnapsackItem> allItems, int capacity) {
            List<KnapsackItem> contents = solution.getContents();
            if (contents.isEmpty()) {
                return false;
            }
            int randomIndex = ThreadLocalRandom.current().nextInt(contents.size());
            solution.removeItemFromContents(contents.get(randomIndex));
            return true;
        }
    }

    private class AddItemMutation implements MutationVariant {
        @Override
        public boolean apply(KnapsackSolution solution, List<KnapsackItem> allItems, int capacity) {
            List<KnapsackItem> availableItems = allItems.stream()
                    .filter(item -> !solution.getContents().contains(item))
                    .filter(item -> solution.getTotalWeight() + item.getWeight() <= capacity)
                    .collect(Collectors.toCollection(ArrayList::new));

            if (availableItems.isEmpty()) {
                return false;
            }

            int randomIndex = ThreadLocalRandom.current().nextInt(availableItems.size());
            solution.addItemToContents(availableItems.get(randomIndex));
            return true;
        }
    }

    private final RemoveItemMutation removeMutation = new RemoveItemMutation();
    private final AddItemMutation addMutation = new AddItemMutation();

    public KnapsackSolution mutate(KnapsackSolution originalSolution, List<KnapsackItem> allItems, int capacity) throws EvolutionException {
        KnapsackSolution mutatedCopy = new KnapsackSolution(originalSolution);

        boolean canRemove = !mutatedCopy.getContents().isEmpty();
        boolean canAdd = allItems.stream()
                .filter(item -> !mutatedCopy.getContents().contains(item))
                .anyMatch(item -> mutatedCopy.getTotalWeight() + item.getWeight() <= capacity);

        if (!canRemove && !canAdd) {
            throw new EvolutionException("Weder Entfernen noch Hinzufügen möglich: Mutation fehlgeschlagen.");
        }

        boolean chooseRemove;
        if (canRemove && canAdd) {
            chooseRemove = ThreadLocalRandom.current().nextBoolean();
        } else {
            chooseRemove = canRemove;
        }

        if (chooseRemove) {
            removeMutation.apply(mutatedCopy, allItems, capacity);
        } else {
            addMutation.apply(mutatedCopy, allItems, capacity);
        }

        return mutatedCopy;
    }
}