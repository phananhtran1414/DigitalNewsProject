/*
 * DBContext.java
 * 
 * All Rights Reserved.
 * Copyright (c) 2020 FPT University
 */

package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * DBContext class.<br>
 * 
 * <pre>
 *  Class này thực hiện tạo 1 connection tới CSDL Digital
 *  Trong class này sẽ tiến hành các xử lí dưới đây.
 * 
 *  ・DBContext()
 *  ・getConnection()
 *  ・closeConnection()
 *  ・getImagePath()
 *  ・
 * 
 * </pre>
 * 
 * @author FU AnhTHPHE130193
 * @version 1.0
 */
public class DBContext {
    
    /** Store InitialContext object */
    InitialContext initial;
    /** Store Context object */
    Context context;
    /** Store Database name */
    String dbname;
    /** Store Server name */
    String serverName;
    /** Store Port number*/
    String portNumber;
    /** Store image url */
    String image;
    /** Store username Database */
    String username;
    /** Store password Database */
    String password;
    /** Store total Digital in each page */
    String pageSize;
    /**
     * Constructor.<br>
     * 
     *<pre>
     * Method sẽ thực hiện tham chiếu để lấy dữ liệu được trỏ tới
     * Trường hợp lấy dữ liệu thất bại thì thực hiện ném exception.
     * 
     * ♦ Trình tự xử lí
     *  1. Sử dụng biến initial để trỏ đến root của project
     *  2. Gọi hàm lookup để tham chiếu đến "java:comp/env" thực hiện lấy dữ liệu
     *  
     * ♦ Xử lí Exception
     *  ・Nếu tên dữ liệu tìm kiếm không tồn tại thì Exception sẽ được bắn ra
     * </pre>
     * 
     */
    public DBContext(){
        try{
            initial = new InitialContext();
            Object obj = initial.lookup("java:comp/env");
            context = (Context) obj;
            
            serverName = context.lookup("serverName").toString();
            dbname = context.lookup("dbName").toString();
            portNumber = context.lookup("portNumber").toString();
            image = context.lookup("image").toString();
            username = context.lookup("username").toString();
            password = context.lookup("password").toString();
            pageSize = context.lookup("pageSize").toString();
        } catch (NamingException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Xử lí mở Connection.<br>
     * 
     * <pre>
     * Method tạo 1 connection tới csdl Digital
     * Trường hợp mở connection thất bại thì thực hiện ném exception.
     * 
     * ♦ Trình tự xử lí
     *  1. Thực hiện load Driver để mở Connection
     *  
     * ♦ Xử lí Exception
     *  ・Nếu không lấy được dữ liệu thì Exception sẽ được bắn ra
     * </pre>
     * 
     * @return Connection
     * @throws java.lang.Exception
     */
    public Connection getConnection() throws Exception {
        String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbname;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, username, password);
    }
    
    /**
     * Xử lí đóng connect với CSDL.<br>
     * 
     * <pre>
     * Method đóng kết nối với CSDL
     * Trường hợp đóng connection thất bại thì thực hiện ném exception.
     * 
     * ♦ Trình tự xử lí
     *  1. Lần lượt gọi hàm close() cho các biến tham số truyền vào
     *  
     * ♦ Xử lí Exception
     *  ・Nếu không lấy được dữ liệu thì SQLException sẽ được bắn ra
     * </pre>
     * 
     * @param rs
     * @param ps
     * @param con
     * @throws SQLException Trường hợp lấy dữ liệu thất bại.
     */
    public void closeConnection(ResultSet rs, PreparedStatement ps, Connection con) throws SQLException {
        if (rs != null && !rs.isClosed()) {
            rs.close();
        }
        if (ps != null && !ps.isClosed()) {
            ps.close();
        }
        if (con != null && !con.isClosed()) {
            con.close();
        }
    }

    /**
     * Xử lí lấy đường dẫn ảnh.<br>
     * 
     * <pre>
     * Method trả về đường dẫn ảnh
     * Trường hợp không lấy được thì thực hiện ném exception.
     * 
     * ♦ Trình tự xử lí
     *  1. trả về đường dẫn ảnh
     *  
     * ♦ Xử lí Exception
     *  ・Nếu không trả được dữ liệu thì Exception sẽ được bắn ra
     * </pre>
     * 
     * @return String
     * @throws Exception Trường hợp trả dữ liệu thất bại.
     */
    public String getImagePath() throws Exception {
        return image;
    }
    
    /**
     * Xử lí lấy tổng số Digital trong từng page index.<br>
     * 
     * <pre>
     * Method trả về page size
     * Trường hợp không lấy được thì thực hiện ném exception.
     * 
     * ♦ Trình tự xử lí
     *  1. trả về tổng digital trong 1 page
     *  
     * ♦ Xử lí Exception
     *  ・Nếu không trả được dữ liệu thì Exception sẽ được bắn ra
     * </pre>
     * 
     * @return String
     * @throws Exception Trường hợp trả dữ liệu thất bại.
     */
    public String getPageSize() throws Exception {
        return pageSize;
    }
    
}
