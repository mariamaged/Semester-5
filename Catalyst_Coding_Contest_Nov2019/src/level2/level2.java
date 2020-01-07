package level2;
import java.io.*;
import java.util.*;

public class level2 {
    public static void main(String[] args) {

        File input  = new File(System.getProperty("user.dir") + File.separator + "level2_example.in");
        String line;
        boolean flag = false;
        int cols, rows;
        String[] first, temp, temp1;
        Long[] temp2;

        List<Long[]> worldID = new ArrayList<>();
        Set<Long> ids = new HashSet<>();
        Map<Long, Long> sums = new HashMap<>();

        boolean[][] truth;
        int countRow = 0;
        int columnCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(input));
             PrintWriter p = new PrintWriter(new BufferedWriter(new FileWriter("level2_exampleme.out")))) {

            first = br.readLine().split(" ");

            rows = Integer.parseInt(first[0]);
            cols = Integer.parseInt(first[1]);

            truth = new boolean[rows][cols];

            for(int i = 0; i<rows; i++) {
                for(int j=0; j<cols; j++) {
                    truth[i][j] = false;
                }
            }

            // Step 1: read the lines and fill the world
            while ((line = br.readLine()) != null) {

                temp = line.split(" ");
                temp1 = new String[cols];

                for (int i = 0; i < (cols*2); i++) {
                    if (flag) {
                        temp1[i/2] = temp[i];
                        ids.add(Long.parseLong(temp[i]));
                    }
                    flag = !flag;
                }

                temp2 = arrayParser(temp1);
                worldID.add(temp2);
            }

             // Step 2: initialize an emtpty sum map
            for (Long id : ids) {
                sums.put(id, 0L);
            }

            // Step 3: search for left and right border
            for(Long[] row : worldID) {
                for (int j = 0; j < row.length; j++) {
                    if (j == 0 || j == (row.length-1) ) {
                        if(!truth[countRow][j]) sums.replace(row[j], sums.get(row[j]), sums.get(row[j]) + 1);
                        truth[countRow][j] = true;
                    }
                }
                countRow++;
            }
            countRow = 0;
            // Step 4: search for different cells horizontally
            for (Long id : ids) {
                for (Long[] row : worldID) {
                    for (int j = 0; j < row.length -1; j++) {
                        if ((j!=0)
                                && (row[j] == id)
                                && (row[j + 1] != row[j])) {

                                if(!truth[countRow][j])
                                    sums.replace(id, sums.get(id), sums.get(id) + 1);
                                if(!truth[countRow][j+1])
                                    sums.replace(row[j+1], sums.get(row[j+1]), sums.get(row[j+1]) + 1);

                                truth[countRow][j] = true;
                                truth[countRow][j + 1] = true;
                        }
                    }
                    countRow++;
                }

                countRow  = 0;
            }

            // Step 5: search for top and bottom borders
            for(int i = 0; i<cols; i++) {
                if(i==0 || i==(cols-1)) {

                    for (Long l : worldID.get(i)) {
                            if(!truth[i][columnCount])
                                sums.replace(worldID.get(i)[columnCount], sums.get(worldID.get(i)[columnCount]),sums.get(worldID.get(i)[columnCount]) +1 );

                        truth[i][columnCount] = true;
                        columnCount++;
                    }

                    columnCount = 0;

                }

            }

            // Step 6: search for different cells vertically
            for(Long id : ids) {
                for(int i = 0; i<cols; i++) {
                    for (int j = 0; j < rows - 1; j++) {
                        if (worldID.get(j)[columnCount] != worldID.get(j + 1)[columnCount]) {

                                if(!truth[j][columnCount])
                                    sums.replace(worldID.get(j)[columnCount], sums.get(worldID.get(j)[columnCount]), sums.get(worldID.get(j)[columnCount]) + 1);
                                if(!truth[j + 1][columnCount])
                                    sums.replace(worldID.get(j + 1)[columnCount], sums.get(worldID.get(j + 1)[columnCount]), sums.get(worldID.get(j + 1)[columnCount]) + 1);


                                truth[j][columnCount] = true;
                                truth[j + 1][columnCount] = true;

                        }

                    }
                    columnCount++;

                }
                columnCount = 0;
            }

            // Step 7: print into file
            for(Map.Entry<Long, Long> sum : sums.entrySet()) {
                p.println(sum.getValue() + " ");

            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Long[] arrayParser(String[] s) {
        Long[] returnS = new Long[s.length];
        for(int i=0; i<s.length; i++) {
            returnS[i] = Long.parseLong(s[i]);
        }
        return returnS;
    }

    public static void arrayPrinter(List<Long[]> s) {
        s.forEach(row -> {
            for(int i = 0; i<row.length; i++) {
                if(i != (row.length-1)) System.out.print(row[i] + " ");
                else System.out.print(row[i] );
            }
            System.out.println("");
        });

    }
}