package server.services.socialService;

import server.model.media.MText;
import server.model.social.MMessage;

/**
 * Created by Hairuo on 2016-03-06.
 */
public interface MessageManager {

    /**
     * Creates a message
     *
     * @param text      the text contained in the message
     * @param timeStamp the time the message was created
     * @param creator   the id of the user that created it
     * @return a message containing the inputed data
     */
    public MMessage createMessage(long text, long timeStamp, long creator);

    /**
     * Retrieves a message from the database
     *
     * @param key id of the message to be retrieved
     * @return the message associated with the id
     */
    public MMessage getMessage(long key);

    /**
     * Edits the text of a message
     *
     * @param text the new text
     * @return the edited message
     */
    public MMessage editMessage(long text, MMessage message);


}

