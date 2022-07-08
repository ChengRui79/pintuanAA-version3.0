package com.team.mange.common.exception.code;

/**
 * ResponseCodeInterface
 *
 * @author dell
 * @version V1.0
 * @date 2022年4月1日
 */
public interface ResponseCodeInterface {
    /**
     * 获取code
     *
     * @return code
     */
    int getCode();

    /**
     * 获取信息
     *
     * @return msg
     */
    String getMsg();
}
