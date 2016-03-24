package client.pages.friends.boxes;

import client.events.executables.internalChanges.ExecuteChangePage;
import client.pageStorage.Pages;
import client.singletons.SkinSingleton;
import client.singletons.StateManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Contains a table of actors. Used in Friends1.
 */
public class FriendBox{

    public static final int RECIEVED_READ = 1;
    public static final int RECIEVED_UNREAD = 2;
    public static final int SENT_READ = 3;
    public static final int SENT_UNREAD = 4;

    private Table table;
    private Image currentIcon;
    /**
     *
     * @param y The vertical distance from the top bar.
     * @param iconNum The number representing an icon.
     * @param friendName The friend's name.
     */
    public FriendBox(int iconNum, String friendName){
        initTable();

        addIcon(iconNum);
        addLabel(friendName);
        addButton();
    }

    private void initTable(){
        this.table = new Table();
//        table.setBounds(0, y * StateManager.M, 750 * StateManager.M, 117 * StateManager.M);
    }

    private void addIcon(int iconNum){
        setIcon(iconNum);
    }

    private void addLabel(String friendName){
        Label name = new Label(friendName, SkinSingleton.getInstance());
        table.add(name).padLeft(20 * StateManager.M);
    }

    private void addButton(){
        Image i = new Image(new Texture("Friends/Enter@" + StateManager.M + ".png"));
        final ImageButton button = new ImageButton(i.getDrawable());

        button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                new ExecuteChangePage(Pages.FRIENDS4).execute();
            }
        });

        table.add(button).expandX().right().padRight(20 * StateManager.M);
    }

    public Table getTable(){
        return this.table;
    }

    public void setIcon(int iconNum){
        table.removeActor(currentIcon);

        Texture texture = new Texture("Friends/Icon" + iconNum + "@" + StateManager.M +".png");
        Image image = new Image(texture);
        this.currentIcon = image;

        table.add(currentIcon).padLeft(60 * StateManager.M);
    }
}
