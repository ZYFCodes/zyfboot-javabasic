package org.zyf.javabasic.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yanfengzhang
 * @description
 * @date 2021/4/19  16:34
 */
@Data
public class Result<T> {
    private int code;
    private String message;
    private DataCsdn data;

    public static class ResultCode {
        public static Result.ResultCode HAS_MORE_DATA = new Result.ResultCode(150, "get range has more data");
        public static Result.ResultCode ASYNC_RESULT_QUEUE_FULL = new Result.ResultCode(-14, "async result queue is full");
        public static Result.ResultCode UNKNOWN = new Result.ResultCode(-1, "unknown");
        public static Result.ResultCode OK = new Result.ResultCode(0, "OK");
        public static Result.ResultCode TIMEOUT = new Result.ResultCode(-3989, "timeout");
        public static Result.ResultCode ASYNC_INVOKE_FAILED = new Result.ResultCode(-10, "async invoke failed");
        public static Result.ResultCode ASYNC_SEND_SUCCESS = new Result.ResultCode(-12, "async invoke ok");
        public static Result.ResultCode DATA_EXIST = new Result.ResultCode(-3966, "data already exist");
        public static Result.ResultCode SHOULD_PROXY = new Result.ResultCode(-3967, "should proxy");
        public static Result.ResultCode QUEUE_OVERLOWED = new Result.ResultCode(-3968, "queue overlowed");
        public static Result.ResultCode HIDDEN = new Result.ResultCode(-3969, "hidden");
        public static Result.ResultCode INVAL_CONN_ERROR = new Result.ResultCode(-3970, "inval conn error");
        public static Result.ResultCode LOCK_NOT_EXIST = new Result.ResultCode(-3974, "lock not exist");
        public static Result.ResultCode LOCK_ALREADY_EXIST = new Result.ResultCode(-3975, "lock already exist");
        public static Result.ResultCode MTIME_EARLY = new Result.ResultCode(-3976, "mtime early");
        public static Result.ResultCode PROXYED_ERROR = new Result.ResultCode(-3977, "proxyed error");
        public static Result.ResultCode DEC_NOT_FOUND = new Result.ResultCode(-3978, "dec not found");
        public static Result.ResultCode DEC_ZERO = new Result.ResultCode(-3979, "dec zero");
        public static Result.ResultCode DEC_BOUNDS = new Result.ResultCode(-3980, "dec bounds");
        public static Result.ResultCode CANNOT_OVERRIDE = new Result.ResultCode(-3981, "cann't override");
        public static Result.ResultCode INVALID_ARGUMENT = new Result.ResultCode(-3982, "invalid argument");
        public static Result.ResultCode INC_BOUNDS = new Result.ResultCode(-3963, "inc bounds");
        public static Result.ResultCode PART_OK = new Result.ResultCode(-3983, "partial success");
        public static Result.ResultCode MIGRATE_BUSY = new Result.ResultCode(-3984, "migrate busy");
        public static Result.ResultCode REQUEST_ROUTE_ERROR = new Result.ResultCode(-3986, "request route error");
        public static Result.ResultCode SERVER_CAN_NOT_WORK = new Result.ResultCode(-3987, "server can not work");
        public static Result.ResultCode ITEM_SIZE_ERROR = new Result.ResultCode(-3991, "item size error");
        public static Result.ResultCode ITEM_EMPTY = new Result.ResultCode(-3993, "item empty");
        public static Result.ResultCode ITEM_SERIALIZE_ERROR = new Result.ResultCode(-3994, "item serialize error");
        public static Result.ResultCode TYPE_NOT_MATCH = new Result.ResultCode(-3996, "data type not match");
        public static Result.ResultCode VERSION_ERROR = new Result.ResultCode(-3997, "version error");
        public static Result.ResultCode NOTEXISTS = new Result.ResultCode(-3998, "data not exist");
        public static Result.ResultCode FAILED = new Result.ResultCode(-3999, "failed");
        public static Result.ResultCode PROXYED = new Result.ResultCode(-4000, "proxyed");
        public static Result.ResultCode NOT_SURPPORT = new Result.ResultCode(-4001, "not surpport");
        public static Result.ResultCode REMOVE_NOT_ON_MASTER = new Result.ResultCode(-4101, "remove not on master");
        public static Result.ResultCode REMOVE_ONE_FAILED = new Result.ResultCode(-4102, "remove one failed");
        public static Result.ResultCode REQUEST_DENIAL = new Result.ResultCode(-4010, "request denial");
        public static Result.ResultCode ELEMENTS_TOO_MANY = new Result.ResultCode(-4013, "elements too many");
        public static Result.ResultCode NONE_DATASERVER = new Result.ResultCode(-5113, "none dataserver");
        public static Result.ResultCode COUNTER_OUT_OF_RANGE = new Result.ResultCode(-5114, "counter out of range");
        public static Result.ResultCode INIT = new Result.ResultCode(-50000, "init");
        public static Result.ResultCode RPC_OVERFLOW = new Result.ResultCode(-6000, "rpc overflow");
        public static Result.ResultCode MIGRATE_DENIAL = new Result.ResultCode(-4014, "migrate denial");
        public static Result.ResultCode EXPIRED = new Result.ResultCode(-3988, "expired");
        public static Result.ResultCode AUTH_SIGNATURE_NOT_EQUAL = new Result.ResultCode(-1001, "signature not equal");
        public static Result.ResultCode AUTH_KMS_RESULT_NULL = new Result.ResultCode(-1002, "kms token is null");
        public static Result.ResultCode AUTH_SIGNATURE_NULL = new Result.ResultCode(-1003, "generate signature failed");
        public static Result.ResultCode AUTH_KMS_RPC_FAIL = new Result.ResultCode(-1004, "kms rpc failed");
        private static Map<Integer, ResultCode> resultCodeMap = new HashMap();
        private int code;
        private String msg;

