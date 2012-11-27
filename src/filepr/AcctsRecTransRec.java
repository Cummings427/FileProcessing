package filepr;

/** An instance of a concrete subclass of this (abstract) class represents a
**  transaction record intended to be applied to an instance of AcctsRecMastRec.
**  There are transactions of various kinds (Add, Change, and Delete are 
**  the three standard kinds); the intent is for there to be a subclass
**  for any such particular kind of transaction.  
**
**  @author R. McCloskey
**  @version September 2009
*/

public abstract class AcctsRecTransRec {

   private String name;  // identifies the master record to which this pertains

   /** Initializes the new transaction record to refer to the master
   **  record whose key field (name) has the specified value.
   */
   public AcctsRecTransRec(String mastName) { name = mastName; }


   /** Returns key identifying the master record to which this transaction
   **  pertains, which in the case of an accounts receivable record is
   **  the name.
   */
   public String getMastKey() { return name; }


   /** Returns a String that describes the object as a transaction
   **  and indicates the name identifying the master record to which
   **  it applies.
   **  @return a String describing the object as a transaction and
   **          indicating the key value of the master record to which
   **          it pertains
   */
   public String toString() {
      return "transaction pertaining to " + name;
   }

}
