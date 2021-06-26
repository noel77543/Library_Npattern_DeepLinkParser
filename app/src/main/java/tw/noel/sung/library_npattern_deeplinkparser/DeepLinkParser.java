package tw.noel.sung.library_npattern_deeplinkparser;

import android.net.Uri;


import java.lang.reflect.Field;

import tw.noel.sung.library_npattern_deeplinkparser.annotation.NParameter;

public class DeepLinkParser<T> {

    public T parse(String linkUrl, T parameterData) throws IllegalAccessException {
        Uri uri = Uri.parse(linkUrl);
        //取得所有宣告的變數
        Field[] fields = parameterData.getClass().getDeclaredFields();
        for (Field field : fields) {
            //取其變數名稱
            String queryName= field.getName();

            //如果有下NParameter 的annotation 則以此annotation的值為準
            if(field.isAnnotationPresent(NParameter.class)){
                NParameter nParameter = field.getAnnotation(NParameter.class);
                if(nParameter!=null){
                    queryName = nParameter.value();
                }
            }

            String parameterValue = uri.getQueryParameter(queryName);
            if (parameterValue != null) {
                field.setAccessible(true);
                field.set(parameterData, parameterValue);
            }
        }
        return parameterData;
    }

}
