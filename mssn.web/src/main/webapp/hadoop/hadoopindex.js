function hadoop_createUser(urlData){
	var time = new Date();
	$.ajax({
		type : "POST",
		dataType : "html",
		url : "/mssn/redirectUrl",
		data : urlData+"&time="+time.getTime(),
		success : function(msg) {
			//alert(msg);
			$("#body_right").html("");
			$("#body_right").html(msg);
		},
		error : function(data) {
			alert("出错了：" + data);
		}
	});
}
function forwadUploadToHadoop(){
	var time = new Date();
	$.ajax({
		type : "get",
		dataType : "json",
		url : "/mssn/service/hdfs/hdfsService/getHdfsDir",
		data : "time="+time.getTime(),
		success : function(msg) {
			var json = msg;
			//debugger
		},
		error : function(data) {
			alert("出错了：" + data);
		}
	});
}