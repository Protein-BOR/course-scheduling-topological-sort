
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CourseGraph {

    //Each course points to courses that depend on it.
    private final Map<String, List<String>> adjacency =
            new LinkedHashMap<>();

    private int edgeCount = 0;

    public void addCourse(String name) {
        adjacency.putIfAbsent(name, new ArrayList<>());
    }

    public void addPrerequisite(String prerequisite, String course) {
        addCourse(prerequisite);
        addCourse(course);

        if (!adjacency.get(prerequisite).contains(course)) {
            adjacency.get(prerequisite).add(course);
            edgeCount++;
        }
    }

    public int courseCount() {
        return adjacency.size();
    }

    public int dependencyCount() {
        return edgeCount;
    }

    public List<String> topologicalSort() {

        // 0 = unvisited, 1 = visiting, 2 = finished
        Map<String, Integer> state = new HashMap<>();
        List<String> finished = new ArrayList<>();

        for (String course : adjacency.keySet()) {
            if (state.getOrDefault(course, 0) == 0) {
                dfs(course, state, finished);
            }
        }

        // Reverse DFS finishing order.
        Collections.reverse(finished);
        return finished;
    }

    private void dfs(
            String course,
            Map<String, Integer> state,
            List<String> finished) {

        state.put(course, 1);

        for (String next : adjacency.get(course)) {
            int status = state.getOrDefault(next, 0);

            if (status == 1) {
                throw new IllegalStateException(
                        "Cycle detected involving " + next);
            }

            if (status == 0) {
                dfs(next, state, finished);
            }
        }

        state.put(course, 2);
        finished.add(course);
    }

    public boolean isValidOrder(List<String> order) {

        if (order.size() != courseCount() ||
                new HashSet<>(order).size() != courseCount()) {
            return false;
        }

        Map<String, Integer> position = new HashMap<>();

        for (int i = 0; i < order.size(); i++) {
            position.put(order.get(i), i);
        }

        if (!position.keySet().equals(adjacency.keySet())) {
            return false;
        }

        for (Map.Entry<String, List<String>> entry :
                adjacency.entrySet()) {

            for (String course : entry.getValue()) {

                if (position.get(entry.getKey()) >=
                        position.get(course)) {
                    return false;
                }
            }
        }

        return true;
    }
}
