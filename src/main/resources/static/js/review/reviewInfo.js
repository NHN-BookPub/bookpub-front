
window.onload = function() {
    const pathname = window.location.pathname
    let pathVariable = pathname.split('/');
    memberNo.value = pathVariable[2];
}

function clickInfo(number) {
    const fiveStar = document.getElementById("rate5");
    const fourStar = document.getElementById("rate4");
    const threeStar = document.getElementById("rate3");
    const twoStar = document.getElementById("rate2");
    const oneStar = document.getElementById("rate1");

    const content = document.getElementById("reviewContents");

    const file = document.getElementById("existingFile");
    const deleteFileBtn = document.getElementById("deleteFileBtn");
    const upload = document.getElementById("fileUpload");

    const modifyNo = document.getElementById("reviewNumber");

    $.ajax({
        url: "/members/my/written-reviews/" + number,
        type: "get",
        async: true,
        dateType: "JSON",

        success: function (response) {

            switch (response.reviewStar) {
                case 1:
                    oneStar.checked = true;
                    break;
                case 2:
                    twoStar.checked = true;
                    break;
                case 3:
                    threeStar.checked = true;
                    break;
                case 4:
                    fourStar.checked = true;
                    break;
                case 5:
                    fiveStar.checked = true;
                    break;
                default:
                    threeStar.checked = true;
            }

            content.value = response.reviewContent;

            if(response.reviewImagePath != null) {
                file.src = response.reviewImagePath;
                upload.style.display = "none";
                deleteFileBtn.style.display = "block";
            }
            else {
                file.src="";
                deleteFileBtn.style.display = "none";
                upload.style.display = "block";
            }

            modifyNo.value = response.reviewNo;
        }
    })
}

function deleteFile() {
    const file = document.getElementById("existingFile");
    const modifyNo = document.getElementById("reviewNumber");
    const deleteFileBtn = document.getElementById("deleteFileBtn");
    const upload = document.getElementById("fileUpload");

    file.src = "";
    deleteFileBtn.style.display = "none";
    upload.style.display = "block";

    $.ajax({
        url: "/members/my/written-reviews/" + modifyNo.value + "/file",
        type: "put",
        async: true,

        success: function(response) {
            alert("이미지 삭제 완료");
        }
    })
}

function submitForm() {
    const submit = document.getElementById("submitForm");
    const modifyNo = document.getElementById("reviewNumber");

    const tmp = submit.action = "/members/written-reviews/" + modifyNo.value + "/modify";

    submit.method = "POST";
    submit.submit();
}