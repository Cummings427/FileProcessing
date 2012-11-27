package filepr;

import java.util.Comparator;
import java.io.IOException;

/** Java application whose main() method applies the records in a
**  transaction file to an accounts receivable master file, thereby
**  producing a new master file.
**
**  This application uses the Balanced Line-based SFU updater class.
**
**  @author R. McCloskey
**  @version September 2009
*/
public class AcctsRecSFUApp {

   /** This method carries out a sequential file update involving files
   **  whose names are provided as command-line arguments.
   **  Specifically, four such names are expected:<br />
   **  --an (old) accounts receivable master file,<br />
   **  --a file of transactions to be applied to that master file,<br />
   **  --the file into which the updated version of the master file is to
   **    be written, and<br />
   **   --a file in which error transactions are to be reported.
   */
   public static void main(String[] args) throws Exception {
	   
      AcctsRecMasterInputFile mastFile =        // old master file
         new AcctsRecMasterInputFile(args[0]);
      AcctsRecTransInputFile transFile =        // transaction file
         new AcctsRecTransInputFile(args[1]);
      AcctsRecMasterOutputFile newMastFile =    // new master file
         new AcctsRecMasterOutputFile(args[2]);
      AcctsRecTransErrorReport transErrorReport =  // invalid transactions
         new AcctsRecTransErrorReport(args[3]);    // reported here

      Comparator<String> strComp = new StringComparator();

      BalancedLine sfu = new BalancedLine();
      sfu.update(mastFile, transFile, newMastFile, transErrorReport, strComp);
   }

}