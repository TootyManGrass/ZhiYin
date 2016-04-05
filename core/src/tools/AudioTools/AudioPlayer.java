package tools.AudioTools;

import org.robovm.apple.avfoundation.AVAudioPlayer;
import org.robovm.apple.avfoundation.AVAudioSession;
import org.robovm.apple.avfoundation.AVAudioSessionCategory;
import org.robovm.apple.foundation.NSData;
import org.robovm.apple.foundation.NSErrorException;
import org.robovm.apple.foundation.NSTimer;
import server.model.media.MAudio;

/**
 * Created by Kevin on 3/10/2016.
 */

public class AudioPlayer {

    private boolean running = false;

    private AVAudioPlayer musicPlayer;
    private AVAudioPlayer voicePlayer;
    private AVAudioPlayer snapShotPlayer;
    private boolean snapShot = false;
    private static AVAudioSession session = AVAudioSession.getSharedInstance();

    private NSTimer timer;

    private MAudio currentSong;
    private static AudioPlayer singleInstance = new AudioPlayer();

    private AudioPlayer(){
        musicPlayer = new AVAudioPlayer();
        voicePlayer = new AVAudioPlayer();
        snapShotPlayer = new AVAudioPlayer();
    }

    public static AudioPlayer getInstance(){
        return singleInstance;
    }


    public void setSongSnapShot(MAudio voice, MAudio song, double startTime){
        currentSong = song;
        //musicPlayer.dispose();
        musicPlayer.stop();
        voicePlayer.dispose();
        snapShotPlayer.dispose();
        snapShot = true;

        try {
            voicePlayer = new AVAudioPlayer(new NSData(voice.getmData()));
            snapShotPlayer = new AVAudioPlayer(new NSData(song.getmData()));
            snapShotPlayer.setCurrentTime(startTime);
        } catch (NSErrorException e) {
            e.printStackTrace();
        }


    }

    public void setSong(MAudio audio){
        currentSong = audio;
        musicPlayer.dispose();
        if(snapShot) {
            voicePlayer.stop();
            snapShotPlayer.stop();

        }
        snapShot = false;

        try {
            musicPlayer = new AVAudioPlayer(new NSData(audio.getmData()));
        } catch (NSErrorException e) {
            e.printStackTrace();
        }
    }

    public void prepareToPlay() {
        try {
            session.setActive(true);
            session.setCategory(AVAudioSessionCategory.PlayAndRecord);
        } catch (NSErrorException e) {
            e.printStackTrace();
        }

        musicPlayer.prepareToPlay();
        musicPlayer.setDelegate(musicPlayer.getDelegate());
        if(snapShot) {
            voicePlayer.prepareToPlay();
            voicePlayer.setDelegate(voicePlayer.getDelegate());
            snapShotPlayer.prepareToPlay();
            snapShotPlayer.setDelegate(snapShotPlayer.getDelegate());
        }
    }

    public void play(){
        running = true;

        if(snapShot) {
            voicePlayer.play();
            snapShotPlayer.play();
            return;
        }


        musicPlayer.play();


    }

    //public void playAtTime(int time){
        //musicPlayer.setCurrentTime((double)time);
         //if(snapShot)
            //player2.setCurrentTime(player2.getCurrentTime() + (double)time);

        //play();
    //}

    public void pause(){
        running = false;
        musicPlayer.stop();
        if(snapShot) {
            voicePlayer.stop();
            snapShotPlayer.stop();
        }

    }

    public void stop(){
        running = false;
        try {
            session.setActive(false);
        } catch (NSErrorException e) {
            e.printStackTrace();
        }
        if(snapShot) {
            voicePlayer.stop();
            snapShotPlayer.stop();
        }
        musicPlayer.stop();



    }

    public boolean isPlaying(){
        if(!running)
            return false;
        if(snapShot)
            return voicePlayer.isPlaying();
        running = musicPlayer.isPlaying();

        return running;
    }

    private double[] time = {0,0};

    public void startSnapChatTime(){
        time[0] = this.getCurrentTime();
    }

    public double[] stopSnapChatTime(){


        time[1] = this.getCurrentTime();

        double[] timeArray = time;

        time = new double[]{0, 0};

        for(double s : timeArray)
            System.out.println(s);

        return timeArray;
    }



    public MAudio getCurrentAudio(){
        return currentSong;
    }

    public double getCurrentTime(){
        return musicPlayer.getCurrentTime();
    }

    public void setTime(double time){
        musicPlayer.setCurrentTime(time);
    }

    public boolean isPlayingSnapshot(){ return snapShot;}


}
