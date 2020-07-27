package com.fengwenyi.mp3demo.stackoverflow.java8;

import com.fengwenyi.mp3demo.dto.UserVO;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MapObject2MapList {

    public static void main(String[] args) {
        List<UserVO> userVOS = Arrays.asList(
                new UserVO("1", "a", "1", "1"),
                new UserVO("1", "b", "2", "2"),
                new UserVO("1", "c", "11", "11"),
                new UserVO("2", "d", "2", "2"),
                new UserVO("2", "e", "2", "2"),
                new UserVO("2", "f", "2", "2")
        );
        Map<String, UserVO> collect2 = userVOS.stream()
                .collect(Collectors.toMap(
                        new Function<UserVO, String>() {
                            public String apply(UserVO p) {
                                return p.getId();
                            }
                        },
                        Function.<UserVO>identity(),
                        (left, right) -> right
                ));
        // wanna succinct expression
        // You can use a lambda:
        Map<String, UserVO> collect3 = userVOS.stream()
                .collect(Collectors.toMap(
                        p -> p.getId(),
                        Function.identity(),
                        (left, right) -> right
                ));
        //or, more concisely, you can use a method reference using :::
        Map<String, UserVO> collect4 = userVOS.stream()
                .collect(Collectors.toMap(
                        UserVO::getId,
                        Function.identity(),
                        (left, right) -> right
                ));
        //and instead of Function.identity, you can simply use the equivalent lambda:
        Map<String, String> stringMap = userVOS.stream()
                .collect(Collectors.toMap(
                        UserVO::getId,
                        p -> (p.getId()),
                        (person1, person2) -> person1 + ";" + person2
                ));

        //For example, the list of account contains:
        /**
         *  +-----------+----------+
         *  | accountId | contacts |
         *  +-----------+----------+
         *  |         1 | {"John"} |
         *  |         1 | {"Fred"} |
         *  |         2 | {"Mary"} |
         *  +-----------+----------+
         */
        //And I want it to produce a list of accounts like this:
        /**
         *  +-----------+------------------+
         *  | accountId |     contacts     |
         *  +-----------+------------------+
         *  |         1 | {"John", "Fred"} |
         *  |         2 | {"Mary"}         |
         *  +-----------+------------------+
         */

        Account account0 = new Account(1, Lists.newArrayList("a", "b", "c"));
        Account account = new Account(1, Lists.newArrayList("d", "e", "f"));
        List<Account> accounts = Lists.newArrayList(account0, account);

        //Fill
        Map<Integer, Account> collect = accounts
                .stream()
                .collect(
                        Collectors.toMap(
                                Account::getAccountId,
                                Function.identity(),
                                (Account account1, Account account2) -> {
                                account1.getContacts().addAll(account2.getContacts());
                                account2.getContacts().clear();
                                return account1;
                        })
                );

        Map<Integer, List<String>> collect1 = accounts
                .stream()
                .collect(
                        Collectors.toMap(
                                Account::getAccountId,
                                Account::getContacts,
                                (account1, account2) -> {
                                    System.err.println(account1);//a,b,c
                                    System.err.println(account2);//d,e,f
                                    account1.addAll(account2);
                                    return account1;
                                })
                );
        System.err.println(new Gson().toJson(collect1));

        List<Account> result = new ArrayList<>(accounts.stream()
                .collect(
                        Collectors.toMap(Account::getAccountId,
                                Function.identity(),
                                (Account account1, Account account2) -> {
                                account1.getContacts().addAll(account2.getContacts());
                                account2.getContacts().clear();
                                return account1;
                        })
                )
                .values());
    }

    @Data
    @AllArgsConstructor
    public static class Account{
        private Integer accountId;
        private List<String> contacts;
    }
}
