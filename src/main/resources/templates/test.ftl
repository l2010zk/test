<!DOCTYPE html>
<html  >
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Insert title here</title>
    <script type="text/javascript">
        var uid = '155042';
        var wid = '331463';
        function encode64(e) {
            e = escape(e);
            var t = "";
            var n, r, i = "";
            var s, o, u, a = "";
            var f = 0;
            do {
                n = e.charCodeAt(f++);
                r = e.charCodeAt(f++);
                i = e.charCodeAt(f++);
                s = n >> 2;
                o = (n & 3) << 4 | r >> 4;
                u = (r & 15) << 2 | i >> 6;
                a = i & 63;
                if (isNaN(r)) {
                    u = a = 64
                } else if (isNaN(i)) {
                    a = 64
                }
                t = t + keyStr.charAt(s) + keyStr.charAt(o) + keyStr.charAt(u) + keyStr.charAt(a);
                n = r = i = "";
                s = o = u = a = ""
            } while ( f < e . length );
            return t
        }
        function jsPopunder(e, t) {

            function d() {
                try {
                    c = Math.floor(document.cookie.split(h + "Cap=")[1].split(";")[0])
                } catch(e) {}
                return l <= c || document.cookie.indexOf(h + "=") !== -1
            }
            function v(e, t, i, s, o, u) {
                if (d()) return;
                var a = "toolbar=no,scrollbars=yes,location=yes,statusbar=yes,menubar=no,resizable=1,width=" + i.toString() + ",height=" + s.toString() + ",screenX=" + o + ",screenY=" + u;
                document.onclick = function() {
                    if (d()) return;
                    window.open("javascript:window.focus();", "_self", "");
                    r = n.window.open(e, t, a);
                    if (r) {
                        var i = new Date;
                        document.cookie = h + "=1;expires=" + (new Date(i.setTime(i.getTime() + f))).toGMTString() + ";path=/";
                        i = new Date;
                        document.cookie = h + "Cap=" + (c + 1) + ";expires=" + (new Date(i.setTime(i.getTime() + 84600 * 1e3))).toGMTString() + ";path=/";
                        m()
                    }
                }
            }
            function m() {
                try {
                    r.blur();
                    r.opener.window.focus();
                    window.self.window.blur();
                    window.focus();
                    if (p.firefox) g();
                    if (p.webkit) y()
                } catch(e) {}
            }
            function g() {
                var e = window.open("about:blank");
                e.focus();
                e.close()
            }
            function y() {
                var e = document.createElement("a");
                e.href = "about:blank";
                e.target = "PopHelper";
                document.getElementsByTagName("body")[0].appendChild(e);
                e.parentNode.removeChild(e);
                var t = document.createEvent("MouseEvents");
                t.initMouseEvent("click", true, true, window, 0, 0, 0, 0, 0, true, false, false, true, 0, null);
                e.dispatchEvent(t);
                window.open(e.href, e.target).close()
            }
            var n = top != self && typeof top.document.location.toString() === "string" ? top: self;
            var r = null;
            t = t || {};
            var i = t.name || Math.floor(Math.random() * 1e3 + 1);
            alert("name:" + i);
            var s = t.width || window.outerWidth || window.innerWidth;
            alert("width:" + s);
            var o = t.height || window.outerHeight - 100 || window.innerHeight;
            alert("height:" + o);
            var u = typeof t.left != "undefined" ? t.left.toString() : window.screenX;
            alert("left:" + u);
            var a = typeof t.top != "undefined" ? t.top.toString() : window.screenY;
            alert("top:" + a );
            var f = t.wait || 3600;

            f = f * 1e3;
            var l = t.cap || 2;
            var c = 0;
            var h = t.cookie || "__.popunder";
            alert("cookie:" + h );
            var p = function() {
                var e = navigator.userAgent.toLowerCase();
                var t = {
                    webkit: /webkit/.test(e),
                    mozilla: /mozilla/.test(e) && !/(compatible|webkit)/.test(e),
                    chrome: /chrome/.test(e),
                    msie: /msie/.test(e) && !/opera/.test(e),
                    firefox: /firefox/.test(e),
                    safari: /safari/.test(e) && !/chrome/.test(e),
                    opera: /opera/.test(e)
                };
                t.version = t.safari ? (e.match(/.+(?:ri)[\/: ]([\d.]+)/) || [])[1] : (e.match(/.+(?:ox|me|ra|ie)[\/: ]([\d.]+)/) || [])[1];
                return t
            } ();
            if (d()) {
                return
            } else {
                v(e, i, s, o, u, a)
            }
        }
        if (!uid) {
            var uid = 0
        }
        if (!wid) {
            var wid = 0
        }
        var keyStr = "ABCDEFGHIJKLMNOP" + "QRSTUVWXYZabcdef" + "ghijklmnopqrstuv" + "wxyz0123456789+/" + "=";
        jsPopunder("http://192.168.56.101/testtrac?para=" + uid + "/" + wid + "/" + encode64(document.URL), {
            name: "pop",
            width: screen.width,
            height: screen.height,
            top: 0,
            left: 0,
            cookie: "popcashpu",
            wait: 24 * 60 * 60,
            cap: 1
        })

    </script>
</head>
<body   >

</body>
</html>