package com.dauble.wow.format;

import com.dauble.wow.domain.DemoObj;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;

public class HttpMessageConvert extends AbstractHttpMessageConverter<DemoObj> {
    public HttpMessageConvert() {
        super(new MediaType("application", "x-wisely", Charset.forName("UTF-8")));
    }

    /**
     * class1.isAssignableFrom(class2)
     * 判定此 Class 对象所表示的类或接口与指定的 Class 参数所表示的类或接口是否相同，
     * 或是否是其超类或超接口。如果是则返回 true；
     * 否则返回 false。
     * 如果该 Class 表示一个基本类型，且指定的 Class 参数正是该 Class 对象，则该方法返回 true；否则返回 false。
     * @param aClass
     * @return
     * 这个方法 只处理返回 true 的 类.
     */
    @Override
    protected boolean supports(Class<?> aClass) {
        return DemoObj.class.isAssignableFrom(aClass);
    }

    /**
     * 处理请求的数据
     * @param aClass
     * @param httpInputMessage
     * @return
     * @throws IOException
     * @throws HttpMessageNotReadableException
     */
    @Override
    protected DemoObj readInternal(Class<? extends DemoObj> aClass,
                                   HttpInputMessage httpInputMessage) throws IOException,
            HttpMessageNotReadableException {
        String temp = StreamUtils.copyToString(httpInputMessage.getBody(),Charset.forName("UTF-8"));
        String [] tempArr =  temp.split("-");
        return new DemoObj(new Long(tempArr[0]),tempArr[1]);
    }

    /**
     * 处理如何输出到 response
     * @param obj
     * @param httpOutputMessage
     * @throws IOException
     * @throws HttpMessageNotWritableException
     */
    @Override
    protected void writeInternal(DemoObj obj, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        String  out = "hello"+obj.getId()+"-"+obj.getName();
        httpOutputMessage.getBody().write(out.getBytes("UTF-8"));
    }


}
