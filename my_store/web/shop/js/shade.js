// JavaScript Document
var pid;

function ShowDiv(show_div, bg_div, p_id) {
    document.getElementById(show_div).style.display = 'block';
    document.getElementById(bg_div).style.display = 'block';
    var bgdiv = document.getElementById(bg_div);
    bgdiv.style.width = document.body.scrollWidth;
    // bgdiv.style.height = $(document).height();
    $("#" + bg_div).height($(document).height());
    pid = p_id;
};

function CloseDiv(show_div, bg_div) {
    document.getElementById(show_div).style.display = 'none';
    document.getElementById(bg_div).style.display = 'none';
};


function ShowDiv_1(show_div, bg_div) {
    document.getElementById(show_div).style.display = 'block';
    document.getElementById(bg_div).style.display = 'block';
    var bgdiv = document.getElementById(bg_div);
    bgdiv.style.width = document.body.scrollWidth;
    // bgdiv.style.height = $(document).height();
    $("#" + bg_div).height($(document).height());
};


//关闭弹出层
function CloseDiv_1(show_div, bg_div) {
    document.getElementById(show_div).style.display = 'none';
    document.getElementById(bg_div).style.display = 'none';
};