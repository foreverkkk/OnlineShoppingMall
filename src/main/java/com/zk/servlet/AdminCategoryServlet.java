package com.zk.servlet;

import com.zk.pojo.Category;
import com.zk.service.AdminCategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zk
 */
@WebServlet("/adminCategoryServlet")
public class AdminCategoryServlet extends HttpServlet {
    private AdminCategoryService adminCategoryService = new AdminCategoryService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        /**
         *  获取 action 的值，根据 action 值调用不同的方法
         */
        String action = req.getParameter("action");
        if ("add".equals(action)){
            add(req,resp);
        }
    }

//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        System.out.println("1111111111111111111111111");
//    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         *  获取表单数据
         *  进行验证表单数据，判断name是否已经存在
         *  封装到Category对象中
         *  调用 service 层进行添加
         */
        // 获取表单数据
        String name = req.getParameter("name");
        String sort = req.getParameter("sort");
        // 进行表单验证
        if (name == null || name.trim().isEmpty()){
            // name为空
            req.setAttribute("msg" , "分类名称不能为空！");
            // 跳转回来
            req.getRequestDispatcher("/addCategory.jsp").forward(req , resp);
            return;
        }
        if (sort == null || sort.trim().isEmpty()){
            // sort为空
            req.setAttribute("msg" , "分类排序不能为空！");
            // 跳转回来
            req.getRequestDispatcher("/addCategory.jsp").forward(req , resp);
            return;
        }
        String regex = "^[1-9]+[0-9]*$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(sort);
        if (!m.find()){
            req.setAttribute("msg" , "分类排序号必须是正整数!");
            req.getRequestDispatcher("/addCategory.jsp").forward(req , resp);
            return;
        }
        // 判断 name 是否已经存在,存在返回错误提示信息,不存在继续添加
        // 调用 Service 中的方法 validateName(name) 如果存在返回 true
        if (adminCategoryService.validateName(name)){
            req.setAttribute("msg" , "该分类名称已被占用!");
            req.getRequestDispatcher("/addCategory.jsp").forward(req , resp);
            return;
        }

        // 封装到 Category 对象中
        Category category = new Category();
        category.setName(name);
        category.setSort(Integer.parseInt(sort));
        // 调用 service 中的方法进行添加
        adminCategoryService.add(category);
        req.setAttribute("msg" , "<script>alert('添加成功！');window.location.href='/OnlineShoppingMall/addCategory.jsp'</script>");
//        // 资源跳转
        req.getRequestDispatcher("/msg.jsp").forward(req , resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
