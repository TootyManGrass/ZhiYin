package server.model.media;

import server.model.structureModels.ServerModel;

import java.util.List;

/**
 * Created by Kevin Zheng on 2016-03-02.
 */
public class Hashtag extends ServerModel {
    private String hashtagName;
    private List<Long> musicKeys;

    /**Returns the string representation of the hashtag.
     *
     * @return
     */
    public String getHashtagName() {
        return hashtagName;
    }

    /**Returns list of
     *
     * @return
     */
    public List<Long> getMusicKeys() {
        return musicKeys;
    }



    public void setHashtag(String hashtag) {
        this.hashtagName = hashtagName;
    }

    public void setMusicKeys(List<Long> musicKeys) {
        this.musicKeys = musicKeys;
    }
}
