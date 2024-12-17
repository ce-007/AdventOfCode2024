import util.Coordinates;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day16 {
    private static List<char[]> map = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("inputDay16");
        Scanner scanner = new Scanner(file);
        Coordinates start = null;
        Coordinates end = null;

        int row = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.contains("S")) {
                start = new Coordinates(line.indexOf("S"), row);
            }
            if (line.contains("E")) {
                end = new Coordinates(line.indexOf("E"), row);
            }
            map.add(line.toCharArray());
            row++;
        }

        int shortestPath = findShortestPath(start, end);
        System.out.println("Lowest Cost Path: " + shortestPath + 1000);
    }

    private static int findShortestPath(Coordinates start, Coordinates end) {
        int[][] directions = { {0, -1}, {1, 0}, {0, 1}, {-1, 0} };
        PriorityQueue<State> queue = new PriorityQueue<>(Comparator.comparingInt(state -> state.cost));
        Map<State, Integer> visited = new HashMap<>();

        for (int d = 0; d < 4; d++) {
            State initialState = new State(start.x, start.y, d, 0);
            queue.add(initialState);
            visited.put(initialState, 0);
        }
        while (!queue.isEmpty()) {
            State current = queue.poll();
            if (current.x == end.x && current.y == end.y) {
                return current.cost;
            }
            for (int nextDir = 0; nextDir < 4; nextDir++) {
                int newX = current.x + directions[nextDir][0];
                int newY = current.y + directions[nextDir][1];
                if (!isValid(newX, newY)) {
                    continue;
                }
                int moveCost = 1;
                int turnCost = (nextDir == current.direction) ? 0 : 1000;
                int newCost = current.cost + moveCost + turnCost;
                State nextState = new State(newX, newY, nextDir, newCost);
                if (!visited.containsKey(nextState) || newCost < visited.get(nextState)) {
                    visited.put(nextState, newCost);
                    queue.add(nextState);
                }
            }
        }
        return -1;
    }

    private static boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && y < map.size() && x < map.get(y).length && map.get(y)[x] != '#';
    }
}

class State {
    int x, y, direction, cost;

    public State(int x, int y, int direction, int cost) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return x == state.x && y == state.y && direction == state.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, direction);
    }
}
