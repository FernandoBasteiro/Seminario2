<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="dto.UsuarioDTO"%>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>FAR</title>
  <link rel="shortcut icon" href="assets/img/FavIcon.png">
  <!-- Fonts -->
  <link href="assets/fontawesome/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
  
  <!-- Styles -->
  <link href="assets/css/far.min.css" rel="stylesheet">
  
</head>
<body id="page-top">
  <!-- Wrapper de la pagina -->
  <div id="wrapper">
    <!-- Inicio del sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">
      <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.jsp">
        <div class="sidebar-brand-icon">
          <img style="max-width: 50px;" src="assets/img/logo.png">
        </div>
        <div class="sidebar-brand-text mx-3">FAR</div>
      </a>
      <hr class="sidebar-divider">
      <div class="sidebar-heading">
        Metas
      </div>
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapse1" aria-expanded="true" aria-controls="collapse1">
          <i class="fas fa-fw fa-flag-checkered"></i>
          <span>Metas</span>
        </a>
        <div id="collapse1" class="collapse" aria-labelledby="headingDos" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <a class="collapse-item" onclick="loadDiv('contenedor-principal','Servlet','action=cargarMeta', 'Cargar nueva meta')">Cargar nueva meta</a>
            <a class="collapse-item" onclick="loadDiv('contenedor-principal','Servlet','action=listarMetas', 'Mis metas')">Ver mis metas</a>
          </div>
        </div>
      </li>
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
          <h5 id="titulo-pagina" class="titulo-grande mt-2">Inicio</h5>
          <ul class="navbar-nav ml-auto">
            <div class="topbar-divider titulo-grande"></div>
            <!-- Nav Item - Usuario -->
            <li class="nav-item dropdown no-arrow">
              <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <span class="mr-2 d-none d-lg-inline text-gray-600 small">
                <% 
                UsuarioDTO usr = (UsuarioDTO) session.getAttribute("loggedUsr");
                String nombre = usr.getLogin();
                out.print(nombre); %></span>
                <img class="img-profile rounded-circle" src="assets/img/falbino.png">
              </a>
              <!-- Dropdown - Usuario -->
              <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
                <a class="dropdown-item" onclick="loadDiv('contenedor-principal','Servlet','action=listarPerfil', 'Modificar perfil')">
                  <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                  Perfil
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
        <h5 id="titulo-pagina-chica" class="m-4 titulo-chico">Inicio</h5>
        <div class="container-fluid" id="contenedor-principal">
          <h1 class="h3 mb-4 text-gray-800"><% if (request.getParameter("error") == null) { out.print("Página principal...");} else if (request.getParameter("error").equals("404")) { out.print("Llegaste demasiado lejos... error 404.");}   %></h1>
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
          <h5 class="modal-title" id="exampleModalLabel">¿Estás seguro?</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">Elegí "Salir" si querés cerrar tu sesión.</div>
        <div class="modal-footer">
          <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
          <a class="btn btn-primary" href="login.html">Salir</a>
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
  
  <script src="assets/js/custom.js"></script>

</body>
</html>