        private static void regist(Result.ResultCode rc) {
            resultCodeMap.put(rc.errno(), rc);
        }

        private ResultCode(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public String toString() {
            StringBuffer sb = new StringBuffer();
            sb.append("[(Code:" + this.code + ") (Message:" + this.msg + ")]");
            return sb.toString();
        }

        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            } else if (obj instanceof Result.ResultCode) {
                return ((Result.ResultCode)obj).errno() == this.code;
            } else {
                return false;
            }
        }

        public int hashCode() {
            return this.code + 5000;
        }

        public String getMsg() {
            return this.msg;
        }

        public static Result.ResultCode castResultCode(int code) {
            if (code == -3988) {
                code = -3998;
            }

            Result.ResultCode rc = (Result.ResultCode)resultCodeMap.get(code);
            return rc == null ? UNKNOWN : rc;
        }

        public int errno() {
            return this.code;
        }

        static {
            regist(UNKNOWN);
            regist(OK);
            regist(ASYNC_INVOKE_FAILED);
            regist(ASYNC_SEND_SUCCESS);
            regist(CANNOT_OVERRIDE);
            regist(DEC_BOUNDS);
            regist(DEC_NOT_FOUND);
            regist(DEC_ZERO);
            regist(FAILED);
            regist(HIDDEN);
            regist(INIT);
            regist(INVAL_CONN_ERROR);
            regist(INVALID_ARGUMENT);
            regist(ITEM_SIZE_ERROR);
            regist(ITEM_EMPTY);
            regist(ITEM_SERIALIZE_ERROR);
            regist(LOCK_ALREADY_EXIST);
            regist(LOCK_NOT_EXIST);
            regist(MIGRATE_BUSY);
            regist(MTIME_EARLY);
            regist(NONE_DATASERVER);
            regist(NOTEXISTS);
            regist(DATA_EXIST);
            regist(PART_OK);
            regist(PROXYED);
            regist(PROXYED_ERROR);
            regist(NOT_SURPPORT);
            regist(QUEUE_OVERLOWED);
            regist(REMOVE_NOT_ON_MASTER);
            regist(REMOVE_ONE_FAILED);
            regist(SERVER_CAN_NOT_WORK);
            regist(SHOULD_PROXY);
            regist(VERSION_ERROR);
            regist(REQUEST_ROUTE_ERROR);
            regist(ASYNC_RESULT_QUEUE_FULL);
            regist(TIMEOUT);
            regist(COUNTER_OUT_OF_RANGE);
            regist(RPC_OVERFLOW);
            regist(REQUEST_DENIAL);
            regist(ELEMENTS_TOO_MANY);
            regist(HAS_MORE_DATA);
            regist(TYPE_NOT_MATCH);
            regist(AUTH_SIGNATURE_NOT_EQUAL);
            regist(AUTH_KMS_RESULT_NULL);
            regist(AUTH_SIGNATURE_NULL);
            regist(AUTH_KMS_RPC_FAIL);
            regist(INC_BOUNDS);
            regist(MIGRATE_DENIAL);
        }
    }
}
