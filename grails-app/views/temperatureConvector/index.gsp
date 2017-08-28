<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'URLResource.label', default: 'Temperature Convector')}" />
        <title><g:message code="default.conversion.label" args="[entityName]" /></title>
        <script  type="application/javascript">
            function shortUrl() {
                var url = document.getElementById('temp');
                var fromUnit = document.getElementById('fromUnit');
                var toUnit = document.getElementById('toUnit');

                $.ajax({url: "/temperatureConvector/convertTemperate?temp="+temp.value+"&fromUnit="+fromUnit.value+"&toUnit="+toUnit.value, success: function(result){
                    var rst = "";

                        rst = result.result;




                    $("#showResponse").html("<h1><b>Covert : </b>"+rst+"</h1>");

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

                    <input name="temp" id="temp" required/>
                    <g:select from="${unitsArrays}" id="fromUnit" name="fromUnit" />
                    <g:select from="${unitsArrays}" id="toUnit" name="toUnit" />
                </fieldset>
                <fieldset class="buttons">

                    <button onclick="shortUrl()" value="Convert" type="submit" class="save" name="convert">
                        Convert
                    </button>

                </fieldset>





            <div id="showResponse">

            </div>
        </div>
    </body>

</html>

