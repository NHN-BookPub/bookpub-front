var exPwd = document.getElementById("exchangePwd");
var checkPwd = document.getElementById("exchangeCheckPwd");

function validatePassword() {
    if (exPwd.value === checkPwd.value) {
        document.getElementById("pwd-register")
            .disabled = false;
        checkPwd.checkValidity(true);
        checkPwd.setCustomValidity('');

    } else {
        checkPwd.setCustomValidity("패스워드가 일치하지 않습니다.");
        checkPwd.disabled = false;
    }
}


function smsAuth() {
    $.ajax({
        type: "post",
        async: false,
        url: "/smsSend",
        success: function (result) {
            alert("인증번호가 전송되었습니다.");
            authMessage = result;
            $('#smsAuthSend').css("display", "none");
            $('#smsAuthConfirm').css("display", "inline-block");
            $('#authInput').css("display", "inline-block");
        }
    })
}

function smsConfirm() {
    const phone = document.getElementById('exchangePhone');
    const authInput = document.getElementById('authInput');
    if (authInput.value === authMessage) {
        alert("인증에 성공하였습니다.");
        phone.readable = true;
        authInput.disabled = true;
        $('#smsAuthConfirm').css("display", "none");
        document.getElementById("phone-auth")
            .disabled = false;
        $('#authInput').css("disabled", 'true');
    } else {
        alert("인증에 실패하였습니다.")
        authInput.value = '';
    }
}


function pwdCheck() {
    const rawPwd = document.getElementById("currentPwd").value;
    $.ajax({
        type: "post",
        async: false,
        dataType: 'json',
        data: {rawPassword: rawPwd},
        url: "/members/password-check"
        , success: function (result) {
            if (result === true) {
                alert("성공했습니다.")
                document.getElementById("exchangePwd")
                    .disabled = false;
                document.getElementById("exchangeCheckPwd")
                    .disabled = false;
                document.getElementById("currentPwd")
                    .disabled = true;
                document.getElementById("check-current-pwd")
                    .disabled = true;
            } else {
                alert("비밀번호가 맞지않습니다.")
            }
        }
    })
}

function nickCheckFunc() {
    let value = document.getElementById("nickname").value;
    $.ajax({
        type: "post",
        async: true,
        url: "/nickCheck",
        data: {"nickname": value},
        success: function (result) {
            if (result === false) {
                document.getElementById("nick-submit")
                    .disabled = false;
                document.document.getElementById("nickname")
                    .disabled = true;

                alert("이용가능한 닉네임 입니다.");
            } else {
                alert("이미 사용하는 닉네임 입니다.");
                document.getElementById("nickname")
                    .value = '';
            }
        }
    })
}

function findAddress() {
    new daum.Postcode({
        oncomplete: function (data) {
            document.getElementById("roadAddress").value = data.roadAddress;
        }
    }).open();
}