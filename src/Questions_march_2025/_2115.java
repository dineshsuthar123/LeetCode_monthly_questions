package Questions_march_2025;

import java.util.*;

public class _2115 {
    public static void main(String[] args) {
        String[] recipes = {"bread", "sandwich", "burger"};
        List<List<String>> ingredients = Arrays.asList(
                Arrays.asList("flour", "water"),
                Arrays.asList("bread", "ham"),
                Arrays.asList("bread", "meat")
        );
        String[] supplies = {"flour", "water", "ham", "meat"};

        FindRecipesSolution solution = new FindRecipesSolution();
        System.out.println(solution.findAllRecipes(recipes, ingredients, supplies));
    }
}

class FindRecipesSolution {
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, Integer> indegree = new HashMap<>();
        Set<String> available = new HashSet<>(Arrays.asList(supplies));
        List<String> result = new ArrayList<>();

        for (int i = 0; i < recipes.length; i++) {
            String recipe = recipes[i];
            indegree.put(recipe, ingredients.get(i).size());

            for (String ing : ingredients.get(i)) {
                graph.computeIfAbsent(ing, k -> new ArrayList<>()).add(recipe);
            }
        }

        Queue<String> queue = new LinkedList<>(available);

        while (!queue.isEmpty()) {
            String item = queue.poll();
            if (!graph.containsKey(item)) continue;

            for (String recipe : graph.get(item)) {
                indegree.put(recipe, indegree.get(recipe) - 1);
                if (indegree.get(recipe) == 0) {
                    result.add(recipe);
                    queue.add(recipe);
                }
            }
        }

        return result;
    }
}

