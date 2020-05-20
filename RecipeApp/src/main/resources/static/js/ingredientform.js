/*
function doAlert(){
	alert("Just check to js integration");
}
*/
$(function(){
	//alert("FormJS integrated");
	$("#ingredientSubmit").submit( function(e){
		
		e.preventDefault();
		var frm = $("#ingredientSubmit");
		var data ={};
		$.each(this, function(i, v){
			var input = $(v) ;
			data[input.attr("name")] = input.val();
			delete data["undefined"];
		});
		sendRequestData( frm, data, "ingredient" )
	});
});

function sendRequestData( frm, data, obj ){
	$.ajax({
		contentType : "application/json; charset=utf-8",
		type : "post",
		url : "/recipe/82/ingredient",
		dataType : "json",
		data : JSON.stringify(data),
		success : function(data,textStatus, jqXHR) {
			alert(data) ;
		},
		error: function(jqXHR, textStatus, errorThrown)
		{
		    alert(textStatus); 
		}
	});
}
