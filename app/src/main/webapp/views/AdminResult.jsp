<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="AdminHeader.jsp" />

    <div class="container-fluid">
      <div class="px-5 mt-4">
        <nav aria-label="breadcrumb">
          <ol class="breadcrumb">
            <li class="breadcrumb-item text-primary"><a href="/admin/dashboard" class="decor-none"> Dashboard</a></li>
            <li class="breadcrumb-item active" aria-current="page"><a href="/admin/history" class="decor-none">Back</a></li>
          </ol>
        </nav>
      </div>

      <div class="row mt-3 px-5">
          <div class="col-12">
              <div class="h5 text-b">Result</div>
              <small class="text-secondary">Submissions of Interview takers</small>
          </div>
      </div>

      <div class="row mt-5 px-5">
          <div class="col-12">
              <div class="d-flex justify-content-between">
                  <div class="h5 text-b">Interview : <span class="text-primary">${test.tname}</span></div>
                  <div> <a href="/admin/excelExport/${test.tid}" class="btn bg-success text-white btn-sm"><i class="fas fa-download"></i> Download Result</a></div>
              </div>
          </div>
      </div>
      
      <div class="row px-5">
          <div class="col-12">
              <table class="table  table-striped">
                  <thead>
                    <tr>
                      <th scope="col">Name</th>
                      <th scope="col">Email</th>
                      <th scope="col">Score</th>
                      <th scope="col">Rank</th>
                    </tr>
                  </thead>
                  <tbody>
                    <c:forEach items="${users}" var="users" varStatus="loop">
                      <tr>
                        <td scope="row">${users.user.name}</td>
                        <td>${users.user.emailid}</td>
                        <td>${users.participation.score}</td>
                        <td>${loop.index+1}</td>
                      </tr>
                    </c:forEach>
                  </tbody>
              </table>
          </div>
      </div> 
    </div>
<jsp:include page="Footer.jsp" />