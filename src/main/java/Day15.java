import util.Coordinates;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day15 {
    static Coordinates robot = null;

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("inputDay15");
        Scanner scanner = new Scanner(file);
        boolean part2 = false;
        List<char[]> map = new ArrayList<>();
        StringBuilder instructions = new StringBuilder();
        int index = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                part2 = true;
                line = scanner.nextLine();
            }
            if (!part2) {
                map.add(line.toCharArray());
                if (line.contains("@")) {
                    robot = new Coordinates(line.indexOf("@"), index);
                }
                index++;
            } else {
                instructions.append(line);
            }
        }

        for (int i = 0; i < instructions.length(); i++) {
            char in = instructions.charAt(i);
            if (in == '^') {
                move(map, 0);
            } else if (in == '<') {
                move(map, 1);
            } else if (in == 'v') {
                move(map, 2);
            } else if (in == '>') {
                move(map, 3);
            }
        }
        long total = 0;
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).length; j++) {
                if (map.get(i)[j] == 'O') {
                    total += j + (i * 100L);
                }
            }
        }
        System.out.println(total);
    }

    private static void move(List<char[]> map, int direction) {
        if (direction == 0) {
            int y = robot.y;
            Character c = map.get(y - 1)[robot.x];
            while (true) {
                if (c.equals('.')) {
                    if (!(robot.y - y > 0)) {
                        char temp = map.get(robot.y)[robot.x];
                        map.get(robot.y)[robot.x] = map.get(robot.y - 1)[robot.x];
                        map.get(robot.y - 1)[robot.x] = temp;
                        robot.y--;
                    }
                    break;
                } else if (c.equals('#')) {
                    y = robot.y;
                    break;
                }
                y--;
                c = map.get(y - 1)[robot.x];
            }
            if (robot.y - y > 0) {
                int a = 1;

                char temp = map.get(robot.y)[robot.x];
                map.get(robot.y)[robot.x] = map.get(robot.y - a)[robot.x];
                map.get(robot.y - a)[robot.x] = temp;
                temp = map.get(robot.y)[robot.x];
                map.get(robot.y)[robot.x] = map.get(y - a)[robot.x];
                map.get(y - a)[robot.x] = temp;
                robot.y--;
            }
        } else if (direction == 1) {
            int x = robot.x;
            Character c = map.get(robot.y)[x - 1];
            while (true) {
                if (c.equals('.')) {
                    if (!(robot.x - x > 0)) {
                        char temp = map.get(robot.y)[robot.x];
                        map.get(robot.y)[robot.x] = map.get(robot.y)[robot.x - 1];
                        map.get(robot.y)[robot.x - 1] = temp;
                        robot.x--;
                    }
                    break;
                } else if (c.equals('#')) {
                    x = robot.x;
                    break;
                }
                x--;
                c = map.get(robot.y)[x - 1];
            }
            if (robot.x - x > 0) {
                char temp = map.get(robot.y)[robot.x];
                map.get(robot.y)[robot.x] = map.get(robot.y)[robot.x - 1];
                map.get(robot.y)[robot.x - 1] = temp;
                temp = map.get(robot.y)[robot.x];
                map.get(robot.y)[robot.x] = map.get(robot.y)[x - 1];
                map.get(robot.y)[x - 1] = temp;
                robot.x--;
            }
        } else if (direction == 2) {
            int y = robot.y;
            Character c = map.get(y + 1)[robot.x];
            while (true) {
                if (c.equals('.')) {
                    if (!(y - robot.y > 0)) {
                        char temp = map.get(robot.y)[robot.x];
                        map.get(robot.y)[robot.x] = map.get(robot.y + 1)[robot.x];
                        map.get(robot.y + 1)[robot.x] = temp;
                        robot.y++;
                    }
                    break;
                } else if (c.equals('#')) {
                    y = robot.y;
                    break;
                }
                y++;
                c = map.get(y + 1)[robot.x];
            }
            if (y - robot.y > 0) {
                int a = 1;

                char temp = map.get(robot.y)[robot.x];
                map.get(robot.y)[robot.x] = map.get(robot.y + a)[robot.x];
                map.get(robot.y + a)[robot.x] = temp;
                temp = map.get(robot.y)[robot.x];
                map.get(robot.y)[robot.x] = map.get(y + a)[robot.x];
                map.get(y + a)[robot.x] = temp;
                robot.y++;
            }
        } else if (direction == 3) {
            int x = robot.x;
            Character c = map.get(robot.y)[x + 1];
            while (true) {
                if (c.equals('.')) {
                    if (!(x - robot.x > 0)) {
                        char temp = map.get(robot.y)[robot.x];
                        map.get(robot.y)[robot.x] = map.get(robot.y)[robot.x + 1];
                        map.get(robot.y)[robot.x + 1] = temp;
                        robot.x++;
                    }
                    break;
                } else if (c.equals('#')) {
                    x = robot.x;
                    break;
                }
                x++;
                c = map.get(robot.y)[x + 1];
            }
            if (x - robot.x > 0) {
                char temp = map.get(robot.y)[robot.x];
                map.get(robot.y)[robot.x] = map.get(robot.y)[robot.x + 1];
                map.get(robot.y)[robot.x + 1] = temp;
                temp = map.get(robot.y)[robot.x];
                map.get(robot.y)[robot.x] = map.get(robot.y)[x + 1];
                map.get(robot.y)[x + 1] = temp;
                robot.x++;
            }
        }
    }
}
