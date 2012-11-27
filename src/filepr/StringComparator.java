package filepr;

import java.util.Comparator;

/** An instance of this class can compare two String objects.
*/
public class StringComparator implements Comparator<String> {

   /** Returns a negative number, zero, or a positive number, respectively,
   **  according to whether s1 is lexicographically less than, equal to,
   **  or greater than s2.
   */
   public int compare(String s1, String s2) { return s1.compareTo(s2); }

}

