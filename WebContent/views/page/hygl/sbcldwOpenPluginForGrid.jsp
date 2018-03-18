<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>上报材料单位列表</title>
    <meta charset="utf-8" />
    <jsp:include page="/views/home/HeadGrid-Hy.jsp"></jsp:include>
    <script type="text/javascript">
        function clickCheckBoxSelect(idx) {
            $("input:checkbox[id='"+idx+"']").prop('checked', !$("input:checkbox[id='"+idx+"']").prop('checked'));
        }
        //被选中的节点信息
        function getCheckedNode(){
            //被选中的节点信息
            var resJOSN = [];
            //被选中的所有数据：
            $("input:checkbox[name='ids']:checked").each(function(){
                var item = {};
                var idEl = this.id;
                var id = this.value;
                var name = $("#name_"+idEl).val();
                item['id'] = id;
                item['name'] = name;
                resJOSN.push(item)
            })
            if(resJOSN != null && resJOSN.length > 0){
                return resJOSN;
            }else{
                return null;
            }
        }
    </script>
</head>
<body>
<div class="container">
    <div id="table" class="mt10">
        <div class="box span10 oh">
            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
                <tr id="grid_head_tr">
                    <th style="min-width:180px;width:15%;">序号</th>
                    <th style="min-width:180px;width:15%;">单位名称</th>
                    <th style="min-width:300px;width:40%;">联系人</th>
                    <th style="min-width:100px;width:10%;">联系人号码</th>
                </tr>
                <c:forEach items="${dwEntities}" var="item" varStatus="status">
                    <tr onclick="clickCheckBoxSelect('${status.index }')">
                        <td>
                            <input type="checkbox" name="ids" id="${status.index }" value="${item.id}"/>
                            <input type="hidden" name="names" id="name_${status.index }" value="${item.jgMc}"/>
                                ${status.index+1 }
                        </td>
                        <td>${item.jgMc }</td>
                        <td>${item.jgLxr }</td>
                        <td>${item.jgLxrHm }</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
</body>
</html>