package Questions_july_2025;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _1233 {

    // Helper class to contain the different solutions

    // --- Approach 1: Brute-Force ---
    // Time Complexity: O(N^2 * L), where N is the number of folders and L is the average length.
    // Space Complexity: O(N*L) to store the result list.
    // Explanation: We compare every folder with every other folder. This is very slow
    // because for each folder, we iterate through the entire list again.
    static class BruteForceSolution {
        public List<String> removeSubfolders(String[] folder) {
            List<String> result = new ArrayList<>();
            for (int i = 0; i < folder.length; i++) {
                boolean isSubfolder = false;
                for (int j = 0; j < folder.length; j++) {
                    if (i == j) continue; // Don't compare a folder with itself

                    // Check if folder[i] is a sub-folder of folder[j]
                    // It must start with the parent path AND be followed by a '/'
                    if (folder[i].startsWith(folder[j] + "/")) {
                        isSubfolder = true;
                        break; // Found a parent, no need to check further
                    }
                }
                if (!isSubfolder) {
                    result.add(folder[i]);
                }
            }
            return result;
        }
    }


    // --- Approach 2: Sorting ---
    // Time Complexity: O(N*log(N) * L) for sorting, plus O(N*L) for the loop. Dominant part is sorting.
    // Space Complexity: O(L) for storing the last valid folder, or O(N*L) for the result list.
    // Explanation: Sorting the folders lexicographically places parent folders directly before their
    // sub-folders. This allows us to find all sub-folders in a single pass.
    static class SortingSolution {
        public List<String> removeSubfolders(String[] folder) {
            List<String> result = new ArrayList<>();
            if (folder == null || folder.length == 0) {
                return result;
            }

            // Sort the array. Example: ["/a", "/a/b", "/c/d", "/c/d/e"]
            Arrays.sort(folder);

            // Add the first folder, as it can't be a sub-folder of anything before it.
            result.add(folder[0]);
            String lastValidFolder = folder[0];

            for (int i = 1; i < folder.length; i++) {
                String currentFolder = folder[i];
                // If the current folder does NOT start with the last valid folder + "/",
                // it means it's not a sub-folder. So, it's a new parent.
                if (!currentFolder.startsWith(lastValidFolder + "/")) {
                    result.add(currentFolder);
                    lastValidFolder = currentFolder;
                }
                // Otherwise, it IS a sub-folder, so we just ignore it and move on.
            }
            return result;
        }
    }


    // --- Approach 3: Trie (Prefix Tree) ---
    // Time Complexity: O(N * L), where N is number of folders and L is average length. We visit each character once.
    // Space Complexity: O(N * L) in the worst case for the Trie itself.
    // Explanation: This is the most efficient approach. We build a tree representing the folder
    // structure. We can instantly detect and discard sub-folders during insertion.
    static class TrieSolution {

        // The node for our Trie data structure
        static class TrieNode {
            Map<String, TrieNode> children = new HashMap<>();
            // We use 'ref' to store the full path if this node is an end-point.
            // This avoids having to rebuild the path during retrieval.
            String ref = null;
        }

        public List<String> removeSubfolders(String[] folder) {
            TrieNode root = new TrieNode();

            // 1. Build the Trie
            for (String path : folder) {
                String[] parts = path.substring(1).split("/"); // Split "/a/b" into ["a", "b"]
                TrieNode current = root;
                for (String part : parts) {
                    // If a parent folder already exists (current.ref != null), this is a sub-folder.
                    // We can stop processing this path immediately.
                    if (current.ref != null) {
                        break;
                    }
                    current.children.putIfAbsent(part, new TrieNode());
                    current = current.children.get(part);
                }
                // Mark the end of the path, but only if it wasn't a sub-folder
                if (current.ref == null) {
                    current.ref = path;
                }
            }

            // 2. Collect results using DFS traversal
            List<String> result = new ArrayList<>();
            collectPaths(root, result);
            return result;
        }

        private void collectPaths(TrieNode node, List<String> result) {
            // If this node represents a complete path, it's a root folder.
            if (node.ref != null) {
                result.add(node.ref);
                return; // IMPORTANT: Don't explore children of a valid folder.
            }
            // If it's not an end-point, continue exploring its children.
            for (TrieNode child : node.children.values()) {
                collectPaths(child, result);
            }
        }
    }

    public static void main(String[] args) {
        String[] folder = {"/a","/a/b","/c/d","/c/d/e","/c/f"};

        System.out.println("--- Brute-Force Solution ---");
        BruteForceSolution brute = new BruteForceSolution();
        System.out.println(brute.removeSubfolders(folder)); // Expected: [/a, /c/d, /c/f]

        System.out.println("\n--- Sorting Solution ---");
        SortingSolution sorting = new SortingSolution();
        System.out.println(sorting.removeSubfolders(folder)); // Expected: [/a, /c/d, /c/f]

        System.out.println("\n--- Trie Solution ---");
        TrieSolution trie = new TrieSolution();
        System.out.println(trie.removeSubfolders(folder)); // Expected: [/a, /c/d, /c/f]
    }
}

