function inquiryInfo(inquiryNo, inquiryMemberNo, memberNo, displayed) {
    if (displayed == 'true') {
        $.ajax({
            url: "/members/inquiries/" + inquiryNo + "/modal",
            type: "get",
            async: true,
            dateType: "JSON",

            success: function (response) {
                callInquiryInfo(response);
            }
        })
    } else {
        if (memberNo < 0) {
            Swal.fire({
                icon: 'warning',
                title: '권한이 없습니다.',
                text: '작성자만 조회 가능합니다.'
            })
        } else {
            var authorities = document.getElementById("myAuthorities").value;
            console.log(authorities);

            if (!(inquiryMemberNo == memberNo) && !authorities.includes("ROLE_ADMIN")) {
                Swal.fire({
                    icon: 'warning',
                    title: '권한이 없습니다.',
                    text: '작성자만 조회 가능합니다.'
                })
            } else {
                $.ajax({
                    url: "/members/inquiries/" + inquiryNo + "/private/modal",
                    type: "get",
                    async: true,
                    dateType: "JSON",

                    success: function (response) {
                        console.log(response);
                        callInquiryInfo(response);
                    }
                })
            }
        }
    }
}


function callInquiryInfo(response) {
    let displayed = "";
    if (response.inquiryDisplayed == true) {
        displayed = "공개 문의글";
    } else {
        displayed = "비공개 문의글";
    }
    document.getElementById("inquiryQuestionDisplayed").textContent = displayed;
    document.getElementById("inquiryQuestionType").textContent = response.inquiryStateCodeName;
    document.getElementById("inquiryQuestionNickname").textContent = response.memberNickname;
    document.getElementById("inquiryQuestionCreatedAt").textContent = response.createdAt[0] + "/" + response.createdAt[1] + "/" + response.createdAt[2] + " " +
        response.createdAt[3] + ":" + response.createdAt[4] + ":" + response.createdAt[5];
    document.getElementById("inquiryQuestionTitle").textContent = response.inquiryTitle;
    document.getElementById("inquiryViewer").innerText = "";
    inquiryViewer = toastui.Editor.factory({
        el: document.querySelector('#inquiryViewer'),
        viewer: true
    });
    inquiryViewer.setMarkdown(response.inquiryContent);

    document.getElementById("answerTable").innerText = "";
    response.childInquiries.forEach(function (child, index) {
        document.getElementById("answerTable").innerHTML =
            document.getElementById("answerTable").innerHTML + 'ㄴ <table class="table table-striped table-bordered"><tr><td width="50%">답변 제목</td><td>관리자</td>' +
            '<td>답변 일자 : <span>' + child.createdAt[0] + '/' + child.createdAt[1] + '/' + child.createdAt[2] + " " + child.createdAt[3] + ':' + child.createdAt[4] + ':' + child.createdAt[5] + '</span></td></tr> <tr> <td colspan="3">' +
            '<div>' + child.inquiryTitle + '</div></td></tr><tr><td colspan="3">답변 내용</td> </tr> <tr>' +
            '<td colspan="3"><div class="viewerGroup" id="inquiryAnswerViewer' + index + '"></div>' +
            // '<p id="childContent' + index + '" style="display: none" text="' + ${answer.inquiryContent}">'
            '</td></tr></table>';
    })

    response.childInquiries.forEach(function (child, index) {
        window['inquiryAnswerViewer' + index] = toastui.Editor.factory({
            el: document.getElementById('inquiryAnswerViewer' + index),
            viewer: true
        });
        window['inquiryAnswerViewer' + index].setMarkdown(child.inquiryContent);
    });

    $("#inquiryDetail").modal().show();
}
