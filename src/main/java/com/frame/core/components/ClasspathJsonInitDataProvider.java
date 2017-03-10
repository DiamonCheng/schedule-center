package com.frame.core.components;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.frame.core.components.HibernateDataInitialiser.InitDataProvider;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class ClasspathJsonInitDataProvider implements InitDataProvider {
	String path="/initData";
	String classToLoad="com.frame.core.components.ClasspathJsonInitDataProvider";
//	String path="/adapter/config";
//	String classToLoad="com.experian.service.impl.order.HumanReviewImpl";
	String charset="UTF-8";
	Gson gson=new Gson();
	@Override
	public Object[][] getObjectList() {
		Class<?> cls;
		try {
			cls = Class.forName(classToLoad);
			URL url=cls.getResource(path);
			List<Object[]> result=new ArrayList<Object[]>();
			if (url.getProtocol().equals("file")){
				File file;
				file = new File(url.toURI());
				if (!file.isDirectory()) return null;
				File[] childFiles=file.listFiles();
				for (File childFile:childFiles) {
					if (!childFile.isFile()) continue;
					String fileName=childFile.getName();
					int dot=fileName.lastIndexOf('.');
					if (dot==-1) continue;
					String suffix=fileName.substring(dot+1);
					if (!suffix.equalsIgnoreCase("JSON")) continue;
					fileName=fileName.substring(0, dot);
					result.add(resolveFile(new FileInputStream(childFile), Class.forName(fileName)));
				}
				return result.toArray(new Object[result.size()][]);
			}else if(url.getProtocol().equals("jar")){
				JarURLConnection jarURLConnection = (JarURLConnection)url.openConnection();
				JarFile jarFile = jarURLConnection.getJarFile();
				Enumeration<JarEntry> entrys = jarFile.entries();
				String pathRegx=url.getFile().substring(url.getFile().lastIndexOf('!')+2)+"/.*\\.(json|JSON)";
				System.out.println(pathRegx);
		        while(entrys.hasMoreElements()){
		        	JarEntry entry = entrys.nextElement();
		        	if (entry.getName().matches(pathRegx)){
		        		String eName=entry.getName();
		        		eName=eName.substring(eName.lastIndexOf('/')+1);
		        		result.add(resolveFile(jarFile.getInputStream(entry), Class.forName(eName)));
		        	}
		        }
		        jarFile.close();
		        return result.toArray(new Object[result.size()][]);
			}else{
				throw new HibernateDataInitialiser.HibernateDataInitException("Unsupported protocol "+url,null);
			}
		} catch (Exception e) {
			throw new HibernateDataInitialiser.HibernateDataInitException(e.getMessage(),e);
		}

	}
	private Object[] resolveFile(InputStream in,Class<?> cls) throws IOException{
		JsonElement jsonElement=new JsonParser().parse(new InputStreamReader(in,charset));
		in.close();
		if (jsonElement.isJsonArray()){
			JsonArray jsonArray=(JsonArray)jsonElement;
			List<Object> list=new ArrayList<Object>(jsonArray.size());
			for (JsonElement child : jsonArray) {
				list.add(gson.fromJson(child, cls));
			}
			return list.toArray();
		}else if(jsonElement.isJsonObject()){
			Object obj=gson.fromJson(jsonElement, cls);
			return Arrays.asList(obj).toArray();
		}
		return new Object[0];
	}
	public void setPath(String path) {
		this.path = path;
	}
	public void setClassToLoad(String classToLoad) {
		this.classToLoad = classToLoad;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
	public void setGson(Gson gson) {
		this.gson = gson;
	}

}
