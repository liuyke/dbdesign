(function($) {
	$.postForm = function(action,params) {
		var form = $("<form method='post' action='"+action+"'></form>");
		var fields = ""; 
		$.each(params, function(k,v) {
			fields += "<input type='hidden' name='"+k+"' value='"+v+"'/>";
		});
		console.info(fields);
		form.append(fields).appendTo("body").submit();
	};
})(jQuery);