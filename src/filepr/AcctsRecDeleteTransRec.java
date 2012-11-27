package filepr;

/** An instance of this class represents a delete transaction intended to be
**  applied to an instance of AcctsRecMastRec.
**
**  @author: R. McCloskey
**  @version: September 2009
*/
public class AcctsRecDeleteTransRec extends AcctsRecTransRec { 

   /** Builds the transaction record using the given data, which identifies
   **  the master record to which this transaction pertains.
   */
   public AcctsRecDeleteTransRec(String mastName) { super(mastName); }

   public String toString() { 
      return "Delete " + super.toString();
   }

}
