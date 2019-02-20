package com.qh.water_management.modules.common.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @Author: qh
 * @Date: 2019/1/3 10:51
 * @Description: 反射工具类,提供访问私有变量,获取泛型类型Class, 提取集合中元素的属性, 转换字符串到对象等Util函数.
 */
public class ReflectionUtil {

    private static Log logger = LogFactory.getLog(ReflectionUtil.class);

    static {
        DateLocaleConverter dc = new DateLocaleConverter();
        // dc.setPatterns(new String[] { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss" });
        ConvertUtils.register(dc, Date.class);
    }

    /**
     * 调用Setter方法.使用value的Class来查找Setter方法.
     * @param target
     * @param propertyName
     * @return
     */
    public static Object invokeGetterMethod(Object target, String propertyName) {
        String getterMethodName = "get" + StringUtils.capitalize(propertyName);
        return invokeMethod(target, getterMethodName, new Class[] {}, new Object[] {});
    }

    /**
     * 调用Setter方法.
     * @param target
     * @param propertyName
     * @param value
     * @param propertyType 用于查找Setter方法,为空时使用value的Class替代.
     */
    public static void invokeSetterMethod(Object target, String propertyName, Object value, Class<?> propertyType) {
        Class<?> type = propertyType != null ? propertyType : value.getClass();
        String setterMethodName = "set" + StringUtils.capitalize(propertyName);
        invokeMethod(target, setterMethodName, new Class[] { type }, new Object[] { value });
    }

    /**
     * 直接读取对象属性值, 无视private/protected修饰符, 不经过getter函数.
     * @param object
     * @param fieldName
     * @return
     */
    public static Object getFieldValue(final Object object, final String fieldName) {
        Field field = getDeclaredField(object, fieldName);
        if (field == null) {
            throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
        }
        makeAccessible(field);
        Object result = null;
        try {
            result = field.get(object);
        } catch (IllegalAccessException e) {
            logger.error("不可能抛出的异常{}" + e.getMessage());
        }
        return result;
    }

    /**
     * 直接设置对象属性值, 无视private/protected修饰符, 不经过setter函数.
     * @param object
     * @param fieldName
     * @param value
     */
    public static void setFieldValue(final Object object, final String fieldName, final Object value) {
        Field field = getDeclaredField(object, fieldName);
        if (field == null) {
            throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
        }
        makeAccessible(field);
        try {
            field.set(object, value);
        } catch (IllegalAccessException e) {
            logger.error("不可能抛出的异常:{}" + e.getMessage());
        }
    }

    /**
     * 直接调用对象方法, 无视private/protected修饰符.
     * @param object
     * @param methodName
     * @param parameterTypes
     * @param parameters
     * @return
     */
    public static Object invokeMethod(final Object object, final String methodName, final Class<?>[] parameterTypes, final Object[] parameters) {
        Method method = getDeclaredMethod(object, methodName, parameterTypes);
        if (method == null) {
            throw new IllegalArgumentException("Could not find method [" + methodName + "] parameterType " + parameterTypes + " on target [" + object + "]");
        }
        method.setAccessible(true);
        try {
            return method.invoke(object, parameters);
        } catch (Exception e) {
            throw convertReflectionExceptionToUnchecked(e);
        }
    }

    /**
     * 循环向上转型, 获取对象的DeclaredField. 如向上转型到Object仍无法找到, 返回null.
     * @param object
     * @param fieldName
     * @return
     */
    protected static Field getDeclaredField(final Object object, final String fieldName) {
        Assert.notNull(object, "object不能为空");
        Assert.hasText(fieldName, "fieldName");
        for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
            try {
                return superClass.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {// NOSONAR
                // Field不在当前类定义,继续向上转型
            }
        }
        return null;
    }

