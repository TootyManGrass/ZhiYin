package server.model.user;

import server.model.structureModels.ServerModel;

import java.util.List;

/**A user of Zhi Yin. Contains all the necessary information for the user.
 *
 * A model filled with the information on a user.
 *
 *      - UserProfileManager:
 *          -Basic user information.
 *      - Conversations
 *      - Diary Content
 *      - Uploaded Content
 *      - Activity Log
 *
 * Created by Kevin Zheng on 2016-02-18.
 */
public class User extends ServerModel {
    /**
     * The key to the userProfile.
     */
    private long profileKey;
    /**
     * The key to the userConversations.
     */
    private long conversationsKey;
    /**
     * The key to the userDiary.
     */
    private long diaryKey;
    /**
     * The key to the userLog.
     */
    private long logKey;
    /**
     * The key to the userContent.
     */
    private long contentKey;
    /**
     * The keys to the friends.
     */
    private List<Long> friendsKey;

    /**Gets all basic user information.
     *
     * @return  The userProfile containing info.
     */
    public long getProfile() {
        return profileKey;
    }

    /**Returns a model of all conversations a user has.
     *
     * @return  The userConversation containing convos.
     */
    public long getConversations() {
        return conversationsKey;
    }

    /**Returns a model of all diary posts a user has.
     *
     * @return  The userDiaryContent.
     */
    public long getDiary() {
        return diaryKey;
    }

    /**Returns a model of all uploaded content a user has.
     *
     * @return  The userUploadedContent.
     */
    public long getContent() {
        return contentKey;
    }

    /**Gets all logged activity of the user.
     *
     * @return  The userActivitylog.
     */
    public long getLog() {
        return logKey;
    }

    /**Gets the list of all friend keys.
     *
     * @return  The List of friend keys.
     */
    public List<Long> getFriendKeys() {
        return friendsKey;
    }



    // SETTERS
    public void setProfile(long profileKey) {
        this.profileKey = profileKey;
    }
    public void setConversations(long conversationsKey) {
        this.conversationsKey = conversationsKey;
    }
    public void setDiary(long diaryKey) {
        this.diaryKey = diaryKey;
    }
    public void setLog(long logKey) {
        this.logKey = logKey;
    }
    public void setContent(long contentKey) {
        this.contentKey = contentKey;
    }
    public void setFriends(List<Long> friendsKey) {
        this.friendsKey = friendsKey;
    }
}
