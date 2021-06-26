package tw.noel.sung.library_npattern_deeplinkparser.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/***
 * 可用於宣告在參數上類似GSON的@Serializedname用法
 * 讓宣告的變數無須跟DeepLink中的參數長的一樣
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface NParameter {
    String value();
}