    /**
     * 强行设置Field可访问.
     * @param field
     */
    protected static void makeAccessible(final Field field) {
        if (!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers())) {
            field.setAccessible(true);
        }
    }

    /**
     * 循环向上转型, 获取对象的DeclaredMethod.如向上转型到Object仍无法找到, 返回null.
     * @param object
     * @param methodName
     * @param parameterTypes
     * @return
     */
    protected static Method getDeclaredMethod(Object object, String methodName, Class<?>[] parameterTypes) {
        Assert.notNull(object, "object不能为空");
        for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
            try {
                return superClass.getDeclaredMethod(methodName, parameterTypes);
            } catch (NoSuchMethodException e) {// NOSONAR
                // Method不在当前类定义,继续向上转型
            }
        }
        return null;
    }

    /**
     * 通过反射, 获得Class定义中声明的父类的泛型参数的类型. 如无法找到, 返回Object.class. eg. public UserDao extends HibernateDao<User>
     * @param clazz
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<T> getSuperClassGenricType(final Class<?> clazz) {
        return getSuperClassGenricType(clazz, 0);
    }

    /**
     * 通过反射, 获得定义Class时声明的父类的泛型参数的类型. 如无法找到, 返回Object.class.
     * @param clazz
     * @param index
     * @return
     */
    public static Class getSuperClassGenricType(final Class<?> clazz, final int index) {
        Type genType = clazz.getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            logger.warn(clazz.getSimpleName() + "'s superclass not ParameterizedType");
            return Object.class;
        }
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if (index >= params.length || index < 0) {
            logger.warn("Index: " + index + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: " + params.length);
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            logger.warn(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
            return Object.class;
        }
        return (Class) params[index];
    }

    /**
     * 提取集合中的对象的属性(通过getter函数), 组合成List.
     * @param collection 来源集合
     * @param propertyName 要提取的属性名.
     * @return
     */
    public static List convertElementPropertyToList(final Collection collection, final String propertyName) {
        List list = new ArrayList();
        try {
            for (Object obj : collection) {
                list.add(PropertyUtils.getProperty(obj, propertyName));
            }
        } catch (Exception e) {
            throw convertReflectionExceptionToUnchecked(e);
        }
        return list;
    }

    /**
     * 提取集合中的对象的属性(通过getter函数), 组合成由分割符分隔的字符串.
     * @param collection 来源集合.
     * @param propertyName 要提取的属性名.
     * @param separator 分隔符.
     * @return
     */
    public static String convertElementPropertyToString(final Collection collection, final String propertyName, final String separator) {
        List list = convertElementPropertyToList(collection, propertyName);
        return StringUtils.join(list, separator);
    }

    /**
     * 转换字符串到相应类型.
     * @param value 待转换的字符串
     * @param toType  转换目标类型
     * @param <T>
     * @return
     */
    public static <T> T convertStringToObject(String value, Class<T> toType) {
        try {
            return (T) ConvertUtils.convert(value, toType);
        } catch (Exception e) {
            throw convertReflectionExceptionToUnchecked(e);
        }
    }

    /**
     * 将反射时的checked exception转换为unchecked exception.
     * @param e
     * @return
     */
    public static RuntimeException convertReflectionExceptionToUnchecked(Exception e) {
        return convertReflectionExceptionToUnchecked(null, e);
    }

    /**
     *
     * @param desc
     * @param e
     * @return
     */
    public static RuntimeException convertReflectionExceptionToUnchecked(String desc, Exception e) {
        desc = (desc == null) ? "Unexpected Checked Exception." : desc;
        if (e instanceof IllegalAccessException || e instanceof IllegalArgumentException || e instanceof NoSuchMethodException) {
            return new IllegalArgumentException(desc, e);
        } else if (e instanceof InvocationTargetException) {
            return new RuntimeException(desc, ((InvocationTargetException) e).getTargetException());
        } else if (e instanceof RuntimeException) {
            return (RuntimeException) e;
        }
        return new RuntimeException(desc, e);
    }

    /**
     *
     * @param cls
     * @param <T>
     * @return
     */
    public static final <T> T getNewInstance(Class<T> cls) {
        try {
            return cls.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 拷贝 source 指定的porperties 属性 到 dest中
     * @param dest
     * @param source
     * @param porperties
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static void copyPorperties(Object dest, Object source, String[] porperties) throws InvocationTargetException, IllegalAccessException {
        for (String por : porperties) {
            Object srcObj = invokeGetterMethod(source, por);
            logger.debug("属性名：" + por + "------------- 属性值：" + srcObj);
            if (srcObj != null) {
                try {
                    BeanUtils.setProperty(dest, por, srcObj);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    throw e;
                } catch (InvocationTargetException e) {
                    throw e;
                }
            }
        }
    }

    /**
     * 两者属性名一致时，拷贝source里的属性到dest里
     * @param dest
     * @param source
     */
    public static void copyPorperties(Object dest, Object source) throws IllegalAccessException, InvocationTargetException {
        Class<? extends Object> srcCla = source.getClass();
        Field[] fsF = srcCla.getDeclaredFields();
        for (Field s : fsF) {
            String name = s.getName();
            Object srcObj = invokeGetterMethod(source, name);
            try {
                BeanUtils.setProperty(dest, name, srcObj);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                throw e;
            } catch (InvocationTargetException e) {
                throw e;
            }
        }
        // BeanUtils.copyProperties(dest, orig);
    }
}
