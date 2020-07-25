package com.xxx.recommendfilm.model;

class ResultModel<T> {
    Meta meta;
    T data;

    public ResultModel(Meta meta, T data) {
        this.meta = meta;
        this.data = data;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    class Meta{
        int code;
        String message;

        public Meta(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
