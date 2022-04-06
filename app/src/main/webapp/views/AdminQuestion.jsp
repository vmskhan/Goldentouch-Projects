<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="AdminHeader.jsp" />

    <div class="container-fluid">      
        <div class="px-5 mt-4">
          <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
              <li class="breadcrumb-item "><a href="/admin/dashboard" class="decor-none">Dashboard</a></li>
              <li class="breadcrumb-item active" aria-current="page">Interview</li>
            </ol>
          </nav>
        </div>
        <div class="row mt-2 px-5">
            <div class="col-12">
                <div class="d-flex justify-content-between">
                  <div>
                    <div class="h5 text-b">Interview : <span class="text-primary">${test.tname}</span></div>
                    <small class="text-secondary">Create and edit questions for the Interview</small>
                  </div>
                    <c:if test="${test.state == 'edit'}">
                      <div>
                        <a href="/admin/test/${test.tid}/start">
                          <button class="btn bg-r text-white btn-sm"><i class="fab fa-cloudscale"></i> Start Interview</button>
                        </a>
                      </div>
                    </c:if>
                    <c:if test="${test.state == 'start'}">
                      <div>
                        <a href="/admin/test/${test.tid}/end">
                          <button class="btn bg-r text-white  btn-sm"><i class="fab fa-cloudscale"></i> End Interview</button>
                        </a>
                        <a href="/admin/startMeet">
                          <button class="btn bg-info text-white  btn-sm"><i class="far fa-handshake"></i> Start Zoom Meeting</button>
                        </a>
                      </div>
                    </c:if>
                </div>
            </div>
        </div>

        <div class="row mt-5 px-5">
            <div class="col-12">
                <div class="d-flex justify-content-between">
                    <div class="h5 text-b">Questions</div>
                    <c:if test="${test.state == 'edit'}">
                      <div><button class="btn text-white bg-success btn-sm" data-bs-toggle="offcanvas" data-bs-target="#qformat_a" aria-controls="offcanvasRight"> <i class="fas fa-plus"></i> Add Question</button></div>
                    </c:if>
                </div>
            </div>
        </div>

        <div class="row px-5">
            <div class="col-12">
                <table class="table table-striped">
                    <thead >
                      <tr >
                        <th scope="col" >S.No</th>
                        <th scope="col" >Question</th>
                        <th scope="col" class="w-25">Image</th>
                        <th scope="col" >Marks</th>
                        <c:if test="${test.state == 'edit'}">
                          <th scope="col" >Action</th>
                        </c:if>
                      </tr>
                    </thead>
                    <tbody>
                      <c:forEach items="${questions}" var="q" varStatus="loop" >
                        <tr >
                          <td scope="row" >
                            ${loop.index+1}
                          </td>
                          <td >${q.questionText}</td>
                          <td>
                            <c:choose>
                            <c:when test="${q.questionImage!=null}">
                            <img class="w-75" src="/img/qs${q.qid}.jpg">
                          </c:when>
                          <c:otherwise>
                            No Image
                          </c:otherwise>
                        </c:choose>
                          </td>
                          <td >${q.mark}</td>
                          <c:if test="${test.state == 'edit'}">
                            <td colspan="2" >
                              <!-- <button class="btn btn-primary btn-sm "><i class="fas fa-edit"></i> edit</button> -->
                              <span class="px-2"></span>
  
                              <a href="/admin/test/${test.tid}/qn/${q.qid}/d">
                                <button class="btn btn-link btn-sm text-r decor-none"><i class="fas fa-trash"></i> delete</button>
                              </a>
                            </td>  
                          </c:if>
                        </tr>
                      </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>


        <!-- MODAL MCQ-1 correct option -->
        <div class="offcanvas offcanvas-end" tabindex="-1" id="qformat_a" aria-labelledby="offcanvasRightLabel">
            <div class="offcanvas-header flex-wrap">
               
              <div class="navbar navbar-dark bg-success container-fluid justify-content-between mb-2">
                <button class="btn text-white btn-sm" data-bs-toggle="offcanvas" data-bs-target="#qformat_a" aria-controls="offcanvasRight"> 
                   Objective-a
                </button>
                <div class="vr text-white"></div>
                  <button class="btn text-white btn-sm" data-bs-toggle="offcanvas" data-bs-target="#qformat_b" aria-controls="offcanvasRight"> 
                     Objective-b
                  </button>
                  <div class="vr text-white"></div>
                  <button class="btn text-white btn-sm" data-bs-toggle="offcanvas" data-bs-target="#qformat_c" aria-controls="offcanvasRight"> 
                    subjective-a
                 </button>
              </div>
              <h5 id="offcanvasRightLabel text-b">Add Objective-Type A</h5>
              <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close" ></button>
            </div>
            <div class="offcanvas-body">
             
                    
                <spring:form name="question" method="POST" action="/admin/test/${test.tid}/qn" modelAttribute="question" enctype="multipart/form-data">
                  
                    <div class="mb-1">
                      <label for="question" class="form-label">Question</label>
                      <textarea class="form-control form-control-sm" name="questionText"></textarea>
                      <div class="d-none">
                        <input class="form-control m-2" type="file" name="images">
                      </div>
                      <div class="">
                        <input type="checkbox" class="form-control-input mx-1" name="toggletext" checked onClick="toggleView(this,'textarea');">
                        <label class="form-control-label" for="toggle1">Text</label>
                        <input type="checkbox" class="form-control-input mx-1" name="Toggleimage" onClick="toggleView(this,'div');">
                        <label class="form-control-label" for="Toggle2">Image</label>
                      </div>
                    </div>
                    <div>
                      <button class="btn text-white bg-success btn-sm" type="button" onClick="addOption(this);">
                        <i class="fas fa-plus"></i> Add Option
                      </button>
                       <div class="mb-1">
                          <label for="option-1" class="form-label">Option 1</label>
                          <input type="text" class="form-control form-control-sm m-2" name="options" />
                          <div class="d-none">
                            <input class="form-control m-2" type="file" name="images">
                          </div>
                          <div class="">
                            <input type="checkbox" class="form-control-input mx-1" name="toggletext" checked onClick="toggleView(this,'input');">
                            <label class="form-control-label" for="toggle1">Text</label>
                            <input type="checkbox" class="form-control-input mx-1" name="Toggleimage" onClick="toggleView(this,'div');">
                            <label class="form-control-label" for="Toggle2">Image</label>
                          </div>
                      </div>
                  </div> 
                    
                    <div class="mb-3">
                      <label for="answer" class="form-label">Correct Option</label>
                        <select class="form-select form-control-sm answer" name="answerText">
                            <option value="0" label="1" />
                        </select>
                        
                    </div>
                    <div class="mb-4">
                      <label for="mark" class="form-label">Mark</label>
                      <input type="text" class="form-control form-control-sm" name="mark" />
                  </div>
                    <input type="hidden" name="questionFormat" value="obj-a"/>
                    <button type="submit" class="btn bg-success text-white btn-sm px-5" onclick="resetCounter();"><i class="fas fa-save"></i> Save</button>
                  </spring:form>
            </div>
          </div>
    </div>

    <!-- MODAL MCQ-many correct option -->
    <div class="offcanvas offcanvas-end" tabindex="-1" id="qformat_b" aria-labelledby="offcanvasRightLabel">
      <div class="offcanvas-header flex-wrap">
        <div class="navbar navbar-dark bg-success container-fluid justify-content-between mb-2">
          <button class="btn text-white btn-sm" data-bs-toggle="offcanvas" data-bs-target="#qformat_a" aria-controls="offcanvasRight"> 
             Objective-a
          </button>
          <div class="vr text-white"></div>
            <button class="btn text-white btn-sm" data-bs-toggle="offcanvas" data-bs-target="#qformat_b" aria-controls="offcanvasRight"> 
               Objective-b
            </button>
            <div class="vr text-white"></div>
            <button class="btn text-white btn-sm" data-bs-toggle="offcanvas" data-bs-target="#qformat_c" aria-controls="offcanvasRight"> 
              subjective-a
           </button>
        </div>
        <h5 id="offcanvasRightLabel text-b">Add Objective-Type B</h5>
        <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
      </div>
      <div class="offcanvas-body">
        
          <spring:form name="question" method="POST" action="/admin/test/${test.tid}/qn" modelAttribute="question" enctype="multipart/form-data">
            <div class="mb-1">
              <label for="question" class="form-label">Question</label>
              <textarea class="form-control form-control-sm" name="questionText"></textarea>
              <div class="d-none">
                <input class="form-control m-2" type="file" name="images">
              </div>
              <div class="">
                <input type="checkbox" class="form-control-input mx-1" name="toggletext" checked onClick="toggleView(this,'textarea');">
                <label class="form-control-label" for="toggle1">Text</label>
                <input type="checkbox" class="form-control-input mx-1" name="Toggleimage" onClick="toggleView(this,'div');">
                <label class="form-control-label" for="Toggle2">Image</label>
              </div>
            </div>
            <div>
              <button class="btn text-white bg-success btn-sm" type="button" onClick="addOption1(this);">
                <i class="fas fa-plus"></i> Add Option
              </button>
               <div class="mb-1">
                  <label for="option-1" class="form-label">Option 1</label>
                  <input type="text" class="form-control form-control-sm m-2" name="options" />
                  <div class="d-none">
                  <input class="form-control m-2" type="file" name="images">
                </div>
                  <div class="">
                    <input type="checkbox" class="form-control-input mx-1" name="toggletext" checked onClick="toggleView(this,'input');">
                    <label class="form-control-label" for="toggle1">Text</label>
                    <input type="checkbox" class="form-control-input mx-1" name="Toggleimage" onClick="toggleView(this,'div');">
                    <label class="form-control-label" for="Toggle2">Image</label>
                  </div>
              </div>
          </div>
              <div class="answer mb-3">
                <label for="answer" class="form-label">Correct Options</label>
                <div class="d-inline">
                <input type="checkbox" class="mx-2" value="0" label="1" name="answerText" />
                <label>1</label>
              </div>
                
              </div> 
              <div class="mb-4">
                <label for="mark" class="form-label">Mark</label>
                <input type="text"  class="form-control form-control-sm" name="mark" />
            </div>
            <input type="hidden" name="questionFormat" value="obj-b"/>
              <button type="submit" class="btn bg-success text-white btn-sm px-5" onclick="resetCounter1();"><i class="fas fa-save"></i> Save</button>
            </spring:form>
      </div>
    </div>

