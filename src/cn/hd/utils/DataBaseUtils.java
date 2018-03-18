package cn.hd.utils;

import cn.hd.common.cfgUtils.CommonProperty;
import cn.hd.common.cfgUtils.ConfigProperty;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;

import java.beans.PropertyVetoException;
import java.sql.*;

/**
 * Created by DELL on 2017/11/28.
 */
public class DataBaseUtils {
    private static Connection conn;
    private static PreparedStatement pst;
    public static void main(String[] args) throws Exception {
        String localTableName = CommonProperty.getInstance().getPropertyValue("LOCAL_TABLE_NAME");
        String sql = "select t.* from user_tab_comments t where UPPER(t.table_name) = UPPER('"+localTableName+"')";
        //c3p0(sql);
        DataBaseUtils du = new DataBaseUtils();
        //du.executeQueryDm(sql);

        System.out.println(du.getTableComments());
    }
    public String getTableComments(){
        String localTableName = CommonProperty.getInstance().getPropertyValue("LOCAL_TABLE_NAME");
        String sql = "select t.* from user_tab_comments t where UPPER(t.table_name) = UPPER('"+localTableName+"')";
        ResultSet rs = getResultSet(sql);
        try{
            while(rs.next()){
                String comments = rs.getString("comments");
                return comments;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close();
        }
        return null;
    }


    /**********************第一种方式start*******************************/
    public void getConnectionDM(){
        try {
            String jdbc_driver = ConfigProperty.getInstance().getPropertyValue("jdbc.driver");
            String jdbc_url = ConfigProperty.getInstance().getPropertyValue("jdbc.url");
            String jdbc_username = ConfigProperty.getInstance().getPropertyValue("jdbc.username");
            String jdbc_password = ConfigProperty.getInstance().getPropertyValue("jdbc.password");
            Class.forName(jdbc_driver);
            conn =  DriverManager.getConnection(jdbc_url,jdbc_username, jdbc_password);// 获取连接
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void getPstDM(String sql){
        try {
            getConnectionDM();
            pst = conn.prepareStatement(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public ResultSet getResultSet(String sql){
        ResultSet rs=null;
        try{
            getPstDM(sql);
            rs=pst.executeQuery();
            return rs;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public void close() {
        try {
            if (conn != null) {
                conn.close();
            }
            if (pst != null) {
                pst.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**********************第一种方式end*******************************/







    public static ComboPooledDataSource getCpds(){
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        // 加载数据库驱动
        try {
            cpds.setDriverClass("dm.jdbc.driver.DmDriver");
        } catch (PropertyVetoException e1) {
            e1.printStackTrace();
        }
        // 设置访问数据库的地址、用户名和密码
        cpds.setJdbcUrl("jdbc:dm://localhost:5236");
        cpds.setUser("YAHY");
        cpds.setPassword("YAHY123");

        // 设置C3P0的一些配置，不设置则使用默认值
        cpds.setMinPoolSize(5);
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(20);
        cpds.setMaxStatements(180);
        return cpds;
    }
    public static Connection getConnection() throws Exception{
        return getCpds().getConnection();
    }
    public static ResultSet c3p0(String sql) throws SQLException{
        ComboPooledDataSource cpds = getCpds();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = cpds.getConnection();// 创建数据库连接
            stmt = conn.prepareStatement(sql);//查询数据库
            rs = stmt.executeQuery();
            while (rs.next()) { // 处理结果集
                String tableCommnet = rs.getString("tableComment");
                System.out.println(tableCommnet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs,stmt,conn,cpds);
        }
        return rs;
    }
    public static void close(ResultSet rs,Statement stmt,Connection conn,ComboPooledDataSource cpds){
        if(rs != null) {
            try { rs.close(); } catch (SQLException e) { }
        }
        // 关闭数据库操作对象
        if(stmt != null) {
            try { stmt.close(); } catch (SQLException e) { }
        }
        // 关闭数据库连接
        if(conn != null) {
            try { conn.close(); } catch (SQLException e) { }
        }
        try {
            DataSources.destroy(cpds);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
