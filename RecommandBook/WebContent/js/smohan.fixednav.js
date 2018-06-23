/*
 * 随滚动条固定导航到顶部插件
 * autho:Smohan
 * http://www.smohan.net
 */
;
(function ($) {
    $.fn.smohanfixednav = function (mtop, zindex) {
        var nav = $(this),
            isIE6 = 'undefined' == typeof(document.body.style.maxHeight),
            mtop = mtop,
            zindex = zindex,
            dftop = nav.offset().top - $(window).scrollTop(),
            dfleft = nav.offset().left - $(window).scrollLeft(),
            dfcss = new Array;
        dfcss[0] = nav.css("position"),
            dfcss[1] = nav.css("top"),
            dfcss[2] = nav.css("left"),
            dfcss[3] = nav.css("zindex"),
            $(window).scroll(function (e) {
                $(this).scrollTop() > dftop ? isIE6 ? nav.css({
                    position : "absolute",
                    top : eval(document.documentElement.scrollTop),
                    left : dfleft,
                    "z-index" : zindex
                }) : nav.css({
                    position : "fixed",
                    top : mtop + "px",
                    left : dfleft,
                    "z-index" : zindex
                }) : nav.css({
                    position : dfcss[0],
                    top : dfcss[1],
                    left : dfcss[2],
                    "z-index" : dfcss[3]
                })
            })
    }
})(jQuery)