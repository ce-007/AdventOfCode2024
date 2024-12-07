import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day7 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("inputDay7");
        Scanner scanner = new Scanner(file);
        long total = 0;
        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(": ");
            if (checkIfCouldBeTrue(Long.parseLong(line[0]), List.of(line[1].split(" ")))) {
                total += Long.parseLong(line[0]);
            }
        }
        System.out.println(total);
    }

    private static boolean checkIfCouldBeTrue(long outcome, List<String> input) {
        boolean[] operators = new boolean[input.size() - 1];
        for (int i = 0; i < operators.length; i++) {
            for (int j = 0; j < Math.pow(2,operators.length); j++) {
                for (int k = 1; k < operators.length + 1; k++) {
                    operators[(j % k)] = !operators[(j % k)];
                }
                if (checkIfSame(input, operators) == outcome) {
                    return true;
                }
            }
            operators[i] = !operators[i];
        }
        return false;
    }

    private static long checkIfSame(List<String> input, boolean[] operators) {
        long total = Long.parseLong(input.getFirst());
        for (int k = 0; k < operators.length; k++) {
            if (operators[k]) {
                total *= Long.parseLong(input.get(k + 1));
            } else {
                total += Long.parseLong(input.get(k + 1));
            }
        }
        return total;
    }
}
