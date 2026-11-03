
import java.util.List;

public class Main {

    public static void main(String[] args) {

        CourseGraph graph = buildCourseGraph();

        List<String> order = graph.topologicalSort();

        System.out.println(
                "CS 3364 Project 2: Course Topological Ordering");

        System.out.println(
                "Total courses: " + graph.courseCount());

        System.out.println(
                "Prerequisite relationships: " +
                graph.dependencyCount());

        System.out.println("\nValid course order:");

        for (int i = 0; i < order.size(); i++) {
            System.out.printf(
                    "%2d. %s%n", i + 1, order.get(i));
        }

        System.out.println(
                "\nAll prerequisites satisfied: " +
                graph.isValidOrder(order));

        runTests();
    }

    private static CourseGraph buildCourseGraph() {

        CourseGraph graph = new CourseGraph();

        // All 29 required courses.
        String[] courses = {

            "CS 1411",
            "MATH 1451",
            "ENGL 1301",
            "CS 1412",
            "MATH 1452",
            "PHYS 1408",
            "ENGL 1302",
            "CS 2413",
            "CS 1382",
            "ECE 2372",
            "MATH 2450",
            "PHYS 2401",
            "CS 2350",
            "CS 2365",
            "ENGR 2392",
            "POLS 1301",
            "MATH 2360",
            "ENGL 2311",
            "CS 3361",
            "CS 3364",
            "MATH 3342",
            "POLS 2306",
            "CS 3365",
            "CS 3375",
            "CS 3383",
            "CS 4365",
            "CS 4352",
            "CS 4354",
            "CS 4366"
        };

        for (String course : courses) {
            graph.addCourse(course);
        }


        graph.addPrerequisite("CS 1411", "CS 1412");

        graph.addPrerequisite("MATH 1451", "MATH 1452");
        graph.addPrerequisite("MATH 1451", "PHYS 1408");
        graph.addPrerequisite("ENGL 1301", "ENGL 1302");

        graph.addPrerequisite("CS 1412", "CS 2413");
        graph.addPrerequisite("CS 1411", "CS 1382");
        graph.addPrerequisite("MATH 1451", "ECE 2372");

        graph.addPrerequisite("MATH 1452", "MATH 2450");
        graph.addPrerequisite("PHYS 1408", "PHYS 2401");

        graph.addPrerequisite("CS 1412", "CS 2350");
        graph.addPrerequisite("ECE 2372", "CS 2350");

        graph.addPrerequisite("CS 2413", "CS 2365");

        graph.addPrerequisite("MATH 1452", "MATH 2360");

        graph.addPrerequisite("ENGL 1301", "ENGL 2311");
        graph.addPrerequisite("ENGL 1302", "ENGL 2311");

        graph.addPrerequisite("CS 2413", "CS 3361");

        graph.addPrerequisite("CS 2413", "CS 3364");
        graph.addPrerequisite("CS 1382", "CS 3364");
        graph.addPrerequisite("MATH 2360", "CS 3364");

        graph.addPrerequisite("MATH 2450", "MATH 3342");

        graph.addPrerequisite("CS 2365", "CS 3365");
        graph.addPrerequisite("CS 2413", "CS 3365");
        graph.addPrerequisite("MATH 3342", "CS 3365");

        graph.addPrerequisite("CS 2350", "CS 3375");

        graph.addPrerequisite("CS 1382", "CS 3383");

        graph.addPrerequisite("CS 3365", "CS 4365");

        graph.addPrerequisite("CS 3364", "CS 4352");
        graph.addPrerequisite("CS 3375", "CS 4352");

        graph.addPrerequisite("CS 3364", "CS 4354");

        graph.addPrerequisite("CS 4365", "CS 4366");

        return graph;
    }

    // Test the DFS topological sorting algorithm.
    private static void runTests() {

        CourseGraph simple = new CourseGraph();

        simple.addPrerequisite("A", "B");
        simple.addPrerequisite("B", "C");
        simple.addPrerequisite("A", "C");

        boolean dagPassed =
                simple.isValidOrder(simple.topologicalSort());

        CourseGraph cyclic = new CourseGraph();

        cyclic.addPrerequisite("A", "B");
        cyclic.addPrerequisite("B", "C");
        cyclic.addPrerequisite("C", "A");

        boolean cycleDetected = false;

        try {
            cyclic.topologicalSort();
        } catch (IllegalStateException exception) {
            cycleDetected = true;
        }

        System.out.println(
                "Simple DAG test passed: " + dagPassed);

        System.out.println(
                "Cycle detection test passed: " + cycleDetected);
    }
}
