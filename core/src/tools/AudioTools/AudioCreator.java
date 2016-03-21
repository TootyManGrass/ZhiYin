package tools.AudioTools;

import org.robovm.apple.avfoundation.AVAsset;
import org.robovm.apple.avfoundation.AVAudioPlayer;
import org.robovm.apple.avfoundation.AVMetadataItem;
import org.robovm.apple.foundation.*;
import server.model.media.MAudio;
import server.model.media.MMusic;
import server.model.media.MSnapShot;
/**
 * Created by Kevin on 3/11/2016.
 *
 * A class that gives the capability to create an MAudio object from an audio file.
 *
 */
public class AudioCreator {

    private static NSFileManager fm;

    private AudioCreator(){

        fm = new NSFileManager();

    }

    public static MAudio createFromFilePath(String filePath){
        NSData data = NSData.read(new NSURL(filePath));
        //data = fm.getContentsAtPath(filePath);
        return createMAudio(data);
    }

    public static MAudio createFromFilePath(NSURL filePath){

        NSData data = NSData.read(filePath);
        //data = fm.getContentsAtPath(filePath.getPath());
        return createMAudio(data);

    }

    public static MMusic createMMusicFromFilePath(String filePath, long audioKey){
        MAudio audio = createMAudio(NSData.read(new NSURL(filePath))); // need to assign this a long key
        audio.setKey(audioKey);
        MMusic music = new MMusic();
        music.setMusicKey(audioKey);


        NSArray<AVMetadataItem> metadata = (new AVAsset(new NSURL(filePath))).getCommonMetadata();
        for(AVMetadataItem item : metadata){
            if(item.getCommonKey().equals("Title"))
                music.setName(item.getStringValue());
        }
        return music;
    }


    public static MSnapShot createSnapShot(long voice, long song,int start, int end){
        MSnapShot ss = new MSnapShot();
        ss.setMessage(voice);
        ss.setSong(song);
        ss.setStartTime(start);
        ss.setEndTime(end);
        return ss;
    }

    public static MAudio createMAudio(NSData data){
        MAudio song = new MAudio(); //Need to set long key
        song.setmData(data);
        AVAudioPlayer temp;
        try {

            temp = new AVAudioPlayer(data);
            song.setTrackLength(temp.getDuration());

        } catch (NSErrorException e) {
            e.printStackTrace();
        }
        return song;
    }
}
