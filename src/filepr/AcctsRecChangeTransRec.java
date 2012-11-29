package filepr;

/** An instance of this class represents a Change transaction intended to
**  be applied to an instance of AcctsRecMastRec.
*/
public class AcctsRecChangeTransRec extends AcctsRecTransRec {

   private int deltaBalance; // amount by which balance field is to be changed

   /** Builds the transaction record using the two pieces of data provided,
   **  the first of which identifies the master record to which this
   **  transaction applies and the second of which indicates the amount
   **  to be added to the balance field of that master record.
   */
   public AcctsRecChangeTransRec(String mastName, int balChange) {

      super(mastName);
      deltaBalance = balChange;
   }

 
   /** Modifies the given instance of AcctsRecMastRec by updating its
   **  balance field in accord with the amount specified by this transaction.
   */
   public void applyChange(AcctsRecMastRec mr) {

	mr.setBalance(mr.getBalance() + deltaBalance);
	   
   }

   public String toString() {
      return "Change " + super.toString() + 
             " to change balance by " + deltaBalance;
   }

}
