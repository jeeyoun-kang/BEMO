$(document).ready(function () {
    // typing animation
    (function ($) {
        $.fn.writeText = function (content) {
            var contentArray = content.split(""),
                current = 0,
                elem = this;
            setInterval(function () {
                if (current < contentArray.length) {
                    elem.text(elem.text() + contentArray[current++]);
                }
            }, 80);
        };
    })(jQuery);

    // input text for typing animation
    //$("#holder").writeText("BEhind + MOvie");
    $("#holder").writeText("movie review and recommendation web service");


    // initialize wow.js
    new WOW().init();


    $(document).ready(main);
});
