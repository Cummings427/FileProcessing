package filepr;

/** An instance of this class represents an Add transaction intended to 
**  describe an instance of AcctsRecMastRec that is to be created.
**
**  @author R. McCloskey
**  @version November 2007
*/
public class AcctsRecAddTransRec extends AcctsRecTransRec {
 

   /** Builds the transaction record using the given data, which 
   **  identifies the master record to which this transaction pertains.
   */
   public AcctsRecAddTransRec(String mastName) { super(mastName); }
 

   /** Returns the master record that, according to this transaction, 
   **  is to be created.
   **  @return the master record that is to be added
   */
   public AcctsRecMastRec formMasterRec() {
	
	AcctsRecMastRec Casper = new AcctsRecMastRec(this.getMastKey());
	return Casper;
   
   }

   public String toString() {
      return "Add " + super.toString();
   }

}
