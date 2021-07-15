package com.ysa.util;

import com.alibaba.fastjson.JSONObject;
import com.ysa.enums.DatePatternEnum;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@Slf4j
public final class DataUtil {

    /**
     * 判断对象是否Empty(null或元素为0)<br>
     * 实用于对如下对象做判断:String Collection及其子类 Map及其子类
     *
     * @param pObj 待检查对象
     * @return boolean 返回的布尔值
     */
    public static boolean isEmpty(Object pObj) {
        if (pObj == null) {
            return true;
        }
        if (pObj == "") {
            return true;
        }
        if (pObj instanceof String) {
            return ((String) pObj).trim().length() == 0;
        } else if (pObj instanceof Collection<?>) {
            return ((Collection<?>) pObj).size() == 0;
        } else if (pObj instanceof Map<?, ?>) {
            return ((Map<?, ?>) pObj).size() == 0;
        } else if (pObj instanceof Object[]) {
            return ((Object[]) pObj).length == 0;
        } else if (pObj instanceof boolean[]) {
            return ((boolean[]) pObj).length == 0;
        } else if (pObj instanceof byte[]) {
            return ((byte[]) pObj).length == 0;
        } else if (pObj instanceof char[]) {
            return ((char[]) pObj).length == 0;
        } else if (pObj instanceof short[]) {
            return ((short[]) pObj).length == 0;
        } else if (pObj instanceof int[]) {
            return ((int[]) pObj).length == 0;
        } else if (pObj instanceof long[]) {
            return ((long[]) pObj).length == 0;
        } else if (pObj instanceof float[]) {
            return ((float[]) pObj).length == 0;
        } else if (pObj instanceof double[]) {
            return ((double[]) pObj).length == 0;
        }
        return false;
    }

    /**
     * 判断对象是否为NotEmpty(!null或元素>0)<br>
     * 实用于对如下对象做判断:String Collection及其子类 Map及其子类
     *
     * @param pObj 待检查对象
     * @return boolean 返回的布尔值
     */
    public static boolean isNotEmpty(Object pObj) {
        return !isEmpty(pObj);
    }

    /**
     * 得到默认值
     *
     * @param value
     * @param defaultValue
     * @param <T>
     * @return
     */
    public static <T> T getDefaultValue(T value, T defaultValue) {
        return getDefaultValue(value, defaultValue, a -> a);
    }

    /**
     * 得到默认值, 如果原值不为空可以处理
     *
     * @param value
     * @param defaultValue
     * @param function
     * @param <T>
     * @return
     */
    public static <T, S> T getDefaultValue(S value, T defaultValue, Function<S, T> function) {
        return isNotEmpty(value)
                ? function.apply(value)
                : defaultValue;
    }

    /**
     * 得到默认值, 如果原值不为空可以处理
     *
     * @param value
     * @param defaultValue
     * @param function
     * @param <T>
     * @return
     */
    public static <T, S> T getDefaultValueSafety(S value, T defaultValue, Function<S, T> function) {
        try {
            return isNotEmpty(value)
                    ? function.apply(value)
                    : defaultValue;
        } catch (Exception e) {
            return defaultValue;
        }
    }


    /**
     * 将json转换为 map
     *
     * @param objStr
     * @return
     */
    public static Map<String, String> json2Map(String objStr) {
        Map<String, String> data = new HashMap<>();
        JSONObject jsonObject = JSONObject.parseObject(objStr);
        for (Object key : jsonObject.keySet()) {
            String value = jsonObject.getString(key.toString());
            if (isNotEmpty(value)) {
                data.put(key.toString(), value);
            }
        }
        return data;
    }

    /**
     * 得到值, 如果原值不为空可以处理
     *
     * @param dataGetter   获取值
     * @param defaultValue 默认值
     * @param <T>          类型
     * @return 获取值 保证不会出现空指针
     */
    public static <T> T getValueSafety(Supplier<T> dataGetter, T defaultValue) {
        try {
            return dataGetter.get();
        } catch (Exception e) {
            return defaultValue;
        }
    }


    /**
     * 对象根据某个字段去重
     * @param keyExtractor
     * @param <T>
     * @return
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    /**
     * 判断一个对象的所有字段是都为空
     * @param obj 对象
     * @return
     * @throws IllegalAccessException
     */
    public static boolean isAllFieldNull(Object obj) throws IllegalAccessException {
        Class<?> aClass = obj.getClass();
        Field[] fs = aClass.getDeclaredFields();
        boolean flag = true;
        for (Field f : fs) {
            f.setAccessible(true);
            Object o = f.get(obj);
            if (o != null) {
                flag = false;
            }
        }
        return flag;
    }

    /**
     * 通过反射设置字段值
     *
     * @param entity 对象
     * @param field  字段
     * @param value  字段值
     * @param <T>    类型
     * @return 返回对象
     * @throws IllegalAccessException
     */
    public static <T> T objectSetFields(T entity, String field, String value) throws IllegalAccessException {
        Class<?> clazz = entity.getClass();
        try {
            // 获取字段
            Field declaredField = clazz.getDeclaredField(field);
            // 开通权限
            declaredField.setAccessible(Boolean.TRUE);
            Object v = null;
            if (DataUtil.isNotEmpty(value)) {
                if (declaredField.getType().getTypeName().equals("java.lang.Integer")) {
                    v = Integer.parseInt(value);
                } else if (declaredField.getType().getTypeName().equals("java.lang.String")) {
                    v = value;
                } else if (declaredField.getType().getTypeName().equals("java.math.BigDecimal")) {
                    v = new BigDecimal(value);
                } else if (declaredField.getType().getTypeName().equals("java.lang.Boolean")) {
                    v = Boolean.parseBoolean(value);
                } else if (declaredField.getType().getTypeName().equals("java.util.Date")) {
                    v = DateUtil.toDate(value, DatePatternEnum.NORM_DATETIME_PATTERN);
                }
            }
            // 赋值，不光可以用set 还可以用setInteger等，但是string没提供，用obj就可以
            declaredField.set(entity, v);
        } catch (Exception e) { // 此异常为 实体内字段不存在
            log.error("-> {}", e.getMessage());
            e.printStackTrace();
        }
        return (T) entity;
    }

    /**
     * Double转Bigdecimal
     *
     * @param val double
     * @return Bigdecimal
     */
    public static BigDecimal toBigDecimal(Double val) {
        return isEmpty(val) ? null : new BigDecimal(val.toString());
    }

    /**
     * 获取list中存放的最后一个元素
     * @param list
     * @param <T>
     * @return
     */
    public static <T> T getLastElement(List<T> list) {
        return list.get(list.size() - 1);
    }

    /**
     * 取最大值
     * @param a a
     * @param b b
     * @return 结果
     */
    public static BigDecimal getMax(BigDecimal a, BigDecimal b) {
        if (a.compareTo(b) >= 0) {
            return a;
        } else {
            return b;
        }
    }

    /**
     * 判断两个List内的元素是否相同 不考虑顺序且元素不重复
     *
     * @param list1 v1
     * @param list2 v2
     * @return 是否相同
     */
    public static boolean getDiffrent(List<String> list1, List<String> list2) {
        Map<String, String> map = new HashMap<>(list1.size());
        if (list2.size() != list1.size()) {
            return false;
        }
        for (String string : list1) {
            map.put(string, string);
        }
        for (String string : list2) {
            if (map.containsKey(string)) {
                continue;
            }
            return false;
        }
        return true;
    }
}