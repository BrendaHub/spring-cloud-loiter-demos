package com.nacos.springcloudnacosconsumer.vo;

import java.io.Serializable;

public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = 2992076632235691301L;

    public static final String STATUS_SUCCESS = "success";
    public static final String STATUS_FAIL = "fail";


    public static final Integer RESULT_CODE_SUCCESS = 200;
    public static final Integer RESULT_CODE_BAD_REQUEST = 400;
    public static final Integer RESULT_CODE_INTERNAL_SERVER_ERROR = 500;

    /**
     * See ResultCode.
     */
    private Integer code;

    private String msg;

    private String status;

    private T data;

    public ResultVO() {
    }

    public static <T> ResultVO<T> build(Integer code, String msg) {
        Builder<T> builder = new Builder<>();
        ResultVO<T> resultVO = builder
                .code(code)
                .msg(msg)
                .build();

        return resultVO;
    }

    public static <T> ResultVO<T> build(Integer code, String msg, T data) {
        Builder<T> builder = new Builder<>();
        ResultVO<T> resultVO = builder
                .code(code)
                .msg(msg)
                .data(data)
                .build();

        return resultVO;
    }

    public static <T> ResultVO<T> success(T data) {
        Builder<T> builder = new Builder<>();
        ResultVO<T> resultVO = builder
                .code(RESULT_CODE_SUCCESS)
                .msg("Execute success!")
                .status(STATUS_SUCCESS)
                .data(data)
                .build();

        return resultVO;
    }

    public static <T> ResultVO<T> fail() {
        Builder<T> builder = new Builder<>();
        ResultVO<T> resultVO = builder
                .code(RESULT_CODE_INTERNAL_SERVER_ERROR)
                .msg("Execute fail!")
                .status(STATUS_FAIL)
                .data(null)
                .build();

        return resultVO;
    }

    private ResultVO(Builder<T> builder) {
        this.code = builder.code;
        this.msg = builder.msg;
        this.status = builder.status;
        this.data = builder.data;
    }

    public static <T> Builder<T> builder() {
        return new Builder<>();
    }

    public static class Builder<T> {

        private Integer code;

        private String msg;

        private String status;

        private T data;

        public <T> ResultVO<T> build() {
            return new ResultVO(this);
        }

        public Builder<T> code(Integer code) {
            this.code = code;
            return this;
        }

        public Builder<T> msg(String msg) {
            this.msg = msg;
            return this;
        }

        public Builder<T> status(String status) {
            this.status = status;
            return this;
        }

        public Builder<T> data(T data) {
            this.data = data;
            return this;
        }

    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
