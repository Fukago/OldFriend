package com.example.apple.oldfriend.model;

import com.example.apple.oldfriend.model.bean.User;

import java.util.List;

/**
 * 老人得到监护人信息
 * 监护人得到老人信息
 * Created by gan on 2016/4/22.
 */
public interface IGetBothMessage {
    void getNurseMessage(User nurse);

    void getNurseMessageError(String s);

    void getOldMessage(List<User> oldList);

    void getOldMessageError(String s);

}
