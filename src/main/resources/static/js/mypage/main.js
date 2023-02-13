/* EXPANDER MENU */
// const showMenu = (toggleId, navbarId, bodyId) => {
//     const toggle = document.getElementById(toggleId),
//     navbar = document.getElementById(navbarId),
//     bodypadding = document.getElementById(bodyId)
//
//     if( toggle && navbar ) {
//         toggle.addEventListener('click', ()=>{
//             navbar.classList.toggle('expander');
//
//             bodypadding.classList.toggle('body-pd')
//         })
//     }
// }
//
// showMenu('nav-toggle', 'navbar', 'body-pd')

/* COLLAPSE MENU */
const linkCollapse = document.getElementsByClassName('collapse__link')
var i

for (i = 0; i < linkCollapse.length; i++) {
    linkCollapse[i].addEventListener('click', function () {
        const collapseMenu = this.nextElementSibling
        collapseMenu.classList.toggle('showCollapse')

        const rotate = collapseMenu.previousElementSibling
        rotate.classList.toggle('rotate')
    });
}

document.addEventListener("DOMContentLoaded", function () {
    var url = document.location.href;

    let linkList = document.querySelectorAll('.nav__link')

    for (var i = 0; i < linkList.length; i++) {
        let navLink = linkList[i].href
        if (url.toString().includes(navLink)) {
            linkList[i].classList.add('active')
            for (var j = 0; j < linkList.length; j++) {
                if (i !== j) {
                    linkList[j].classList.remove('active')
                    break;
                }
            }
        }
    }
})