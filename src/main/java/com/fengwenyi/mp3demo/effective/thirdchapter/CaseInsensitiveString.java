package com.fengwenyi.mp3demo.effective.thirdchapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author : Caixin
 * @date 2019/10/21 16:51
 */

// Broken - violates symmetry !

public class CaseInsensitiveString {

    private final String s;

    public CaseInsensitiveString(String s) {
        this.s = Objects.requireNonNull(s);
    }

    // Broken - violates symmetry !

    @Override
    public boolean equals(Object o) {

        return o instanceof CaseInsensitiveString
                && ((CaseInsensitiveString) o).s.equalsIgnoreCase(s);

//        if (o instanceof CaseInsensitiveString) {
//            return s.equalsIgnoreCase(
//                    ((CaseInsensitiveString) o).s
//            );
//        }
//        // One-way interoperability!
//        if (o instanceof String) {
//            return s.equalsIgnoreCase((String) o);
//        }
//        return false;
    }

    public static void main(String[] args) {
        CaseInsensitiveString cis = new CaseInsensitiveString("Polish");
        String s = "Polish";
        System.err.println(cis.equals(s)); //true
        System.err.println(s.equals(cis)); //false

        List<CaseInsensitiveString> caseInsensitive = new ArrayList<>();
        caseInsensitive.add(cis);

        //一旦违反了 equals 约定，当其他对象面对 的对象时，你完全不知道这些对象的行为会怎么样
        System.err.println(caseInsensitive.contains(s));

    }


}
