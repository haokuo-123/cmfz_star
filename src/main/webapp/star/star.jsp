<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!--页头-->

    <h1>所有明星</h1>

<%--jqgrid--%>
<script>
    $(function(){
        jQuery("#bannerTable").jqGrid(
            {
                height:300,
                styleUI:"Bootstrap",//使用bootstrap样式
                autowidth:true,//自动适应父容器
                url : '${pageContext.request.contextPath}/star/findAll',
                datatype : "json",
                editurl:'${pageContext.request.contextPath}/star/edit',
                colNames : [ 'id', '艺名', '真名', '照片', '性别','生日'],
                colModel : [
                    {name : 'id',hidden:true,editable:false},
                    {name : 'nickname',editable:true},
                    {name : 'realname',editable:true},
                    {name : 'photo',editable:true,edittype:'file',formatter:function (value,option,rows) {
            return "<img style='width:60px;height:40px' src='${pageContext.request.contextPath}/star/img/"+rows.photo+"'>";
                 }},
                    {name : 'sex',editable:true,edittype:'select',editoptions:{value:"男:男;女:女"}},
                    {name : 'bir',editable:true}
                ],
                rowNum : 3,
                rowList : [ 3, 10, 20, 30 ],
                pager : '#pager',
                sortname : 'id',
                viewrecords : true,
                subGrid : true,
                caption : "所有明星",

                subGridRowExpanded : function(subgrid_id, id) {
                    var subgrid_table_id, pager_id;
                    subgrid_table_id = subgrid_id + "_t";
                    pager_id = "p_" + subgrid_table_id;
                    $("#" + subgrid_id).html(
                        "<table id='" + subgrid_table_id  +"' class='scroll'></table>" +
                        "<div id='" + pager_id + "' class='scroll'></div>");
                    $("#" + subgrid_table_id).jqGrid(
                        {
                            url : "${pageContext.request.contextPath}/user/selectUsersByStarId?starId=" + id,
                            datatype : "json",
                            colNames : [ '编号', '用户名', '昵称', '头像','电话', '性别','地址','签名' ],
                            colModel : [
                                {name : "id"},
                                {name : "username"},
                                {name : "nickname"},
                                {name : "photo"},
                                {name : "phone"},
                                {name : "sex"},
                                {name : "province"},
                                {name : "sign"}
                            ],
                            styleUI:"Bootstrap",
                            rowNum : 2,
                            pager : pager_id,
                            autowidth:true,
                            height : '100%'
                        });
                    jQuery("#" + subgrid_table_id).jqGrid('navGrid',
                        "#" + pager_id, {
                            edit : false,
                            add : false,
                            del : false,
                            search:false
                        });
                },

            }).jqGrid('navGrid', '#pager', {
            add : true,
            edit : false,
            del : false,
            search:false
        },{},{
            closeAfterAdd:true,
            afterSubmit:function(data){
                var status = data.responseJSON.status;
                var message = data.responseJSON.message;
                if(status){
                    $.ajaxFileUpload({
                        url:"${pageContext.request.contextPath}/star/upload",
                        data:{id:message},
                        fileElementId:"photo",
                        type:"post",
                        success:function(){
                            $("#bannerTable").trigger("reloadGrid");
                        }
                    })
                }
                return "123";
            }
            });
    });
</script>
<!--创建表格-->
<table id="bannerTable"></table>
<!--分页-->
<div id="pager"></div>
