<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!--页头-->

<h1>所有文章</h1>
<%--jqgrid--%>
<script>
    $(function(){
        $("#bannerTable").jqGrid({
            height:300,
            styleUI:"Bootstrap",//使用bootstrap样式
            autowidth:true,//自动适应父容器
            url:"${pageContext.request.contextPath}/article/findAll",
            datatype:"json",
            colNames:["id","标题","作者","简介","内容","创建时间","操作"],
            pager:'#pager',
            rowNum:3,
            rowList:[3,10,15,20,25],
            viewrecords:true,
            caption : "所有文章",
            editurl : "someurl.php",
            sortname : 'id',
            autowidth:true,
            colModel:[
                {name : 'id'},
                {name : 'title'},
                {name : 'author'},
                {name : 'brief'},
                {name : 'content',hidden:true},
                {name : 'createDate'},
                {name : 'operation',formatter:function (value,option,rows) {
                        return "<a class='btn btn-warning' onclick=\"openModal('edit','"+rows.id+"')\">修改</a>" +
                            "&nbsp;&nbsp;&nbsp;<a class='btn btn-danger' onclick=\"deleteModal('"+rows.id+"','')\">删除</a>";
                    }}
            ],
        }).navGrid("#pager",{edit:false,add:false,del:false,search:false
        });
    })

   function openModal(oper,id) {
       if("add"==oper){
           $("#id").val("");
           $("#title").val("");
           $("#author").val("");
           $("#brief").val("");
           KindEditor.html("#editor_id","");
       }
       if("edit"==oper){
           var article = $("#bannerTable").jqGrid("getRowData",id);
           $("#id").val(article.id);
           $("#title").val(article.title);
           $("#author").val(article.author);
           $("#brief").val(article.brief);
           KindEditor.html("#editor_id",article.content);

       }

       $("#article-modal").modal("show");
   }
    function addAndUpdate(){
        var id = $("#id").val();
        var url = "";
        if(id){
            url= "${pageContext.request.contextPath}/article/edit";
        }else{
            url = "${pageContext.request.contextPath}/article/add";
        }
        $.ajax({
            url:url,
            type:"post",
            data:$("#article-form").serialize(),
            datatype:"json",
            success:function () {
                $("#bannerTable").trigger("reloadGrid");
            }
        })
    }
    function deleteModal(id){
        $.ajax({
            url:"${pageContext.request.contextPath}/article/del",
            type:"post",
            data:{id:id},
            datatype:"json",
            success:function () {
                $("#bannerTable").trigger("reloadGrid");
            }
        })
    }

    KindEditor.create('#editor_id',{
        width : '460px',
        //点击图片空间按钮时发送的请求
        fileManagerJson:"${pageContext.request.contextPath}/article/browse",
        //展示图片空间按钮
        allowFileManager:true,
        //上传图片所对应的方法
        uploadJson:"${pageContext.request.contextPath}/article/upload",
        //上传图片是图片的形参名称
        filePostName:"articleImg",
        afterCreate: function() { // kindeditor创建后，将编辑器的内容设置到原来的textarea控件里
            this.sync();
        },
        afterChange: function() { // 编辑器内容发生变化后，将编辑器的内容设置到原来的textarea控件里
            this.sync();
        },
        afterBlur:function () {
            this.sync();
        }
    });

</script>

<body>
<ul class="nav nav-tabs">
    <li role="presentation" class="active"><a href="#">所有文章</a></li>
    <li role="presentation"><a href="#" onclick="openModal('add','')">添加文章</a></li>
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
                <h4 class="modal-title">文章操作</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="article-form">
                    <input type="hidden" name="id" id="id">
                    <div class="form-group">
                        <label for="title" class="col-sm-2 control-label">文章标题</label>
                        <div class="col-sm-10">
                            <input type="text" name="title" class="form-control" id="title" placeholder="请输入文章标题">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="author" class="col-sm-2 control-label">文章作者</label>
                        <div class="col-sm-10">
                            <input type="text" name="author" class="form-control" id="author" placeholder="请输入文章作者">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="brief" class="col-sm-2 control-label">文章简介</label>
                        <div class="col-sm-10">
                            <input type="text" name="brief" class="form-control" id="brief" placeholder="请输入文章简介">
                        </div>
                    </div>
                        <textarea id="editor_id" name="content" style="width:700px;height:300px;"></textarea>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="addAndUpdate()" data-dismiss="modal">提交</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->


    <div class="modal fade" tabindex="-1" role="dialog" id="delete-modal">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">删除章节</h4>
                </div>
                <div class="modal-body">
                    <p>确认要删除吗？</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" onclick="deletes()">确认</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
</div>
