<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>outter</title>
    <script >
        function reWrite()
        {
            var topUrl = location.href;
           // alert(topUrl);
            if(topUrl.indexOf('product') < 1)
            {
                return ;
            }
//         topUrl = topUrl.replace(/%20\/\?(product)(\d+)/, '');
            //     topUrl = topUrl.replace(/\/\?(product)(\d+)/, '');
            topUrl = topUrl.replace("product", '').replace(/\d+/,'');
            location.href = topUrl;
        }

    </script>
</head>
<body onload="reWrite()" >
</body>
</html>