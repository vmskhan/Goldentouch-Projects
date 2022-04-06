<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="AdminHeader.jsp" />

    <div class="container-fluid mb-5">

        <div class="row mt-4 px-5">
          <div class="col-12">
              <div class="h5 text-b">My Interview</div>
              <small class="text-secondary">List of all my interviews</small>
          </div>
        </div>

        <div class="row mt-3 px-5">
            <div class="col-12">
                <div class="d-flex justify-content-between">
                    <div class="h5 text-b">Current Interview</div>
                    <div>
                      <button class="btn bg-r text-white btn-sm" data-bs-toggle="offcanvas" data-bs-target="#offcanvasRight" aria-controls="offcanvasRight">
                        <i class="fas fa-plus"></i> Create Interview
                      </button>
                    </div>
                </div>
            </div>
        </div>

        <div class="row px-5">
          <c:if test="${tests.size() == 0 }">
            <div class="text-center text-secondary mt-5">
              You don't have any Interviews yet.
            </div>
            <div class="text-center text-secondary mt-3">
              <button class="btn bg-r text-white btn-sm" data-bs-toggle="offcanvas" data-bs-target="#offcanvasRight" aria-controls="offcanvasRight">
                <i class="fas fa-plus"></i> Create Interview
              </button>
            </div>
          </c:if>
          <c:if test="${tests.size() > 0 }">
          <c:forEach items="${tests}" var="test">
              <div class="col-4 mt-4">
                <div class="shadow">
                  <div class="card-header text-img text-d" style="height: 120px;">
                   <a href="/admin/test/${test.tid}" class="decor-none">
                     <h4>${test.tname}</h4>
                   </a>
                  </div>
                  <div class="px-3 py-2">
                    <div class="d-flex justify-content-between">
                      <div>
                         <small>Date : <strong>${test.date}</strong></small>
                      </div>
                      <div>
                        <c:if test="${test.state == 'start'}">
                          <span class="badge rounded-pill bg-r">live</span>
                        </c:if>
                        <c:if test="${test.needPayment == 'true'}">
                          <span class="badge rounded-pill bg-y">Rs ${test.amount}</span>
                        </c:if>
                      </div>
                    </div>
                    <div class="mt-1">
                      <small>Start Time : ${test.start_time}</small>
                    </div>
                    <div class="mt-1">
                       <small>Duration : ${test.duration} hr</small>
                    </div>
                    <div class="mt-1">
                       <small>Total Marks : <c:if test="${test.state == 'start'}">${test.totalMarks}</c:if>
                        <c:if test="${test.state != 'start'}">-</c:if>
                      </small>
                    </div>
                  </div>
                </div>
              </div>
            </c:forEach>
          </c:if>
          
        </div>

        

    <!-- MODAL -->
    <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasRight" aria-labelledby="offcanvasRightLabel">
        <div class="offcanvas-header">
          <h5 id="offcanvasRightLabel text-b">Add Test</h5>
          <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
        </div>
        <div class="offcanvas-body">
            <spring:form name="dashboard" method="POST" action="/admin/dashboard" modelAttribute="test">
                <div class="mb-3">
                    <label for="testName" class="form-label">Interview Name</label>
                    <spring:input placeholder="Eg. Aptitude-Kec" type="text" path="tname" class="form-control form-control-sm" />
                </div>
                <div class="mb-3">
                    <label for="date" class="form-label">Date</label>
                    <spring:input type="date" path="date" class="form-control form-control-sm" id="date" />
                </div>
                <div class="mb-3">
                    <label for="time" class="form-label">Start Time</label>
                    <spring:input type="time" path="start_time" class="form-control form-control-sm" id="time" />
                </div>
                <div class="mb-3">
                    <label for="duration" class="form-label">Duration (Eg. 1.30) </label>
                    <spring:input type='text' placeholder="eg. 1 hr" path="duration" class="form-control form-control-sm" id="duration" />
                </div>
                <div class="mb-3">
                  <label for="duration" class="form-label">Payment (in Rs)</label>
                  <spring:input type='text' path="amount" class="form-control form-control-sm" id="duration" />
                  <small class="mt-1 text-secondary">Leave empty if you don't want have any fee to take this Interview</small>
                </div>

                <div class="mb-3">
                  <label for="participant_id" class="form-label">Interviewee</label>
                  <spring:select path="pid" class="form-control form-control-sm" id="participant_id" >
                    <c:forEach var="user" items="${participants}">
                      <spring:option value="${user.uid}" label="${user.name}"></spring:option>
                    </c:forEach> 
                  </spring:select>
                </div>

                <button type="submit" class="btn bg-success text-white btn-sm px-5">Create Interview</button>
              </spring:form>
        </div>
      </div>
  </div>
  
<jsp:include page="Footer.jsp" />