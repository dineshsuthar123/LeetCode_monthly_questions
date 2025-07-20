package Questions_july_2025;

import java.util.*;

public class _1948 {

    public static void main(String[] args) {
        // Sample input from the problem description
        List<List<String>> paths = Arrays.asList(
                List.of("a"),
                List.of("c"),
                List.of("d"),
                Arrays.asList("a", "b"),
                Arrays.asList("c", "b"),
                Arrays.asList("d", "a")
        );
        // Expected Output: [[d], [d, a]] (order may vary)

        System.out.println("--- 1. Testing Brute-Force Solution ---");
        SolutionBruteForce bruteForce = new SolutionBruteForce();
        List<List<String>> result1 = bruteForce.deleteDuplicateFolder(new ArrayList<>(paths));
        System.out.println(result1);

        System.out.println("\n--- 2. Testing Optimal Two-Pass Solution ---");
        SolutionOptimalTwoPass optimalTwoPass = new SolutionOptimalTwoPass();
        List<List<String>> result2 = optimalTwoPass.deleteDuplicateFolder(new ArrayList<>(paths));
        System.out.println(result2);

        System.out.println("\n--- 3. Testing Best (Cleanest) Solution ---");
        SolutionBest best = new SolutionBest();
        List<List<String>> result3 = best.deleteDuplicateFolder(new ArrayList<>(paths));
        System.out.println(result3);
    }

    //-------------------------------------------------------------------------//
    // Solution 1: Brute-Force (O(N^2 * D))                                    //
    //-------------------------------------------------------------------------//
    static class SolutionBruteForce {
        private static class FolderBF {
            Map<String, FolderBF> children = new TreeMap<>();
        }

        private boolean areIdentical(FolderBF f1, FolderBF f2) {
            if (f1.children.size() != f2.children.size()) return false;
            Iterator<Map.Entry<String, FolderBF>> iter1 = f1.children.entrySet().iterator();
            Iterator<Map.Entry<String, FolderBF>> iter2 = f2.children.entrySet().iterator();
            while (iter1.hasNext()) {
                Map.Entry<String, FolderBF> entry1 = iter1.next();
                Map.Entry<String, FolderBF> entry2 = iter2.next();
                if (!entry1.getKey().equals(entry2.getKey())) return false;
                if (!areIdentical(entry1.getValue(), entry2.getValue())) return false;
            }
            return true;
        }

        private void collectAllFolders(FolderBF current, List<FolderBF> list) {
            list.add(current);
            for (FolderBF child : current.children.values()) collectAllFolders(child, list);
        }

        private void buildResult(FolderBF current, List<String> currentPath, Set<FolderBF> foldersToDelete, List<List<String>> result) {
            if (foldersToDelete.contains(current)) return;
            if (!currentPath.isEmpty()) result.add(new ArrayList<>(currentPath));
            for (String folderName : current.children.keySet()) {
                currentPath.add(folderName);
                buildResult(current.children.get(folderName), currentPath, foldersToDelete, result);
                currentPath.remove(currentPath.size() - 1);
            }
        }

        public List<List<String>> deleteDuplicateFolder(List<List<String>> paths) {
            FolderBF root = new FolderBF();
            for (List<String> path : paths) {
                FolderBF current = root;
                for (String folderName : path) {
                    current = current.children.computeIfAbsent(folderName, k -> new FolderBF());
                }
            }
            List<FolderBF> allFolders = new ArrayList<>();
            collectAllFolders(root, allFolders);
            Set<FolderBF> foldersToDelete = new HashSet<>();
            for (int i = 0; i < allFolders.size(); i++) {
                for (int j = i + 1; j < allFolders.size(); j++) {
                    FolderBF f1 = allFolders.get(i);
                    FolderBF f2 = allFolders.get(j);
                    if (foldersToDelete.contains(f1) || foldersToDelete.contains(f2)) continue;
                    if (!f1.children.isEmpty() && areIdentical(f1, f2)) {
                        foldersToDelete.add(f1);
                        foldersToDelete.add(f2);
                    }
                }
            }
            List<List<String>> result = new ArrayList<>();
            buildResult(root, new ArrayList<>(), foldersToDelete, result);
            return result;
        }
    }

