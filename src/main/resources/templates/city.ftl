<html>
<head>
    <title>Mybatis分页插件 - 测试页面</title>
    <script src="${request.contextPath}/static/js/jquery-1.11.1.min.js"></script>
    <link href="${request.contextPath}/static/css/style.css" rel="stylesheet" type="text/css"/>
    <style type="text/css">
    </style>
    <script>
        $(function () {

        });
        function addOrEditPage(id) {
            if (id.length==0){
                $("#addOrUpdate input").val('新增');
            }else {
                $("#addOrUpdate input").val('修改');
            }
        }
    </script>
</head>
<body>
<div class="wrapper">
    <div class="middle">
        <h1 style="padding: 50px 0 20px;">城市列表</h1>

        <form action="${request.contextPath}/city/list" method="post">
            <table class="gridtable" style="width:100%;">
                <tr>
                    <th>城市名称：</th>
                    <td><input type="text" name="name"
                               value="<#if pageBean.conditionMap.name??>${pageBean.conditionMap.name}</#if>"/></td>
                    <td rowspan="2"><input type="submit" value="查询"/></td>
                </tr>
                <tr>
                    <td><input type="hidden" name="page" value="${pageBean.pageNum!0}"/></td>
                    <td><input type="hidden" name="rows" value="${pageBean.pageSize!10}"/></td>
                </tr>
            </table>
        </form>
    <#if pageBean??>
        <table class="gridtable" style="width:100%;">
            <tr class="pageDetail">
                <th style="width: 300px;">当前页号</th>
                <td>${pageBean.pageNum}</td>
                <th>页面大小</th>
                <td>${pageBean.pageSize}</td>
                <th>起始行号(>=)</th>
                <td>${pageBean.startRow}</td>
                <th>总结果数</th>
                <td>${pageBean.total}</td>
                <th>终止行号(<=)</th>
                <td>${pageBean.endRow}</td>
                <th>总页数</th>
                <td>${pageBean.pages}</td>
                <th>第一页</th>
                <td>${pageBean.firstPage}</td>
                <th>前一页</th>
                <td>${pageBean.prePage}</td>
                <th>下一页</th>
                <td>${pageBean.nextPage}</td>
                <th>最后一页</th>
                <td>${pageBean.lastPage}</td>
                <th>是否为第一页</th>
                <td>${pageBean.isFirstPage?c}</td>
                <th>是否为最后一页</th>
                <td>${pageBean.isLastPage?c}</td>
                <th>是否有前一页</th>
                <td>${pageBean.hasPreviousPage?c}</td>
                <th>是否有下一页</th>
                <td>${pageBean.hasNextPage?c}</td>
            </tr>
        </table>
        <table class="gridtable" style="width:100%;">
            <#if msg??>
                <tr style="color:red;">
                    <th colspan="5">${msg}</th>
                </tr>
            </#if>
        </table>
        <table class="gridtable" style="width:100%;">
            <thead>
            <tr>
                <th colspan="4">查询结果 - [<input type="button" value="新增" onclick="addOrEditPage('')"/>]</th>
            </tr>
            <tr>
                <th>ID</th>
                <th>城市名</th>
                <th>描述</th>
            </tr>
            </thead>
            <tbody>
                <#list pageBean.list as city>
                <tr>
                    <td>${city.id}</td>
                    <td><#if city.name??>${city.name}</#if></td>
                    <td><#if city.description??>${city.description}</#if></td>
                    <td style="text-align:center;">
                        [<input type="button" value="修改" onclick="addOrEditPage('${city.id}')"/>] -
                        [<a href="${request.contextPath}/city/delete/${city.id}">删除</a>]
                    </td>
                </tr>
                </#list>
            </tbody>
        </table>
    </#if>
    </div>
    <form action="" method="post" id="addOrUpdate">
        <table class="gridtable" style="width:100%;">
            <tr>
                <th>城市名称：</th>
                <td><input type="text" name="name"/></td>
                <th>城市描述：</th>
                <td><input type="text" name="description"/></td>
                <td rowspan="2"><input type="submit" value=""/></td>
            </tr>
        </table>
    </form>
    <div class="push"></div>
</div>
</body>
</html>