package com.longyao.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 全局统一返回结果类
 */
@Data
@ApiModel(value = "全局统一返回结果")
public class Meta<T> {

    @ApiModelProperty(value = "返回数据")
    private T data;

    @ApiModelProperty(value = "返回码2")
    private Integer status;

    @ApiModelProperty(value = "返回消息2")
    private String msg;


    public Meta(){}

    protected static <T> Meta<T> build(T data) {
        Meta<T> result = new Meta<T>();
        if (data != null)
            result.setData(data);
        return result;
    }

    public static <T> Meta<T> build(T body, ResultCodeEnum resultCodeEnum) {
        Meta<T> result = build(body);
        result.setStatus(resultCodeEnum.getCode());
        result.setMsg(resultCodeEnum.getMessage());
        return result;
    }

    public static <T> Meta<T> build(Integer code, String message) {
        Meta<T> result = build(null);
        result.setStatus(code);
        result.setMsg(message);
        return result;
    }

    public static<T> Meta<T> ok(){
        return Meta.ok(null);
    }

    /**
     * 操作成功
     * @param data
     * @param <T>
     * @return
     */
    public static<T> Meta<T> ok(T data){
        Meta<T> result = build(data);
        return build(data, ResultCodeEnum.SUCCESS);
    }

    public static<T> Meta<T> fail(){
        return Meta.fail(null);
    }

    /**
     * 操作失败
     * @param data
     * @param <T>
     * @return
     */
    public static<T> Meta<T> fail(T data){
        Meta<T> result = build(data);
        return build(data, ResultCodeEnum.FAIL);
    }

    public Meta<T> msg(String msg){
        this.setMsg(msg);
        return this;
    }

    public Meta<T> status(Integer code){
        this.setStatus(code);
        return this;
    }

    public boolean isOk() {
        if(this.getStatus().intValue() == ResultCodeEnum.SUCCESS.getCode().intValue()) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "meta:{" +
                "data:" + data +
                ", status:" + status +
                ", msg:'" + msg + '\'' +
                '}';
    }
}
