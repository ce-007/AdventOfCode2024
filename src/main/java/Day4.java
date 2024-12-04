import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day4 {
    public static void main(String[] args) throws FileNotFoundException {
        int total = 0;
        int total2 = 0;
        File file = new File("inputDay4");
        Scanner scanner = new Scanner(file);
        List<char[]> data = new ArrayList<>();
        while (scanner.hasNextLine()) {
            data.add(scanner.nextLine().toCharArray());
        }

        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < data.get(i).length; ++j) {
                if (data.get(i).length - j > 3) {
                    total += find(data.get(i)[j], data.get(i)[j + 1], data.get(i)[j + 2], data.get(i)[j + 3]);
                }
                if (data.size() - i > 3) {
                    total += find(data.get(i)[j], data.get(i + 1)[j], data.get(i + 2)[j], data.get(i + 3)[j]);
                }
                if (data.get(i).length - j > 3 && data.size() - i > 3) {
                    total += find(data.get(i)[j], data.get(i + 1)[j + 1], data.get(i + 2)[j + 2], data.get(i + 3)[j + 3]);
                    total += find(data.get(i + 3)[j], data.get(i + 2)[j + 1], data.get(i + 1)[j + 2], data.get(i)[j + 3]);
                }
                if (data.get(i).length - j > 2 && data.size() - i > 2) {
                    total2 += find2(data.get(i)[j], data.get(i + 1)[j + 1], data.get(i)[j + 2], data.get(i + 2)[j], data.get(i + 2)[j + 2]);
                }
            }
        }
        System.out.println(total);
        System.out.println(total2);
    }

    private static int find(char a, char b, char c, char d) {
        if (a == 'X' && b == 'M' && c == 'A' && d == 'S' || a == 'S' && b == 'A' && c == 'M' && d == 'X') {
            return 1;
        } else {
            return 0;
        }
    }

    private static int find2(char a, char b, char c, char d, char e) {
        if (a == 'M' && b == 'A' && c == 'S' && d == 'M' && e == 'S' ||
                a == 'S' && b == 'A' && c == 'M' && d == 'S' && e == 'M' ||
                a == 'M' && b == 'A' && c == 'M' && d == 'S' && e == 'S' ||
                a == 'S' && b == 'A' && c == 'S' && d == 'M' && e == 'M') {
            return 1;
        } else {
            return 0;
        }
    }
}
