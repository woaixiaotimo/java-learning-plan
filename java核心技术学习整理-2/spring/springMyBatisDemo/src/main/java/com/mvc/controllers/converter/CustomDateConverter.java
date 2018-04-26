package com.mvc.controllers.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * <p>Title: CustomDateConverter</p>
 * <p>Description:日期转换器 </p>
 * <p>Company: www.itcast.com</p>
 *
 * @author 传智.燕青
 * @version 1.0
 * @date 2015-4-13下午5:49:14
 */
public class CustomDateConverter implements Converter<String, Date> {

    public String getDatePattern() {
        return datePattern;
    }

    public void setDatePattern(String datePattern) {
        this.datePattern = datePattern;
    }

    private String datePattern = "yyyy-MM-dd HH:mm:ss";

    public Date convert(String source) {

        //实现 将日期串转成日期类型(格式是yyyy-MM-dd HH:mm:ss)

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);

        try {
            //转成直接返回
            return simpleDateFormat.parse(source);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //如果参数绑定失败返回null
        return null;
    }

}
