package cn.hd.utils;

import cn.hd.common.cfgUtils.CommonProperty;
import cn.hd.common.cfgUtils.ConfigProperty;
import cn.hd.common.util.CodeModel;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.*;
import java.net.URL;
import java.util.Properties;

/*代办生成器。目的是生成相应的控制层和持久层*/
public class CodeUtils {
    private static VelocityEngine ve;
    private static final String CONTENT_ENCODING = "UTF-8";
    static
    {
        try
        {
            String templateBasePath = "";
            Properties properties = new Properties();
            properties.setProperty("resource.loader", "file");
            properties.setProperty("file.resource.loader.description", "Velocity File Resource Loader");
            properties.setProperty("file.resource.loader.path", templateBasePath);
            properties.setProperty("file.resource.loader.cache", "true");
            properties.setProperty("file.resource.loader.modificationCheckInterval", "30");
            properties.setProperty("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.Log4JLogChute");
            properties.setProperty("runtime.log.logsystem.log4j.logger", "org.apache.velocity");
            properties.setProperty("directive.set.null.allowed", "true");
            properties.put("file.resource.loader.class","org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            VelocityEngine velocityEngine = new VelocityEngine();

            velocityEngine.init(properties);
            ve = velocityEngine;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void mainwerwrew(String[] args) {
        String FTP_ADDR =  ConfigProperty.getInstance().getPropertyValue("jdbc.username");
        System.out.println(FTP_ADDR);
        String FTP_ADD2R =  CommonProperty.getInstance().getPropertyValue("asdfafda");
        System.out.println(FTP_ADD2R);
    }
    public static void createFmtServiceFile() throws Exception{
        /* jdbc数据库信息*/
        CodeUtils codeUtils = new CodeUtils();
        VelocityContext context = new VelocityContext();
        String localTableName = CommonProperty.getInstance().getPropertyValue("LOCAL_TABLE_NAME");
        String localServicePackage = CommonProperty.getInstance().getPropertyValue("LOCAL_SERVICE_PACKAGE");
        String localEntityPackage = CommonProperty.getInstance().getPropertyValue("LOCAL_ENTITY_PACKAGE");
        if(localTableName != null && !"".equals(localTableName)){
            localTableName = localTableName.toLowerCase();
            CodeModel codeModel = new CodeModel();
            codeModel.setClassName(HumpUtils.upperFirstCase(HumpUtils.underlineToCamel2(localTableName)));
            codeModel.setClassInstName(HumpUtils.underlineToCamel2(localTableName));
            codeModel.setPackageName(localServicePackage);
            codeModel.setEntityPackage(localEntityPackage);
            DataBaseUtils dbu = new DataBaseUtils();
            codeModel.setTableComment(dbu.getTableComments());
            context.put("codeModel",codeModel);
            codeUtils.writerPage(context,localServicePackage,codeModel.getClassName(),"service");
        }
    }


/* 控制层实现类@throws Exception*/
    public static void createFmtControllerFile() throws Exception{
        /*jdbc数据库信息*/
        CodeUtils codeUtils = new CodeUtils();
        VelocityContext context = new VelocityContext();
        String localTableName = CommonProperty.getInstance().getPropertyValue("LOCAL_TABLE_NAME");
        String localServicePackage = CommonProperty.getInstance().getPropertyValue("LOCAL_SERVICE_PACKAGE");
        String localEntityPackage = CommonProperty.getInstance().getPropertyValue("LOCAL_ENTITY_PACKAGE");
        String localControllerPackage = CommonProperty.getInstance().getPropertyValue("LOCAL_CONTROLLER_PACKAGE");

        if(localTableName != null && !"".equals(localTableName)){
            localTableName = localTableName.toLowerCase();
            CodeModel codeModel = new CodeModel();
            codeModel.setClassName(HumpUtils.upperFirstCase(HumpUtils.underlineToCamel2(localTableName)));
            codeModel.setClassInstName(HumpUtils.underlineToCamel2(localTableName));
            codeModel.setPackageName(localServicePackage);
            codeModel.setEntityPackage(localEntityPackage);
            //codeModel.setDaoPackage(locaDaoPackage);
            codeModel.setControllerPackage(localControllerPackage);
            DataBaseUtils dbu = new DataBaseUtils();
            codeModel.setTableComment(dbu.getTableComments());
            context.put("codeModel",codeModel);
            codeUtils.writerPage(context,localControllerPackage+"",codeModel.getClassName(),"controller");
        }
    }
/**业务层实现类@throws Exception*/
    public static void createFmtServiceImplFile() throws Exception{
    /* jdbc数据库信息*/

        CodeUtils codeUtils = new CodeUtils();
        VelocityContext context = new VelocityContext();
        String localTableName = CommonProperty.getInstance().getPropertyValue("LOCAL_TABLE_NAME");
        String localServicePackage = CommonProperty.getInstance().getPropertyValue("LOCAL_SERVICE_PACKAGE");
        String localEntityPackage = CommonProperty.getInstance().getPropertyValue("LOCAL_ENTITY_PACKAGE");
        String locaDaoPackage = CommonProperty.getInstance().getPropertyValue("LOCAL_DAO_PACKAGE");

        if(localTableName != null && !"".equals(localTableName)){
            localTableName = localTableName.toLowerCase();
            CodeModel codeModel = new CodeModel();
            codeModel.setClassName(HumpUtils.upperFirstCase(HumpUtils.underlineToCamel2(localTableName)));
            codeModel.setClassInstName(HumpUtils.underlineToCamel2(localTableName));
            codeModel.setPackageName(localServicePackage);
            codeModel.setEntityPackage(localEntityPackage);
            codeModel.setDaoPackage(locaDaoPackage);
            DataBaseUtils dbu = new DataBaseUtils();
            codeModel.setTableComment(dbu.getTableComments());
            context.put("codeModel",codeModel);
            codeUtils.writerPage(context,localServicePackage+".impl",codeModel.getClassName(),"serviceImpl");
        }
    }
/**
     * 持久层接口
     * @throws Exception*/


    public static void createFmtDaoFile() throws Exception{
 /*jdbc数据库信息*/

        CodeUtils codeUtils = new CodeUtils();
        VelocityContext context = new VelocityContext();
        String localTableName = CommonProperty.getInstance().getPropertyValue("LOCAL_TABLE_NAME");
        String localServicePackage = CommonProperty.getInstance().getPropertyValue("LOCAL_SERVICE_PACKAGE");
        String localEntityPackage = CommonProperty.getInstance().getPropertyValue("LOCAL_ENTITY_PACKAGE");
        String locaDaoPackage = CommonProperty.getInstance().getPropertyValue("LOCAL_DAO_PACKAGE");

        if(localTableName != null && !"".equals(localTableName)){
            localTableName = localTableName.toLowerCase();
            CodeModel codeModel = new CodeModel();
            codeModel.setClassName(HumpUtils.upperFirstCase(HumpUtils.underlineToCamel2(localTableName)));
            codeModel.setClassInstName(HumpUtils.underlineToCamel2(localTableName));
            codeModel.setPackageName(localServicePackage);
            codeModel.setEntityPackage(localEntityPackage);
            codeModel.setDaoPackage(locaDaoPackage);
            DataBaseUtils dbu = new DataBaseUtils();
            codeModel.setTableComment(dbu.getTableComments());
            context.put("codeModel",codeModel);
            codeUtils.writerPage(context,locaDaoPackage,codeModel.getClassName(),"dao");
        }
    }
/**
     * 持久层实现层
     * @throws Exception*/


    public static void createFmtDaoImplFile() throws Exception{
 /*jdbc数据库信息*/

        CodeUtils codeUtils = new CodeUtils();
        VelocityContext context = new VelocityContext();
        String localTableName = CommonProperty.getInstance().getPropertyValue("LOCAL_TABLE_NAME");
        String localServicePackage = CommonProperty.getInstance().getPropertyValue("LOCAL_SERVICE_PACKAGE");
        String localEntityPackage = CommonProperty.getInstance().getPropertyValue("LOCAL_ENTITY_PACKAGE");
        String locaDaoPackage = CommonProperty.getInstance().getPropertyValue("LOCAL_DAO_PACKAGE");

        if(localTableName != null && !"".equals(localTableName)){
            localTableName = localTableName.toLowerCase();
            CodeModel codeModel = new CodeModel();
            codeModel.setClassName(HumpUtils.upperFirstCase(HumpUtils.underlineToCamel2(localTableName)));
            codeModel.setClassInstName(HumpUtils.underlineToCamel2(localTableName));
            codeModel.setPackageName(localServicePackage);
            codeModel.setEntityPackage(localEntityPackage);
            codeModel.setDaoPackage(locaDaoPackage);
            DataBaseUtils dbu = new DataBaseUtils();
            codeModel.setTableComment(dbu.getTableComments());
            context.put("codeModel",codeModel);
            codeUtils.writerPage(context,locaDaoPackage+".impl",codeModel.getClassName(),"daoImpl");
        }
    }

    public static void main(String[] args) {
        CodeUtils muDemo = new CodeUtils();
        try {
            /**/muDemo.createFmtServiceImplFile();
            muDemo.createFmtServiceFile();
            muDemo.createFmtDaoFile();
            muDemo.createFmtDaoImplFile();
            muDemo.createFmtControllerFile();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



    public String getROOTPath() throws IOException {
        File f = new File(this.getClass().getResource("/").getPath());
        System.out.println(f);
        File f2 = new File(this.getClass().getResource("").getPath());
        System.out.println(f2);
        File directory = new File("");// 参数为空
        String courseFile = directory.getCanonicalPath();
        System.out.println(courseFile);
        URL xmlpath = this.getClass().getClassLoader().getResource("");
        System.out.println(xmlpath.getPath());
        System.out.println(System.getProperty("user.dir"));
        System.out.println(System.getProperty("java.class.path"));
        String localPackage = CommonProperty.getInstance().getPropertyValue("LOCAL_SERVICE_PACKAGE");
        File file = new File(courseFile+File.separator+localPackage.replace(".",File.separator)+File.separator+"xxx.xml");
        if(!file.getParentFile().exists()){
            new File(file.getParent()).mkdirs();
        }
        return courseFile;
    }
    public void writerPage(VelocityContext context,String localPackage,String fileName,String category)
    {
        File file = null;
        try
        {
            String fileNameTmp = fileName;
            String templateName = null;
            if("service".equals(category)){
                System.out.println("。。。。。。。。。。。。。。。。生成服务接口层。。。。。。。。。。。。。。。。");
                fileNameTmp = fileNameTmp+"Service.java";
                templateName = "BeanFmtService.vm";
            }else if("serviceImpl".equals(category)){
                System.out.println("。。。。。。。。。。。。。。。。生成服务实现层。。。。。。。。。。。。。。。。");
                fileNameTmp = fileNameTmp+"ServiceImpl.java";
                templateName = "BeanFmtServiceImpl.vm";
            }else if("dao".equals(category)){
                System.out.println("。。。。。。。。。。。。。。。。生成持久层接口。。。。。。。。。。。。。。。。");
                fileNameTmp = fileNameTmp+"Dao.java";
                templateName = "BeanFmtDao.vm";
            }else if("daoImpl".equals(category)){
                System.out.println("。。。。。。。。。。。。。。。。生成持久层接口。。。。。。。。。。。。。。。。");
                fileNameTmp = fileNameTmp+"DaoImpl.java";
                templateName = "BeanFmtDaoImpl.vm";
            }else if("controller".equals(category)){
                System.out.println("。。。。。。。。。。。。。。。。生成控制层。。。。。。。。。。。。。。。。");
                fileNameTmp = fileNameTmp+"Controller.java";
                templateName = "BeanFmtController.vm";
            }
            file = new File(getROOTPath()+File.separator+"baseProject"+File.separator+"src"+File.separator+localPackage.replace(".",File.separator)+File.separator+fileNameTmp);
            if(!file.getParentFile().exists()){
                new File(file.getParent()).mkdirs();
            }
            if(!file.exists()){
                file.createNewFile();
            }else{
                System.out.println("替换文件:" + file.getAbsolutePath());
            }
            Template template = ve.getTemplate(""+templateName, "UTF-8");
            FileOutputStream fos = new FileOutputStream(file);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos, "UTF-8"));
            template.merge(context, writer);
            writer.flush();
            writer.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            System.out.println("生成文件：" + file.getAbsolutePath());
        }
    }

}
