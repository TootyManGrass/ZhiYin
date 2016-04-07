package client.pages.musicDiary;

import client.events.executables.internalChanges.TestExecutable;
import client.events.executables.internalChanges.updatePageExecutables.ExecuteToTempState;
import client.pages.State;
import client.pages.other.Comment;
import client.pages.other.Sec1;
import client.pages.other.TransitionType;
import client.singletons.SkinSingleton;
import client.stateInterfaces.Gesturable;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import server.model.media.MAudio;
import server.model.media.MImage;
import server.model.media.MText;
import server.model.social.MDiaryPost;
import server.services.factories.ImageManagerFactory;
import tools.serverTools.databases.LocalDatabase;
import tools.serverTools.databases.LocalDatabaseFactory;

/**
 * Diary4 page.
 *
 * Created by Hongyu Wang on 3/19/2016.
 */
public class Diary4 extends Diary4Shell implements Gesturable{
    MDiaryPost thisPost;

    private State previousState;

    private ScrollPane scrollpane;
    private String title;
    private String content;
    private Image image;
    private MAudio audio;

    public Diary4(State previousState, MDiaryPost thisPost){
        this.previousState = previousState;
        this.thisPost = thisPost;

        populateFromServer();

        init();
    }

    protected void init() {
        super.init();

        ExecuteToTempState backEx = new ExecuteToTempState(previousState, TransitionType.LEFT_TO_RIGHT);
        addImage("NowPlaying/Back@", backEx, 0, 1217, 117, 117);

        if(audio != null){
            TestExecutable playAudio = new TestExecutable("play audio");
            addImage("Diary/Play@", playAudio, 0, 0, 250, 250);
        }

        ExecuteToTempState toComment = new ExecuteToTempState(new Comment(this, thisPost), TransitionType.FADE_IN);
        addImage("Diary/Comment@", toComment, 250, 0, 250, 250);

        ExecuteToTempState toSec = new ExecuteToTempState(new Sec1(this, thisPost), TransitionType.FADE_IN);
        addImage("Diary/Sec@", toSec, 500, 0, 250, 250);

        Table table = new Table();
        table.top();

//        Label label1 = new Label("Title string that should be long enough to wrap several times; testing purposes only. Also plz work.", SkinSingleton.getInstance());
        Label label1 = new Label(title, SkinSingleton.getInstance());
        label1.setWrap(true);
        label1.setWidth(700*M);
        table.add(label1).width(700*M).padLeft(50*M).padTop(50*M);
        table.row();

        table.add(new Image(tx = new Texture("Home/Line@1.0.png"))).width(750*M).height(4*M).expandX().padLeft(50*M).padTop(50*M);
        disposables.add(tx);
        table.row();

//        Label label2 = new Label("What you are reading right now is a very long string that I typed just to test our diary4 page;" +
//                "hopefully this is long enough for the text to wrap several times. Also plz work.", SkinSingleton.getInstance());
        Label label2 = new Label(content, SkinSingleton.getInstance());
        label2.setWrap(true);
        label2.setWidth(700*M);
        table.add(label2).width(700*M).padLeft(50*M).padTop(50*M);

        if(image != null){
            table.row();
            table.add(image).width(750*M).height(750/image.getWidth()*image.getHeight()*M).padTop(50*M);
        }

        scrollpane = new ScrollPane(table);
        scrollpane.setBounds(0, 250*M, 750*M, 967*M);
        stage.addActor(scrollpane);

        scrollpane.setScrollingDisabled(true, false);
    }


    @Override
    public void reset() {

    }

    @Override
    public void update(float dt){
        stage.act();
    }


    private void populateFromServer(){
        LocalDatabase localDatabase = LocalDatabaseFactory.createLocalDatabase();
        MText text = localDatabase.getModel(thisPost.getText());

        this.title = thisPost.getTitle();
        this.content = text.getText();

        MImage image = localDatabase.getModel(thisPost.getImageKey());
        this.image = ImageManagerFactory.createImageManager().mImageToImage(image);

        this.audio = localDatabase.getModel(thisPost.getAudioKey());
    }

    @Override
    public void handleGesture(boolean gestureXRight, boolean gestureYUp, boolean directionMainlyX) {
        if(gestureXRight && directionMainlyX)
            new ExecuteToTempState(previousState, TransitionType.LEFT_TO_RIGHT).execute();
    }
}
