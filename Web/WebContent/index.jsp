<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>FAR</title>
  
  <!-- Fonts -->
  <link href="assets/fontawesome/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
  
  <!-- Styles -->
  <link href="assets/css/far.min.css" rel="stylesheet">
  
</head>
<body id="page-top">
<%
	//TODO: if not logged in: response.sendRedirect("/Web/login.jsp"); 
%>
  <!-- Wrapper de la pagina -->
  <div id="wrapper">
    <!-- Inicio del sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">
      <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.jsp">
        <div class="sidebar-brand-icon rotate-n-15">
          <i class="fas fa-laugh-wink"></i>
        </div>
        <div class="sidebar-brand-text mx-3">FAR</div>
      </a>
      <hr class="sidebar-divider my-0">
      <li class="nav-item active">
        <a class="nav-link" href="index.jsp">
          <i class="fas fa-fw fa-tachometer-alt"></i>
          <span>Principal</span></a>
      </li>
      <hr class="sidebar-divider">
      <div class="sidebar-heading">
        Metas
      </div>
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">
          <i class="fas fa-fw fa-cog"></i>
          <span>Metas</span>
        </a>
        <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <a class="collapse-item" href="">Cargar Metas</a>
            <a class="collapse-item" href="">Modificar Metas</a>
          </div>
        </div>
      </li>
      <div class="sidebar-heading">
        Experiencias
      </div>
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">
          <i class="fas fa-fw fa-cog"></i>
          <span>Experiencias</span>
        </a>
        <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <a class="collapse-item" href="buttons.html">Cargar Experiencia</a>
            <a class="collapse-item" href="cards.html">Buscar Experiencias</a>
          </div>
        </div>
      </li>
      <hr class="sidebar-divider d-none d-md-block">
      <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
      </div>
    </ul>
	<!-- Fin del sidebar -->
	
    <!-- Wrapper del Contenido -->
    <div id="content-wrapper" class="d-flex flex-column">
      <!-- Inicio del main -->
      <div id="content">
        <!-- Inicio de la barra superior -->
        <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
          <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
            <i class="fa fa-bars"></i>
          </button>
          <ul class="navbar-nav ml-auto">
            <!-- Alertas -->
            <li class="nav-item dropdown no-arrow mx-1">
              <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-bell fa-fw"></i>
                <!-- Contador de notificaciones TODO -->
                <span class="badge badge-danger badge-counter">1987</span> 
              </a>
              <!-- Dropdown - Notificaciones -->
              <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="alertsDropdown">
                <h6 class="dropdown-header">
                  Notificaciones
                </h6>
                <a class="dropdown-item d-flex align-items-center" href="#">
                  <div class="mr-3">
                    <div class="icon-circle bg-primary">
                      <i class="fas fa-file-alt text-white"></i>
                    </div>
                  </div>
                  <div>
                    <div class="small text-gray-500">32/13/2041</div>
                    <span class="font-weight-bold">Ten�s un mensaje nuevo</span>
                  </div>
                </a>
                <a class="dropdown-item d-flex align-items-center" href="#">
                  <div class="mr-3">
                    <div class="icon-circle bg-success">
                      <i class="fas fa-donate text-white"></i>
                    </div>
                  </div>
                  <div>
                    <div class="small text-gray-500">12/10/1492</div>
                    Col�n descubri� America!
                  </div>
                </a>
                <a class="dropdown-item d-flex align-items-center" href="#">
                  <div class="mr-3">
                    <div class="icon-circle bg-warning">
                      <i class="fas fa-exclamation-triangle text-white"></i>
                    </div>
                  </div>
                  <div>
                    <div class="small text-gray-500">21/05/2019</div>
                    �No deber�as estar estudiando?
                  </div>
                </a>
                <a class="dropdown-item text-center small text-gray-500" href="#">Mostrar todas</a>
              </div>
            </li>
            <!-- Fin de alertas -->
            <div class="topbar-divider d-none d-sm-block"></div>
            <!-- Nav Item - Usuario -->
            <li class="nav-item dropdown no-arrow">
              <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <span class="mr-2 d-none d-lg-inline text-gray-600 small">Fabian Albino</span>
                <img class="img-profile rounded-circle" src="assets/img/falbino.png">
              </a>
              <!-- Dropdown - Usuario -->
              <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
                <a class="dropdown-item" href="#">
                  <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                  Perfil
                </a>
                <a class="dropdown-item" href="#">
                  <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                  Configuraci�n
                </a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                  <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                  Salir
                </a>
              </div>
            </li>
			<!-- Usuario -->
          </ul>
        </nav>
        <!-- Fin de la barra superior -->
        <!-- Contenido de la pagina -->
        <div class="container-fluid">
          <h1 class="h3 mb-4 text-gray-800">P�gina vac�a</h1>
        </div>
        <!-- Fin del contenido de la pagina -->
      </div>
      <!-- Fin del Main -->
      <!-- Pie de pagina -->
      <footer class="sticky-footer bg-white">
        <div class="container my-auto">
          <div class="copyright text-center my-auto">
            <span>Copyright &copy; FAR 2019</span>
          </div>
        </div>
      </footer>
      <!-- Fin del pie de pagina -->
    </div>
    <!-- Fin del "Content-Wrapper" -->
  </div>
  <!-- Fin del "Wrapper" de la pagina -->      
  
  <!-- Scroll to Top Button-->
  <a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
  </a>
        
  <!-- Logout Modal-->
  <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">�Est�s seguro?</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">�</span>
          </button>
        </div>
        <div class="modal-body">Eleg� "Salir" si quer�s cerrar tu sesi�n.</div>
        <div class="modal-footer">
          <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
          <a class="btn btn-primary" href="usuario?action=logout">Salir</a>
        </div>
      </div>
    </div>
  </div>      

  <!-- Bootstrap core JavaScript-->
  <script src="assets/jquery/jquery.min.js"></script>
  <script src="assets/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="assets/jquery-easing/jquery.easing.min.js"></script>
  
    <!-- Custom scripts for all pages-->
  <script src="assets/js/far.min.js"></script>
  

</body>
</html>