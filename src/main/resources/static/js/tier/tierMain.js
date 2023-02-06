function tierCheck() {
    let tierName = document.getElementById("tierName").value;

    $.ajax({
        type: "get",
        async: false,
        data: {"tierName": tierName},
        url: "/tier-check",
        success: function (result) {
            if (result === false) {
                alert("사용할 수 있는 등급입니다.")
                document.getElementById("tier-add-submit")
                    .disabled = false;
                document.getElementById("")
            } else {
                alert("이미 사용중인 등급입니다.")
                document.getElementById("tierName")
                    .value = '';
            }
        }
    })
}