<!-- Modal subjective-text answer -->
<div class="offcanvas offcanvas-end" tabindex="-1" id="qformat_c" aria-labelledby="offcanvasRightLabel">
  <div class="offcanvas-header flex-wrap">
    <div class="navbar navbar-dark bg-success container-fluid justify-content-between mb-2">
      <button class="btn text-white btn-sm" data-bs-toggle="offcanvas" data-bs-target="#qformat_a" aria-controls="offcanvasRight"> 
         Objective-a
      </button>
      <div class="vr text-white"></div>
        <button class="btn text-white btn-sm" data-bs-toggle="offcanvas" data-bs-target="#qformat_b" aria-controls="offcanvasRight"> 
           Objective-b
        </button>
        <div class="vr text-white"></div>
        <button class="btn text-white btn-sm" data-bs-toggle="offcanvas" data-bs-target="#qformat_c" aria-controls="offcanvasRight"> 
          subjective-a
       </button>
    </div>
    <h5 id="offcanvasRightLabel text-b">Add Subjective-Type A</h5>
    <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
  </div>
  <div class="offcanvas-body">
    
      <spring:form name="question" method="POST" action="/admin/test/${test.tid}/qn" modelAttribute="question" enctype="multipart/form-data">
        <div class="mb-1">
          <label for="question" class="form-label">Question</label>
          <textarea class="form-control form-control-sm" name="questionText"></textarea>
          <div class="d-none">
            <input class="form-control m-2" type="file" name="images">
          </div>
          <div class="">
            <input type="checkbox" class="form-control-input mx-1" name="toggletext" checked onClick="toggleView(this,'textarea');">
            <label class="form-control-label" for="toggle1">Text</label>
            <input type="checkbox" class="form-control-input mx-1" name="Toggleimage" onClick="toggleView(this,'div');">
            <label class="form-control-label" for="Toggle2">Image</label>
          </div>
        </div>
          <div class="answer mb-3">
            <label for="answer" class="form-label">Correct answer</label>
            <textarea class="form-control form-control-sm" name="answerText"></textarea>
            <div class="d-none">
              <input class="form-control m-2" type="file" name="images">
            </div>
            <div class="">
              <input type="checkbox" class="form-control-input mx-1" name="toggletext" checked onClick="toggleView(this,'textarea');">
              <label class="form-control-label" for="toggle1">Text</label>
              <input type="checkbox" class="form-control-input mx-1" name="Toggleimage" onClick="toggleView(this,'div');">
              <label class="form-control-label" for="Toggle2">Image</label>
            </div>            
          </div> 
          <div class="mb-4">
            <label for="mark" class="form-label">Mark</label>
            <input type="text"  class="form-control form-control-sm" name="mark" />
        </div>
          <input type="hidden" name="questionFormat" value="sub-a"/>
          <button type="submit" class="btn bg-success text-white btn-sm px-5"><i class="fas fa-save"></i> Save</button>
      </spring:form>
  </div>
