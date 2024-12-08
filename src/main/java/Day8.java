import util.Coordinates;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day8 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("inputDay8");
        Scanner scanner = new Scanner(file);
        List<char[]> map = new ArrayList<>();
        while (scanner.hasNextLine()) {
            map.add(scanner.nextLine().toCharArray());
        }
        Map<Character, List<Coordinates>> characterMap = new HashMap<>();
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).length; j++) {
                if (Character.isAlphabetic(map.get(i)[j]) || Character.isDigit(map.get(i)[j])) {
                    if (!characterMap.containsKey(map.get(i)[j])) {
                        characterMap.put(map.get(i)[j], new ArrayList<>());
                    }
                    characterMap.get(map.get(i)[j]).add(new Coordinates(j, i));
                }
            }
        }
        int size = map.getFirst().length;
        List<Coordinates> antinode = new ArrayList<>();
        for (int i = 0; i < characterMap.size(); i++) {
            List<Coordinates> coordinates = characterMap.get(characterMap.keySet().toArray()[i]);
            for (int j = 0; j < coordinates.size(); j++) {
                for (int k = 0; k < coordinates.size(); k++) {
                    int x = coordinates.get(j).x - coordinates.get(k).x;
                    int y = coordinates.get(j).y - coordinates.get(k).y;
                    int addX = coordinates.get(j).x + x;
                    int addY = coordinates.get(j).y + y;
                    if (!((coordinates.get(j).x == addX && coordinates.get(j).y == addY) || (coordinates.get(k).x == addX && coordinates.get(k).y == addY))) {
                        if (!antinode.contains(new Coordinates(addX, addY)) && addX > -1 && addY > -1 && addX < size && addY < size) {
                            antinode.add(new Coordinates(addX, addY));
                        }
                    }
                    int subX = coordinates.get(k).x - x;
                    int subY = coordinates.get(k).y - y;
                    if (!((coordinates.get(j).x == subX && coordinates.get(j).y == subY) || (coordinates.get(k).x == subY && coordinates.get(k).y == subY))) {
                        if (!antinode.contains(new Coordinates(subX, subY)) && subX > -1 && subY > -1 && subX < size && subY < size) {
                            antinode.add(new Coordinates(subX, subY));
                        }
                    }
                }
            }
        }
        System.out.println(antinode.size());
    }
}

