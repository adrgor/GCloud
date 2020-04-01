
$(document).ready(()=>{
	
	$('#testLi').click(()=>{
		$("#defaultSelect").trigger('click');
	})
	
	$('#selectFilesButton').click(()=>{
		$("#defaultSelect").trigger('click');
	})
	
	$('.err_msg').delay(500).fadeTo(1000, 0, ()=>{
		$(this).remove();
	});
	$(".err_msg").mouseover(function(e){
	    $(this).stop(true).fadeTo(100,1);
	}).mouseout(function(e){
	    $(this).fadeTo(1000,0, function(){
	    	$(this).remove();
	    });
	});
	
	$('.close_alert').click(()=>{
		$('.err_msg').remove();
	});
})

function downloadDoc(id){
	$.ajax({
		url: 'download_doc?id='+id,
		success: function(data){
			//alert(data)
		}
	})
}

function deleteDoc(id){
	//alert('You deleted record with id '+id)
	$.ajax({
		url: 'delete_doc?id='+id,
		success: function(data){
			alert('Usunieto dokument'+data)
			$('#file_id_'+id).remove();
		}
	})
}