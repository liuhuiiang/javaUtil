package com.xh.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.xml.sax.InputSource;

import com.xh.file.FileManage;

/**
 * 
 * ����������
 */
public class StreamManage {
	public static String UTF8 = "UTF-8";

	/**
	 * ��������ת��Ϊbyte����
	 * 
	 * @param is
	 * @return
	 */
	public static byte[] inputStream2byte(InputStream is) {
		try {
			// TODO Auto-generated method stub
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			int len = 0;
			byte[] buffer = new byte[1024];
			while ((len = is.read(buffer)) != -1) {
				bos.write(buffer, 0, len);
			}
			bos.flush();
			byte[] arr = bos.toByteArray();
			bos.close();
			is.close();
			return arr;
		} catch (Exception e) {
			// TODO: handle exception
			return e.getMessage().getBytes();
		}
	}

	/**
	 * ��������ת��Ϊ String
	 * 
	 * @param is
	 * @return
	 * @throws Exception
	 */
	public static String inputStream2String(InputStream is, String charsetName)
			throws Exception {
		if (charsetName == null || charsetName.isEmpty())
			return new String(inputStream2byte(is), UTF8);
		return new String(inputStream2byte(is), charsetName);
	}

	/**
	 * ���ֽ�ת��Ϊ������
	 * 
	 * @param buff
	 * @return
	 */
	public static InputStream bytes2InputStream(byte[] buff) {
		return new ByteArrayInputStream(buff);
	}

	/**
	 * ��String ת��Ϊ������
	 * 
	 * @param string
	 * @return
	 * @throws Exception
	 */
	public static InputStream string2InputStream(String string,
			String charsetName) throws Exception {
		if (charsetName == null || charsetName.isEmpty())
			return new ByteArrayInputStream(string.getBytes(UTF8));
		return new ByteArrayInputStream(string.getBytes(charsetName));
	}

	/**
	 * ��������ת��Ϊ�ļ�
	 * 
	 * @param is
	 *            ������
	 * @param file_path
	 *            �ļ�����λ��
	 * @param file_name
	 *            �ļ���
	 * @return
	 * @throws Exception
	 */
	public static File inputStream2File(InputStream is, String file_path,
			String file_name) throws Exception {
		if (file_path == null || file_path.isEmpty())
			throw new RuntimeException("file_path is null or empty");
		if (file_name == null || file_name.isEmpty())
			throw new RuntimeException("file_name is null or empty");
		File path = new File(file_path);
		if (!path.exists())
			path.mkdirs();
		File file = new File(file_path + "/" + file_name);
		FileOutputStream fos = new FileOutputStream(file_path + "/" + file_name);
		int len = -1;
		byte b[] = new byte[1024];
		while ((len = is.read(b)) > 0) {
			fos.write(b, 0, len);
		}
		fos.flush();
		fos.close();
		is.close();
		return file;
	}


	/**
	 * ���ļ�ת��Ϊ������
	 * 
	 * @param file
	 *            �ļ�·��������·����
	 * @return
	 * @throws Exception
	 */
	public static InputStream file2InputStream(File file) throws Exception {
		if (!FileManage.isFile(file))
			throw new RuntimeException(
					"file is null or not exists or directory");
		return new FileInputStream(file);
	}
	/**
	 * ���ֽ�ת��Ϊobject
	 * @param bytes
	 * @return
	 */
	 public static Object byte2object(byte[] bytes){
	        Object obj = null;
	        try {
	        //bytearray to object
	        ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
	        ObjectInputStream oi = new ObjectInputStream(bi);

	        obj = oi.readObject();

	        bi.close();
	        oi.close();
	        }
	        catch(Exception e) {
	            System.out.println("translation"+e.getMessage());
	            e.printStackTrace();
	        }
	        return obj;
	    }
	 /**
	  * ��objectת��λ�ֽ�
	  * @param obj
	  * @return
	  */
	 public static byte[] object2byte(Object obj)
	    {
	        byte[] bytes = null;
	        try {
	            //object to bytearray
	            ByteArrayOutputStream bo = new ByteArrayOutputStream();
	            ObjectOutputStream oo = new ObjectOutputStream(bo);
	            oo.writeObject(obj);

	            bytes = bo.toByteArray();

	            bo.close();
	            oo.close();    
	        }
	        catch(Exception e) {
	            System.out.println("translation"+e.getMessage());
	            e.printStackTrace();
	        }
	        return bytes;
	    }
	 /**
	  * ��uriת��λis
	  * author:xh
	  * email:825378291@qq.com
	  * time:2017-2-10 ����11:20:32
	  * @param uri
	  * @return
	  */
	 public static InputStream uri2is(String uri){
		 if(uri==null||uri.isEmpty())
			 return null;
		 try {
			URI u=new URI(uri);
			URL url=u.toURL();
			return url.openStream();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		} 
	 }
}