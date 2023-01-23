function clickInfo(num) {
    $("#clickNo").val(num);
}

const templateNoCheck = $("#templateCheck");
function checkTemplateNo() {
    const templateNo = $("#templateNo").val();
    $.ajax({
        type: "get",
        async: true,
        url: "/templateCheck",
        data: {"templateNo": templateNo},
        success: function (result) {
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
    return templateNoCheck.value == "checked";
}