<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!--页头-->

    <h1>轮播图</h1>

<%--jqgrid--%>
<script>
    $(function(){
        $("#bannerTable").jqGrid({
            height:300,
            styleUI:"Bootstrap",//使用bootstrap样式
            autowidth:true,//自动适应父容器
            url:"${pageContext.request.contextPath}/banner/findAll",
            datatype:"json",
            colNames:["id","姓名","图片","描述","状态","创建时间"],
            pager:'#pager',
            rowNum:3,
            rowList:[3,10,15,20,25],
            viewrecords:true,
            caption : "轮播图列表",
            sortname : 'id',
            autowidth:true,
            editurl:"${pageContext.request.contextPath}/banner/edit",
            colModel:[
                {name:"id",hidden:true,editable:false},
                {name:"name",editable:true},
                {name:"cover",editable:true,edittype:'file',formatter:function (value,option,rows) {
                        return "<img style='width:60px;height:40px;' src='${pageContext.request.contextPath}/banner/img/"+rows.cover+"'>";
                    }},
                {name:"description",editable:true},
                {name:"status",editable:true,edittype:'select',editoptions:{value:"正常:正常;冻结:冻结"}},
                {name:"createDate",editable:false}
            ],
          }).navGrid("#pager",{edit:true,add:true,del:true},{
                //控制修改
                closeAfterEdit:true,
                beforeShowForm:function (fmt) {
                    fmt.find("#cover").attr("disabled",true);
                }
            },{
            //控制添加
            closeAfterAdd:true,
            afterSubmit:function (data) {
                console.log(data);
                var status = data.responseJSON.status;
                var id = data.responseJSON.message;
                if(status){
                    $.ajaxFileUpload({
                        url:"${pageContext.request.contextPath}/banner/upload",
                        type:"post",
                        fileElementId:"cover",
                        data:{id:id},
                        success:function (response) {
                            //自动刷新jqgrid表格
                            $("#bannerTable").trigger("reloadGrid");
                        }
                    });
                }
                return "123";
            }
        });
    })
</script>
<!--创建表格-->
<table id="bannerTable"></table>
<!--分页-->
<div id="pager"></div>
