package client.pages.friends;


import client.events.executables.internalChanges.TestExecutable;
import client.pages.friends.boxes.MessageBox;
import client.singletons.SkinSingleton;
import client.singletons.StateManager;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.WorkingTextArea;

import static client.singletons.StateManager.M;

public class Friends4 extends Friends4Shell{
    private TextField messageField;

    private Table table;

    public void init(){
        super.init();

        addMessageField();

        table = new Table();
        table.top();

        ScrollPane scrollpane = new ScrollPane(table);
        scrollpane.setBounds(0, 122 * StateManager.M, 750 * StateManager.M, 1212 * StateManager.M);

        stage.addActor(scrollpane);


        MessageBox box1 = new MessageBox("This is a long message made for the sole purpose of testing our stuff. Please do not read this unless you want to waste your time." +
                "Why are you still reading this? Do you really have nothing better to do right now? Go find yourself a hobby or something. That, or go do some work." +
                "Just stop reading this really long string. Please. And thank you.", 0);
        MessageBox box2 = new MessageBox("This is a long message made for the sole purpose of testing our stuff.", 1);
        MessageBox box3 = new MessageBox("Message", 0);
        MessageBox box4 = new MessageBox("String", 1);

        MessageBox box5 = new MessageBox(new TestExecutable("clicked 1"), 0);
        MessageBox box6 = new MessageBox(new TestExecutable("clicked 2"), 1);
        MessageBox box7 = new MessageBox(new TestExecutable("clicked 3"), 0);
        MessageBox box8 = new MessageBox(new TestExecutable("clicked 4"), 1);

        addMessage(box1);
        addMessage(box2);
        addMessage(box3);
        addMessage(box4);
        addMessage(box5);
        addMessage(box6);
        addMessage(box7);
        addMessage(box8);
    }

    public void addMessage(MessageBox box){
        table.add(box.getStack()).width(240).padTop(28).left().padLeft((32 + 214 * box.getByUser()) * StateManager.M);
        table.row().expandX();
    }

    @Override
    public void reset() {
        messageField.remove();
        messageField = new WorkingTextArea("Message...", SkinSingleton.getInstance());
        messageField.setPosition((26 + 1) * M, 31 * M);
        messageField.setSize(560 * M, 60 * M);

        stage.addActor(messageField);
    }

    @Override
    public void dispose() {

    }

    private void addMessageField(){
        messageField = new WorkingTextArea("Message...", SkinSingleton.getInstance());
        messageField.setPosition((26 + 1) * M, 31 * M);
        messageField.setSize(560 * M, 60 * M);

        stage.addActor(messageField);
    }

    @Override
    public void update(float fy){
        super.update(fy);

        stage.act(); //This bug tho

        messageField.getText();//TODO something
    }

}