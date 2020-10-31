package cn.co.allttss.api.common.message;

/**
 * 这里添加错误信息
 */
public enum UnicomResponseEnums {

    SYSTEM_ERROR("GTAP9999", "系统异常"),
    BAD_REQUEST("GTAP0001", "错误的请求参数"),
    NOT_FOUND("GTAP0002", "找不到请求路径！"),
    CONNECTION_ERROR("GTAP0003", "网络连接请求失败！"),
    METHOD_NOT_ALLOWED("GTAP0004", "不合法的请求方式"),
    DATABASE_ERROR("GTAP99998", "数据库异常"),
    BOUND_STATEMENT_NOT_FOUNT("GTAP0005", "找不到方法！"),
    REPEAT_REGISTER("GTAP0006", "重复注册"),
    NO_USER_EXIST("GTAP0007", "用户不存在"),
    INVALID_PASSWORD("GTAP0008", "密码错误"),
    NO_PERMISSION("GTAP0009", "非法请求！"),
    SUCCESS_OPTION("GTAP0010", "操作成功！"),
    NOT_MATCH("GTAP0011", "用户名和密码不匹配"),
    FAIL_GETDATA("GTAP0012", "获取信息失败"),
    BAD_REQUEST_TYPE("GTAP0013", "错误的请求类型"),
    INVALID_MOBILE("GTAP0014", "无效的手机号码"),
    INVALID_EMAIL("GTAP0015", "无效的邮箱"),
    INVALID_GENDER("GTAP0016", "无效的性别"),
    REPEAT_MOBILE("GTAP0017", "已存在此手机号"),
    REPEAT_EMAIL("GTAP0018", "已存在此邮箱地址"),
    NO_RECORD("GTAP0019", "没有查到相关记录"),
    LOGIN_SUCCESS("GTAP0020", "登陆成功"),
    LOGOUT_SUCCESS("GTAP0021", "已退出登录"),
    SENDEMAIL_SUCCESS("GTAP0022", "邮件已发送，请注意查收"),
    EDITPWD_SUCCESS("GTAP0023", "修改密码成功"),
    No_FileSELECT("GTAP0024", "未选择文件"),
    FILEUPLOAD_SUCCESS("GTAP0025", "上传成功"),
    NOLOGIN("GTAP0026", "未登陆"),
    ILLEGAL_ARGUMENT("GTAP0027", "参数不合法"),
    ERROR_IDCODE("GTAP0028", "验证码不正确");

    private String code;
    private String msg;

    private UnicomResponseEnums(String code, String msg) {

        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}