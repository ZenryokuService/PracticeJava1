package jp.zenryoku.sample.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Type;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * アノテーション・インターフェース
 * 自前アノテーションを作成する
 */
@Retention(RUNTIME)
@Target({TYPE, METHOD})
public @interface Sample {
}
