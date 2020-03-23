/*
 * DetailController.java
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
 * Detail controller class.<br>
 * 
 * <pre>
 * Class này là 1 servlet thực hiện lấy các request và dữ liệu cần thiết để đẩy lên detail.jsp để xử lý
 * Trong class này sẽ tiến hành các xử lí dưới đây.
 * 
 * ・processRequest()
 *
 * </pre>
 * 
 * @author FU AnhTHPHE130193
 * @version 1.0
 */
@WebServlet(name = "DetailController", urlPatterns = {"/detail"})
public class DetailController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.<br>
     * 
     *<pre>
     * ♦ Trình tự xử lí.
     *  1.Tạo mới 1 đối tượng DigitalDAO để tiến hành lấy dữ liệu từ database.
     *  2.Lấy parameter id về để thực hiện lấy từ CSDL về Digital có id đó
     *  3.Tạo 1 request để set attribute tên one chứa đối tượng Digital vừa tìm được
     *  4.Tạo 1 request để set attribute tên top1 chứa thông tin về Digital mới nhất
     *  5.Tạo 1 request để set attribute tên top5 1 list Digital mới nhất 
     *  6.Lẩy toàn bộ dữ liệu lên trang detail.jsp.
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
            
            String idTmp = request.getParameter("id");
            if(digitalDAO.checkInt(idTmp) == false){
                response.sendRedirect("error.jsp");
            }
            int id = Integer.parseInt(request.getParameter("id"));
            
            if(digitalDAO.checkExist(id)==false){
                response.sendRedirect("error.jsp");
            }
            
            Digital d = digitalDAO.getOne(id);
            request.setAttribute("one", d);
            
            Digital top1 = digitalDAO.getTop1();
            request.setAttribute("top1", top1);
    
            List<Digital> list = digitalDAO.getTop5();
            request.setAttribute("top5", list);

            request.getRequestDispatcher("detail.jsp").forward(request, response);
            
            
        } catch (Exception ex) {
            Logger.getLogger(DetailController.class.getName()).log(Level.SEVERE, null, ex);
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
