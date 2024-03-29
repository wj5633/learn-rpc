package com.wj5633.framework.serialization.serializer.impl;

import com.wj5633.framework.serialization.serializer.ISerializer;
import org.apache.commons.lang3.reflect.MethodUtils;

/**
 * @author liyebing created on 17/1/25.
 * @version $Id$
 */
public class ProtocolBufferSerializer implements ISerializer {


    public <T> byte[] serialize(T obj) {
        try {
            return (byte[]) MethodUtils.invokeMethod(obj, "toByteArray");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> T deserialize(byte[] data, Class<T> cls) {
        try {
            Object o = MethodUtils.invokeStaticMethod(cls, "getDefaultInstance");
            return (T) MethodUtils.invokeMethod(o, "parseFrom", new Object[]{data});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
