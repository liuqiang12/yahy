package cn.hd.common.util;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class WordUtil {
	
	private static Configuration configuration = null;
    private static Map<String, Template> allTemplates = null;
      
    static {  
        configuration = new Configuration();
        configuration.setDefaultEncoding("utf-8");  
        configuration.setClassForTemplateLoading(WordUtil.class, "/templates/office/word/tmp");
        allTemplates = new HashMap<>();
        try {

            allTemplates.put("listGrid", configuration.getTemplate("chdw.ftl"));
            allTemplates.put("listGrid4Chry", configuration.getTemplate("chry_back.ftl"));
        } catch (IOException e) {
            e.printStackTrace();  
            throw new RuntimeException(e);
        }  
    }  
  
    private WordUtil() {
        throw new AssertionError();
    }  
  
    public static File createDoc(Map<?, ?> dataMap, String type) {
        String name = "temp" + (int) (Math.random() * 100000) + ".doc";
        File f = new File(name);
        Template t = allTemplates.get(type);
        try {  
            // 这个地方不能使用FileWriter因为需要指定编码类型否则生成的Word文档会因为有无法识别的编码而无法打开  
            Writer w = new OutputStreamWriter(new FileOutputStream(f), "utf-8");
            t.process(dataMap, w);  
            w.close();  
        } catch (Exception ex) {
            ex.printStackTrace();  
            throw new RuntimeException(ex);
        }  
        return f;  
    }  
    
}
