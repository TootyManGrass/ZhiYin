package yonghu;

import java.util.ArrayList;

/**
 * Created by Kevin Zheng on 2016-02-19.
 */
public class Group {
    ArrayList<User> members;

    /**Creates a group with various other users.
     *
     * @param users
     */
    public Group(ArrayList<User> users){
        members = users;
    }
}
