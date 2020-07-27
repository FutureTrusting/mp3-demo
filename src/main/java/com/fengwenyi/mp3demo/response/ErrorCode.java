package com.fengwenyi.mp3demo.response;

public enum ErrorCode implements ErrorBase {
    SUCCESS("0", "SUCCESS");

    private String code;
    private String prompt;
    private String errorMessage;

    private ErrorCode(String code, String prompt) {
        this.setCode(code);
        this.setPrompt(prompt);
    }

    private ErrorCode(String code, String prompt, String errorMessage) {
        this.setCode(code);
        this.setPrompt(prompt);
        this.setErrorMessage(errorMessage);
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getPrompt() {
        return this.prompt;
    }

    @Override
    public String getErrorMessage() {
        return this.errorMessage;
    }

    private void setCode(String code) {
        this.code = code;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

