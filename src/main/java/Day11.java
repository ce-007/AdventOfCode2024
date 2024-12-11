import util.Coordinates;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day11 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("inputDay11");
        Scanner scanner = new Scanner(file);
        List<String> rawInput = List.of(scanner.nextLine().split(" "));
        List<Long> input = new ArrayList<>();
        for (int i = 0; i < rawInput.size(); i++) {
            input.add(Long.parseLong(rawInput.get(i)));
        }

        scanner.close();
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < input.size(); j++) {
                if (input.get(j) == 0L) {
                    input.set(j, 1L);
                } else if (input.get(j).toString().length() % 2 == 0) {
                    int size = input.get(j).toString().length();
                    String temp = input.get(j).toString();
                    input.set(j, Long.parseLong(temp.substring(0, size / 2)));
                    input.add(j + 1, Long.parseLong(temp.substring(size / 2)));

                    j++;
                } else {
                    input.set(j, input.get(j) * 2024);
                }
            }
        }

        System.out.println(input.size());
    }
}
