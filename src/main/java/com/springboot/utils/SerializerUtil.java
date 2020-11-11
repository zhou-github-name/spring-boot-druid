package com.springboot.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 序列化工具类，来提供对象的序列化和反序列化的工作
 *
 */
public class SerializerUtil {

	/**
	 * 序列化object
	 * 
	 * @param object
	 * @return
	 */
	public static byte[] serialize(Object object) {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;

		try {

			// 序列化
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			byte[] bytes = baos.toByteArray();
			return bytes;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 序列化List
	 * 
	 * @param list
	 * @return
	 */
	public static byte[] serialize(List<?> list) {
		if (list == null) {
			throw new NullPointerException("Can't serialize null");
		}
		byte[] rv = null;
		ByteArrayOutputStream bos = null;
		ObjectOutputStream os = null;
		try {
			bos = new ByteArrayOutputStream();
			os = new ObjectOutputStream(bos);
			for (int i = 0; i < list.size(); i++) {
				os.writeObject(list.get(i));
			}
			os.writeObject(null);
			os.close();
			bos.close();
			rv = bos.toByteArray();
		} catch (IOException e) {
			throw new IllegalArgumentException("Non-serializable object", e);
		} finally {
			close(os);
			close(bos);
		}
		return rv;
	}

	/**
	 * 反序列化Object
	 * 
	 * @param bytes
	 * @return
	 */
	public static Object unserialize(byte[] bytes) {
		ByteArrayInputStream bais = null;
		if (bytes != null) {
			try {
				// 反序列化
				bais = new ByteArrayInputStream(bytes);
				ObjectInputStream ois = new ObjectInputStream(bais);
				return ois.readObject();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 反序列化List
	 * 
	 * @param bytes
	 * @return
	 */
	public static List<Object> unserializeList(byte[] bytes) {
		List<Object> list = new ArrayList<Object>();
		ByteArrayInputStream bis = null;
		ObjectInputStream is = null;
		try {
			if (bytes != null) {
				bis = new ByteArrayInputStream(bytes);
				is = new ObjectInputStream(bis);
				while (true) {
					Object object = is.readObject();
					if (object == null) {
						break;
					} else {
						list.add(object);
					}
				}
				is.close();
				bis.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			close(is);
			close(bis);
		}
		return list;
	}

	public static void close(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}