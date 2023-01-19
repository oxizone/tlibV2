let crenaux = document.querySelectorAll(".creneau");
console.log(crenaux);
console.log("test");

for (var i = 0; i < crenaux.length; i++) {
    crenaux[i].addEventListener("click", function () {
        // Remove the "selected" class from all divs
        for (var j = 0; j < crenaux.length; j++) {
            crenaux[j].classList.remove("creneau-selected");
        }
        // Add the "selected" class to the clicked div
        this.classList.add("creneau-selected");
    })}