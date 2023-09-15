$(document).ready(function() {
	$('.btn-xoa').click(function() {
		var id = $(this).attr("id-task")
		var This = $(this)
		$.ajax({
			method: "GET",
			url: `http://localhost:8080/crm_project22/api/task/delete?id=${id}`,
			//data: { name: "John", location: "Boston" }
		})
			.done(function(result) {
				console.log(result, "data",result.data)
				if(result.data==true){
					This.parent().parent().remove();
					//This.closest('tr').remove();
				}
			});
	})
})