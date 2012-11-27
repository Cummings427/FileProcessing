package filepr;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

/** An instance of this class provides a way of retrieving records
**  sequentially from a file of accounts receivable records, each
**  of which is comprised of two character strings (separated by
**  whitespace), the first representing a name (and acting as the
**  primary key) and the second an (integer-valued) balance owed.
**
**  @author R. McCloskey
**  @version September 2009
*/
public class AcctsRecMasterInputFile {

   private Scanner sc;  // for reading from the provided file

   /** Prepares the object to read data from the file with the specified
   **  name.
   */
   public AcctsRecMasterInputFile(String fileName) throws FileNotFoundException 
      { sc = new Scanner(new File(fileName)); }


   /** Returns the next master record in the file (unless there is none,
   **  in which case the null value is returned), in the form of an
   **  instance of the class AcctsRecMastRec.
   */
   public AcctsRecMastRec get() throws Exception {

      AcctsRecMastRec result;

      try {
         String name = sc.next();
         int balance = sc.nextInt();
         result = new AcctsRecMastRec(name, balance);
      }
      catch (NoSuchElementException e) { 
         result = null;
      }
      catch (Exception e) {
         throw e;
      }
      return result;
   }


   /** Undoes the effect of the most recent invocation of get(), so that
   **  in the sequence of calls get(), unGet(), and get(), the second call
   **  to get() returns an object that is identical to the one returned in
   **  response to the first call.
   */
   public void unGet() { 

      /***  missing body  ***/

   }

}