</div>
</div>


<!-- template object -->
<div class="mb-1 d-none" id="templateOptionObject">
  <label for="option-x" class="form-label">Option x</label>
  <input type="text" class="form-control form-control-sm m-2" name="options" />
  <div class="d-none">
  <input class="form-control m-2"type="file" name="images">
  <!-- <label  for="image-x">Choose Image</label> -->
  </div>
  <div class="">
    <input type="checkbox" class="form-control-input mx-1" name="toggletext" checked onClick="toggleView(this,'input');">
    <label class="form-control-label" for="toggletext">Text</label>
    <input type="checkbox" class="form-control-input mx-1" name="Toggleimage" onClick="toggleView(this,'div');">
    <label class="form-control-label" for="Toggleimage">Image</label>
  </div>
</div>
<!--option label-->
<div class="d-none" id="templateCheckbox"> 
  <input type="checkbox" class="mx-2" value="0" name="answerText"/>
  <label>x</label>
</div>

<script>
  var counter=2,counter1=2;
  function resetCounter(){
    counter=2;
  }
  function resetCounter1()
  {
    counter1=2;
  }
  function addOption(ele)
  {
    
    console.log(counter);
    var x=document.getElementById("templateOptionObject");
    var y=x.cloneNode(true);
    y.classList.toggle("d-none");
    y.removeAttribute("id");
    var label=y.querySelectorAll("label");
    label[0].setAttribute("for","option-"+counter);
    // label[1].setAttribute("for","image-"+counter);
    label[0].textContent="option "+counter;
    var inputTag=y.querySelectorAll("input");
    inputTag[0].setAttribute("name","options");//+counter);
    inputTag[1].setAttribute("name","images");//+counter);
    ele.parentElement.appendChild(y);
    var option=document.createElement("option");
    option.setAttribute("label",counter);
    option.setAttribute("value",counter-1);
    
    ele.parentElement.parentElement.getElementsByClassName("answer")[0].appendChild(option);
    console.log("successful");
    counter++;

  }

  function addOption1(ele)
  {
    
    console.log(counter1);
    var x=document.getElementById("templateOptionObject");
    var y=x.cloneNode(true);
    y.classList.toggle("d-none");
    y.removeAttribute("id");
    var label=y.querySelectorAll("label");
    label[0].setAttribute("for","option-"+counter1);
    // label[1].setAttribute("for","image-"+counter);
    label[0].textContent="option "+counter1;
    var inputTag=y.querySelectorAll("input");
    inputTag[0].setAttribute("name","options");
    inputTag[1].setAttribute("name","images");
    ele.parentElement.appendChild(y);
    var option=document.getElementById("templateCheckbox");
    var op=option.cloneNode(true);
    op.classList.toggle("d-none");
    op.classList.toggle("d-inline");
    op.removeAttribute("id");
    op.getElementsByTagName("label")[0].textContent=counter1;
    op.getElementsByTagName("input")[0].setAttribute("value",counter1-1);
    
    ele.parentElement.parentElement.getElementsByClassName("answer")[0].appendChild(op);
    console.log("successful");
    counter1++;

  }

  function toggleView(ele,type){
    var x=ele.parentElement.parentElement.querySelector(type);
    x.classList.toggle("d-none");
  }
</script>
<jsp:include page="Footer.jsp" />