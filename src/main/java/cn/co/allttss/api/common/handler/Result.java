package cn.co.allttss.api.common.handler;

import cn.co.allttss.api.common.message.UnicomResponseEnums;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Result<T> {
    private boolean success;
    private String errCode;
    private String errMsg;
    private T data;
    private String time;

    public Result() {
    }

    public Result(boolean success, T data) {
        super();
        this.success = success;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "success=" + success +
                ", errCode='" + errCode + '\'' +
                ", errMsg='" + errMsg + '\'' +
                ", data=" + data + '\'' +
                ", time=" + time +
                '}';
    }

    public Result(boolean success, T data, String errCode, String errMsg, String time) {
        super();
        this.success = success;
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.data = data;
        this.time = time;
    }

    public Result(boolean success, String errCode, String errMsg, String time) {
        this.success = success;
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.time = time;
    }

    public Result(boolean success, UnicomResponseEnums enums, String time) {
        this.success = success;
        this.errCode = enums.getCode();
        this.errMsg = enums.getMsg();
        this.time = time;
    }

    public Result(boolean success, T data, UnicomResponseEnums enums, String time) {
        this.success = success;
        this.errCode = enums.getCode();
        this.errMsg = enums.getMsg();
        this.data = data;
        this.time = time;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

}