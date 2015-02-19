/** Shareable.java		Brandon John		2/18/2015
 * defines the methods that will be used to pack and unpack data being transfered with another player.
 */
package web;

public interface Shareable{

	String getIdentifier(); //get a unique identifier so that the WebInterface knows where to send what data. This should be initialized somewhere in the main initialization place.
	
	String[][] packData(); 
	/*gather any data to share with other player and package it, returning a 2D String array, containing key,value pairs.
	 * All values will be CSV formatted by Web.java
	  {{"Key1","Value1"},
	   {"Key2","Value2"},
	   {"Key3","Value3"}}		*/
	
	void unpackData(String[][] update); 
	/* update all internal variables to new values based on data packed by a different client.
	 * Should follow exact same pattern for decoding as it does for encoding.
	 */
}