    //-------------------------------------------------------------------------//
    // Solution 2: Optimal Two-Pass (Stateful Node)                            //
    //-------------------------------------------------------------------------//
    static class SolutionOptimalTwoPass {
        private static class FolderOP {
            Map<String, FolderOP> children = new TreeMap<>();
            String signature = "";
            boolean isDuplicate = false;
        }

        private void calculateSignatures(FolderOP node, Map<String, FolderOP> signatureMap) {
            for (FolderOP child : node.children.values()) calculateSignatures(child, signatureMap);
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, FolderOP> entry : node.children.entrySet()) {
                sb.append('(').append(entry.getKey()).append(entry.getValue().signature).append(')');
            }
            node.signature = sb.toString();
            if (!node.signature.isEmpty()) {
                if (signatureMap.containsKey(node.signature)) {
                    signatureMap.get(node.signature).isDuplicate = true;
                    node.isDuplicate = true;
                } else {
                    signatureMap.put(node.signature, node);
                }
            }
        }

        private void buildResult(FolderOP node, List<String> currentPath, List<List<String>> result) {
            if (node.isDuplicate) return;
            if (!currentPath.isEmpty()) result.add(new ArrayList<>(currentPath));
            for (Map.Entry<String, FolderOP> entry : node.children.entrySet()) {
                currentPath.add(entry.getKey());
                buildResult(entry.getValue(), currentPath, result);
                currentPath.remove(currentPath.size() - 1);
            }
        }

        public List<List<String>> deleteDuplicateFolder(List<List<String>> paths) {
            FolderOP root = new FolderOP();
            for (List<String> path : paths) {
                FolderOP current = root;
                for (String folderName : path) {
                    current = current.children.computeIfAbsent(folderName, k -> new FolderOP());
                }
            }
            calculateSignatures(root, new HashMap<>());
            List<List<String>> result = new ArrayList<>();
            buildResult(root, new ArrayList<>(), result);
            return result;
        }
    }

    //-------------------------------------------------------------------------//
    // Solution 3: Best / Cleanest Solution (Stateless Node)                   //
    //-------------------------------------------------------------------------//
    static class SolutionBest {
        private static class FolderBest {
            Map<String, FolderBest> children = new TreeMap<>();
        }

        private String computeSignatures(FolderBest node, Map<String, Integer> signatureCounts) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, FolderBest> entry : node.children.entrySet()) {
                sb.append('(').append(entry.getKey()).append(computeSignatures(entry.getValue(), signatureCounts)).append(')');
            }
            String signature = sb.toString();
            if (!signature.isEmpty()) {
                signatureCounts.put(signature, signatureCounts.getOrDefault(signature, 0) + 1);
            }
            return signature;
        }

        private void collectPaths(FolderBest node, List<String> currentPath, Map<String, Integer> signatureCounts, List<List<String>> result) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, FolderBest> entry : node.children.entrySet()) {
                sb.append('(').append(entry.getKey()).append(computeSignatures(entry.getValue(), new HashMap<>())).append(')');
            }
            String signature = sb.toString();

            if (signatureCounts.getOrDefault(signature, 0) > 1) {
                return;
            }

            if (!currentPath.isEmpty()) {
                result.add(new ArrayList<>(currentPath));
            }

            for (Map.Entry<String, FolderBest> entry : node.children.entrySet()) {
                currentPath.add(entry.getKey());
                collectPaths(entry.getValue(), currentPath, signatureCounts, result);
                currentPath.remove(currentPath.size() - 1);
            }
        }

        public List<List<String>> deleteDuplicateFolder(List<List<String>> paths) {
            FolderBest root = new FolderBest();
            for (List<String> path : paths) {
                FolderBest current = root;
                for (String folderName : path) {
                    current = current.children.computeIfAbsent(folderName, k -> new FolderBest());
                }
            }
            Map<String, Integer> signatureCounts = new HashMap<>();
            computeSignatures(root, signatureCounts);
            List<List<String>> result = new ArrayList<>();
            collectPaths(root, new ArrayList<>(), signatureCounts, result);
            return result;
        }
    }
}