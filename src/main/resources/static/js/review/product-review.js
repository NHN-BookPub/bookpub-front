let currentPageNo;
let totalPages;

const pageButtonNum = 5;

let reviewOne = document.getElementById("test1");
let reviewTwo = document.getElementById("test2");
let reviewThree = document.getElementById("test3");
let reviewFour = document.getElementById("test4");
let reviewFive = document.getElementById("test5");
let reviewSix = document.getElementById("test6");
let reviewSeven = document.getElementById("test7");
let reviewEight = document.getElementById("test8");
let reviewNine = document.getElementById("test9");
let reviewTen = document.getElementById("test10");

let reviewList = []
reviewList.push(reviewOne)
reviewList.push(reviewTwo)
reviewList.push(reviewThree)
reviewList.push(reviewFour)
reviewList.push(reviewFive)
reviewList.push(reviewSix)
reviewList.push(reviewSeven)
reviewList.push(reviewEight)
reviewList.push(reviewNine)
reviewList.push(reviewTen)

let pathVariable;

let reviewPreviousBtn = document.getElementById("reviewPreviousBtn");
let reviewPreviousLink = document.getElementById("reviewPreviousLink");
let reviewNextBtn = document.getElementById("reviewNextBtn");
let reviewNextLink = document.getElementById("reviewNextLink");

window.onload = function () {

    const pathname = window.location.pathname
    pathVariable = pathname.split('/')[pathname.split('/').length - 1]

    $.ajax({
        url: "/reviews/product/" + pathVariable,
        data: {
            page: 0,
            size: 10
        },
        type: "get",
        async: true,
        dateType: "JSON",

        success: function (responses) {
            responses.content.forEach((response, index) => {
                var percent = Number(response.reviewStar) * 20.0;
                reviewList[index].innerHTML =
                    "<div class='star-ratings'><div class='star-ratings-fill space-x-2 text-sm reviewView'" +
                    " style='width:" + percent + "%;'>" +
                    "<span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>" +
                    "</div>" +
                    "<div class='star-ratings-base space-x-2 text-sm reviewView'>" +
                    "<span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>" +
                    "</div></div>" +
                    response.memberNickname + '<br/>' +
                    response.createdAt[0] + '/' + response.createdAt[1] + '/' + response.createdAt[2] + ' ' +
                    response.createdAt[3] + ':' + response.createdAt[4] + ':' + response.createdAt[5] + '  ' + '<br/>' + response.reviewContent +
                    "<div className='col-sm-2' style='text-align: right'>" +
                    "<img src=\"" + response.imagePath +
                    " \"style='width: 10%; height: auto'" + "onerror=\"this.onerror=null; this.src='/static/image.review/no-image.png'\">" +
                    "</div>";
            })

            currentPageNo = 0;
            totalPages = responses.totalPages;

            reviewPreviousBtn.classList.add('disabled');
            reviewPreviousLink.onclick = null;

            if (responses.next) {
                reviewNextBtn.classList.remove('disabled');
                reviewNextLink.setAttribute("onClick", "reviewNext()");
            } else {
                reviewNextBtn.classList.add('disabled');
                reviewNextLink.onclick = null;
            }
        }
    })
}

function reviewPageNum(pageNo) {

}

function reviewPrevious() {
    $.ajax({
        url: "/reviews/product/" + pathVariable,
        data: {
            page: currentPageNo - 1,
            size: 10
        },
        type: "get",
        async: true,
        dateType: "JSON",

        success: function (responses) {
            reviewList.forEach((review) => {
                review.innerHTML = "";
            })
            console.log(responses);

            responses.content.forEach((response, index) => {
                var percent = Number(response.reviewStar) * 20.0;
                reviewList[index].innerHTML =
                    "<div class='star-ratings'><div class='star-ratings-fill space-x-2 text-sm reviewView'" +
                    " style='width:" + percent + "%;'>" +
                    "<span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>" +
                    "</div>" +
                    "<div class='star-ratings-base space-x-2 text-sm reviewView'>" +
                    "<span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>" +
                    "</div></div>" +
                    response.memberNickname + '<br/>' +
                    response.createdAt[0] + '/' + response.createdAt[1] + '/' + response.createdAt[2] + ' ' +
                    response.createdAt[3] + ':' + response.createdAt[4] + ':' + response.createdAt[5] + '  ' + '<br/>' + response.reviewContent +
                    "<div className='col-sm-2' style='text-align: right'>" +
                    "<img src=\"" + response.imagePath +
                    " \"style='width: 10%; height: auto'" + "onerror=\"this.onerror=null; this.src='/static/image.review/no-image.png'\">" +
                    "</div>";
            });

            currentPageNo -= 1;
            totalPages = responses.totalPages;

            if (responses.previous) {
                reviewPreviousBtn.classList.remove('disabled');
                reviewPreviousLink.setAttribute("onClick", "reviewPrevious()");
            } else {
                reviewPreviousBtn.classList.add('disabled');
                reviewPreviousLink.onclick = null;
            }

            if (responses.next) {
                reviewNextBtn.classList.remove('disabled');
                reviewNextLink.setAttribute("onClick", "reviewNext()");
            } else {
                reviewNextBtn.classList.add('disabled');
                reviewNextLink.onclick = null;
            }
        }
    })
}

function reviewNext() {
    $.ajax({
        url: "/reviews/product/" + pathVariable,
        data: {
            page: currentPageNo + 1,
            size: 10
        },
        type: "get",
        async: true,
        dateType: "JSON",

        success: function (responses) {
            reviewList.forEach((review) => {
                review.innerHTML = "";
            })

            responses.content.forEach((response, index) => {
                var percent = Number(response.reviewStar) * 20.0;
                reviewList[index].innerHTML =
                    "<div class='star-ratings'><div class='star-ratings-fill space-x-2 text-sm reviewView'" +
                    " style='width:" + percent + "%;'>" +
                    "<span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>" +
                    "</div>" +
                    "<div class='star-ratings-base space-x-2 text-sm reviewView'>" +
                    "<span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>" +
                    "</div></div>" +
                    response.memberNickname + '<br/>' +
                    response.createdAt[0] + '/' + response.createdAt[1] + '/' + response.createdAt[2] + ' ' +
                    response.createdAt[3] + ':' + response.createdAt[4] + ':' + response.createdAt[5] + '  ' + '<br/>' + response.reviewContent +
                    "<div className='col-sm-2' style='text-align: right'>" +
                    "<img src=\"" + response.imagePath +
                    " \"style='width: 10%; height: auto'" + "onerror=\"this.onerror=null; this.src='/static/image.review/no-image.png'\">" +
                    "</div>";
            })
            currentPageNo += 1;
            totalPages = responses.totalPages;

            if (responses.previous) {
                reviewPreviousBtn.classList.remove('disabled');
                reviewPreviousLink.setAttribute("onClick", "reviewPrevious()");
            } else {
                reviewPreviousBtn.classList.add('disabled');
                reviewPreviousLink.onclick = null;
            }

            if (responses.next) {
                reviewNextBtn.classList.remove('disabled');
                reviewNextLink.setAttribute("onClick", "reviewNext()");
            } else {
                reviewNextBtn.classList.add('disabled');
                reviewNextLink.onclick = null;
            }
        }
    })
}
