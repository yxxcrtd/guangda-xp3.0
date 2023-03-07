package com.joyintech.tams.common.svreport.datacheck.beforeinsert.util;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import com.googlecode.aviator.Options;
import com.googlecode.aviator.runtime.JavaMethodReflectionFunctionMissing;
import com.googlecode.aviator.runtime.function.AbstractVariadicFunction;

import java.util.HashMap;
import java.util.Map;

/**
 * 表达式工具类
 */
public class ExpressionUtil {

    static {
        // 基于反射的方法查找和调用
        AviatorEvaluator.setFunctionMissing(JavaMethodReflectionFunctionMissing.getInstance());

//        AviatorEvaluator.setOptimize(1);
//        AviatorEvaluator.getInstance().getOption(Options.OPTIMIZE_LEVEL);
        AviatorEvaluator.setOption(Options.PUT_CAPTURING_GROUPS_INTO_ENV, false);

        try {
            registerStaticFunction("str_kit", StringExpression.class);
            registerStaticFunction("date_kit", DateExpression.class);
            registerStaticFunction("big_decimal_kit", BigDecimalExpression.class);
            registerStaticFunction("unique_kit", UniqueExpression.class);
            registerStaticFunction("enum_kit", EnumerateExpression.class);
            registerStaticFunction("list_kit", ListExpression.class);
            registerInstanceFunction("map", HashMap.class);
        } catch (Exception e) {
            throw new RuntimeException("注册脚本函数异常", e);
        }
    }

    public static Object execute(final String expression, final Map<String, Object> env, final boolean cached) {
        return AviatorEvaluator.execute(expression, env, cached);
    }

    public static Object execute(final String expression, final Map<String, Object> env) {
        return AviatorEvaluator.execute(expression, env, true);
    }

    public static void registerCustomFunction(AbstractVariadicFunction function) {
        AviatorEvaluator.addFunction(function);
    }

    public static Expression compile(String expression, boolean cached) {
        return AviatorEvaluator.compile(expression, cached);
    }

    /**
     * 示例: registerStaticFunction("str", StringUtils.class);
     * str.isBlank('')
     *
     * @param key
     * @param clazz
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     */
    public static void registerStaticFunction(String key, Class<?> clazz) throws IllegalAccessException, NoSuchMethodException {
        AviatorEvaluator.addStaticFunctions(key, clazz);
    }

    /**
     * 示例: registerInstanceFunction("s", String.class);
     * s.indexOf("hello", "l")
     *
     * @param key
     * @param clazz
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     */
    public static void registerInstanceFunction(String key,Class<?> clazz) throws IllegalAccessException, NoSuchMethodException {
        AviatorEvaluator.addInstanceFunctions(key, clazz);
    }

    /**
     * 同时导入静态方法和实例方法
     *
     * registerInstanceFunction(StringUtils.class)
     * StringUtils.isBlank('hello world')
     *
     * @param clazz
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     */
    public static void registerAllFunction(Class<?> clazz) throws IllegalAccessException, NoSuchMethodException {
        AviatorEvaluator.importFunctions(clazz);
    }


    /**
     * 返回结果工具
     * @param flag
     * @param data
     * @return
     */
    public static Map<String, Object> resultMap(boolean flag,Object data) {
        Map<String, Object> map = new HashMap<>();
        map.put("flag",flag);
        if(data != null){
            map.put("data",data);
        }
        return map;
    }

}
