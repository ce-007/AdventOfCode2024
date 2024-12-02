import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Day1 {
    public static void main(String[] args) throws FileNotFoundException {
        List<Integer> one = new ArrayList<>();
        List<Integer> two = new ArrayList<>();
        //List<Integer> distances = new ArrayList<>();

        File file = new File("inputDayOne.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            String[] temp = data.split("   ");
            one.add(Integer.valueOf(temp[0]));
            two.add(Integer.valueOf(temp[1]));
        }
        scanner.close();
        Collections.sort(one);
        Collections.sort(two);

        int total = 0;
        for (int i = 0; i < one.size(); i++) {
            total += Math.abs(one.get(i) - two.get(i));
        }
        System.out.println("part one: " + total);

        int p2Total = 0;
        for (int i = 0; i < one.size(); i++) {
            int index = 0;
            int oneI = one.get(i);
            for (int j = 0; j < two.size(); j++) {
                if (oneI == two.get(j)) {
                    index++;
                }
            }
            p2Total += (index * oneI);
        }
        System.out.println("part two: " + p2Total);
    }
}
