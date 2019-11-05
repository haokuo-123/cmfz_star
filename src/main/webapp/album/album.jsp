<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!--页头-->

    <h1></h1>

<%--jqgrid--%>
<script>


    function getOptionValue() {
        var options = "";
        var i = 0;
        $.ajax({
            async: false,
            type: "post",
            url: "${pageContext.request.contextPath}/album/findAllStar",
            success: function (data) {
                for (i; i < data.length; i++) {
                    if (i != data.length - 1) {
                        options += data[i].nickname + ":" + data[i].nickname + ";";
                    } else {
                        options += data[i].nickname + ":" + data[i].nickname;
                    }
                }
            }
        });
        return options;
    }
    $(function(){
        jQuery("#bannerTable").jqGrid(
            {
                height:300,
                styleUI:"Bootstrap",//使用bootstrap样式
                autowidth:true,//自动适应父容器
                url : '${pageContext.request.contextPath}/album/findAll',
                datatype : "json",
                colNames : [ '编号', '专辑名称', '专辑作者', '专辑封面', '音乐数量', '专辑简介','创建时间' ],
                colModel : [
                    {name : 'id',hidden:true},
                    {name : 'title',editable:true},
                    {name : 'author',editable:true,edittype:"select",editoptions:{value:getOptionValue()}},
                    {name:"cover",editable:true,edittype:'file',formatter:function (value,option,rows) {
                            return "<img style='width:60px;height:40px;' src='${pageContext.request.contextPath}/album/img/"+rows.cover+"'>";
                        }},
                    {name : 'count',editable:false},
                    {name : 'brief',editable:true},
                    {name : 'createDate',editable:true,edittype:"date"}
                ],
                rowNum : 3,
                rowList : [ 3, 10, 20, 30 ],
                pager : '#pager',
                sortname : 'id',
                editurl:'${pageContext.request.contextPath}/album/edit',
                viewrecords : true,
                subGrid : true,
                caption : "专辑列表",
                subGridRowExpanded : function(subgrid_id, id) {
                    var subgrid_table_id, pager_id;
                    subgrid_table_id = subgrid_id + "_t";
                    pager_id = "p_" + subgrid_table_id;
                    $("#" + subgrid_id).html(
                        "<table id='" + subgrid_table_id  +"' class='scroll'></table>" +
                        "<div id='" + pager_id + "' class='scroll'></div>");
                    $("#" + subgrid_table_id).jqGrid(
                        {
                            url : "${pageContext.request.contextPath}/chapter/findAll?albumId=" + id,
                            editurl:"${pageContext.request.contextPath}/chapter/edit?albumId=" + id,
                            datatype : "json",
                            colNames : [ 'id', '名字', '歌手', '大小','时长', '创建日期','在线播放'],
                            colModel : [
                                {name : 'id',hidden:true,editable:false},
                                {name : 'name',editable:true,edittype:'file'},
                                {name: 'singer',editable:true},
                                {name : 'size'},
                                {name : 'duration'},
                                {name : 'createDate'},
                                {name : "operation",width:300,formatter:function (value,option,rows) {
                                        return "<audio controls>\n" +
                                            "  <source src='${pageContext.request.contextPath}/chapter/music/"+rows.name+"' >\n" +
                                            "</audio>";
                                    }}
                            ],
                            styleUI:"Bootstrap",
                            rowNum : 3,
                            pager : pager_id,
                            autowidth:true,
                            height : '100%'
                        });
                    jQuery("#" + subgrid_table_id).jqGrid('navGrid',
                        "#" + pager_id, {
                            edit : false,
                            add : true,
                            del : false,
                            search:false
                        },{},{
                            closeAfterAdd:true,
                            afterSubmit:function(data){
                                var status = data.responseJSON.status;
                                if(status){
                                    var cid = data.responseJSON.message;
                                    $.ajaxFileUpload({
                                        url:"${pageContext.request.contextPath}/chapter/upload",
                                        data:{id:cid,albumId:id},
                                        fileElementId:"name",
                                        type:"post",
                                        success:function(){
                                            $("#bannerTable").trigger("reloadGrid");
                                        }
                                    })
                                }
                                return "123";
                            }
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
                        url:"${pageContext.request.contextPath}/album/upload",
                        data:{id:message},
                        fileElementId:"cover",
                        type:"post",
                        success:function(){
                            $("#bannerTable").trigger("reloadGrid");
                        }
                    })
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
