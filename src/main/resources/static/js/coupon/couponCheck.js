const idCheck = $("#idCheck");
const templateNoCheck = $("#templateNo");

function checkMemberId() {
    const id = $("#memberId").val();
    $.ajax({
        type: "post",
        async: true,
        url: "/idCheck",
        data: {"id": id},
        success: function (result) {
            console.log(result);
            if (result === true) {
                $('.no-id').css("display", "none");
                idCheck.value = "checked";
                $("#memberId").attr("readonly",true);
                if (allCheck()) {
                    $("#submitBtn").removeAttr("disabled");
                }
            } else {
                $('.no-id').css("display", "inline-block");
                idCheck.value = "unChecked";
            }
        },
    })
}

function checkTemplateNo() {
    const templateNo = $("#templateNo").val();
    $.ajax({
        type: "get",
        async: true,
        url: "/templateCheck",
        data: {"templateNo": templateNo},
        success: function (result) {
            console.log(result);
            if (result === true) {
                $('.no-templateNo').css("display", "none");
                templateNoCheck.value = "checked";
                $("#templateNo").attr("readonly",true);
                if (allCheck()) {
                    $("#submitBtn").removeAttr("disabled");
                }
            } else {
                $('.no-templateNo').css("display", "inline-block");
                templateNoCheck.value = "unChecked";}
        },
    })
}


function allCheck() {
    return (idCheck.value == "checked") && (templateNoCheck.value == "checked");
}