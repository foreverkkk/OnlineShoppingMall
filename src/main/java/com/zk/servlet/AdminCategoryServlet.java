package com.zk.servlet;

import com.zk.pojo.Category;
import com.zk.pojo.Page;
import com.zk.service.AdminCategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
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
        }else if ("findAll".equals(action)){
            findAll(req,resp);
        }else if ("delete".equals(action)){
            deleteById(req,resp);
        }else if ("deleteMore".equals(action)){
            deleteMore(req,resp);
        }else if ("updatebefore".equals(action)){
            updateBefore(req,resp);
        }else if ("update".equals(action)){
            update(req,resp);
        }
    }

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
        req.setAttribute("msg" , "<script>alert('添加成功！');window.location.href='/OnlineShoppingMall/adminCategoryServlet?action=findAll'</script>");
//        // 资源跳转
        req.getRequestDispatcher("/msg.jsp").forward(req , resp);
    }

    /**
     *  获取商品列表
     */
    private void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         *  获取当前页码，如果没有，默认为第一页
         *  调用 service 中的 findAll方法获取数据  Page对象
         *  将数据保存进 request
         *  请求转发 --> categoryList.jsp 页面
         */
        // 获取当前页码
        int currentPage = 1;
        String c = req.getParameter("currentPage");
        try {
            currentPage = Integer.parseInt(c);
        }catch (Exception e){
            currentPage = 1;
        }
        Page page = adminCategoryService.findAll(currentPage);
        req.setAttribute("page", page);
        req.getRequestDispatcher("/categoryList.jsp").forward(req,resp);
    }

    /**
     * 根据商品 ID 删除指定商品
     * @param req
     * @param resp
     */
    private void deleteById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 获取要删除的 ID 调用 service 层
         * 调用 findAll 方法显示删除后的列表
         */
        String str = req.getParameter("id");
        adminCategoryService.deleteById(Integer.parseInt(str));
        findAll(req, resp);
    }

    /**
     *  批量删除
     */
    private void deleteMore(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         *  获取要删除的id数组
         *  判断id是否为空
         *  调用 service 进行删除
         *  调用 findAll 方法重新显示删除后的记录
         */
        String []ids = req.getParameterValues("sel");
        if (ids != null){
            adminCategoryService.deleteMore(ids);
            findAll(req, resp);
        }
    }

    /**
     * 获取要修改的 id
     * 通过service层把该条记录读取出来
     * 存入到request中
     * 请求转发到updateCategory.jsp页面中
     */
    private void updateBefore(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
//        Map<String, Object> byId = adminCategoryService.findById(Integer.parseInt(id));
        req.setAttribute("item",adminCategoryService.findById(Integer.parseInt(id)));
//        req.setAttribute("item" , byId);
        req.getRequestDispatcher("/updateCategory.jsp").forward(req,resp);
    }
    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 获取要修改的所有信息
         * 验证
         * 调用service进行修改
         * 禁止刷新重复提交，先转向 msg.jsp
         */
        // 获取表单数据
        String name = req.getParameter("name");
        String sort = req.getParameter("sort");
        String id = req.getParameter("id");
        // 进行表单验证
        if (name == null || name.trim().isEmpty()){
            // name为空
            req.setAttribute("msg" , "分类名称不能为空！");
            // 跳转回来
            req.getRequestDispatcher("/updateCategory.jsp").forward(req , resp);
            return;
        }
        if (sort == null || sort.trim().isEmpty()){
            // sort为空
            req.setAttribute("msg" , "分类排序不能为空！");
            // 跳转回来
            req.getRequestDispatcher("/updateCategory.jsp").forward(req , resp);
            return;
        }
        String regex = "^[1-9]+[0-9]*$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(sort);
        if (!m.find()){
            req.setAttribute("msg" , "分类排序号必须是正整数!");
            req.getRequestDispatcher("/updateCategory.jsp").forward(req , resp);
            return;
        }
        // 判断 name 是否已经存在,存在返回错误提示信息,不存在继续添加
        // 调用 Service 中的方法 validateName(name) 如果存在返回 true
        if (adminCategoryService.validateName(name)){
            req.setAttribute("msg" , "该分类名称已被占用!");
            req.getRequestDispatcher("/updateCategory.jsp").forward(req , resp);
            return;
        }

        // 封装到 Category 对象中
        Category category = new Category();
        category.setName(name);
        category.setSort(Integer.parseInt(sort));
        category.setCategoryid(Integer.parseInt(id));
        // 调用 service 层进行修改
        adminCategoryService.update(category);
        // 禁止刷新重复提交
        req.setAttribute("msg" , "<script>alert('修改成功！'); window.location.href' "+req.getContextPath()+"/adminCategoryServlet?action=findAll'</script>");
        req.getRequestDispatcher("/msg.jsp").forward(req,resp);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
