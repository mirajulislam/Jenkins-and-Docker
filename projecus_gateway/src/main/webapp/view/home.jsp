<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Friendship | PROJECUS-GATEWAY</title>
        <link rel="icon" type="image/x-icon" href="./images/favicon.png" />
        <meta name="description" content="A description of your website">
        <meta name="keywords" content="keyword1, keyword2, keyword3">
        <link href="./style.css" rel="stylesheet" type="text/css">
        
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
        <script>
            $(function () {
                $("#accordion").accordion({
                    collapsible: true
                });
            });
        </script>
        <style>
            .new-ftr {
                color: greenyellow;
                font-weight: bold;
                font-style: italic;
            }
        </style>
    </head>
    <body>
        <div id="wrapper"> 
            <div id="header"> 

                <div class="top_banner">
                    <div class="div1">
                        <a href="#">
                            <img src="./images/fship_logo.png" alt="FRIENDSHIP"style="padding: 26px;width: 60%;">
                        </a>
                    </div>

                    <div class="div2" style="text-align: justify;">
                        <div class="centerDiv">
                            <div class="div3">
                                <img style="width:30%;" src="./images/Projecus.png">
                            </div>
                            <div class="div4"  style="margin-bottom: 5px; padding-right:5px ;  margin-top: -12px; font-size: small;">

                            </div>
                        </div>
                    </div>

                </div>

            </div>

            <div id="page_content">

                <div class="navigation">
                    <h2 style="margin-bottom:5px; text-align: center;">
                        CMS-PROJECUS
                    <a href="/projecus-gateway/swagger-ui.html" 
                        target="_blank"
                        style="color: #fff; float: right;margin-right: 5px;">
                        API
                    </a></h2>
                </div> 

                <div class=se_content">
                    <div id="accordion">
                        <div>Version: 1.0.1<span style="float:right;">Release Date: 20/11/2022</span></div>
                        <div>
                            <p>
                                1. Init project!!!
                                <hr>
                                2. GET >> /projecus/get-by-code/{sys_entity_code}  >> Get projecus list by entity code<br/>
                            </p>
                        </div>
                    </div>
                </div>

                <div class="clear"></div>

                <!--start footer from here-->
                <div id="footer">Copyright &copy; 2022. Developed and maintained by Application Development, Friendship. </div>

            </div>
        </div>
    </body>
</html>