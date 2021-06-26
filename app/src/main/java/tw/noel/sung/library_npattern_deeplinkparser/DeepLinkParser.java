package tw.noel.sung.library_npattern_deeplinkparser;

import android.net.Uri;


import java.lang.reflect.Field;

import tw.noel.sung.library_npattern_deeplinkparser.annotation.NParameter;

public class DeepLinkParser {

    public <T> T parse(String linkUrl, Class<T> parameterDataClass) {
        T parameterObject = null;
        try {
            parameterObject = (T) Class.forName(parameterDataClass.getName()).newInstance();

            Uri uri = Uri.parse(linkUrl);
            //取得所有宣告的變數
            Field[] fields = parameterDataClass.getDeclaredFields();
            for (Field field : fields) {
                //取其變數名稱
                String queryName = field.getName();

                //如果有下NParameter 的annotation 則以此annotation的值為準
                if (field.isAnnotationPresent(NParameter.class)) {
                    NParameter nParameter = field.getAnnotation(NParameter.class);
                    if (nParameter != null) {
                        queryName = nParameter.value();
                    }
                }

                String parameterValue = uri.getQueryParameter(queryName);
                if (parameterValue != null) {
                    field.setAccessible(true);
                    field.set(parameterObject, parameterValue);
                }
            }
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return parameterObject;
    }
}
