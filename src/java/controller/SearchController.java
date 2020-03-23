/*
 * SearchController.java
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
 * Search controller class.<br>
 * 
 * <pre>
 * Class này là 1 servlet thực hiện lấy các request và dữ liệu cần thiết để đẩy lên search-result.jsp để xử lý
 * Trong class này sẽ tiến hành các xử lí dưới đây.
 * 
 * ・processRequest()
 *
 * </pre>
 * 
 * @author FU AnhTHPHE130193
 * @version 1.0
 */
@WebServlet(name = "SearchController", urlPatterns = {"/search"})
public class SearchController extends HttpServlet {
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.<br/>
     * 
     *<pre>
     * ♦ Trình tự xử lí.
     *  1.Tạo mới 1 đối tượng DigitalDAO để tiến hành lấy dữ liệu từ database.
     *  2.Lấy parameter txtSearch về để thực hiện tìm kiếm các Digitals chứa keyword đó
     *  3.Gọi 1 list để chứa 1 list các Digital tìm được
     *  4.Tạo 1 request để set attribute tên list chứa 1 list Digital trên
     *  5.Tạo 1 request để set attribute tên top1 chứa thông tin về Digital mới nhất
     *  6.Tạo 1 request để set attribute tên top5 1 list Digital mới nhất 
     *  7.Lẩy toàn bộ dữ liệu lên trang search-result.jsp.
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
            DigitalDAO digitalDAO = new DigitalDAO();

            String txt = request.getParameter("txtSearch");
            if(txt == null){
                txt = (String)request.getSession().getAttribute("txtSearch");
                if(txt == null){
                    response.sendRedirect("error.jsp");
                }
            }

            String pageIndex = request.getParameter("index"); 
            if(pageIndex==null){
                request.getSession().setAttribute("txtSearch", txt);
                pageIndex = "1";
            }
            if(digitalDAO.checkInt(pageIndex) == false){
                response.sendRedirect("error.jsp");
            }
            
            int index = Integer.parseInt(pageIndex);
            
            int total = digitalDAO.count(txt);
            
            //begin of set imagePath
            DBContext dbContext = new DBContext();
            int pageSize = Integer.parseInt(dbContext.getPageSize());
            
            int maxPage = total/pageSize;
            if(total % pageSize !=0){
                maxPage++;
            }
            
            
            if(index>maxPage || index <=0){
                response.sendRedirect("error.jsp");
            }
            List<Digital> listSearch = digitalDAO.search(txt, index, pageSize);
            
            request.setAttribute("list", listSearch);
            request.getSession().setAttribute("maxPage", maxPage);
            request.setAttribute("maxPage", maxPage);
            request.setAttribute("txt", txt);
            request.setAttribute("index", index);
            
            
            String imagePath = dbContext.getImagePath();
            request.setAttribute("imagePath", imagePath);
            
            Digital top1 = digitalDAO.getTop1();
            request.setAttribute("top1", top1);
            
            List<Digital> list = digitalDAO.getTop5();
            request.setAttribute("top5", list);
           
            request.getRequestDispatcher("search-result.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
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
