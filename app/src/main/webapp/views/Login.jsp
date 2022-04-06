<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Onview</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.1/css/all.css" integrity="sha384-vp86vTRFVJgpjF9jiIGPEEqYqlDwgyBgEF109VFjmqGmIY/Y4HV4d3Gp2irVfcrp" crossorigin="anonymous">
    <link rel="stylesheet" href="/styles/style.css">
  </head>
<body class="d-flex ">
    <div class="d-flex flex-fill">
        <div class="d-flex auth-bg-left-img border-end border-5 border-success  align-items-center justify-content-center w-50">
            <div class="shadow rounded bg-white border border-success border-2 px-5 py-3 w-75">
                
                <div class="h3 mt-4 text-success text-center"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-workspace" viewBox="0 0 16 16">
                    <path d="M4 16s-1 0-1-1 1-4 5-4 5 3 5 4-1 1-1 1H4Zm4-5.95a2.5 2.5 0 1 0 0-5 2.5 2.5 0 0 0 0 5Z"/>
                    <path d="M2 1a2 2 0 0 0-2 2v9.5A1.5 1.5 0 0 0 1.5 14h.653a5.373 5.373 0 0 1 1.066-2H1V3a1 1 0 0 1 1-1h12a1 1 0 0 1 1 1v9h-2.219c.554.654.89 1.373 1.066 2h.653a1.5 1.5 0 0 0 1.5-1.5V3a2 2 0 0 0-2-2H2Z"/>
                  </svg> Onview</div>
                <div class="text-center"> 
                    <small class="text-secondary">An Online interview platform for great interviews</small>             
                </div>
                <div class="h5 mt-4 text-d text-center">L o g i n</div>     
        
                <form class="mt-3" name="login" method="POST" action="/login">
                    <div class="form-group">
                        <label for="email" class="mb-2 text-secondary">Email id</label>
                        <input type="email"  name="username" placeholder="Email" class="form-control" />
                    </div>
                    <div class="form-group mt-4">
                        <label for="password" class="mb-2 text-secondary">Password</label>
                        <input type="password" name="password" placeholder="Password" class="form-control" />
                    </div>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <div class="text-center mt-4">
                        <input class="btn bg-success text-white btn-sm px-5" type="submit" value="Login" />
                    </div>
                    <div class="my-2 text-center text-danger">
                        <%= request.getAttribute("msg") %>
                    </div>
                    <div class="mt-3 text-center">
                        <div class="text-secondary mb-2"> OR</div>
                        Don't have an account ? <a href="/signup" >Sign Up</a><br>
                    </div>
                </form>
            </div>

            <!-- <div class="vr"></div> -->
            
        </div>
        <div class="d-flex flex-wrap justify-content-center text-center border-start border-success border-5 w-50 flex-fill auth-bg-right-img">
            <h2 class="text-dark  display-1"><svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor" class="bi bi-person-workspace" viewBox="0 0 16 16">
                <path d="M4 16s-1 0-1-1 1-4 5-4 5 3 5 4-1 1-1 1H4Zm4-5.95a2.5 2.5 0 1 0 0-5 2.5 2.5 0 0 0 0 5Z"/>
                <path d="M2 1a2 2 0 0 0-2 2v9.5A1.5 1.5 0 0 0 1.5 14h.653a5.373 5.373 0 0 1 1.066-2H1V3a1 1 0 0 1 1-1h12a1 1 0 0 1 1 1v9h-2.219c.554.654.89 1.373 1.066 2h.653a1.5 1.5 0 0 0 1.5-1.5V3a2 2 0 0 0-2-2H2Z"/>
              </svg> Onview</h2>
            <p class="text-dark fw-bolder align-self-end mx-2">Onview is an Online Interview Platform designed to conduct interactive sessions between Interviewer and the Interviewee. It Provides various features to support a full fledged online assessment.
                <br><span class="text-secondary">&copy; Onview Pvt. Ltd.</span>
            </p>
        </div>    
        </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>