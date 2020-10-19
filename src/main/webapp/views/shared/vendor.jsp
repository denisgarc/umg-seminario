<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="baseURL" value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}" />

<!-- variables globales -->
<script>
    var $BaseUrl = '${req.contextPath}/';
</script>

<!-- icono -->
<link rel="icon" type="image/vnd.microsoft.icon" href="${req.contextPath}/favicon.ico">

<!-- estilo general -->
<link href="${req.contextPath}/style/CustomeStyle.css" rel="stylesheet" type="text/css"/>

<!-- jquery -->
<script src="${req.contextPath}/scripts/jquery-3.4.1/jquery-3.4.1.min.js" type="text/javascript"></script>
<script src="${req.contextPath}/scripts/jquery.validate/jquery.validate.min.js" type="text/javascript"></script>

<!-- jquery Validator -->
<script src="${req.contextPath}/scripts/jquery.simple.validator/jquery.simple.validator.min.1.0.0.js" type="text/javascript"></script>
<script src="https://unpkg.com/@popperjs/core@2"></script>

<!-- bootstrap -->
<link href="${req.contextPath}/scripts/bootstrap-4.4.1/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<script src="${req.contextPath}/scripts/bootstrap-4.4.1/js/bootstrap.bundle.min.js" type="text/javascript"></script>

<!-- fontawesome -->
<link href="${req.contextPath}/style/fontawesome-5.14.0/css/all.min.css" rel="stylesheet" type="text/css"/>

<!-- jquery datatable -->
<link href="${req.contextPath}/scripts/DataTables/datatables.min.css" rel="stylesheet" type="text/css"/>
<link href="${req.contextPath}/scripts/DataTables/FixedHeader-3.1.6/css/fixedHeader.bootstrap4.min.css" rel="stylesheet" type="text/css"/>
<script src="${req.contextPath}/scripts/DataTables/datatables.min.js" type="text/javascript"></script>
<script src="${req.contextPath}/scripts/DataTables/FixedHeader-3.1.6/js/fixedHeader.bootstrap4.min.js" type="text/javascript"></script>

<!-- sweet alert -->
<link href="${req.contextPath}/scripts/bootstrap-sweetalert/dist/sweetalert.css" rel="stylesheet" type="text/css"/>
<script src="${req.contextPath}/scripts/bootstrap-sweetalert/dist/sweetalert.min.js" type="text/javascript"></script>

<!-- jquery UI -->
<link href="${req.contextPath}/scripts/jquery-ui/jquery-ui.min.css" rel="stylesheet" type="text/css"/>
<script src="${req.contextPath}/scripts/jquery-ui/jquery-ui.min.js" type="text/javascript"></script>

<!-- varios -->
<script src="${req.contextPath}/scripts/jquery.mask.js" type="text/javascript"></script>
<script src="${req.contextPath}/scripts/app.jquery.components.js" type="text/javascript"></script>
<script src="${req.contextPath}/scripts/app.javascript.custom.js" type="text/javascript"></script>
<script src="${req.contextPath}/scripts/jquery.block.UI/jquery.blockUI.js" type="text/javascript"></script>

<!-- wizard -->

<!-- select picker -->
<link href="${req.contextPath}/scripts/bootstrap-select-1.13.9/css/bootstrap-select.min.css" rel="stylesheet" type="text/css"/>
<script src="${req.contextPath}/scripts/bootstrap-select-1.13.9/js/bootstrap-select.min.js" type="text/javascript"></script>

<!-- jquery treegrid -->

