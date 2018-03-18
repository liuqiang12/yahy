//扩展jquery获取url参数方法
(function ($) {
    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    }
})(jQuery);

function setBodyHeight() {
    $("#body").css("height", document.documentElement.clientHeight - 88);
    
    $("#body_content").css("width", $("#body").width() - $("#body_left").width()-1);
}

function setCurrentHeaderMenu() {
    var id = $.getUrlParam('id');
    var obj = $("#" + id);
    if (obj.length == 0) {
        obj = $("#home");
    }
    obj.removeClass("common");
    obj.addClass("select");

    $("#menu_item_" + obj.attr("id")).css("display", "block");
}

function setBgHover() {
    $("#header_login_info .link").mouseover(function () {
        $(this).addClass("li_hover");
    });
    $("#header_login_info .link").mouseout(function () {
        $(this).removeClass("li_hover");
    });

    $("#header_menu .common").mouseover(function () {
        $(this).addClass("common_hover");
    });
    $("#header_menu .common").mouseout(function () {
        $(this).removeClass("common_hover");
    });

    $("#body #body_left li").mouseover(function () {
        $(this).addClass("select");
    });
    $("#body #body_left li").mouseout(function () {
        $(this).removeClass("select");
    });

    $("#body_content .button").mouseover(function () {
        $(this).addClass("button_hover");
    });
    $("#body_content .button").mouseout(function () {
        $(this).removeClass("button_hover");
    });

    $("#body_content .content .row").mouseover(function () {
        $(this).addClass("row_hover");
    });
    $("#body_content .content .row").mouseout(function () {
        $(this).removeClass("row_hover");
    });
}

function addHeaderMenuClick() {
    $("#header_menu li").click(function () {
        var url = window.location.href;
        var array = url.split('?');
        var ar = array[0];
        ar = ar + "?id=" + $(this).attr("id");
        window.location.href = ar;
    });
}