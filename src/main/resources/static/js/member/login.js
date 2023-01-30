let github = document.getElementById("github")
github.addEventListener("click", () => {

    $.ajax({
        type: "get",
        async: true,
        url: "./oauth/redirectUrl",
        data: {"oauthName": "github"},
        success: function (result) {
            location.href = result;
        }
    })

})