package filepr;

/** An instance of this class represents a master record that holds
**  accounts receivable data in the form of a name (of type String)
**  that identifies a client and a balance owed (of type int).
**
**  @author R. McCloskey
**  @version November 2012
*/
public class AcctsRecMastRec {

   // fields
   private String name;
   private int balance;
   private boolean isEmpty;


   // constructors

   /** Initializes a new record to have the specified values for name
   **  and balance.
   */
   public AcctsRecMastRec(String name, int bal) { 
      this.name = name;
      balance = bal;
      isEmpty = false;
   } 

   /** Initializes a new record to have the specified value for name
   **  and a balance of zero.
   */
   public AcctsRecMastRec(String name) { this(name, 0); }
   


   // observers

   public boolean isEmpty() { return isEmpty; }

   /** Returns the key field of the record, which is the name.
   **  @return key field of record, which is name
   */
   public String getKey() { return name; }


   /** Returns the name field of the record.
   **  @return name field of record
   */
   public String getName() { return name; }

   /** Returns the balance field of the record.
   **  @return balance (amount owed) field of record
   */
   public int getBalance() { return balance; }

   public String toString() {
      return "Master Record: name = " + name + ", balance = " + balance;
   }

   // mutators

   /** Sets the balance (amount owed) field to the specified value.
   */
   public void setBalance(int newBalance) { balance = newBalance; }

   public void makeEmpty() { isEmpty = true; }

}

