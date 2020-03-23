/*
 * DigitalDAO.java
 * 
 * All Rights Reserved.
 * Copyright (c) 2020 FPT University
 */
package dal;

import entity.Digital;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * DigitalDAO class.<br>
 * 
 * <pre>
 * Class chứa các method nhằm mục đích lấy dữ liệu cần từ database
 * Trong class này sẽ tiến hành các xử lí dưới đây.
 * 
 * ・getTop1()
 * ・getTop5()
 * ・getOne()
 * ・search()
 * ・count()
 * ・checkExist()
 * ・checkInt()
 * 
 * </pre>
 * 
 * @author FU AnhTHPHE130193
 * @version 1.0
 */
public class DigitalDAO {
    
    
    /**
     * Xử lí lấy top 1 Digital.<br>
     * 
     * <pre>
     * Method sẽ lấy dữ liệu từ database ra để trả về 1 Digital hoàn chỉnh.
     * Trường hợp lấy dữ liệu thất bại thì thực hiện ném exception.
     * 
     * ♦ Trình tự xử lí
     *  1. Câu query được xây dựng để lấy ra record Digital có thời gian tạo(TimePost) gần nhất trong database.
     *  2. Sử dụng PrepareStatement để thực hiện câu truy vấn.
     *  3. Kết quả của quá trình trên được lưu vào 1 ResultSet.
     *   3.1 Nếu kết quả ResultSet trả về ít nhất 1 record, tạo 1 đối tượng Digital mới, set các giá trị cho đối tượng đó
     *       sau đó return chính đối tượng đó.
     *   3.2 Nếu không có record nào trong ResultSet thì return về null.
     *  4.Đóng kết nối database 
     * ♦ Xử lí Exception
     *  ・Nếu không lấy được dữ liệu thì Exception sẽ được bắn ra
     * </pre>
     * 
     * @throws Exception
     * @return Digital object
     */
    public Digital getTop1() throws Exception{
        DBContext db = new  DBContext();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String query = "select top 1 * from Digital\n"
                    + "order by timePost desc";
            conn = db.getConnection();
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Digital d = new Digital(rs.getInt("ID"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getString("author"),
                        rs.getDate("timePost"),
                        rs.getString("shortDes"));
                return d;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            db.closeConnection(rs, pstmt, conn);
        }
        return null;
        
    }
    
    
     /**
     * Xử lí lấy 1 đối tượng Digital thông qua Id.<br>
     * 
     * <pre>
     * Method sẽ lấy dữ liệu từ database ra để trả về 1 Digital hoàn chỉnh.
     * Trường hợp lấy dữ liệu thất bại thì thực hiện ném exception.
     * 
     * ♦ Trình tự xử lí.
     *   1.Viết câu query để lấy ra Record có Id bằng với Id user ghi trong tham số truyền vào.
     *   2.Dùng PrepareStatement để truyền tham số và thực hiện câu truy vấn.
     *   3. Kết quả của quá trình trên được lưu vào 1 ResultSet.
     *      3.1 Nếu kết quả ResultSet trả về ít nhất 1 Record, tạo 1 đối tượng Digital mới, 
     *          set các giá trị cho đối tượng đó sau đó return về chính đối tượng đó.
     *      3.2 Nếu không có record nào trong ResultSet thì return về null.
     *   4.Đóng kết nối database 
     * ♦ Xử lí Exception
     *  ・Nếu không lấy được dữ liệu thì Exception sẽ được bắn ra
     * </pre>
     * 
     * @param id
     * @throws Exception
     * @return Digital object
     */
    public Digital getOne(int id) throws Exception {
        DBContext db = new DBContext();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String query = "select * from Digital where ID = ?";
            conn = db.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Digital d = new Digital(rs.getInt("ID"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getString("author"),
                        rs.getDate("timePost"),
                        rs.getString("shortDes"));
                return d;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            db.closeConnection(rs, pstmt, conn);
        }
        return null;
    }
    
    
    /**
     * Xử lí lấy Top 5 Digital.<br>
     * 
     * <pre>
     * Method sẽ lấy dữ liệu từ database ra để trả về 1 list Digitals
     * Trường hợp lấy dữ liệu thất bại thì thực hiện ném exception.
     * 
     * ♦ Trình tự xử lí
     *  1. Câu query được xây dựng để lấy ra 5 record Digital có thời gian tạo (TimePost) gần nhất trong database.
     *  2. Sử dụng PrepareStatement để thực hiện câu truy vấn.
     *  3. Kết quả của quá trình trên được lưu vào 1 ResultSet.
     *   3.1 Nếu kết quả ResultSet trả về nhiều hơn 1 record, tạo 1 list để chứa các record đó,
     *       sau đó return chính list vừa tạo.
     *   3.2 Nếu không có record nào trong ResultSet thì return về list rỗng.
     *  4.Đóng kết nối database 
     * ♦ Xử lí Exception
     *  ・Nếu không lấy được dữ liệu thì Exception sẽ được bắn ra
     * </pre>
     * 
     * @throws Exception
     * @return ArrayList Digital
     */
    public List<Digital> getTop5() throws Exception {
        DBContext db = new DBContext();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            List<Digital> list = new ArrayList<>();
            String query = "select top 5 * from Digital\n"
                    + "where timePost not in(\n"
                    + "select max(timepost) from Digital\n"
                    + ")\n"
                    + "order by timePost desc";
            conn = db.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Digital d = new Digital(rs.getInt("ID"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getString("author"),
                        rs.getDate("timePost"),
                        rs.getString("shortDes"));
                list.add(d);
            }
            return list;
        } catch (Exception e) {
            throw e;
        } finally {
            db.closeConnection(rs, ps, conn);
        }
    }
    
    
    /**
     * Xử lí tìm kiếm Digitals.<br>
     * 
     * <pre>
     * Method sẽ lấy dữ liệu từ database ra để trả về 1 list Digitals tìm được
     * Trường hợp lấy dữ liệu thất bại thì thực hiện ném exception.
     * 
     * ♦ Trình tự xử lí
     *  1.Câu query dùng để lấy 1 list Digital theo từng đợt phân trang
     *   1.1.Do ID của bản tin trong CSDL không theo tuần tự nên không có cách nào phân biệt vị trí các record cạnh nhau,
     *       do đó phải thêm 1 cột Row_Number() để đánh dấu thứ tự tuần tự cho các dòng,
     *       sau đó chọn ra những Digital 
     *       thỏa mãn điều kiện nằm trong khoảng từ ... đến ...
     *  2.Sử dụng PrepareStatement để thực hiện câu truy vấn.
     *  3.Kết quả của quá trình trên được lưu vào 1 ResultSet:
     *   3.1.Nếu kết quả ResultSet trả về ít nhất 1 record, tạo 1 list để chứa các record đó 
     *       với mỗi record là 1 đối tượng Digital mới, set các giá trị cho đối tượng đó
     *       sau đó thêm vào list đã tạo.
     *   3.2.1.Return về list vừa tạo
     *   3.2.2 Nếu không có record nào trong ResultSet thì return về list rỗng.
     *  4.Đóng kết nối database 
     * 
     * ◆Xử lí exception 
     *    ・Nếu không lấy được dữ liệu thì Exception sẽ được bắn ra
     * </pre>
     * 
     * @param txt String
     * @param pageIndex int
     * @param pageSize int
     * @throws Exception Trường hợp lấy dữ liệu thất bại.
     * @return ArrayList Digital
     */
    public List<Digital> search(String txt, int pageIndex, int pageSize) throws Exception {
        DBContext db = new DBContext();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int totalNews1Page = pageSize-1;
        try {
            List<Digital> list = new ArrayList<>();
            String query = "Select *from( "
                    + " Select ROW_NUMBER() over (order by ID ASC) as rn, *\n "
                    + " from Digital where title \n "
                    + " like ? "
                    + " )as x\n "
                    + " where rn between ((?*?)-?) and ?*? ";
            conn = db.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + txt + "%");
            ps.setInt(2, pageIndex);
            ps.setInt(3, pageSize);
            ps.setInt(4, totalNews1Page);
            ps.setInt(5, pageIndex);
            ps.setInt(6, pageSize);
            rs = ps.executeQuery();
            while (rs.next()) {
                Digital d = new Digital(rs.getInt("ID"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getString("author"),
                        rs.getDate("timePost"),
                        rs.getString("shortDes"));
                list.add(d);
            }
            return list;
        } catch (Exception e) {
            throw e;
        } finally {
            db.closeConnection(rs, ps, conn);
        }
    }
    
    
    /**
     * Xử lí count Digitals.<br>
     * 
     * <pre>
     * Method sẽ đếm tổng số các record với thông tin tựa đề mà mình cần tìm kiếm
     * Trường hợp lấy dữ liệu thất bại thì thực hiện némí exception.
     * 
     * ♦ Trình tự xử lí.
     *   
     *   1.Viết câu query để lấy danh sách các bản ghi phù hợp với điều kiện tìm kiếm.
     *   2.Dùng PrepareStatement để truyền tham số và thực hiện câu truy vấn.
     *   3.Khởi tạo 1 biến count = 0 để đếm số lượng.
     *   4.Kết quả của quá trình trên được lưu vào 1 ResultSet:
     *      4.1.Nếu kết quả ResultSet trả về ít nhất 1 record, với mỗi 1 record tăng giá trị biến count lên 1 đơn vị.
     *   5.Trả về giá trị biến count.
     *   6.Đóng kết nối database 
     * ◆Xử lí exception 
     *    ・Nếu không lấy được dữ liệu thì Exception sẽ được bắn ra
     * </pre>
     * 
     * @param txt String
     * @throws Exception 
     * @return count integer number
     */
    public int count(String txt) throws Exception {
        DBContext db = new DBContext();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "select count(*) from Digital \n"
                    + "where title like ?";
            conn = db.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + txt + "%");
            rs = ps.executeQuery();
            int count = 0;
            while (rs.next()) {
                count = rs.getInt(1);
            }
            return count;
        } catch (Exception e) {
            throw e;
        } finally {
            db.closeConnection(rs, ps, conn);
        }
    }
    
    
    /**
     * Check valid integer.<br>
     * 
     * <pre>
     * Method sẽ kiểm tra tham số truyền vào có phải là số nguyên không
     * 
     * ♦ Trình tự xử lí.
     *   
     *   1.parse biến tham số truyền vào
     *   2.Nếu parse thành công thì trả về true, và ngược lại
     *        
     * </pre>
     * 
     * @param tmp String
     * @return boolean
     */
    public boolean checkInt(String tmp){
        int a;
        try{
            a = Integer.parseInt(tmp);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
        
    }
    
    /**
     * Xử lí kiểm tra sự tồn tại của Digital.<br>
     * 
     * <pre>
     * Method sẽ thực hiện kiểm tra xem digital tồn tại hay không
     * Trường hợp lấy dữ liệu thất bại thì thực hiện ném exception.
     * 
     * ♦ Trình tự xử lí.
     *   1.Viết câu query để lấy ra Record có Id bằng với Id user ghi trong tham số truyền vào.
     *   2.Dùng PrepareStatement để truyền tham số và thực hiện câu truy vấn.
     *   3. Kết quả của quá trình trên được lưu vào 1 ResultSet.
     *      3.1 Nếu kết quả ResultSet trả về ít nhất 1 Record, return true.
     *      3.2 Nếu không có record nào trong ResultSet thì return về false.
     *   4.Đóng kết nối database 
     * ♦ Xử lí Exception
     *  ・Nếu không lấy được dữ liệu thì Exception sẽ được bắn ra
     * </pre>
     * 
     * @param id int
     * @throws Exception Trường hợp lấy dữ liệu thất bại.
     * @return boolean
     */
    public boolean checkExist(int id) throws Exception {
        DBContext db = new DBContext();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String query = "select * from Digital where ID = ?";
            conn = db.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                
                return true;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            db.closeConnection(rs, pstmt, conn);
        }
        return false;
    }
}
