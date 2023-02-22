/**
 * JavaScript function to highlight rows that are moused over in the tables
 */
window.onload = function() {
    var rows = document.querySelectorAll("tr");
    for (var i = 0; i < rows.length; i++) {
        rows[i].addEventListener("mouseover", function() {
            this.style.backgroundColor = "yellow";
        });
        rows[i].addEventListener("mouseout", function() {
            this.style.backgroundColor = "white";
        });
    }
}