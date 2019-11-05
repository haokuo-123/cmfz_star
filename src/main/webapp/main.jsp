<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!--当前页面更好支持移动端-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>

    <%--引入bootstrap核心样式--%>
      <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/boot/css/bootstrap.min.css">
    <%--引入bootstrap核心样式--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/jqgrid/css/trirand/ui.jqgrid-bootstrap.css">
    <%--引入jquery核心js--%>
    <script src="${pageContext.request.contextPath}/statics/boot/js/jquery-3.4.1.min.js"></script>
    <%--引入boot核心js--%>
    <script src="${pageContext.request.contextPath}/statics/boot/js/bootstrap.min.js"></script>
    <%--引入jqgrid核心js--%>
    <script src="${pageContext.request.contextPath}/statics/jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>
     <script src="${pageContext.request.contextPath}/statics/jqgrid/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="${pageContext.request.contextPath}/statics/jqgrid/js/ajaxfileupload.js"></script>
    <script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/kindeditor-all-min.js"></script>
    <script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/lang/zh-CN.js"></script>
    <script charset="utf-8" src="${pageContext.request.contextPath}/statics/echarts/echarts.min.js"></script>
    <script>
        KindEditor.ready(function(K) {
            window.editor = K.create('#editor_id',{
                width : '1000px',
                //点击图片空间按钮时发送的请求
                fileManagerJson:"${pageContext.request.contextPath}",
                //展示图片空间按钮
                allowFileManager:true,
                //上传图片所对应的方法
                uploadJson:"${pageContext.request.contextPath}",
                //上传图片是图片的形参名称
                filePostName:"articleImg"
            });
        });
    </script>
</head>
<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">持明法州后台管理系统</a>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <li>
                <a href="#"> 欢迎: ${admin1.username}</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/admin/exit">安全退出</a>
            </li>
        </ul>
    </div>
    </div>
</nav>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-2 aa">
            <div class="panel-group" id="accordion" >

                <!--面板-->
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="userPanel">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#bannerLists" aria-expanded="true" aria-controls="collapseOne">
                                轮播图管理
                            </a>
                        </h4>
                    </div>
                    <div id="bannerLists" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                        <div class="panel-body">
                            <ul class="list-group">
                                <li class="list-group-item"><a href="javascript:$('#centerLayout').load('${pageContext.request.contextPath}/statics/banner.jsp');" id="btn">所有轮播图</a></li>
                            </ul>
                        </div>
                    </div>
                </div>

                <!--类别面板-->
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="editionPanel">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#editionLists" aria-expanded="true" aria-controls="collapseOne">
                                专辑管理
                            </a>
                        </h4>
                    </div>
                    <div id="editionLists" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                        <div class="panel-body">
                            <ul class="list-group">
                                <li class="list-group-item"><a href="javascript:$('#centerLayout').load('${pageContext.request.contextPath}/album/album.jsp');">专辑列表</a></li>
                            </ul>
                        </div>
                    </div>
                </div>

                <!--面板-->
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="bookPanel">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#bookLists" aria-expanded="true" aria-controls="collapseOne">
                                文章管理
                            </a>
                        </h4>
                    </div>
                    <div id="bookLists" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                        <div class="panel-body">
                            <ul class="list-group">
                                <li class="list-group-item"><a href="javascript:$('#centerLayout').load('${pageContext.request.contextPath}/article/article.jsp');">所有文章</a></li>
                            </ul>
                        </div>
                    </div>
                </div>


                <!--面板-->
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="orderPanel">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#orderLists" aria-expanded="true" aria-controls="collapseOne">
                                用户管理
                            </a>
                        </h4>
                    </div>
                    <div id="orderLists" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                        <div class="panel-body">
                            <ul class="list-group">
                                <li class="list-group-item"><a href="javascript:$('#centerLayout').load('${pageContext.request.contextPath}/user/user.jsp');">所有用户</a></li>
                                <li class="list-group-item"><a href="javascript:$('#centerLayout').load('${pageContext.request.contextPath}/user/user-echarts.jsp');">用户注册趋势</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <!--面板-->
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="starPanel">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#starLists" aria-expanded="true" aria-controls="collapseOne">
                                明星管理
                            </a>
                        </h4>
                    </div>
                    <div id="starLists" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                        <div class="panel-body">
                            <ul class="list-group">
                                <li class="list-group-item"><a href="javascript:$('#centerLayout').load('${pageContext.request.contextPath}/star/star.jsp');">所有明星</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-10" id="centerLayout">
            <div class="jumbotron" style="padding-top: 25px; margin-bottom: 15px;">
                <h5><span style="padding-left: 80px;">欢迎来到持明法州后台管理系统</span>!</h5>
            </div>
            <div>
                <img src="statics/zuguo.png" width="100%px" height="460px"/>
            </div>
        </div>
        <div id="footer" class="container">
            <nav class="navbar navbar-default navbar-fixed-bottom">
                <div class="navbar-inner navbar-content-center">
                    <p class="text-muted credit" style="text-align: center;padding-top: 12px;">
                        持明法州后台管理系统@百知教育2019.10.25
                    </p>
                </div>
            </nav>
    </div>
    </div>
</div>
</body>
</html>