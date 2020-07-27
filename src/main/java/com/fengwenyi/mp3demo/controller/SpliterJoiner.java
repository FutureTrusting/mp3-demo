package com.fengwenyi.mp3demo.controller;

import cn.hutool.core.util.URLUtil;
import jdk.nashorn.internal.runtime.regexp.RegExp;
import jdk.nashorn.internal.runtime.regexp.RegExpMatcher;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriUtils;

import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.*;

/**
 * @author : Caixin
 * @date 2019/10/10 11:02
 */
public class SpliterJoiner {


    public static void main(String[] args){
        String logoPic = "/files/191010C1RKCvdpnLRWtMJfzmdvuFH7XfhcUDQX7exGM7j6w-o.xlsx?token=GWsIv9rb157GCo56DUttBSGEkuvJ1ELzqPqV9GIaQQA=&ts=31556889864403199";
//        URI uri = URLUtil.toURI(logoPic);
        UriComponents build = UriComponentsBuilder.fromUriString(logoPic).build();
        String token = build.getQueryParams().get("token").get(0);
        String ts = build.getQueryParams().get("ts").get(0);

        Optional<String> buildPath = Optional.ofNullable(build.getPath());

        System.err.println(ts);
        System.err.println(token);
        System.err.println(buildPath);

        String oriName = buildPath.map(x -> x.substring(x.lastIndexOf("/") + 1)).orElse("");
        String type = oriName.substring(oriName.lastIndexOf(".") + 1);

        System.err.println(oriName);
        System.err.println(type);

    }
}
