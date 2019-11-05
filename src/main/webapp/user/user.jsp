<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!--页头-->

<h1>所有用户</h1>
<%--jqgrid--%>
<script>
    $(function(){
        $("#bannerTable").jqGrid({
            height:300,
            styleUI:"Bootstrap",//使用bootstrap样式
            autowidth:true,//自动适应父容器
            url:"${pageContext.request.contextPath}/user/selectAll",
            datatype:"json",
            colNames:["id","用户名","密码","姓名","手机号","省","市","标签","头像","性别","创建时间"],
            pager:'#pager',
            rowNum:3,
            rowList:[3,10,15,20,25],
            caption : "所有用户",
            sortname : 'id',
            autowidth:true,
            colModel:[
                {name : 'id',hidden:true},
                {name : 'username'},
                {name : 'password'},
                {name : 'nickname'},
                {name : 'phone'},
                {name : 'province'},
                {name : 'city'},
                {name : 'sign'},
                {name : 'photo',formatter:function (value,option,rows) {
                        return "<img style='width:60px;height:40px;' src='${pageContext.request.contextPath}/user/img/"+rows.photo+"'>";
                    }},
                {name : 'sex'},
                {name : 'createDate'}
            ],
        }).navGrid("#pager",{edit:false,add:false,del:false,search:false
        });
    })

    function exportExcel(){
        var form = $("<form>");
        form.attr('style', 'display:none');
        form.attr('target', '');
        form.attr('method', 'post');
        form.attr('action', '${pageContext.request.contextPath}/user/export');

        var input1 = $('<input>');
        input1.attr('type', 'hidden');
        input1.attr('name', 'item');
        input1.attr('value', 'test');

        $('body').append(form);
        form.append(input1);

        form.submit();
        form.remove();
    }

    function openModal() {
        $("#article-modal").modal("show");
    }
    function add() {
        $.ajax({
            url: "${pageContext.request.contextPath}/user/add",
            type: "post",
            data: $("#article-form").serialize(),
            datatype: "json",
            success: function () {
                $("#bannerTable").trigger("reloadGrid");
            }
        })
    }
    function send() {
        var phone = $("#phone").val();
        $.ajax({
            url:"${pageContext.request.contextPath}/sendMessage/sendCode",
            type:"post",
            data:{phone:phone},
            datatype:"json",
            success:function () {
                $("#bannerTable").trigger("reloadGrid");
            }
        })
    }


</script>

<body>
<ul class="nav nav-tabs">
    <li role="presentation" class="active"><a href="#">所有用户</a></li>
    <li role="presentation"><a href="#" onclick="exportExcel()">导出用户</a></li>
    <li role="presentation"><a href="#" onclick="openModal('add','')">注册用户</a></li>
</ul>
<!--创建表格-->
<table id="bannerTable">
</table>
<!--分页-->
<div id="pager">
</div>
<div id="article-modal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width: 683px">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">注册用户</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="article-form">
                    <input type="hidden" name="id" id="id">
                    <div class="form-group">
                        <label for="username" class="col-sm-2 control-label">用户名</label>
                        <div class="col-sm-10">
                            <input type="text" name="username" class="form-control" id="username" placeholder="请输入用户名">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-sm-2 control-label">密码</label>
                        <div class="col-sm-10">
                            <input type="password" name="password" class="form-control" id="password" placeholder="请输入密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="nickname" class="col-sm-2 control-label">真实姓名</label>
                        <div class="col-sm-10">
                            <input type="text" name="nickname" class="form-control" id="nickname" placeholder="请输入姓名">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="phone" class="col-sm-2 control-label">电话</label>
                        <div class="col-sm-10">
                            <input type="text" name="phone" class="form-control" id="phone" placeholder="请输入电话">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sex" class="col-sm-2 control-label">性别</label>
                        <div class="col-sm-10">
                            <input type="text" name="sex" class="form-control" id="sex" placeholder="请输入性别">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="codeMessage" class="col-sm-2 control-label">验证码</label>
                        <div class="col-sm-8">
                            <input type="text" name="codeMessage" class="form-control" id="codeMessage" placeholder="请输入验证码">
                        </div>
                        <div class="col-sm-2">
                            <input type="button" name="codeMessage" class="btn btn-primary" value="发送验证码" id="sendMessage" onclick="send()">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="add()" data-dismiss="modal">提交</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>