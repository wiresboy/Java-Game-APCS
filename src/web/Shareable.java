/** Shareable.java		Brandon John		2/18/2015
 * defines the methods that will be used to pack and unpack data being transfered with another player.
 */
package web;

import java.io.Serializable;

interface Shareable extends Serializable {

	void getPackagedData();
	
	void unpackData(String update);
}
