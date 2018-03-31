package lecture7.primes3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author LE-HONG Phuong
 * <p>
 * Nov 11, 2012, 6:30:15 PM
 * <p>
 * Write/read a list of prime numbers to a text file.
 */
public class PrimeNumberIO {
    /**
     * Writes a list of integers to a text file. Each number is on a line.
     *
     * @param numbers  a list of numbers
     * @param fileName a file name
     * @see #readList(String)
     */
    public static void writeList(List<Integer> numbers, String fileName) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fileName));
            for (Integer n : numbers) {
                writer.write(n.toString());
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Reads a list of numbers from a text file which was written by
     * {@link #writeList(List, String)}.
     *
     * @param fileName a file name
     * @return a list of numbers.
     */
    public static List<Integer> readList(String fileName) {
        List<Integer> list = new ArrayList<Integer>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line = null;
            while ((line = reader.readLine()) != null) {
                list.add(Integer.parseInt(line));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
}
