package com.belazy.library.core.enums;

/**
 * 访问来源
 * @author tangcp
 */
public enum  SourceEnum {
    APP("App"),
    WEB("Web");
    public String source;
    SourceEnum(String source) {
        this.source = source;
    }
}
