import util.Coordinates;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day10 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("inputDay10");
        Scanner scanner = new Scanner(file);
        List<char[]> tempMap = new ArrayList<>();
        List<List<Integer>> map = new ArrayList<>();
        List<Coordinates> zeros = new ArrayList<>();

        while (scanner.hasNextLine()) {
            tempMap.add(scanner.nextLine().toCharArray());
        }
        scanner.close();

        for (int i = 0; i < tempMap.size(); i++) {
            List<Integer> temp = new ArrayList<>();
            temp.add(-1);
            for (int j = 0; j < tempMap.get(i).length; j++) {
                temp.add(Character.getNumericValue(tempMap.get(i)[j]));
            }
            temp.add(-1);
            map.add(temp);
        }

        List<Integer> boundaryRow = new ArrayList<>();
        for (int j = 0; j < map.getFirst().size(); j++) {
            boundaryRow.add(-1);
        }
        map.addFirst(boundaryRow);
        map.add(boundaryRow);

        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).size(); j++) {
                if (map.get(i).get(j) == 0) {
                    zeros.add(new Coordinates(j, i));
                }
            }
        }
        int total = 0;

        for (Coordinates zero : zeros) {
            List<List<Integer>> localMap = copyMap(map);
            total += calculate(zero, localMap);
        }

        System.out.println("Total: " + total);
    }

    private static List<List<Integer>> copyMap(List<List<Integer>> original) {
        List<List<Integer>> copy = new ArrayList<>();
        for (List<Integer> row : original) {
            copy.add(new ArrayList<>(row));
        }
        return copy;
    }

    private static int calculate(Coordinates start, List<List<Integer>> localMap) {
        int x = start.x;
        int y = start.y;

        List<List<Coordinates>> allPaths = new ArrayList<>();
        List<Coordinates> currentPath = new ArrayList<>();
        explorePaths(x, y, localMap, new boolean[localMap.size()][localMap.get(0).size()], currentPath, allPaths);
        return allPaths.size();
    }

    private static void explorePaths(int x, int y, List<List<Integer>> localMap, boolean[][] visited, List<Coordinates> currentPath, List<List<Coordinates>> allPaths) {
        if (localMap.get(y).get(x) == 9) {
            currentPath.add(new Coordinates(x, y));
            allPaths.add(new ArrayList<>(currentPath));
            currentPath.remove(currentPath.size() - 1);
            return;
        }
        visited[y][x] = true;
        currentPath.add(new Coordinates(x, y));

        for (int[] direction : new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}) {
            int nx = x + direction[0];
            int ny = y + direction[1];

            if (ny >= 0 && ny < localMap.size() && nx >= 0 && nx < localMap.get(ny).size() && !visited[ny][nx] && localMap.get(ny).get(nx) == localMap.get(y).get(x) + 1) {
                explorePaths(nx, ny, localMap, visited, currentPath, allPaths);
            }
        }
        currentPath.remove(currentPath.size() - 1);
        visited[y][x] = false;
    }

}
