import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day6 {
    public static void main(String[] args) throws FileNotFoundException {
        int x = 0;
        int y = 0;
        int direction = 0;
        List<char[]> map = new ArrayList<>();
        File file = new File("inputDay6");
        Scanner scanner = new Scanner(file);

        int counter = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            map.add(line.toCharArray());
            if (line.contains("^")) {
                x = line.indexOf('^');
                y = counter;
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
                    map.get(y)[x] = 'X';
                }
            } catch (Exception e) {
                break;
            }
        }
        int out = 0;
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).length; j++) {
                if (map.get(i)[j] == 'X') {
                    out++;
                }
            }
        }
        System.out.println(out);
    }
}
