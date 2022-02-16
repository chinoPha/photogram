// (1) 회원정보 수정
function update(userid,event) {
	event.preventDefault(); //폼태그 액션을 막기!!
	
	let data = $("#profileUpdate").serialize();
	
	console.log(data);
	
	$.ajax({
		type:"put",
		url:'/api/user/${userid}',
		data:data,
		contentType:"application/x-www-form-urlencoded; charset=utf-8",
		dataType:"json"
	}).done(res=>{
		console.log("update 성공");
		location.href='/user/${userid}';
	}).fail(error=>{
		console.log("update 실패");
	});
}