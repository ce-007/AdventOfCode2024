import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Day7 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("inputDay7");
        Scanner scanner = new Scanner(file);
        long totalP1 = 0;
        long totalP2 = 0;
        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(": ");
            if (checkIfCouldBeTrue(Long.parseLong(line[0]), List.of(line[1].split(" ")).stream().map(Long::parseLong).collect(Collectors.toList()), 3)) {
                totalP2 += Long.parseLong(line[0]);
            }
            if (checkIfCouldBeTrue(Long.parseLong(line[0]), List.of(line[1].split(" ")).stream().map(Long::parseLong).collect(Collectors.toList()), 2)) {
                totalP1 += Long.parseLong(line[0]);
            }
        }
        System.out.println("Part one: " + totalP1);
        System.out.println("Part two: " + totalP2);
    }

    private static boolean checkIfCouldBeTrue(long outcome, List<Long> input, int power) {
        int[] operators = new int[input.size() - 1];
        for (int i = 0; i < operators.length; i++) {
            for (int j = 0; j < Math.pow(power, operators.length); j++) {
                for (int k = 1; k < operators.length + 1; k++) {
                    int temp = (i + j) % k;
                    operators[temp]++;
                    operators[temp] %= power;
                    if (checkIfSame(input, operators) == outcome) {
                        return true;
                    }
                }
                operators[i]++;
                operators[i] %= power;
                if (checkIfSame(input, operators) == outcome) {
                    return true;
                }
            }
        }
        return false;
    }

    private static long checkIfSame(List<Long> input, int[] operators) {
        long total = input.getFirst();
        for (int k = 0; k < operators.length; k++) {
            if (operators[k] == 0) {
                total *= input.get(k + 1);
            } else if (operators[k] == 1) {
                total += input.get(k + 1);
            } else {
                String string = total + input.get(k + 1).toString();
                total = Long.parseLong(string);
            }
        }
        return total;
    }
}
