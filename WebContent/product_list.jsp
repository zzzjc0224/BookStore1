<%@ page import="org.lq.bean.PageBean" language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


<title>bookStore列表</title>
<%--导入css --%>
<link rel="stylesheet" href="css/main.css" type="text/css" />
</head>

<body class="main">

	<jsp:include page="head.jsp" />
	<jsp:include page="menu_search.jsp" />

	<div id="divpagecontent">
		
		<table width="100%" border="0" cellspacing="0">
			<tr>

				<td>
					<div style="text-align: right; margin: 5px 10px 5px 0px">
						<a href="index.jsp">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;计算机&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;图书列表
					</div>

					<table cellspacing="0" class="listcontent">
						<tr>
							<td>
								<h1>商品目录</h1>
								<hr />
								<h1>计算机</h1>&nbsp;&nbsp;&nbsp;&nbsp;共100种商品
								<hr />
								<div style="margin-top: 20px; margin-bottom: 5px">
									<img src="images/productlist.gif" width="100%" height="38" />
								</div>
								
								<table cellspacing="0" class="booklist">

									<c:forEach begin="0" end="1" step="1" varStatus="vars">
										<tr>
											<c:forEach items="${pageBean.map}" var="entry" begin="${vars.index*4}" end="${4*(vars.index+1)-1}">
												
												<td>

													<div class="divbookpic">
														<p>
															<a href="product_info?id=${entry.value.id}"><img
																src="bookimage/${entry.value.imgurl}_b.jpg" width="115" height="129"
																border="0" /> </a>
														</p>
													</div>

													<div class="divlisttitle">
														<a href="product_info?id=${entry.value.id}">书名:${entry.value.name}<br />售价:<c:out value="${entry.value.price}"></c:out>
														</a>
													</div>
												</td>
											</c:forEach>
										</tr>

									</c:forEach>
									
								</table>
								
								<div class="pagination">
									<ul>


										<li class="nextpage" ><a href="showProductByPage?pageIndex=${pageBean.pageIndex==1? 1 : pageBean.pageIndex-1}&size=${pageBean.size}">上一页 &lt;&lt;</a></li>
										
										<c:forEach begin="0" end="${pageBean.numberOfPages>4?3:pageBean.numberOfPages-1}" varStatus="vs" >
											<c:choose>
												<c:when test="${pageBean.pageIndex==vs.index+1}">
													<li class="currentpage"><c:out value="${vs.index+1}"></c:out></li>
												</c:when>
												<c:otherwise>
													<li><a href="showProductByPage?pageIndex=${vs.index+1}&size=${pageBean.size}"><c:out value="${vs.index+1}"></c:out></a></li>
												</c:otherwise>
											</c:choose>
										
										</c:forEach>
										

										<li class="nextpage"><a class="" href="showProductByPage?pageIndex=${pageBean.pageIndex==pageBean.numberOfPages?pageBean.pageIndex:pageBean.pageIndex+1}&size=${pageBean.size}">下一页&gt;&gt;</a>
										</li>

									</ul>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>



	<jsp:include page="foot.jsp" />


</body>
</html>
