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
            String[] temp = data.split(" ");
            if (checkStatus(temp)) {
                amount++;
                amount2++;
            } else {
                for (int i = 0; i < temp.length; i++) {
                    List<String> temp2 = new java.util.ArrayList<>(List.of(temp));
                    temp2.remove(i);
                    if (checkStatus2(temp2)) {
                        amount2++;
                        i = temp.length;
                    }
                }
            }
        }
        System.out.println("Part One: " + amount);
        System.out.println("Part Two: " + amount2);
    }

    public static boolean checkStatus(String[] temp) {
        boolean out = true;
        boolean out2 = true;
        int f = Integer.valueOf(temp[0]) - Integer.valueOf(temp[1]);
        for (int i = 0; i < temp.length - 1; i++) {
            int a = Integer.valueOf(temp[i]);
            int b = Integer.valueOf(temp[i+1]);
            if(Math.abs(a-b) > 3 || a == b) {
                if (!out) {
                    out2 = false;
                }
                out = false;
            }
            if ((f > 0 && (a-b) < 0) || (f < 0 && (a-b) > 0)){
                if (!out) {
                    out2 = false;
                }
                out = false;
            }
        }
        return out;
    }
    public static boolean checkStatus2(List<String> temp) {
        boolean out = true;
        boolean out2 = true;
        int f = Integer.valueOf(temp.get(0)) - Integer.valueOf(temp.get(1));
        for (int i = 0; i < temp.size() - 1; i++) {
            int a = Integer.valueOf(temp.get(i));
            int b = Integer.valueOf(temp.get(i + 1));
            if(Math.abs(a-b) > 3 || a == b) {
                if (!out) {
                    out2 = false;
                }
                out = false;
            }
            if ((f > 0 && (a-b) < 0) || (f < 0 && (a-b) > 0)){
                if (!out) {
                    out2 = false;
                }
                out = false;
            }
        }
        return out;
    }
}
