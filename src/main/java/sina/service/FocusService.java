package sina.service;

import sina.domain.Focus;
import sina.domain.FocusUserList;
import sina.domain.User;

public interface FocusService {

    Focus findFocusAndBeFocusNub(int uid);

    String addOneFocus(int befid, int uid ,boolean isFocus);

    FocusUserList<User> findUserList(int uid, int status);
}
