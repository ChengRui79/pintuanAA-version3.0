package com.team.mange.common.exception;

import com.team.mange.common.exception.code.BaseResponseCode;
import com.team.mange.common.exception.code.ResponseCodeInterface;

/**
 * BusinessException
 *
 * @author dell
 * @version V1.0
 * @date 2022年4月1日
 */
public class BusinessException extends RuntimeException {
    /**
     * 异常编号
     */
    private final int messageCode;

    /**
     * 对messageCode 异常信息进行补充说明
     */
    private final String detailMessage;

    public BusinessException(int messageCode, String message) {
        super(message);
        this.messageCode = messageCode;
        this.detailMessage = message;
    }

    public BusinessException(String message) {
        super(message);
        this.messageCode = BaseResponseCode.OPERATION_ERRO.getCode();
        this.detailMessage = message;
    }

    /**
     * 构造函数
     *
     * @param code 异常码
     */
    public BusinessException(ResponseCodeInterface code) {
        this(code.getCode(), code.getMsg());
    }

    public int getMessageCode() {
        return messageCode;
    }

    public String getDetailMessage() {
        return detailMessage;
    }
}
