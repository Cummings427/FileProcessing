package filepr;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

/** An instance of this class provides a way to write transaction records
**  that could not be applied successfully (and accompanying messages) into
**  a file.
**
**  @author R. McCloskey
**  @version September 2009
*/
public class AcctsRecTransErrorReport {

   private BufferedWriter writer; 

   /** Prepares the object to write records into the file with the
   **  specified name.
   */
   public AcctsRecTransErrorReport(String fileName) throws IOException {

      writer = new BufferedWriter(new FileWriter(fileName));
   }


   /** Writes into the file an encoding of the given (invalid) transaction
   **  object together with an accompanying message.
   */
   public void put(AcctsRecTransRec tr, String message) throws IOException {
      writer.write(tr.toString() + ":" + message);
      writer.newLine();
      writer.flush();
   }

   /** Closes the report, making it unfit for further use.
   */
   public void close() throws IOException {
      writer.flush();
      //writer.close();
      writer = null;
   }

}
