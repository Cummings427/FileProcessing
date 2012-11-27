package filepr;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

/** An instance of this class provides a way to write records
**  sequentially into a file of accounts receivable records,
**  each of which is comprised of two character strings, the first
**  representing a name (and acting as the file's primary key) and the
**  second an (integer-valued) balance owed.
**
**  @author R. McCloskey
**  @version September 2009
*/
public class AcctsRecMasterOutputFile {

   private BufferedWriter writer; 

   /** Prepares the object to write records into the specified file.
   */
   public AcctsRecMasterOutputFile(File file) throws IOException {

      writer = new BufferedWriter(new FileWriter(file));
   }

   /** Prepares the object to write records into the file with the
   **  specified name.
   */
   public AcctsRecMasterOutputFile(String fileName) throws IOException {

      this(new File(fileName));
      //writer = new BufferedWriter(new FileWriter(fileName));
   }


   /** Writes to the file a record that encodes the given object.
   */
   public void put(AcctsRecMastRec mr) throws IOException {

      String str = mr.getName() + " " + mr.getBalance();
      writer.write(str);
      writer.newLine();
      writer.flush();
   }

   /** Flushes the buffer and closes the file.
   */
   public void close() throws IOException {
      writer.flush();
      writer.close();
   }

}
