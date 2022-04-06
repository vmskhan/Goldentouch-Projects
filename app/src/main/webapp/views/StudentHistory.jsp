<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="Header.jsp" />
      
    <div class="container-fluid mb-5">

        <!-- <div class="row mt-4 px-5">
            <div class="col-12">
                <div class="h5 text-b">My Test</div>
            </div>
            <div class="col-12">
                <p>List of all my completed tests</p>
            </div>
        </div> -->

        <div class="row mt-3 px-5">
            <div class="col-12">
                <div class="d-flex justify-content-between">
                    <div class="h5 text-b text-decoration-underline">Completed Interview</div>
                </div>
            </div>
        </div>

        <div class="row px-5">
          <c:if test="${completedTest.size() == 0}">
            <div class="text-center text-secondary mt-5">
              You don't have any completed Interviews.
            </div>
          </c:if>
          <c:if test="${completedTest.size() > 0}">
            <c:forEach items="${completedTest}" var="test">
              <div class="col-4 mt-4">
                <div class="shadow border rounded-3 border-4 border-primary">
                  <div class="card-header border-bottom border-dark text-img text-d" style="height: 120px;">
                      <a href="/user/studentresult/${test.test.tid}/${test.participation.pk.uid}" class="decor-none">
                          <h4>${test.test.tname}</h4>
                        </a>
                  </div>
                  <div class="px-3 py-2">
                    <div class="d-flex justify-content-between">
                      <div class="mt-1">
                        <small>Total Marks : ${test.test.totalMarks}</small>
                      </div>
                      <div class="mt-1">
                        <small>Marks Scored : <strong>${test.participation.score}</strong></small>
                      </div>
                    </div>
                    <div class="mt-1">
                        <small>Date : ${test.test.date}</small>
                    </div>
                    
                  </div>
                </div>
              </div>
            </c:forEach>          
          </c:if>
        </div>
      </div>
<jsp:include page="Footer.jsp" />