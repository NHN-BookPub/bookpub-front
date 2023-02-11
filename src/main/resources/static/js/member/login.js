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

function loginSubmit() {
    let idInput = document.getElementById("bookpub-id");
    let pwdInput = document.getElementById("bookpub-pwd");

    let id = idInput.value;
    let pwd = pwdInput.value;

    if (id === "") {
        Swal.fire({
            icon: 'warning',
            title: '아이디를 입력해 주세요',
            text: '아이디를 입력해 주세요'
        })
    } else if (pwd === "") {
        Swal.fire({
            icon: 'warning',
            title: '패스워드를 입력해 주세요',
            text: '패스워드를 입력해 주세요'
        })
    } else {
        let loading = document.getElementById("loading");
        let img = document.getElementById("loading-img");
        loading.style.display = "flex";
        img.style.display = "block";
        $.ajax({
            type: "post",
            async: true,
            url: "/auth",
            data: {
                "id": id,
                "pwd": pwd,
                "social": "no"
            },
            success: function (data, textStatus, request) {
                if (request.getResponseHeader("X-LOGIN") === null) {
                    loading.style.display = "none";
                    img.style.display = "none";
                    Swal.fire({
                        icon: 'warning',
                        title: '로그인 실패',
                        text: '아이디나 패스워드가 잘못 입력되었습니다.'
                    })
                } else {
                    window.location.href = "/";
                }
            }
        })
    }
}