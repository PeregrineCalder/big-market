package cn.peregrine.types.annotations;

import java.lang.annotation.*;

/**
 * @projectName: big-market
 * @package: cn.peregrine.types.annotations
 * @className: DCCValue
 * @author: Peregrine Calder
 * @version: 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface DCCValue {
    String value() default "";
}
