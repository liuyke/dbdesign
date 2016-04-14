(function($) {
	$.fn.smartFloat = function() {
	    var position = function(element) {
	        $(window).scroll(function() {
	        	var bottom = element.offset().top + element.height();
	            var scrolls = $(this).scrollTop();
	            if (scrolls > bottom) {
	            }
	        });
	    };
	    return $(this).each(function() {
	        position($(this));                         
	    });
	};
})(jQuery);
