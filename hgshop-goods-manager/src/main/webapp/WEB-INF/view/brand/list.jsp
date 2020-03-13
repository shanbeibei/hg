<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%
	String path=request.getContextPath();
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script>
</head>
<body>
	<div>
	
	<input id="xxxxxxx" value="${queryName}"/>
	<button type="button" class="btn btn-primary" onclick="query()">查询 </button>
   		
	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#staticBackdrop">添加</button>
</div>	
	
	<!-- Modal -->
<div class="modal fade" id="staticBackdrop" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">添加品牌</h5>
        <button type="button" onclick="addProp()"> 添加属性
         </button>
         
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
        	
      </div>
      <div class="modal-body">
        	<form id="addspec">
        		 <div class="form-group">
    				<label for="specName">名称</label>
    				<input type="text" class="form-control" name="name" id="specName" aria-describedby="specNamelHelp">
    				<small id="specNamelHelp" class="form-text text-muted">请输入名称</small>
  				</div>
  				<div class="form-group form-group-proper">
    				<label for="inputAddress">属性值</label>
    				<input type="text" name="tirstChar" class="form-control" id="inputAddress" placeholder="1234 Main St">
  				</div>
  				
    			
        	</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" onclick="commitSpec()">提交</button>
      </div>
    </div>
  </div>
</div>



<!-- Modal -->
<div class="modal fade" id="staticBackdropUpdate" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">修改品牌</h5>
         
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
        	
      </div>
      <div class="modal-body">
        	<form id="updatespec">
        		 <input type="hidden" name="id" id="upId">
        		 <div class="form-group">
    				<label for="name">名称</label>
    				
  				</div>
  				<div class="form-group form-group-proper">
    				<label for="inputAddress">属性值</label>
    				<input type="text" name="firstChar"  class="form-control" id="first_char" placeholder="1234 Main St">
  				</div>
  				
    			
        	</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" onclick="commitUpdateSpec()">提交</button>
      </div>
    </div>
  </div>
</div>



	<table class="table">
	
	    <th>id</th>
	    <th>名称</th>
	    <th>详情</th>
	    <th>操作</th>
	  </tr>
	  <c:forEach items="${pageInfo.list}" var="spec">
	  <tr>
	    <td>${spec.id}</td>
	    <td>${spec.name}</td>
	    <td>${spec.firstChar}</td>
	    <td>
				<button type="button" class="btn btn-danger" onclick="delSec(${spec.id})">删除</button>
				<button type="button" class="btn btn-warning" onclick="openUpdateSpec(${spec.id})">修改</button>
			</td>
	  </tr>
	  </c:forEach>
	  <tr>
		<td colspan="3">
			<jsp:include page="../common/page.jsp"></jsp:include>
		</td>
	</tr>
	</table>

</body>
<script type="text/javascript">
function addProp(){
	$("#addspec").append('<div class="form-group">'+
				'<label for="inputAddress">属性值</label>'+
				'<input type="text" name="firstChar" class="form-control" id="inputAddress" placeholder="1234 Main St">'+
				'<button onclick="$(this).parent().remove()">删除</button>'+
				'</div>')
} 



//提交修改
function commitUpdateSpec(){
	
	
	//addspec
	var formData = new FormData($("#updatespec")[0]);
	$.ajax({url:"/brand/update",
		 // dataType:"json",
		  data:formData,
		  // 让jQuery 不要再提交数据之前进行处理
		  processData : false,
		  // 提交的数据不能加消息头
		  contentType : false,
		  // 提交的方式 
		  type:"post",
		  // 成功后的回调函数
		  success:function(data){
			 if(data=="success"){
				 alert("数据提交成功");
				 $('#staticBackdrop').modal('hide')
			 }else{
				 alert("数据保存失败")
			 }
			 
		  }
		  })
	
}
// 弹出修改的窗口
function openUpdateSpec(id){
	
	 //清空数据
	$(".form-group-proper").each(function(){
		$(this).remove();
	}) 
	$("#upspecName").val("")
	
	
	$.post("/brand/getbrand",{id:id},function(data){
		//品牌的id
		
		 $("#updatespec").append('<div class="form-group form-group-proper">'+
				 '<input type="text" name="id" value="'+data.id+'">' +
 				'<input type="text" name="name" value="'+data.name+'"class="form-control" id="inputAddress" >'+
				'<small id="specNamelHelp" class="form-text text-muted">请输入名称</small>'+
				'<input type="text" name="firstChar" value="'+data.firstChar+'"class="form-control" id="inputAddress" >'+
				'</div>') 
		$("#upId").val(data.id);
		$("#upspecName").val(data.name);
		$("#first_char").val(data.firstChar);
		
		$("#staticBackdropUpdate").modal('show')
	});
	
	
}

/**
* 删除品牌
*/
function delSec(id){
	if(confirm("您确认删除该条数据么？")){
		$.post("/brand/delbrand",{id:id},function(data){
			if(data="success"){
				alert("删除成功")
				refresh();
			}else{
				alert("删除失败")
			}
			
		});
	}else{
		return;
	}
}






/**
查询
*/
function query(){
var url="/brand/list?firstChar="+$("#xxxxxxx").val();
$("#main").load(url);
}

/**
* 分页 
*/
function goPage(pagenum){

var url="/brand/list?name="+$("#queryName").val()+'&page='+pagenum;
$("#main").load(url);
}

/**
* 刷新 而且保持查询条件和页码
*/
function refresh(){
	
	var url="/brand/list?name=${queryName}"+'&page=${pageInfo.pageNum}';
	$("#main").load(url);
}



//添加窗口的复位
function resetAddForm(){
	$(".form-group-proper").each(function(){
		$(this).remove();
	})
	addindex=1;
	$("#specName").val("")
	
} 

// 给模态框增加显示成成功后的事件  
$('#staticBackdrop').on('shown.bs.modal', function (e) {
	  // do something...
	resetAddForm();
})

// 给模态框增加关闭以后的事件  
$('#staticBackdrop').on('hidden.bs.modal', function (e) {
	  // do something...
	refresh();
})





function commitSpec(){
	var formData = new FormData($("#addspec")[0]);
	$.ajax({url:"/brand/add",
		  dataType:"json",
		  data:formData,
		  processData : false,
		  contentType : false,
		  type:"post",
		  success:function(data){
			 alert(data)  
		  }
		  })
	
}
</script>
</html>