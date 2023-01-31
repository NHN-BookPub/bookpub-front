let github = document.getElementById("github")
let kakao = document.getElementById("kakao")
let naver = document.getElementById("naver")
github.addEventListener("click", () => {
    $.ajax({
        type: "get",
        async: true,
        url: "/oauth/redirect-url",
        data: {"oauthName": "github"},
        success: function (result) {
            location.href = result;
        }
    })
})

kakao.addEventListener("click", () => {
    $.ajax({
        type: "get",
        async: true,
        url: "/oauth/redirect-url",
        data: {"oauthName": "kakao"},
        success: function (result) {
            location.href = result;
        }
    })
})

naver.addEventListener("click", () => {
    $.ajax({
        type: "get",
        async: true,
        url: "/oauth/redirect-url",
        data: {"oauthName": "naver"},
        success: function (result) {
            location.href = result;
        }
    })
})