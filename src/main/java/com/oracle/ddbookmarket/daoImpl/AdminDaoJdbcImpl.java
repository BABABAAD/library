package com.oracle.ddbookmarket.daoImpl;

import com.oracle.ddbookmarket.dao.AdminDao;
import com.oracle.ddbookmarket.model.Admin;
import com.oracle.ddbookmarket.model.BigType;
import com.oracle.ddbookmarket.uitl.DBUtile;
import com.oracle.ddbookmarket.uitl.MD5Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdminDaoJdbcImpl implements AdminDao {
    @Override
    public boolean findadmin(Admin admin) {
        Connection conn=null;
        PreparedStatement stmt=null;
        ResultSet rst=null;

        try {
            /*conn= DBUtile.getConnection();
            String sql="select * from t_admin where name=? and pwd=?";
            stmt=conn.prepareStatement(sql);
            stmt.setString(1,admin.getName());
            //加密
            stmt.setString(2, MD5Util.getEncryptedPwd(admin.getPwd()));
            rst=stmt.executeQuery();*/
            conn=DBUtile.getConnection();
            String sql="select pwd from t_admin where name=?";
            stmt=conn.prepareStatement(sql);
            stmt.setString(1,admin.getName());
            rst=stmt.executeQuery();
            if (rst.next()) {
                String dbpwd=rst.getString(1);
               return MD5Util.validPasswd(admin.getPwd(),dbpwd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtile.fiee(rst, stmt, conn);
        }
        return false;
    }
}
