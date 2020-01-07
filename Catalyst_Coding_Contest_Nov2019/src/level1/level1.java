package level1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class level1 {

    public static void main(String[] args) {
        File input  = new File(System.getProperty("user.dir") + File.separator + "level1_5.in");
        String line;
        String[] newLine;
        Long[] upLine;
        List<Long> world = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(input))) {
            br.readLine();
            while((line = br.readLine()) != null) {
                newLine = line.split(" ");
                upLine = new Long[newLine.length];
                for(int i = 0; i<newLine.length; i++) {
                    upLine[i] = Long.parseLong(newLine[i]);
                }
                world.addAll(Arrays.asList(upLine));
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        long min = world.get(0);
        long max = world.get(0);
        long avg;
        long sum = 0;
        long count = 0;

        for(Long m : world) {

            if(m<min) min = m;
            if(m>max) max = m;
            sum += m;
            count++;
        }

        avg = (long)Math.floor((double)sum/(double)count);
        try(PrintWriter p = new PrintWriter(new BufferedWriter(new FileWriter("level1_5.out")))) {
            p.print(min + " " + max + " " + avg);
        }
        catch(IOException e) {
            e.printStackTrace();
        }


    }
}
