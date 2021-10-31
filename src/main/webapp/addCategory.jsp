<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <link href="css/style.css" rel="stylesheet" type="text/css" />
    <link href="css/select.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/select-ui.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function(e) {

            $(".select1").uedSelect({
                width : 280
            });
            $(".select2").uedSelect({
                width : 167
            });
            $(".select3").uedSelect({
                width : 100
            });

        });

        function validateName(){
            let name = $('input[name="name"]');
            let result = $("#result");
            result.html("");
            if (name.val()==null || $.trim(name.val())==""){
                result.html("分类名称不能为空！");
                name.focus();
                return false;
            }
            return true;
        }

        // 验证非空
        function validateSort(){
            let sort = $('input[name="sort"]');
            let result = $("#result");
            result.html("");
            if (sort.val()==null || $.trim(sort.val())==""){
                result.html("分类排序号不能为空！");
                sort.focus();
                return false;
            }
            // 验证是否为正整数
            let regix = new RegExp("^\\+?[1-9][0-9]*$");
            if (!regix.test($.trim(sort.val()))){
                result.html("分类必须输入正整数！");
                sort.focus();
                return false;
            }
            return true;
        }

        function validate(){
            if (validateName() && validateSort()){
                $("#form1").submit();
            }
        }
    </script>
</head>

<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">首页</a>
        </li>
        <li><a href="#">添加分类</a>
        </li>
    </ul>
</div>
<form action="<c:url value="/adminCategoryServlet"/>" method="post" id="form1">
    <input type="hidden" name="action" value="add"/>
    <div class="formbody">
        <div class="formtitle">
            <span>添加分类</span>
        </div>

        <ul class="forminfo">
            <li><label>分类名称<b>*</b>
            </label><input name="name" type="text" class="dfinput" style="width:278px;" onblur="validateName()"/>
            </li>
            <li><label>商品分类排序<b>*</b>
            </label>
                <div class="vocation">
                    <input name="sort" type="text" class="dfinput" style="width:278px;" onblur="validateSort()"/>
                </div>
            </li>
            <li><label style="color: red; width:278px;" id="result" >&nbsp; ${msg}</label></li>
            <li><label>&nbsp;</label>
<%--                <input name="" type="button" class="btn" value="添加" onclick="validate()"/>--%>
                <input name="" type="submit" class="btn" value="添加"/>
            </li>
        </ul>

    </div>
    <script>${msg}</script>
</form>
</body>
</html>

