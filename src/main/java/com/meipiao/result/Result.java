package com.meipiao.result;

import org.anyline.entity.DataRow;
import org.anyline.entity.DataSet;
import org.anyline.entity.PageNavi;
import org.anyline.util.BasicUtil;
import org.anyline.util.ConfigTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @Author: Chenwx
 * @Date: 2020/5/9 15:36
 */
public class Result<T> {
        private static final Logger log = LoggerFactory.getLogger(Result.class);
        private boolean result;
        private Integer code;
        private String message;
        private T data;
        private PageNavi pageNavi;
        private String type;
        private long response_time_to;

        private static Result commonResult(ResultCode resultCode, Object data) {
            Result result = new Result();
            String message = "";
            String dataType = null;
            int code = 0;
            boolean flagException = false;
            if (null == data) {
                message = (String) BasicUtil.nvl(new String[]{resultCode.getMsg(), "没有返回数据"});
                data = "";
            } else if (data instanceof DataSet) {
                DataSet set = (DataSet)data;
                message = message + (String)BasicUtil.nvl(new String[]{resultCode.getMsg(), set.getMessage()});
                dataType = "list";
                data = set.getRows();
                result.setPageNavi(set.getNavi());
            } else if (data instanceof Iterable) {
                dataType = "list";
            } else if (data instanceof DataRow) {
                dataType = "map";
            } else if (data instanceof Map) {
                dataType = "map";
            } else if (data instanceof String) {
                dataType = "string";
                data = data.toString();
            } else if (data instanceof Number) {
                dataType = "number";
                data = data.toString();
            } else if (data instanceof Exception) {
                dataType = "com/meipiao/exception";
                Exception e = (Exception)data;
                message = e.getClass().getName();
                if (data instanceof CustomException) {
                    code = ((CustomException)data).getCode();
                    message = ((CustomException)data).getMsg();
                }

                data = stackTraceToString(e.getClass().getName(), e.getMessage(), e.getStackTrace());
                flagException = true;
            } else {
                dataType = "map";
            }

            if (!resultCode.isSuccess() && null != data && !flagException) {
                message = message + data.toString();
            }

            result.setType(dataType);
            result.setResult(resultCode.isSuccess());
            result.setCode(code == 0 ? resultCode.getCode() : code);
            result.setMessage(BasicUtil.isEmpty(message) ? resultCode.getMsg() : message);
            result.setData(data);
            result.setResponse_time_to(System.currentTimeMillis());
            if (ConfigTable.isDebug() && log.isWarnEnabled()) {
                log.warn("[controller return][result:{}][message:{}]", resultCode.isSuccess(), message);
            }

            return result;
        }

        public static Result success(Object... data) {
            return null != data && data.length == 1 ? commonResult(ResultCode.SUCCESS, data[0]) : commonResult(ResultCode.SUCCESS, data);
        }

        public static Result fail() {
            return fail(ResultCode.FAILED);
        }

        public static Result fail(String msg) {
            return commonResult(ResultCode.FAILED, msg);
        }

        public static Result fail(ResultCode resultCode, Object data) {
            return commonResult(resultCode, data);
        }

        public static Result fail(ResultCode resultCode) {
            return fail(resultCode, (Object)null);
        }

        public static String stackTraceToString(String exceptionName, String exceptionMessage, StackTraceElement[] elements) {
            StringBuffer strbuff = new StringBuffer();
            StackTraceElement[] var4 = elements;
            int var5 = elements.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                StackTraceElement stet = var4[var6];
                strbuff.append(stet);
                strbuff.append("\\r\\n");
            }

            String message = exceptionName + ":" + exceptionMessage + "\\r\\n" + strbuff.toString();
            return message;
        }

        public Result(boolean result, Integer code, String message, T data, PageNavi pageNavi, String type, long response_time_to) {
            this.result = result;
            this.code = code;
            this.message = message;
            this.data = data;
            this.pageNavi = pageNavi;
            this.type = type;
            this.response_time_to = response_time_to;
        }

        public Result() {
        }

        public boolean isResult() {
            return this.result;
        }

        public Integer getCode() {
            return this.code;
        }

        public String getMessage() {
            return this.message;
        }

        public T getData() {
            return this.data;
        }

        public PageNavi getPageNavi() {
            return this.pageNavi;
        }

        public String getType() {
            return this.type;
        }

        public long getResponse_time_to() {
            return this.response_time_to;
        }

        public void setResult(boolean result) {
            this.result = result;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public void setData(T data) {
            this.data = data;
        }

        public void setPageNavi(PageNavi pageNavi) {
            this.pageNavi = pageNavi;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setResponse_time_to(long response_time_to) {
            this.response_time_to = response_time_to;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            } else if (!(o instanceof Result)) {
                return false;
            } else {
                Result<?> other = (Result)o;
                if (!other.canEqual(this)) {
                    return false;
                } else if (this.isResult() != other.isResult()) {
                    return false;
                } else {
                    label77: {
                        Object this$code = this.getCode();
                        Object other$code = other.getCode();
                        if (this$code == null) {
                            if (other$code == null) {
                                break label77;
                            }
                        } else if (this$code.equals(other$code)) {
                            break label77;
                        }

                        return false;
                    }

                    label70: {
                        Object this$message = this.getMessage();
                        Object other$message = other.getMessage();
                        if (this$message == null) {
                            if (other$message == null) {
                                break label70;
                            }
                        } else if (this$message.equals(other$message)) {
                            break label70;
                        }

                        return false;
                    }

                    Object this$data = this.getData();
                    Object other$data = other.getData();
                    if (this$data == null) {
                        if (other$data != null) {
                            return false;
                        }
                    } else if (!this$data.equals(other$data)) {
                        return false;
                    }

                    label56: {
                        Object this$pageNavi = this.getPageNavi();
                        Object other$pageNavi = other.getPageNavi();
                        if (this$pageNavi == null) {
                            if (other$pageNavi == null) {
                                break label56;
                            }
                        } else if (this$pageNavi.equals(other$pageNavi)) {
                            break label56;
                        }

                        return false;
                    }

                    Object this$type = this.getType();
                    Object other$type = other.getType();
                    if (this$type == null) {
                        if (other$type != null) {
                            return false;
                        }
                    } else if (!this$type.equals(other$type)) {
                        return false;
                    }

                    if (this.getResponse_time_to() != other.getResponse_time_to()) {
                        return false;
                    } else {
                        return true;
                    }
                }
            }
        }

        protected boolean canEqual(Object other) {
            return other instanceof Result;
        }

        public String toString() {
            boolean var10000 = this.isResult();
            return "Result(result=" + var10000 + ", code=" + this.getCode() + ", message=" + this.getMessage() + ", data=" + this.getData() + ", pageNavi=" + this.getPageNavi() + ", type=" + this.getType() + ", response_time_to=" + this.getResponse_time_to() + ")";
        }
}
