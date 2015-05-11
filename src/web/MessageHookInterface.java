/* MessageHook.java		This interface is 
 * 
 */
package web;

public interface MessageHookInterface {
	/**
	 * Catch a message received by the web connection. If the message applies, use it+return true that it should be removed from the queue, otherwise 
	 * @param m message object that was received.
	 * @return true if the object should be removed from the queue, otherwise false that the search should continue.
	 */
	public boolean MessageHook(Message m);
}
