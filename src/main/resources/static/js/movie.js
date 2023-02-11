$.fn.extend({
    animateCss: function (animationName) {
        var animationEnd = 'webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend';

        $(this).addClass('animated ' + animationName).one(animationEnd, function() {
            $(this).removeClass('animated ' + animationName);
        }).one(animationEnd, function() {
            $(this).addClass('displaynone');
        });

    }
});

// $('h1').animateCss('pulse');

$('.scrollbanner').mouseover(function() {
    $('.scrollbanner').animateCss('rubberBand');
});

setTimeout(function(){
    randomAnimate();
}, 3000);

function randomAnimate () {
    var ranNum = Math.floor((Math.random() * 7) + 1);
    if (ranNum == 1) {
        $('.scrollbanner').animateCss('tada');
    } else if (ranNum == 2) {
        $('.scrollbanner').animateCss('swing');
    } else if (ranNum == 3) {
        $('.scrollbanner').animateCss('shake');
    } else if (ranNum == 4) {
        $('.scrollbanner').animateCss('pulse');
    } else if (ranNum == 5) {
        $('.scrollbanner').animateCss('bounce');
    } else if (ranNum == 6) {
        $('.scrollbanner').animateCss('jello');
    } else {}
    setTimeout(function(){
        randomAnimate();
    }, 3000);
}


function getRandomIntInclusive(min, max) {
    min = Math.ceil(min);
    max = Math.floor(max);
    return Math.floor(Math.random() * (max - min + 1)) + min;
}