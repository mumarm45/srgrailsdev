<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'URLResource.label', default: 'URLResource')}" />
        <title><g:message code="default.conversion.label" args="[entityName]" /></title>
        <script  type="application/javascript">
            function shortUrl() {
                var url = document.getElementById('url');
                $.ajax({url: "/URLResource/shortUrl?url="+url.value, success: function(result){
                    var rst = "";
                    if (result.status) {
                        rst = (result.status === "ok") ? result.shortUrl : result.message;

                    } else {
                        rst = result;



                    }
                    $("#showResponse").html("<h1><b>Short Url: </b>"+rst+"</h1>");

                }});
            }
            function expandUrl() {
                var url = document.getElementById('url');
                $.ajax({url: "/URLResource/expandUrl?url="+url.value, success: function(result){
                    var rst = "";
                    if (result.status) {
                        rst = (result.status === "ok") ? result.longUrl : result.message;

                    } else {
                        rst = result;



                    }
                    $("#showResponse").html("<h1><b>Expand Url: </b>"+rst+"</h1>");

                }});
            }
        </script>
    </head>
    <body>
        <a href="#list-URLResource" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-URLResource" class="content scaffold-list" role="main">
            <h1><g:message code="default.conversion.label" args="[entityName]" /></h1>

                <fieldset class="form">
                    <input name="url" id="url"/>
                </fieldset>
                <fieldset class="buttons">
                    <button onclick="shortUrl()" name="shortUrl" action="shortUrl" class="save"
                             >
                        Shorten a long URL
                    </button>
                    <button onclick="expandUrl()" name="Long" action="longUrl" class="save">
                        Expand a short URL
                    </button>
                </fieldset>


            <div id="showResponse">

            </div>
        </div>
    </body>

</html>

