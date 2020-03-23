/*
 * HomeController.java
 * 
 * All Rights Reserved.
 * Copyright (c) 2020 FPT University
 */

package controller;

import dal.DBContext;
import dal.DigitalDAO;
import entity.Digital;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Khanh Xinh Tuoi
 */

/**
 * Home controller class.<br>
 * 
 * <pre>
 * Class này là 1 servlet thực hiện lấy các request và dữ liệu cần thiết để đẩy lên homepage.jsp để xử lý
 * Trong class này sẽ tiến hành các xử lí dưới đây.
 * 
 * ・processRequest()
 * 
 *
 * </pre>
 * 
 * @author FU AnhTHPHE130193
 * @version 1.0
 */
@WebServlet(name = "HomeController", urlPatterns = {"/home"})
public class HomeController extends HttpServlet {
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.<br/>
     * 
     *<pre>
     * ♦ Trình tự xử lí.
     *  1.Tạo mới 1 đối tượng DigitalDAO để tiến hành lấy dữ liệu từ database.
     *  2.Tạo 1 request để set attribute tên top1 chứa thông tin về Digital mới nhất
     *  3.Tạo 1 request để set attribute tên top5 1 list Digital mới nhất 
     *  4.Đẩy toàn bộ dữ liệu lên trang homepage.jsp.
     * </pre>
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            DBContext bContext = new DBContext();

            String imagePath = bContext.getImagePath();
            request.setAttribute("imagePath", imagePath);
           
            DigitalDAO digitalDAO = new DigitalDAO();
            Digital top1 = digitalDAO.getTop1();
            request.setAttribute("top1", top1);
            
            List<Digital> list = digitalDAO.getTop5();
            request.setAttribute("top5", list);
            
            request.getRequestDispatcher("homepage.jsp").forward(request, response);
            
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
