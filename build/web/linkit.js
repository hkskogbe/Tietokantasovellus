document.addEventListener("DOMContentLoaded", function() {
    linkit = document.getElementsByTagName("a");

    for (var a = 0; a < linkit.length; a++) {
        if (document.location.href.match(linkit[a].href)) {
            linkit[a].removeAttribute("href");
        }
    }
}, false);