package com.fengwenyi.mp3demo.util;

/**
 * Created by Zidong on 2019/1/15 下午7:56
 */
public enum FileType {
    /**
     * 各种文件格式
     */
    jpg("ffd8ff"),

    png("89504e47"),

    gif("47494638"),

    bmp("424d"),

    html("3c21444f435459504520"),

    rtf("7b5c72"),

    email("5265"),

    pdf("255044"),

    mp4("0000001866747970"),

    mp3("494433"),

    avi("52494646"),

    rar("526172211a0700cf9073"),

    exe_dll("4d5a"),

    swf1("465753"),

    swf2("435753"),

    ttf("000100"),

    DOCX_XLSX("504b0304140006"),

    zip("504b0304"),

    ppt_doc_xls("d0cf11e0a1b11ae1");

    private String value = "";

    private FileType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
