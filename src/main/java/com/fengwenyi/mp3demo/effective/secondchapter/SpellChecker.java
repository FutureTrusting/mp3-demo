package com.fengwenyi.mp3demo.effective.secondchapter;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author : Caixin
 * @date 2019/10/14 17:29
 */
public class SpellChecker {

    //依赖注入构造方法
    // Dependency injection provides flexibility and testability

    private final Lexicon dictionary;

    public SpellChecker(Lexicon dictionary) {
        this.dictionary = Objects.requireNonNull(dictionary);
    }

    public boolean isValid(String word) {
        return Boolean.TRUE;
    }

    public List<String> suggestions(String typo) {
        return Collections.emptyList();
    }

    private class Lexicon {
        String name = "";
        String word = "";
    }
}
