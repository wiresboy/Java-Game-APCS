package util;

import java.util.ArrayList;

public class Data {
	//data is in format of  <type>:<key name> = <value>  where <type> is either an I, D, or S where I is an integer, D is a double, and S is a string.
	private ArrayList<String> integers = new ArrayList<String>();
	private ArrayList<String> doubles = new ArrayList<String>();
	private ArrayList<String> strings = new ArrayList<String>();
	public Data(ArrayList<String> lines){
		for(String s : lines){
			if(s.startsWith("I:")){
				s = s.substring(2);
				integers.add(s);
			}else if(s.startsWith("D:")){
				s = s.substring(2);
				doubles.add(s);
			}else if(s.startsWith("S:")){
				s = s.substring(2);
				strings.add(s);
			}
		}
	}
	public int getInt(String key){
		for(String s : integers){
			int i = s.indexOf('=');
			if(i == -1) return -1099;
			String str = s.substring(0,i);
			str = str.trim();
			if(key.equals(str)){
				String numStr = (s.substring(i+1)).trim();
				return Integer.parseInt(numStr);
			}
		}
		return -1099;
	}
	public double getDouble(String key){
		for(String s : doubles){
			int i = s.indexOf('=');
			if(i == -1) return -1099;
			String str = s.substring(0,i);
			str = str.trim();
			if(key.equals(str)){
				String numStr = (s.substring(i+1)).trim();
				return Double.parseDouble(numStr);
			}
		}
		return -1099;
	}
	public String getString(String key){
		for(String s : integers){
			int i = s.indexOf('=');
			if(i == -1) return null;
			String str = s.substring(0,i);
			str = str.trim();
			if(key.equals(str)){
				return (s.substring(i+1)).trim();
			}
		}
		return null;
	}
	public void setInt(String key, int val){
		int i = getInt(key);
		if(i != -1099){
			for(int ix = 0; ix < integers.size(); ix++){
				String s = integers.get(ix);
				int i2 = s.indexOf('=');
				if(i2 != -1){
					String str = s.substring(0,i2);
					str = str.trim();
					if(key.equals(str)){
						integers.set(ix, "I:"+key+" = "+val);
						return;
					}
				}
			}
		}
		integers.add("I:"+key+" = "+val);
	}
	public void setDouble(String key, double val){
		double i = getDouble(key);
		if(i != -1099){
			for(int ix = 0; ix < doubles.size(); ix++){
				String s = doubles.get(ix);
				int i2 = s.indexOf('=');
				if(i2 != -1){
					String str = s.substring(0,i2);
					str = str.trim();
					if(key.equals(str)){
						doubles.set(ix, "D:"+key+" = "+val);
						return;
					}
				}
			}
		}
		doubles.add("D:"+key+" = "+val);
	}
	public void setString(String key, String val){
		String i = getString(key);
		if(i != null){
			for(int ix = 0; ix < strings.size(); ix++){
				String s = strings.get(ix);
				int i2 = s.indexOf('=');
				if(i2 != -1){
					String str = s.substring(0,i2);
					str = str.trim();
					if(key.equals(str)){
						strings.set(ix, "S:"+key+" = "+val);
						return;
					}
				}
			}
		}
		strings.add("S:"+key+" = "+val);
	}
	public String save(){
		String save = "";
		for(String s : integers){
			save+=s+"\n";
		}
		for(String s : doubles){
			save+=s+"\n";
		}
		for(String s : strings){
			save+=s+"\n";
		}
		save = save.substring(0,save.length()-2);
		return save;
	}
}
