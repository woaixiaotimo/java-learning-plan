package com.po;

import java.util.List;

/**
 * Created by 啊Q on 2018/4/17.
 */
public class UserQueryVo {

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    private List<Integer> ids;

    public UserCustom getUserCustom() {
        return userCustom;
    }

    public void setUserCustom(UserCustom userCustom) {
        this.userCustom = userCustom;
    }

    private UserCustom userCustom;
}
