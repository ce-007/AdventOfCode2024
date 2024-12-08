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
                    for (int l = 0; l < size; l++) {
                        int x = (coordinates.get(k).x - (coordinates.get(j).x - coordinates.get(k).x) * l);
                        int y = (coordinates.get(k).y - (coordinates.get(j).y - coordinates.get(k).y) * l);
                        if (!antinode.contains(new Coordinates(x, y)) && x > -1 && y > -1 && x < size && y < size) {
                            antinode.add(new Coordinates(x, y));
                        }
                    }
                }
            }
        }
        System.out.println(antinode.size());
    }
}

