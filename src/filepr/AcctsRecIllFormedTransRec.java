package filepr;

/** An instance of this class represents a transaction intended to be
**  applied to an Accounts Receivable master file, but whose original
**  form was such that its intended meaning could not be determined.
*/
public class AcctsRecIllFormedTransRec extends AcctsRecTransRec { 

   private byte[] contents;

   /** Builds the transaction record using the given data, which identifies
   **  (if possible) the master record to which this transaction pertains
   **  and an array of bytes intended to represent the original, raw
   **  form of the transaction.
   */
   public AcctsRecIllFormedTransRec(String mastName, byte[] rawForm) { 
      super(mastName);
      contents = rawForm;
   }

   public String toString() { 
      return "Ill-formed " + super.toString() + " " + contents;
   }

}

