package server.services.implementations.mediaService;

import tools.serverTools.databases.LocalDatabaseFactory;
import server.model.media.MText;
import server.services.interfaces.models.TextManager;

/**
 * Created by Kevin Zheng on 2016-03-07.
 */
public class TextManagerImplementation implements TextManager {
//    @Override
//    public MText requestText(long key) {
//        MText text = new MText();
//        text.setKey(key);
//
//        int type = 0;
//        text.setType(type);
//        //TODO request from server.
//
//        String textString = null;
//        text.setText(textString);
//        //TODO request from server.
//
//        return text;
//    }

    @Override
    public MText createText(String message, int type){
        MText text = new MText();

        text.setKey(LocalDatabaseFactory.createModelStorage().generateKey());

        text.setText(message);

        text.setType(type);

        return text;
    }

    @Override
    public MText modifyType(MText text, int type) {
        text.setType(type);
        return text;
        //TODO request change to server.
    }

    @Override
    public MText modifyText(MText text, String textString) {
        text.setText(textString);
        return text;
        //TODO request change to server.
    }
}
