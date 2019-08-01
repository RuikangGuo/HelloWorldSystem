package com.nowcoder.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.CharUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

@Service
public class SensitiveService implements InitializingBean {
	//实现InitializingBean，把外部文件读取处理，采用构建树的方法
	@Override
	public void afterPropertiesSet() throws Exception {
		try {
			InputStream is=Thread.currentThread().getContextClassLoader().getResourceAsStream("SensitiveWords.txt");
			BufferedReader bf=new BufferedReader(new InputStreamReader(is));
			String lineText;
			while((lineText=bf.readLine())!=null) {
				lineText=lineText.trim();
				addWord(lineText);
				System.out.println("eeeee");
			}
			bf.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private boolean isSymbol(char c) {
		int ic=(int)c;
		return !CharUtils.isAsciiAlphanumeric(c) && (ic<0x2E80||ic>0x9FFF);
		
	}
	
	//过滤关键词的实现
	public String filter(String target) {
		if(target.isEmpty()) {
			return target;
		}
		
		StringBuilder result =new StringBuilder();
		String replacement="***";
		TireNode temp=rootNode;
		int begin=0;
		int position=0;
		while(position<target.length()) {
			char c=target.charAt(position);//当前位置的字符
			
			if(isSymbol(c)) {
				if(temp==rootNode) {
					result.append(c);
					++begin;
				}
				++position;
				continue;
			}
			temp=temp.getSubNode(c);//当前节点的下一个节点取出来
			if(temp==null) {
				result.append(target.charAt(begin));
				position=begin+1;
				begin=position;
				temp=rootNode;
			}else if(temp.isKeyWordEnd()) {
				result.append(replacement);
				position=position+1;
				begin=position;
				temp=rootNode;
			}else {
				++position;
			}
		}
		System.out.println("oooo");
		result.append(target.substring(begin));
		return result.toString();
	}
	
	
	//从外部文件String中创建字典树
	private void addWord(String sensitWord) {
		
		TireNode tempNode=rootNode;
		
		for(int i=0;i<sensitWord.length();++i) {
			Character c=sensitWord.charAt(i);
			if(isSymbol(c)) {
				continue;
			}
			TireNode node =tempNode.getSubNode(c);
			
			if(node==null) {
				 node=new TireNode();
				tempNode.addSubNode(c, node);
			}
			tempNode=node;
			
			if(i==(sensitWord.length()-1)) {
				tempNode.setKeyEnd(true);
			}
		}
	}
	
	//定义节点，其中每个节点要照顾下一个节点
	   private  class TireNode{
		   private  boolean end=false;
		   private  Map<Character,TireNode> subNodes= new HashMap<Character,TireNode>();
		   public void addSubNode(Character key,TireNode node) {
			  subNodes.put(key, node);
		  }
		  
		   public TireNode getSubNode(Character key) {
			   return subNodes.get(key);
		   }
		   
		   public  boolean isKeyWordEnd() {return end;}
		   
		   public   void setKeyEnd(boolean end) {this.end=end;}
	   }
	  private   TireNode rootNode=new TireNode();

}
