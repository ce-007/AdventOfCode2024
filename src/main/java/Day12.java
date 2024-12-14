import util.Coordinates;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Day12 {
    public static List<List<Character>> map = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("inputDay12");
        Scanner scanner = new Scanner(file);
        int h = 0;
        while (scanner.hasNextLine()) {
            map.add(new String(scanner.nextLine().toCharArray()).chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
            map.get(h).add(0, (char) -1);
            map.get(h).add((char) -1);
            h++;
        }
        List<Character> temp = new ArrayList<>();
        for (int i = 0; i < map.getFirst().size(); i++) {
            temp.add((char) -1);
        }
        map.add(0, temp);
        map.add(temp);

        scanner.close();
        long total = 0;
        for (int i = 'A'; i < 'A' + 26; i++) {
            char currentChar = (char) i;
            List<Coordinates> coordinates = new ArrayList<>();
            for (int j = 0; j < map.size(); j++) {
                if (map.get(j).contains(currentChar)) {
                    for (int k = 0; k < map.get(j).size(); k++) {
                        if (map.get(j).get(k) == currentChar) {
                            coordinates.add(new Coordinates(k, j));
                        }
                    }
                }
            }
            System.out.println(currentChar + " " + getPrice(coordinates));
            total += getPrice(coordinates);
        }
        System.out.println(total);//ex should be 1930
    }

    private static long getPrice(List<Coordinates> coordinates) {
        List<List<Coordinates>> shapes = getShape(coordinates);
        long total = 0;
        for (int i = 0; i < shapes.size(); i++) {
            long p = 0;
            for (int j = 0; j < shapes.get(i).size(); j++) {
                p += 4 - numberOfSameAdjacent(shapes.get(i).get(j));
            }
            total += p * shapes.get(i).size();
        }
        return total;
    }

    private static List<List<Coordinates>> getShape(List<Coordinates> coordinates) {
        List<List<Coordinates>> shapes = new ArrayList<>();
        Set<Coordinates> visited = new HashSet<>();

        for (Coordinates coordinate : coordinates) {
            if (!visited.contains(coordinate)) {
                List<Coordinates> shape = new ArrayList<>();
                buildShape(coordinate, visited, shape, coordinates);
                shapes.add(shape);
            }
        }
        return shapes;
    }

    private static void buildShape(Coordinates start, Set<Coordinates> visited, List<Coordinates> shape, List<Coordinates> coordinates) {
        Queue<Coordinates> queue = new LinkedList<>();
        queue.offer(start);
        visited.add(start);
        while (!queue.isEmpty()) {
            Coordinates current = queue.poll();
            shape.add(current);
            for (Coordinates neighbor : getNeighbors(current, coordinates)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
    }

    private static List<Coordinates> getNeighbors(Coordinates coordinate, List<Coordinates> coordinates) {
        List<Coordinates> neighbors = new ArrayList<>();
        for (int i = 0; i < coordinates.size(); i++) {
            if ((Math.abs(coordinates.get(i).x - coordinate.x) == 1 && coordinates.get(i).y == coordinate.y) || (Math.abs(coordinates.get(i).y - coordinate.y) == 1 && coordinates.get(i).x == coordinate.x)) {
                neighbors.add(coordinates.get(i));
            }
        }
        return neighbors;
    }


    private static int numberOfSameAdjacent(Coordinates coordinates) {
        char character = map.get(coordinates.y).get(coordinates.x);
        int p = 0;
        if (map.get(coordinates.y + 1).get(coordinates.x) == character) {
            p++;
        }
        if (map.get(coordinates.y).get(coordinates.x + 1) == character) {
            p++;
        }
        if (map.get(coordinates.y - 1).get(coordinates.x) == character) {
            p++;
        }
        if (map.get(coordinates.y).get(coordinates.x - 1) == character) {
            p++;
        }
        return p;
    }
}