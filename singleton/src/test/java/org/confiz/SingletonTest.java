package org.confiz;

import org.confiz.singleton.Singleton;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SingletonTest {

    @Test
    public void testSerialization() throws Exception {
        Singleton instance = Singleton.getInstance();
        try(ObjectOutput out = new ObjectOutputStream(new FileOutputStream("file.text"))){
            out.writeObject(instance);
        }

        try(ObjectInput in = new ObjectInputStream(new FileInputStream("file.text"))){
            Singleton instance2 = (Singleton) in.readObject();
            Assert.assertEquals(instance.hashCode(), instance2.hashCode());
        }
    }

    @Test(expected = InvocationTargetException.class)
    public void testNewInstance() throws Exception {

        Singleton.getInstance();
        Constructor constructor = Singleton.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        constructor.newInstance();

    }

}
