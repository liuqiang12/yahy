<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="search-box">
    <div class="kv-item">
        <label>开始时间：</label>
        <div class="kv-item-content time-select-wrap">
            <div class="time-select">
                <input type="text" placeholder="开始时间">
                <i class="date-icon"></i>
            </div>
        </div>
    </div>
    <div class="kv-item">
        <label>结束时间：</label>
        <div class="kv-item-content time-select-wrap">
            <div class="time-select">
                <input type="text" placeholder="开始时间">
                <i class="date-icon"></i>
            </div>
        </div>
    </div>
    <div class="kv-item">
        <label>级别：</label>
        <div class="kv-item-content">
            <select>
                <option>选择类型</option>
                <option>收入</option>
                <option>支出</option>
            </select>
        </div>
    </div>
    <div class="kv-item">
        <label>关键字：</label>
        <div class="kv-item-content">
            <input class="keyword" type="text">
        </div>
    </div>
    <a href="javascript:;" class="button">查询</a>
</div>