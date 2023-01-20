var exPwd = document.getElementById("exchangePwd");
var checkPwd = document.getElementById("exchangeCheckPwd");

console.log(exPwd.value);
console.log(checkPwd.value);

function validatePassword() {
    console.log(exPwd.value)
    console.log(checkPwd.value)
    if (exPwd.value === checkPwd.value) {
        console.log("if 안에 exPwd : {}",exPwd.value)
        console.log("if 안에 checkPwd : {}",checkPwd.value)
        document.getElementById("pwd-register")
            .disabled = false;
        checkPwd.checkValidity(true);
        checkPwd.setCustomValidity('');

    } else {
        console.log("else 안에 exPwd : {}",exPwd.value)
        console.log("else 안에 checkPwd : {}",checkPwd.value)

        checkPwd.setCustomValidity("패스워드가 일치하지 않습니다.");
        checkPwd.disabled =false;
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

const rawPwd = document.getElementById("currentPwd").value;
let elementById = document.getElementById("member-no").value;

function pwdCheck() {
    $.ajax({
        type: "post",
        async: false,
        dataType: 'json',
        data: {rawPassword: rawPwd},
        url: "/members/" + 388 + "/password-check"
        , success: function (result) {
            console.log(result);
            if (result === true) {
                console.log("result: {}", result);
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
                document.getElementById(checkPwd)
                    .value = '';
            }
        }
    })
}