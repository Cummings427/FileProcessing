package filepr;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/** An instance of this class provides a way of retrieving records
**  sequentially from a file of transaction records intended to be
**  applied to a master file of accounts receivable records.
*/
public class AcctsRecTransInputFile {

   private Scanner sc;
   private String line;
   private String[] tokens;

   public AcctsRecTransInputFile(String fileName) throws FileNotFoundException
   { 
      sc = new Scanner(new File(fileName));
   }

   /** Returns the next record in the file (unless there is none, in which
   **  case the null value is returned), in the form of an instance of one
   **  of the concrete subclasses of AcctsRecTransRec.
   */
   public AcctsRecTransRec get() {
      String addCode = "A";
      String changeCode = "C";
      String deleteCode = "D";

      AcctsRecTransRec result;

      if (sc.hasNext()) {
         line = sc.nextLine();
         tokens = line.split("[ ]++");
         if (tokens.length == 0  ||  tokens[0].length() != 1) {
            result = new AcctsRecIllFormedTransRec("", line.getBytes());
         }
         else {
            if (tokens[0].equals(addCode))
               { result = parseAddTransaction(); }
            else if (tokens[0].equals(changeCode))
               { result = parseChangeTransaction(); }
            else if (tokens[0].equals(deleteCode))
               { result = parseDeleteTransaction(); }
            else {  // transaction type not properly specified
               if (tokens.length > 1) {
                  result = new AcctsRecIllFormedTransRec(tokens[1], 
                                                         line.getBytes());
               }
               else {
                  result = new AcctsRecIllFormedTransRec("", line.getBytes());
               }
            }
         }
      }
      else {
         result = null;
      }
      return result;
   }

   private AcctsRecTransRec parseAddTransaction() {

      String mastID;
      AcctsRecTransRec result;

      if (tokens.length > 1) {
         mastID = tokens[1];
         if (tokens.length == 2) 
            { result = new AcctsRecAddTransRec(mastID); }
         else {
            result = new AcctsRecIllFormedTransRec(mastID, line.getBytes());
         }
      }
      else {
         result = new AcctsRecIllFormedTransRec("", line.getBytes());
      }
      return result; 
   }


   private AcctsRecTransRec parseChangeTransaction() {

      String mastID;
      AcctsRecTransRec result;

      if (tokens.length > 1) {
         mastID = tokens[1];
         if (tokens.length != 3) {
            result = new AcctsRecIllFormedTransRec(mastID, line.getBytes());
         }
         else { 
            try {
               int balChange = Integer.parseInt(tokens[2]);
               result = new AcctsRecChangeTransRec(mastID, balChange); 
            }
            catch (Exception e) {
               result = new AcctsRecIllFormedTransRec(mastID, line.getBytes());
            }
         }
      }
      else {
         result = new AcctsRecIllFormedTransRec("", line.getBytes());
      }
      return result; 
   }


   private AcctsRecTransRec parseDeleteTransaction() {

      String mastID;
      AcctsRecTransRec result;

      if (tokens.length > 1) {
         mastID = tokens[1];
         if (tokens.length == 2) 
            { result = new AcctsRecDeleteTransRec(mastID); }
         else {
            result = new AcctsRecIllFormedTransRec(mastID, line.getBytes());
         }
      }
      else {
         result = new AcctsRecIllFormedTransRec("", line.getBytes());
      }
      return result; 
   }
      

/* former version of the method
   public TransactionRecord<String> get() {
      String addCode = "A";
      String changeCode = "C";
      String deleteCode = "D";

      AcctsRecTransRec result;
      if (sc.hasNext()) {
         String transType = sc.next();
         String mastID = sc.next();
         if (transType.equals(addCode)) {
            result = new AcctsRecAddTransRec(mastID);
         }
         else if (transType.equals(changeCode)) {
            int balChange = sc.nextInt();
            //System.out.println("just read trans deltaBal = " + balChange);
            result = new AcctsRecChangeTransRec(mastID, balChange);
         }
         else if (transType.equals(deleteCode)) {
            result = new AcctsRecDeleteTransRec(mastID);
         }
         else { 
            result = new AcctsRecIllFormedTransRec(mastID);
         }
      }
      else {
         result = null;
      }
      return result;
   }
*/

}

