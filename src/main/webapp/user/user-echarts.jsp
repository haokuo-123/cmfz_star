<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<script>
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '上半年用户注册趋势'
        },
        tooltip: {},
        legend: {
            data:['男','女']
        },
        xAxis: {
            data: [1,2,3,4,5,6]
        },
        yAxis: {},
        series: [{
            name: '男',
            type: 'line',//bar:柱状图
            data: [23,54,65,21,56,67]
        },{
            name: '女',
            type: 'line',//bar:柱状图
            data: [23,54,65,21,56,12]
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);

 $.ajax({
          url:"${pageContext.request.contextPath}/user/",
            type:"get",
            datatype:"json",
            success:function (data) {
                myChart.setOption({
                    xAxis: {
                        data: data.name
                    },
                    series: [{
                        name: '男',
                        type: 'line',//bar:柱状图
                        data: {}
                    },{
                        name: '女',
                        type: 'line',//bar:柱状图
                        data: data.nv
                    }]
                });
            }
        })

</script>
<body>
    <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
    <div id="main" style="width: 600px;height:400px;"></div>
</body>
</html>