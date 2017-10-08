
function log(x) { console && console.log(x); }

$(window).bind('load', function(){
    log('Im loaded');
    $('h1').click(function(){
        var elem = $(this);
        var tColor = elem.css("color");
        log(tColor);
        var color = (tColor != "rgb(255, 0, 0)" ? "red" : "black") ;
        $(this).css({color:color});
    });
});
