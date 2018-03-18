<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8" />
    <jsp:include page="/views/home/Head-hygl.jsp"></jsp:include>
    <script type="text/ecmascript">
        $(document).ready(function () {
            setBodyHeight();
            setCurrentHeaderMenu();
            setBgHover();
            addHeaderMenuClick();
            $('dd').click(function(){
                if($(this).find('a').text()=='已报'||$(this).find('a').text()=='未报'||$(this).find('a').text()=='已办结'||$(this).find('a').text()=='未办结'){
                    $('#body_content .search-box .title').html($(this).find('a').html()+'会议');
                }else{
                    $('#body_content .search-box .title').html($(this).find('a').html());
                }
            })
        });

        $(window).resize(function () {
            setBodyHeight();
        });
        function removeIframeMenuAct(){
        	$("dl.add_meet dt").each(function(){
        		$(this).removeClass("active");
        	})
        	$("dl.add_meet dd").each(function(){
        		$(this).removeClass("active");
        	})
        }
        function addIframeMenuClass(obj){
        	$("#"+obj).addClass("active");
        }
    </script>

</head>
<body style="overflow:hidden;">
<jsp:include page="/views/home/Hygl-Head.jsp"></jsp:include>
<div id="body">
    <div id="body_left">
        <div class="menu">
            <p class="title">会议管理系统</p>
            <dl class="meeting">
                <dt class="cjhy"><a href="javascript:void(0)" target="rightFrame"><i></i>参加会议</a></dt>
                <dd class="active wb"><a href="<%=request.getContextPath() %>/hyGlController/cjhyRecordPageList?flagStatus=1&sbZtInt=0" target="rightFrame"><i></i>未报</a></dd>
                <dd class="yb"><a href="<%=request.getContextPath() %>/hyGlController/cjhyRecordPageList?flagStatus=1&sbZtInt=1" target="rightFrame"><i></i>已报</a></dd>
            </dl>
            <dl class="add_meet">
                <dt class="fqhy"><a href="javascript:;" target="rightFrame"><i></i>发起会议</a></dt>
                <dd class="wbj" id="hygl_wbj_init_id"><a href="<%=request.getContextPath() %>/hyGlController/fqhyRecordPageList?flagStatus=2&bjBzInt=0" target="rightFrame"><i></i>未办结</a></dd>
                <dd class="ybj"><a href="<%=request.getContextPath() %>/hyGlController/fqhyRecordPageList?flagStatus=2&bjBzInt=1" target="rightFrame"><i></i>已办结</a></dd>
                <dd class="count"><a href="<%=request.getContextPath() %>/hyGlController/menuLink/meet_count/20"  target="rightFrame"><i></i>会议统计</a></dd>
                <dd class="add"><a href="<%=request.getContextPath() %>/hyGlController/createHyModel" target="rightFrame" style="border:none;"><i></i>新建会议</a></dd>
            </dl>
            <dl class="log">
                <dt class="fqhy"><a href="<%=request.getContextPath() %>/hyGlController/logpage" target="rightFrame"><i></i>系统日志</a></dt>
            </dl>
        </div>

    </div>
    <div id="body_content">
        <div class="search-box">
            <h2 class="title">未报会议</h2>
        </div>
        <iframe id="rightFrame" title="rightFrame" name="rightFrame" src="<%=request.getContextPath() %>/hyGlController/cjhyRecordPageList?flagStatus=1&sbZtInt=0" frameborder="0"></iframe>
    </div>
</div>
<jsp:include page="/views/home/Hygl-Footer.jsp"></jsp:include>
<div id="footer">
    <span class="left">雅安市会议管理系统</span>
    <span class="right">维护电话：0835-2852058 0835-2852119 0835-2852181</span>
</div>
<script>
    $(function(){
        $('.menu dd').click(function(){
            $(this).addClass('active').siblings().removeClass('active');
            $(this).parent().siblings().find('dd').removeClass('active');
        });
        /*$('.menu dt').click(function(){
            $('dd').removeClass('active');
        });*/
        setSize();
        $(window).resize(function(){
            setSize();
        })
    });
    function setSize(){
        $('#rightFrame').css({
            height:$('#body_content').height()-40,
            width:$('#body_content').width()
        })
    }
</script>
</body>
</html>
