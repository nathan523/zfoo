package com.zfoo.storage.util.lambda;

import com.zfoo.storage.resource.TeacherResource;
import com.zfoo.storage.util.LambdaUtils;
import com.zfoo.storage.util.function.Func1;
import org.junit.Test;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * @author veione
 */
public class TestLambdaFunction {
    //https://blog.csdn.net/iteye_19045/article/details/119299015
    @Test
    public void testFuncSerialization() throws Exception {
        Func1<TeacherResource, String> func = TeacherResource::name;
        Object object = getObject(func);
        System.out.println(object);

        Class<? extends Serializable> clazz = func.getClass();
        Method writeReplace = clazz.getDeclaredMethod("writeReplace");
        System.out.println(writeReplace);

        LambdaMeta meta = LambdaUtils.extract(func);
        System.out.println(meta);

//        Function<StudentResource, String> func2 = StudentResource::name;
//        Method method = func2.getClass().getDeclaredMethod("writeReplace");
//        object = method.invoke(func2);
//        System.out.println(object);
    }

    private static Object getObject(Serializable func) throws Exception {
        Method method = func.getClass().getDeclaredMethod("writeReplace");
        Object object = method.invoke(func);
        return object;
    }
}
