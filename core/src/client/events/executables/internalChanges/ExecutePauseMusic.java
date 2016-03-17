package client.events.executables.internalChanges;

import client.pageStorage.Pages;
import client.pages.other.NowPlaying;
import client.stateInterfaces.Executable;
import org.robovm.apple.foundation.NSErrorException;

/**
 * Created by Hongyu Wang on 3/15/2016.
 */
public class ExecutePauseMusic implements Executable{


    @Override
    public void execute() {
        NowPlaying page = (NowPlaying)(Pages.NOWPLAYING.getStateReference());

        try {
            page.getPlayer().pause();
        } catch (NSErrorException e) {
            e.printStackTrace();
        }
    }
}
