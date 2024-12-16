import util.Coordinates;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day14 {
    public static int length = 103;
    public static int width = 101;

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("inputDay14");
        Scanner scanner = new Scanner(file);
        List<Robot> robots = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String[] robot = scanner.nextLine().split(",");
            robots.add(new Robot(new Coordinates(Integer.parseInt(robot[0].substring(2)), Integer.parseInt(robot[1].substring(0, robot[1].indexOf(' ')))), Integer.parseInt(robot[1].substring(robot[1].indexOf('=') + 1)), Integer.parseInt(robot[2])));
        }

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < robots.size(); j++) {
                robots.get(j).moveRobot();
            }
        }

        int[] quads = {0, 0, 0, 0};
        for (int i = 0; i < robots.size(); i++) {
            if (robots.get(i).coordinates.x != width / 2 && robots.get(i).coordinates.y != length / 2) {
                if (robots.get(i).coordinates.x < width / 2) {
                    if (robots.get(i).coordinates.y < length / 2) {
                        quads[0]++;
                    } else if (robots.get(i).coordinates.y > length / 2) {
                        quads[1]++;
                    }
                } else if (robots.get(i).coordinates.x > width / 2) {
                    if (robots.get(i).coordinates.y < length / 2) {
                        quads[2]++;
                    } else if (robots.get(i).coordinates.y > length / 2) {
                        quads[3]++;
                    }
                }
            }
        }
        long total = 1;
        for (int i = 0; i < quads.length; i++) {
            total *= quads[i];
        }
        System.out.println(total);
    }
}

class Robot {
    Coordinates coordinates;
    int verVelocity;
    int horVelocity;


    public Robot(Coordinates coordinates, int horVelocity, int verVelocity) {
        this.coordinates = coordinates;
        this.verVelocity = verVelocity;
        this.horVelocity = horVelocity;
    }

    public void moveRobot() {
        coordinates.y += verVelocity;
        coordinates.y %= Day14.length;
        if (coordinates.y < 0) {
            coordinates.y = Day14.length + coordinates.y;
        }
        coordinates.x += horVelocity;
        coordinates.x %= Day14.width;
        if (coordinates.x < 0) {
            coordinates.x = Day14.width + coordinates.x;
        }
    }
}
