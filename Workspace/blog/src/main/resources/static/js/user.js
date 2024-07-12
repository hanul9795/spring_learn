let index ={
	init: function(){
		$("#btn-save").on("click",()=>{ //function(){},()=>{} this를 바인딩하기위해서
			this.save();
		});//on - 첫번째 매개변수: 만약 해당 이밴트가 발생하면, 두번째 매개변수 함수가 실행
		
		$("#btn-login").on("click",()=>{ 
			this.login();
		});
 	},
 	
 	save: function(){
		//alert('user의 save함수 호출됨');
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}
		
		//console.log(data); javascript객체
		//console.log(JSON.stringify(data); json객체
		//ajax 호출시 비동기가 디폴트
		//ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해준다.
		$.ajax({
			//회원가입이 100초가 걸려도 밑부분 코드 실행 이때 밑 코드 실행중 ajax가 완료되면 밑 코드 진행중에도 done에 위치한 코드 실행
			type: "POST",
			url: "/blog/api/user",
			data: JSON.stringify(data),//이줄하고 아래줄은 세트
			contentType: "application/json; charset=utf-8",  //body 데이터가 어떤 타입인지(mime)
			dataType:"json" //요청을서버로해서 응답이 왔을떄 기본적으로 모든것이 문자열(생긴게 json이라면) -> javascript 오브젝트로 변경
		}).done( function(resp){
			alert("회원가입이 완료되었습니다.");
			console.log(resp);
			location.href="/blog";
			
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); //ajax 통신을 통해 3개의 데이터를 json으로 변경하여 insert요청
		
		
	},
	login: function(){
		//alert('user의 save함수 호출됨');
		let data = {
			username: $("#username").val(),
			password: $("#password").val()
		}
		$.ajax({
			type: "POST", //Get으로 하면 URL에 데이터가 남음으로 post로 전송
			url: "/blog/api/user/login",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",  
			dataType:"json" 
		}).done( function(resp){
			alert("로그인이 완료되었습니다.");
			console.log(resp);
			location.href="/blog";
			
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); //ajax 통신을 통해 3개의 데이터를 json으로 변경하여 insert요청
		
		
	}
}

index.init();