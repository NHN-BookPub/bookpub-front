let currentInquiryPageNo;
let totalInquiryPages;

const pageInquiryButtonNum = 5;

let inquiryOne = document.getElementById("inquirytest1");
let inquiryTwo = document.getElementById("inquirytest2");
let inquiryThree = document.getElementById("inquirytest3");
let inquiryFour = document.getElementById("inquirytest4");
let inquiryFive = document.getElementById("inquirytest5");
let inquirySix = document.getElementById("inquirytest6");
let inquirySeven = document.getElementById("inquirytest7");
let inquiryEight = document.getElementById("inquirytest8");
let inquiryNine = document.getElementById("inquirytest9");
let inquiryTen = document.getElementById("inquirytest10");

let inquiryList = []
inquiryList.push(inquiryOne)
inquiryList.push(inquiryTwo)
inquiryList.push(inquiryThree)
inquiryList.push(inquiryFour)
inquiryList.push(inquiryFive)
inquiryList.push(inquirySix)
inquiryList.push(inquirySeven)
inquiryList.push(inquiryEight)
inquiryList.push(inquiryNine)
inquiryList.push(inquiryTen)

let pathInquiryVariable;

let inquiryPreviousBtn = document.getElementById("inquiryPreviousBtn");
let inquiryPreviousLink = document.getElementById("inquiryPreviousLink");
let inquiryNextBtn = document.getElementById("inquiryNextBtn");
let inquiryNextLink = document.getElementById("inquiryNextLink");

window.addEventListener('load', function () {

    const pathname = window.location.pathname
    pathInquiryVariable = pathname.split('/')[pathname.split('/').length - 1]

    $.ajax({
        url: "/inquiries/products/" + pathInquiryVariable,
        data: {
            page: 0,
            size: 10
        },
        type: "get",
        async: true,
        dateType: "JSON",

        success: function (responses) {
            let answerYes = document.createElement('span');
            answerYes.innerHTML = "답변 완료";
            let answerNo = document.createElement('span');
            answerNo.innerHTML = "답변 미완료";

            responses.content.forEach((response, index) => {
                if (!response.inquiryDisplayed) {
                    inquiryList[index].innerHTML = '<span style="width=80%">비밀글입니다.</span>'
                } else {
                    inquiryList[index].innerHTML = "<span style='width=80%'>" + response.inquiryTitle + response.memberNickname + response.createdAt[0] + "/" + response.createdAt[1] + "/" + response.createdAt[2] + " " + response.createdAt[3] + ":" + response.createdAt[4] + ":" + response.createdAt[5] + "</span>";
                    if (response.inquiryAnswered) {
                        inquiryList[index].append(answerYes)
                    } else {
                        inquiryList[index].append(answerNo);
                    }
                }
            })

            currentInquiryPageNo = 0;
            totalInquiryPages = responses.totalPages;

            inquiryPreviousBtn.classList.add('disabled');
            inquiryPreviousLink.onclick = null;

            if (responses.next) {
                inquiryNextBtn.classList.remove('disabled');
                inquiryNextLink.setAttribute("onClick", "inquiryNext()");
            } else {
                inquiryNextBtn.classList.add('disabled');
                inquiryNextLink.onclick = null;
            }
        }
    })
});

function inquiryPageNum(pageNo) {

}

function inquiryPrevious() {
    $.ajax({
        url: "/inquiries/products/" + pathInquiryVariable,
        data: {
            page: currentInquiryPageNo - 1,
            size: 10
        },
        type: "get",
        async: true,
        dateType: "JSON",

        success: function (responses) {
            inquiryList.forEach((inquiry) => {
                inquiry.innerHTML = "";
            })
            console.log(responses);

            // responses.content.forEach((response, index) => {
            //     inquiryList[index].innerHTML = response.memberNickname + '<br/>' +
            //         response.createdAt[0] + '/' + response.createdAt[1] + '/' + response.createdAt[2] + ' ' +
            //         response.createdAt[3] + ':' + response.createdAt[4] + ':' + response.createdAt[5] + '  ' + '<br/>' + response.reviewContent +
            //         "<div className='col-sm-2' style='text-align: right'>" +
            //         "<img src=\"" + response.imagePath +
            //         " \"style='width: 10%; height: auto'" + "onerror=\"this.onerror=null; this.src='/static/image.review/no-image.png'\">" +
            //         "</div>";
            // })

            currentInquiryPageNo -= 1;
            totalInquiryPages = responses.totalPages;

            if (responses.previous) {
                inquiryPreviousBtn.classList.remove('disabled');
                inquiryPreviousLink.setAttribute("onClick", "inquiryPrevious()");
            } else {
                inquiryPreviousBtn.classList.add('disabled');
                inquiryPreviousLink.onclick = null;
            }

            if (responses.next) {
                inquiryNextBtn.classList.remove('disabled');
                inquiryNextLink.setAttribute("onClick", "inquiryNext()");
            } else {
                inquiryNextBtn.classList.add('disabled');
                inquiryNextLink.onclick = null;
            }
        }
    })
}

function inquiryNext() {
    $.ajax({
        url: "/inquiries/products/" + pathInquiryVariable,
        data: {
            page: currentInquiryPageNo + 1,
            size: 10
        },
        type: "get",
        async: true,
        dateType: "JSON",

        success: function (responses) {
            inquiryList.forEach((inquiry) => {
                inquiry.innerHTML = "";
            })

            // responses.content.forEach((response, index) => {
            //     inquiryList[index].innerHTML = response.memberNickname + '<br/>' +
            //         response.createdAt[0] + '/' + response.createdAt[1] + '/' + response.createdAt[2] + ' ' +
            //         response.createdAt[3] + ':' + response.createdAt[4] + ':' + response.createdAt[5] + '  ' + '<br/>' + response.reviewContent +
            //         "<div className='col-sm-2' style='text-align: right'>" +
            //         "<img src=\"" + response.imagePath +
            //         " \"style='width: 10%; height: auto'" + "onerror=\"this.onerror=null; this.src='/static/image.review/no-image.png'\">" +
            //         "</div>";
            // })
            currentInquiryPageNo += 1;
            totalInquiryPages = responses.totalPages;

            if (responses.previous) {
                inquiryPreviousBtn.classList.remove('disabled');
                inquiryPreviousLink.setAttribute("onClick", "inquiryPrevious()");
            } else {
                inquiryPreviousBtn.classList.add('disabled');
                inquiryPreviousLink.onclick = null;
            }

            if (responses.next) {
                inquiryNextBtn.classList.remove('disabled');
                inquiryNextLink.setAttribute("onClick", "inquiryNext()");
            } else {
                inquiryNextBtn.classList.add('disabled');
                inquiryNextLink.onclick = null;
            }
        }
    })
}
