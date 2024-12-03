import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class Day3 {
    public static void main(String[] args) throws FileNotFoundException {
        boolean addingIsEnabled = true;
        File file = new File("inputDay3");
        Scanner scanner = new Scanner(file);
        int total = 0;
        int total2 = 0;
        StringBuilder data = new StringBuilder();
        while (scanner.hasNextLine()) {
            data.append(scanner.nextLine());
        }
        data.append("mul(0,0)don't()");
        while (!data.isEmpty()) {
            System.out.println(total);
            int index = data.indexOf("mul(");
            int indexOfComma = data.indexOf(",", index);
            int indexOfEnd = data.indexOf(")", indexOfComma);
            if(Objects.equals(data.substring(index + 4, indexOfComma), "0")) {
                System.out.println("Part One: " + total);
                System.out.println("Part Two: " + total2);
                return;
            }
            addingIsEnabled = CheckStatus(data.substring(0,indexOfEnd), addingIsEnabled);
            if (isValid(data.substring(index + 4, indexOfComma), data.substring(indexOfComma + 1, indexOfEnd))) {
                if (addingIsEnabled) {
                    total2 += Integer.parseInt(data.substring(index + 4, indexOfComma)) * Integer.parseInt(data.substring(indexOfComma + 1, indexOfEnd));
                }
                total += Integer.parseInt(data.substring(index + 4, indexOfComma)) * Integer.parseInt(data.substring(indexOfComma + 1, indexOfEnd));
                data.replace(0, indexOfEnd, "");
            } else {
                data.replace(0, index+4, "");
            }
        }
    }

    private static boolean isValid(String a, String b) {
        for (int i = 0; i < a.length(); i++) {
            if (!Character.isDigit(a.charAt(i))) {
                return false;
            }
        }
        for (int i = 0; i < b.length(); i++) {
            if (!Character.isDigit(b.charAt(i))) {
                return false;
            }
        }
        System.out.println("a: " + a);
        System.out.println("b: " + b);
        return true;
    }

    private static boolean CheckStatus(String input, boolean og) {
        System.out.println(input);
        if(input.contains("don't()")){
            System.out.println("efsesffesef");
            return false;
        } else if (input.contains("do()")) {
            return true;
        }
        return og;
    }
}
