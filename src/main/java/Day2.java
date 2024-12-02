import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Day2 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("inputDay2");
        Scanner scanner = new Scanner(file);
        int amount = 0;
        int amount2 = 0;
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            List<String> list = List.of(data.split(" "));
            if (checkStatus(list)) {
                amount++;
                amount2++;
            } else {
                for (int i = 0; i < list.size(); i++) {
                    List<String> tempList = new java.util.ArrayList<>(list);
                    tempList.remove(i);
                    if (checkStatus(tempList)) {
                        amount2++;
                        i = tempList.size();
                    }
                }
            }
        }
        System.out.println("Part One: " + amount);
        System.out.println("Part Two: " + amount2);
    }

    public static boolean checkStatus(List<String> list) {
        boolean out = true;
        int first = Integer.parseInt(list.get(0)) - Integer.parseInt(list.get(1));
        for (int i = 0; i < list.size() - 1; i++) {
            int a = Integer.parseInt(list.get(i));
            int b = Integer.parseInt(list.get(i + 1));
            if (Math.abs(a - b) > 3 || a == b) {
                out = false;
            }
            if ((first > 0 && (a - b) < 0) || (first < 0 && (a - b) > 0)) {
                out = false;
            }
        }
        return out;
    }
}
