package com.fengwenyi.mp3demo.java8.collectionstream;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.fengwenyi.mp3demo.dto.User;
import com.fengwenyi.mp3demo.java8.efficientrefact.service.A;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author : Caixin
 * @date 2019/9/10 16:27
 */
public class MergeListList {

    public static void main(String[] args) {
        List<List<User>> list = new ArrayList<>();
        User user = new User("user0","user0","user0","user0","user0");
        User user1 = new User("user1","user1","user1","user1","user1");
        User user2 = new User("user2","user2","user2","user2","user2");
        User user3 = new User("user3","user3","user3","user3","user3");
        User user4 = new User("user4","user4","user4","user4","user4");
        User user5 = new User("user5","user5","user5","user5","user5");

        list.add(Arrays.asList(user,user1));
        list.add(Arrays.asList(user1,user2));
        list.add(null);
        list.add(Arrays.asList(user3,user4));
        list.add(Arrays.asList(user4,user5));

        List<User> reduce = list.stream()
                .filter(CollectionUtils::isNotEmpty)
                .reduce(new ArrayList<>(), (all, item) -> {
                    all.addAll(item);
                    return all;
                });

        System.out.println(reduce);
    }
}
