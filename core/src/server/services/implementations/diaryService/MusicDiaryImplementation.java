package server.services.implementations.diaryService;

import server.model.social.MDiaryPost;
import server.model.media.*;
import server.model.user.User;
import server.services.interfaces.models.MusicDiary;

/**
 * Created by calin on 06/03/2016.
 */
public class MusicDiaryImplementation implements MusicDiary {
//    @Override
//    public MDiaryPost requestDiaryPostData(long diaryPostKey){
//        MDiaryPost diaryPost = new MDiaryPost();
//
//        long key = 0;
//        diaryPost.setKey(key);
//
//        long audioKey = 0;
//        diaryPost.setAudioKey(audioKey);
//        //TODO request from server.
//
//        long imageKey = 0;
//        diaryPost.setImageKey(imageKey);
//        //TODO request from server.
//
//        long musicKey = 0;
//        diaryPost.setMusicKey(musicKey);
//        //TODO request from server.
//
//        long textKey = 0;
//        diaryPost.setTextKey(textKey);
//        //TODO request from server.
//
//        return diaryPost;
//    }
    @Override
    public MDiaryPost createDiaryPost(User user, long image, long music, String title, long description){
        MDiaryPost diaryPost = new MDiaryPost();
        diaryPost.setCreator(user.getKey());
        diaryPost.setImageKey(image);
        diaryPost.setMusicKey(music);
        diaryPost.setTitle(title);
        diaryPost.setText(description);

        return diaryPost;
    }


    @Override
    public MDiaryPost modifyAudio(MDiaryPost diaryPost, MAudio audio){
        diaryPost.setAudioKey(audio.getKey());
        return diaryPost;
        //TODO request change to server.
    }

    @Override
    public MDiaryPost modifyImage(MDiaryPost diaryPost, MImage image){
        diaryPost.setImageKey(image.getKey());
        return diaryPost;
        //TODO request change to server.
    }

    @Override
    public MDiaryPost modifyMusic(MDiaryPost diaryPost, MMusic music){
        diaryPost.setMusicKey(music.getKey());
        return diaryPost;
        //TODO request change to server.
    }
}
