package com.qkx.example.lock;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnSafeWrapper {
    /**
     * 通过反射创建UnSafe类实例，
     * 禁止通过Unsafe.getUnsafe()来得到UnSafe实例，会得到throw new SecurityException("Unsafe");
     * 除非使用BootstrapClassLoader类加载器来获取这个UnSafe实例
     * 参看 https://www.cnblogs.com/throwable/p/9139947.html
     *
     * @return
     */
    public static Unsafe getUnSageInstanceByReflect() {
        try {
            // 通过反射得到UnSafe中的theUnsafe属性
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            // 允许通过反射访问私有属性,不然无法使用这个私有属性
            f.setAccessible(true);
            // 返回UnSafe实例
            return (Unsafe) f.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
