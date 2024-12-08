import util.Coordinates;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day6 {
    public static void main(String[] args) throws FileNotFoundException {
        int initialX = 0;
        int initialY = 0;
        int x = 0;
        int y = 0;
        int direction = 0;
        int partTwo = 0;
        List<char[]> map = new ArrayList<>();
        List<Coordinates> listOfPositions = new ArrayList<>();
        File file = new File("inputDay6");
        Scanner scanner = new Scanner(file);

        int counter = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            map.add(line.toCharArray());
            if (line.contains("^")) {
                x = line.indexOf('^');
                y = counter;
                initialX = x;
                initialY = y;
            }
            counter++;
        }

        while (true) {
            try {
                boolean canEnter = false;
                if (direction % 4 == 0 && map.get(y - 1)[x] == '#') {
                    direction++;
                } else if (direction % 4 == 0) {
                    canEnter = true;
                    y--;
                }
                if ((direction % 4) == 1 && map.get(y)[x + 1] == '#') {
                    direction++;
                } else if (direction % 4 == 1) {
                    canEnter = true;
                    x++;
                }
                if (direction % 4 == 2 && map.get(y + 1)[x] == '#') {
                    direction++;
                } else if (direction % 4 == 2) {
                    canEnter = true;
                    y++;
                }
                if (direction % 4 == 3 && map.get(y)[x - 1] == '#') {
                    direction++;
                } else if (direction % 4 == 3) {
                    canEnter = true;
                    x--;
                }
                if (canEnter) {
                    if (map.get(y)[x] != 'X') {
                        listOfPositions.add(new Coordinates(x, y));
                    }
                    map.get(y)[x] = 'X';
                }
            } catch (Exception e) {
                break;
            }
        }
        System.out.println("Part one: " + listOfPositions.size());
        for (int i = 0; i < listOfPositions.size(); i++) {
            map.get(listOfPositions.get(i).y)[listOfPositions.get(i).x] = '#';
            partTwo += checkIfLoops(map, initialX,initialY,0);
            map.get(listOfPositions.get(i).y)[listOfPositions.get(i).x] = '.';
        }
        System.out.println("Part two: " + partTwo);
    }

    private static int checkIfLoops(List<char[]> modifiedMap, int x, int y, int direction) {
        for (int i = 0; i < modifiedMap.size()*1000; i++) {
            try {
                if (direction % 4 == 0 && modifiedMap.get(y - 1)[x] == '#') {
                    direction++;
                } else if (direction % 4 == 0) {
                    y--;
                }
                if ((direction % 4) == 1 && modifiedMap.get(y)[x + 1] == '#') {
                    direction++;
                } else if (direction % 4 == 1) {
                    x++;
                }
                if (direction % 4 == 2 && modifiedMap.get(y + 1)[x] == '#') {
                    direction++;
                } else if (direction % 4 == 2) {
                    y++;
                }
                if (direction % 4 == 3 && modifiedMap.get(y)[x - 1] == '#') {
                    direction++;
                } else if (direction % 4 == 3) {
                    x--;
                }
            } catch (Exception e) {
                return 0;
            }
        }
        return 1;
    }
}
