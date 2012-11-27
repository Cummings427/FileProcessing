package filepr;

import java.util.Comparator;

/**
 * An instance of this class, via its update() method, can apply the
 * transactions of a specified transaction file to the records in a specified
 * master file and thereby produce an updated version of the master file, which
 * is written to a specified file.
 ** 
 ** The code is based upon the Balanced Line Algorithm.
 ** 
 ** @author R. McCloskey
 ** @version September 2009
 */

public class BalancedLine {

	/**
	 * Applies the transactions in the specified transaction file to the
	 * specified master file, thereby producing an updated master file, which is
	 * written to the specified place; any transactions that could not be
	 * applied (e.g., add an already-existing master record, change/delete a
	 * non-existent master record) are written to the specified error
	 * transactions report.
	 ** <p>
	 * pre: The records yielded by mastFile and transFile (via calls to their
	 * respective get() methods) are in ascending order with respect to the
	 * specified Comparator, comp. That is, if we execute r1 = mastFile.get();
	 * r2 = mastFile.get(), then we must have
	 * comp.compare(r1.getKey(),r2.getKey()) < 0. Similarly,
	 * comp.compare(t1.getMastKey(),t2.getMastKey()) <= 0 if t1 and t2 are two
	 * consecutive transaction records retrieved from transFile.
	 */
	public void update(AcctsRecMasterInputFile mastFile,
			AcctsRecTransInputFile transFile,
			AcctsRecMasterOutputFile newMastFile,
			AcctsRecTransErrorReport transErrorReport, Comparator<String> comp)

	throws Exception {

		AcctsRecMastRec mastRec = mastFile.get(); // Read first master record
		AcctsRecTransRec transRec = transFile.get(); // Read first transaction
		Boolean mastRec_Empty = false;

		//While we have a master record or a transaction record
		while (mastRec != null || transRec != null) {

			// Compare the keys of master record and trans record and see if the
			// master record's key is bigger
			if (this.compareMastRecWithTransRec(mastRec, transRec, comp) > 0) {
				// Ensure the next get call will return the same master record
				mastFile.unGet();
				// Set the master record empty flag to true
				mastRec_Empty = true;
				// mastRec.setKey(transRec.getMastKey())
			}

			// While the transaction record matches our master record
			while (this.compareMastRecWithTransRec(mastRec, transRec, comp) == 0) {
				// Apply the transaction
				System.out.println(transRec.toString());

				// Get the next transaction
				transRec = transFile.get();
			}

			// Ensure that the master key is greater than the transaction record
			// key
			if (this.compareMastRecWithTransRec(mastRec, transRec, comp) > 0)
				throw new RuntimeException(
						"The master record's key must be greater than the transaction record's key. Algorithm terminated.");

			// If the master record empty flag is true
			if (mastRec_Empty) {
				// Put the master record in our new master record file
				newMastFile.put(mastRec);
			}

			// Get the next master record
			mastRec = mastFile.get();

			// Reset the master record empty flag
			mastRec_Empty = false;
		}

		// Close open files
		newMastFile.close();
		transErrorReport.close();
	}

	/**
	 * Uses the specified Comparator object to compare the specified master
	 * record's key to the specified transaction record's foreign key. Returns a
	 * negative value if the master record is "less than" the transaction record
	 * (i.e., the transaction pertains to a master record that comes after the
	 * given one); returns zero if the two records are "equal" (i.e., the
	 * transaction pertains to the given master record); returns a positive
	 * value if the master record is "greater than" the transaction record
	 * (i.e., the transaction pertains to a master record that comes before the
	 * given one). The null value acts as a sentinel value considered to be
	 * greater than anything else. Hence, in the case that the master record is
	 * null, a positive value is returned; in the case that the transaction
	 * record is null, a negative value is returned.
	 */
	private int compareMastRecWithTransRec(AcctsRecMastRec m,
			AcctsRecTransRec t, Comparator<String> comp) {
		int result;
		if (t == null) {
			result = -1;
		} else if (m == null) {
			result = 1;
		} else {
			result = comp.compare(m.getKey(), t.getMastKey());
		}
		return result;
	}

}
