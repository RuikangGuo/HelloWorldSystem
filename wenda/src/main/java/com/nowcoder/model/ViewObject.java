package com.nowcoder.model;

import java.util.HashMap;
import java.util.Map;

public class ViewObject {
	private Map<String,Object> objs=new HashMap<String,Object>();
	
      public void set(String str,Object obj) {
    	  objs.put(str, obj);
      }
      
      
      public Map<String, Object> getObjs() {
		return objs;
	}


	public void setObjs(Map<String, Object> objs) {
		this.objs = objs;
	}


	public Object get(String str,Object obj) {
    	 return objs.get(str);
      }
  
}
